package kr.co.oraclejava.item.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.thymeleaf.util.StringUtils;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import kr.co.oraclejava.item.entity.ItemImg;
import kr.co.oraclejava.item.repository.ItemImgRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor // 다른 레포지터리(kr.co.oraclejava.item.repository)를 불러 사용하기
@Transactional  //이미지 저장중 문제 생기면 다 취소 합니다. 
public class ItemImgService {

	@Value("${itemImgLocation}")
	private String itemImgLocation;
	
	//이 부분을 @autowired하지 않아도 @RequiredArgsConstructor으로 처리가 됩니다.
	// 생성자 이용하여 메모리에 올려도 됩니다.
    private final ItemImgRepository itemImgRepository;

    private final FileService fileService;

    public void saveItemImg(ItemImg itemImg, MultipartFile itemImgFile) throws Exception{
        String oriImgName = itemImgFile.getOriginalFilename();
        String imgName = "";
        String imgUrl = "";

        //파일 업로드 : org.thymeleaf.util.StringUtils 
        /* 사용자가 상품 이미지를 등록 했다면, 저장할 경로와 파일의 이름,
         * 파일을 파일의 바이트 배열을 업로드 매개변수로 uploadFile를 호출하고
         * 그 결과를 로컬에 저장된 파일의 이름인 imgName에 저장합니다.
         */
        if(!StringUtils.isEmpty(oriImgName)){
            imgName = fileService.uploadFile(itemImgLocation, oriImgName, itemImgFile.getBytes());
            imgUrl = "/shop/item/" + imgName; //저장한 상품 이미지 불러올 경로 받아오기
        }

        //상품 이미지 정보 저장
        /* oriImgName : 업로드했던 상품 이미지 파일의 원래 이름
         * imgName : 실제 로컬에 저장된 상품 이미지 파일 이름
         * imgUrl : 업로드 결과 로컬에 저장된 상품 이미지 파일을 불러오는 경로 
         */
        itemImg.updateItemImg(oriImgName, imgName, imgUrl);
        itemImgRepository.save(itemImg);// 저장 및 등록
    }
    
    // 상품 이미지를 수정한 경우 업데이트
    public void updateItemImg(Long itemImgId, MultipartFile itemImgFile) throws Exception{
        if(!itemImgFile.isEmpty()){
            ItemImg savedItemImg = itemImgRepository.findById(itemImgId)
                    .orElseThrow(EntityNotFoundException::new);

            //기존 이미지 파일 삭제
            if(!StringUtils.isEmpty(savedItemImg.getImgName())) {
                fileService.deleteFile(itemImgLocation+"/"+
                        savedItemImg.getImgName());
            }

            String oriImgName = itemImgFile.getOriginalFilename();
            //수정된 이미지 파일 업로드
            String imgName = fileService.uploadFile(itemImgLocation, oriImgName, itemImgFile.getBytes());
            
            /* 변경된 상품 이미지 정보 설정
             * savedItemImg 엔티티는 현재 영속 상태이므로 데이터를 변경하는 것만으로도 변경 감지 기능이 동작하여
             * 트랜잭션이 끝날때 update 쿼리가 실행됩니다.
             */
            String imgUrl = "/shop/item/" + imgName;
            savedItemImg.updateItemImg(oriImgName, imgName, imgUrl);
        }
    }

    
    
    
	
}
