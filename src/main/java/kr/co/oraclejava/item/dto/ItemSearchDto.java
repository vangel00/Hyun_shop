package kr.co.oraclejava.item.dto;

import kr.co.oraclejava.item.ItemSellStatus;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ItemSearchDto {

	//검색일자별
	private String searchDateType;
	
	//판매상태기준
	private ItemSellStatus searchSellStatus;
	
	//사람이름인가? 상품명인가?
	private String searchBy;
	
	//searchBy가 itemNm인 경우 상품명 기준 검색이고,
	//createBy일 경우에는 상품 등록자 아이디 기준 검색으로 진행 합니다.
	private String searchQuery = "";
	
	
}
