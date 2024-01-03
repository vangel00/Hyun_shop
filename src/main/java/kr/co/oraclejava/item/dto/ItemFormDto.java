package kr.co.oraclejava.item.dto;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;

import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Lob;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import kr.co.oraclejava.item.ItemSellStatus;
import kr.co.oraclejava.item.entity.Item;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ItemFormDto {

	private Long id;	  // 상품 코드	
	
	@NotBlank(message = "상품명은 필수 입력 값 입니다.")
	private String itemNm;	// 상품 이름	
	
	@NotNull(message = "가격은 필수 입력 값 입니다.")
	private int price;      // 상품 가격	
	
	@NotNull(message = "수량은 필수 입력 값 입니다.")
	private int stockNumber; // 재고 수량	
	
	@NotBlank(message = "상품 상세 설명은 필수 입력 값 입니다.")
	private String itemDetail; // 상품 상세 설명	
	
	private ItemSellStatus itemSellStatus;//상품 판매 상태 표시	
	
	//상품 저장후에 수정 할 때 이미지 정보를 저장하는 리스트
	private List<ItemImgDto> itemImgDtoList = new ArrayList<>();
	
	/* 상품의 이미지 아이디를 저장하는 리스트
	 * 상품 등록시에는 아직 상품의 이미지를 저장하지 않았으므로 아무런 값이 없어서
	 * 수정시에 이미지를 저장합니다.
	 */
	private List<Long> itemImgIds = new ArrayList<>();
	
	private static ModelMapper modelMapper = new ModelMapper();
	
	// dto에서 entity로 복사 => dtoToentity
	public Item createItem() {
		Item item = modelMapper.map(this, Item.class);				
		return item;		
	}
	
	// entity에서 dto로 복사 => entityToDto
	public static ItemFormDto entityToDto(Item item) {
		return modelMapper.map(item, ItemFormDto.class);
	}
	
	
	
}
