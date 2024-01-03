package kr.co.oraclejava.item.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import jakarta.persistence.EntityNotFoundException;
import kr.co.oraclejava.item.dto.ItemFormDto;
import kr.co.oraclejava.item.dto.ItemImgDto;
import kr.co.oraclejava.item.dto.ItemSearchDto;
import kr.co.oraclejava.item.entity.Item;
import kr.co.oraclejava.item.entity.ItemImg;
import kr.co.oraclejava.item.repository.ItemImgRepository;
import kr.co.oraclejava.item.repository.ItemRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor // 다른 레포지터리(kr.co.oraclejava.item.repository)를 불러 사용하기
@Transactional  // 저장중 문제발생하면 모두 취소 시킴.
public class ItemService {

	private final ItemRepository itemRepository;

    private final ItemImgService itemImgService;

    private final ItemImgRepository itemImgRepository;

    public Long saveItem(ItemFormDto itemFormDto, List<MultipartFile> itemImgFileList) throws Exception{

        //상품 등록폼으로부터 입력 받은 데이터를 이용하여 Item 객체 생성 및 저장
        Item item = itemFormDto.createItem();
        itemRepository.save(item); // 저장된 item을 나중에 받아옵니다.

        //이미지 등록
        for(int i=0;i<itemImgFileList.size();i++){
            ItemImg itemImg = new ItemImg();// 1~10개 이미지 설정
            itemImg.setItem(item);

            if(i == 0) // 첫번재 이미지는 대표 상품 이미지로 "Y"를, 나머지는 "N"으로 합니다.
                itemImg.setRepImgYn("Y");
            else
                itemImg.setRepImgYn("N");
            
            //상품 이미지 정보 저장.
            itemImgService.saveItemImg(itemImg, itemImgFileList.get(i));
        }
        //아이디 반환
        return item.getId();
    }

    /* 상품 데이터를 읽어오는 트랜잭션 읽기 전요으로 설정
     * JPA가 변경감지에 대한 체크를 하지 않아서 성능이 향상 됩니다. 
     */
    @Transactional(readOnly = true)
    public ItemFormDto getItemDetail(Long itemId){
    	// 해당 상품 이미지 조회, 상품 이미지 오름차순 가져오기
        List<ItemImg> itemImgList = itemImgRepository.findByItemIdOrderByIdAsc(itemId);
        List<ItemImgDto> itemImgDtoList = new ArrayList<>();
        
        //조회한 ItemImg 엔티티를 ItemImgDto 객체로 만들어서 리스트에 추가 합니다.
        for (ItemImg itemImg : itemImgList) {
            //ItemImgDto itemImgDto = ItemImgDto.of(itemImg);
            ItemImgDto itemImgDto = ItemImgDto.entityToDto(itemImg);
            itemImgDtoList.add(itemImgDto);// 빈 리스트 추가
        }

        /* 상품의 아이디를 통하여 상품 엔티티를 조회
         * 존재하지 않으면 EntityNotFoundException 오류 발생.
         */
        Item item = itemRepository.findById(itemId)
                .orElseThrow(EntityNotFoundException::new);
       // ItemFormDto itemFormDto = ItemFormDto.of(item);
        ItemFormDto itemFormDto = ItemFormDto.entityToDto(item);
        itemFormDto.setItemImgDtoList(itemImgDtoList);
        
        return itemFormDto;
    }
    
    // 수정시에 변경감지 기능 사용
    public Long updateItem(ItemFormDto itemFormDto, List<MultipartFile> itemImgFileList) throws Exception{
        //상품 수정 : 상품 등록 화면으로부터 전달 받은 상품 아이디를 이용하여 상품 엔티티 조회
        Item item = itemRepository.findById(itemFormDto.getId())
                .orElseThrow(EntityNotFoundException::new);
        
        //itemFormDto를 통해 엔티티 업데이트
        item.updateItem(itemFormDto);
        //상품 이미지 리스트 조회
        List<Long> itemImgIds = itemFormDto.getItemImgIds();

        //이미지 등록
        for(int i=0;i<itemImgFileList.size();i++){
        	//updateItemImg에 상품 이미지 아이디와 파일 정보 전달
            itemImgService.updateItemImg(itemImgIds.get(i),
                    itemImgFileList.get(i));
        }
        return item.getId();
    }
    
    /* 상품조회 조건과 페이지 정보를 매개변수로 받아서 상품 데이터를 조회하는 getAdminItemPage()를 추가,
     * 데이터 수정이 일어나지 않도록 최적화 @Transactional(readOnly = true) 
     */
    @Transactional(readOnly = true)
	public Page<Item> getAdminItemPage(ItemSearchDto itemSearchDto, PageRequest pageable) {
    	return itemRepository.getAdminItemPage(itemSearchDto, pageable);    	
    }
    
    
    
}
