package kr.co.oraclejava.item.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import kr.co.oraclejava.item.dto.ItemSearchDto;
import kr.co.oraclejava.item.entity.Item;

public interface ItemRepositoryCustom {

	/* 상품 조회 조건을 담고 있는 itemSearchDto 객체와 페이징 정보를 담고 있는 pageable 객체를
       파라메터로 받는 getAdminItemPage 메소드를 정의하고, 반환 데이터로 Page(item)객체를 반환 합니다.
	 */
	Page<Item> getAdminItemPage(ItemSearchDto itemSearchDto, Pageable pageable);

	Page<Item> getAdminItemPage(ItemSearchDto itemSearchDto, java.awt.print.Pageable pageable);
	
	
}
