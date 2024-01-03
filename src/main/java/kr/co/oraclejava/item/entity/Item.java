package kr.co.oraclejava.item.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;
import kr.co.oraclejava.common.entity.BaseEntity;
import kr.co.oraclejava.item.ItemSellStatus;
import kr.co.oraclejava.item.dto.ItemFormDto;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "item")
@Getter
@Setter
@ToString
public class Item extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "item_id")
	private Long id;	  // 상품 코드
	
	@Column(nullable = false, length = 50)
	private String itemNm;	// 상품 이름
	
	@Column(nullable = false)
	private int price;      // 상품 가격
	
	@Column(nullable = false, name = "number")
	private int stockNumber; // 재고 수량
	
	@Lob
	@Column(nullable = false)
	private String itemDetail; // 상품 상세 설명
	
	@Enumerated(EnumType.STRING)
	private ItemSellStatus itemSellStatus;//상품 판매 상태 표시	
	
	public void updateItem(ItemFormDto itemFormDto){
	       this.itemNm = itemFormDto.getItemNm();
	       this.price = itemFormDto.getPrice();
	       this.stockNumber = itemFormDto.getStockNumber();
	       this.itemDetail = itemFormDto.getItemDetail();
	       this.itemSellStatus = itemFormDto.getItemSellStatus();
	  }
	
}
