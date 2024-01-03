package kr.co.oraclejava.item.dto;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Lob;
import kr.co.oraclejava.common.entity.BaseEntity;
import kr.co.oraclejava.item.ItemSellStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
                            // 등록자와 수정자 상속
public class Itemdto extends BaseEntity {
	
	private Long id;	  // 상품 코드
		
	private String itemNm;	// 상품 이름
		
	private int price;      // 상품 가격
		
	private int stockNumber; // 재고 수량
		
	private String itemDetail; // 상품 상세 설명
		
	private ItemSellStatus itemSellStatus;//상품 판매 상태 표시
		
	private LocalDateTime regTime; //등록 시간
	
	private LocalDateTime updateTime; // 수정 시간
	
}
