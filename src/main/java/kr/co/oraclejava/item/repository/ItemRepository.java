package kr.co.oraclejava.item.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.query.Param;

import kr.co.oraclejava.item.dto.ItemSearchDto;
import kr.co.oraclejava.item.entity.Item;

//  Item : class = tablename, Long = primary key가 되는 속성의 데이터 타입을 지정합니다.                                         
public interface ItemRepository extends JpaRepository<Item, Long>, QuerydslPredicateExecutor<Item>, ItemRepositoryCustom {

	//내부적으로 Query가 작성되어 실행됩니다.
	List<Item> findByItemNm(String itemNm);	
	
	//상품명 또는 상세정보중에서 하나를 찾는다. 
	List<Item> findByItemNmOrItemDetail(String itemNm, String itemDetail);
	
	//상품명과 상세정보중에서 하나를 찾는다. 
	List<Item> findByItemNmAndItemDetail(String itemNm, String itemDetail);
	
	// price가 10005보다 작은 것이면 출력하세요
	List<Item> findByPriceLessThan(Integer price);
	
	// price가 10005보다 작으면 역순(내림차순)으로 출력하세요
	List<Item> findByPriceLessThanOrderByPriceDesc(Integer price);
		
	// 테스트 상품 상세 설명을 포함하는 상품 데이터 10개를 가격이 높은 순으로 조회하여 출력하세요.
	// @Query안에 JPQL로 작성한 쿼리문을 넣어줍니다.
	// from뒤에는 entity class로 작성한 Item을 지정하고, Item으로부터 데이터를 가져오게 합니다.
	
	@Query("select i from Item i where i.itemDetail like " +
			"%:itemDetail% order by i.price desc")
	List<Item> findByItemDetail(@Param("itemDetail") String itemDetail);
	// 매개변수 @Param("itemDetail")을 이용하여 매개변수에 넘어온 JPQL에 들어갈 변수로 지정해줍니다.
	// 현재는 itemDetail변수를 %  % 사이에 넣었으므로 그 값이 들어가도록 합니다.
		
	//nativeQuery, item은 테이블명 입니다.
	@Query(value = "select * from item i where i.item_Detail like " +
			"%:itemDetail% order by i.price desc", nativeQuery = true)
	List<Item> findByItemDetailByNative(@Param("itemDetail") String itemDetail);
	
	
	Page<Item> getAdminItemPage(ItemSearchDto itemSearchDto, Pageable pageable);
	
}
