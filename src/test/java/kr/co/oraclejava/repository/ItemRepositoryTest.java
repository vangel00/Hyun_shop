package kr.co.oraclejava.repository;

import static kr.co.oraclejava.item.entity.QItem.item;

import java.time.LocalDateTime;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.TestPropertySource;
import org.thymeleaf.util.StringUtils;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.QueryFactory;
import com.querydsl.jpa.impl.JPAQueryFactory;

import jakarta.persistence.Column;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Lob;
import jakarta.persistence.PersistenceContext;
import kr.co.oraclejava.item.ItemSellStatus;
import kr.co.oraclejava.item.entity.Item;
import kr.co.oraclejava.item.entity.QItem;
import kr.co.oraclejava.item.repository.ItemRepository;

@SpringBootTest
@TestPropertySource(locations = "classpath:application_test.properties")
public class ItemRepositoryTest {

	@Autowired
	public ItemRepository itemRepository;
	
	@Autowired
	//@PersistenceContext // jpa의 영속성 켄텍스트 사용
	EntityManager em;
	
	@Test
	@DisplayName("--- 상품 저장 테스트 ---")
	public void createItemTest() {
		
		Item item = new Item();
		item.setItemNm("테스트 상품"); 
		item.setPrice(10000); 
		item.setItemDetail("테스트 상품 상세 설명"); 
		item.setItemSellStatus(ItemSellStatus.SELL);
		item.setStockNumber(100);
		item.setRegTime(LocalDateTime.now()); 
		item.setUpDateTime(LocalDateTime.now()); 
		
		Item savedItem = itemRepository.save(item);
		System.out.println(savedItem.toString());		
	}
	
	// 10개 상품 내용 저장
	public void createItemList() {
		
		for(int i=1; i <= 10;  i++) {
			Item item = new Item();
		
			item.setItemNm(i + " 테스트 상품"); 
			item.setPrice(i + 10000); 
			item.setItemDetail(i + "테스트 상품 상세 설명"); 
		    item.setItemSellStatus(ItemSellStatus.SELL);
			item.setStockNumber(100);
			item.setRegTime(LocalDateTime.now()); 
			item.setUpDateTime(LocalDateTime.now()); 
		
		Item savedItem = itemRepository.save(item);		
		}		
	}
	
	@Test
	@DisplayName("--- 상품명 조회 테스트 ---")
	public void findByItemNmTest() {
		
		this.createItemList();
		List<Item> itemList = itemRepository.findByItemNm("테스트 상품 1");
		
		for(Item item : itemList) {
			System.out.println(item.toString()); 
		}
	}
	
	//상품명 또는 상세정보 조회
		@Test
		@DisplayName("--- 상품명 조회 테스트 ---")
		public void findByItemNmOrItemDetailTest() {
			
			this.createItemList();
			List<Item> itemList = itemRepository.findByItemNmOrItemDetail("테스트 상품 1", "테스트 상품 상세 설명3"); 
			
			for(Item item : itemList) {
				System.out.println(item.toString()); 
			}
		}
	
	
	//상품명 또는 상세정보 조회
	@Test
	@DisplayName("--- 상품명 조회 테스트2 ---")
	public void findByItemNmAndItemDetailTest() {
		
		this.createItemList();
		List<Item> itemList = itemRepository.findByItemNmAndItemDetail("테스트 상품 1", "테스트 상품 상세 설명3"); 
		
		for(Item item : itemList) {
			System.out.println(item.toString()); 
		}
	}
	
	//상품명 가격 조회 테스트(LessThan)
		@Test
		@DisplayName("--- 상품명 가격 조회 테스트(LessThan) ---")
		public void findByPriceLessThanTest() {
			
			this.createItemList();
			List<Item> itemList = itemRepository.findByPriceLessThan(10005); 
			
			for(Item item : itemList) {
				System.out.println(item.toString()); 
			}
		}
		
	//상품명 가격 조회 내림차순 테스트(LessThan)
		@Test
		@DisplayName("--- 상품명 가격 조회 내림차순 테스트(LessThan) ---")
		public void findByPriceLessThanOrderByPriceDescTest() {
			
			this.createItemList();
			List<Item> itemList = itemRepository.findByPriceLessThanOrderByPriceDesc(10005); 
			
			for(Item item : itemList) {
				System.out.println(item.toString()); 
			}
		}
					
		//상품명 가격 조회 내림차순 테스트2(LessThan)
		@Test
		@DisplayName("--- 상품명 가격 조회 내림차순 테스트2(LessThan) ---")
		public void findByItemDetailTest() {
			
			this.createItemList();
			List<Item> itemList = itemRepository.findByItemDetail("테스트 상품 상세 설명"); 
			
			for(Item item : itemList) {
				System.out.println(item.toString()); 
			}
		}	
		 
		//상품명 가격 조회 내림차순 테스트2(LessThan)
		@Test
		@DisplayName("--- 상품명 가격 조회 내림차순 테스트3(LessThan) ---")
		public void findByItemDetailByNativeTest() {
			
			this.createItemList();
			List<Item> itemList = itemRepository.findByItemDetailByNative("테스트 상품 상세 설명"); 
			
			for(Item item : itemList) {
				System.out.println(item.toString()); 
			}
		}	
		
		// querydsl test desc 			
		@Test
		@DisplayName("--- querydsl test desc ---")
		public void querydslTest() {	
			createItemList();
			
			//factory작업
			JPAQueryFactory QueryFactory = new JPAQueryFactory(em);
			//QItem qItem = item;
			
			List<Item> list = QueryFactory
					.selectFrom(item)
					//.from(item)
					.where(item.itemSellStatus.eq(ItemSellStatus.SELL))
					//.where(qItem.itemDetail.like("%" + "설명 1" + "%"))
					.orderBy(item.price.desc())
					.fetch();
			
			for(Item item : list) {
				System.out.println(item);
			}					
		}
		
		//dsl
		// 5개 상품 내용 저장
		public void createItemList2() {
			
			for(int i=1; i <= 5;  i++) {
				Item item = new Item();
			
				item.setItemNm(i + " 테스트 상품"); 
				item.setPrice(i + 10000); 
				item.setItemDetail(i + "테스트 상품 상세 설명"); 
			    item.setItemSellStatus(ItemSellStatus.SELL);
				item.setStockNumber(100);
				item.setRegTime(LocalDateTime.now()); 
				item.setUpDateTime(LocalDateTime.now()); 
			
			 itemRepository.save(item);		
			}		
			
			for(int i=6; i <= 10;  i++) {
				Item item = new Item();
			
				item.setItemNm(i + " 테스트 상품"); 
				item.setPrice(i + 10000); 
				item.setItemDetail(i + "테스트 상품 상세 설명"); 
			    item.setItemSellStatus(ItemSellStatus.SOLD_OUT);
				item.setStockNumber(100);
				item.setRegTime(LocalDateTime.now()); 
				item.setUpDateTime(LocalDateTime.now()); 
			
			 itemRepository.save(item);		
			}		
			
		}
		
		// querydsl test2 			
		@Test
		@DisplayName("--- querydsl test2 ---")
		public void querydslTest2() {	
			createItemList2();
			
			String itemDetail = "테스트"; 
			int price = 10003;
			String itemSellStatus = "SELL";
			
			BooleanBuilder builder = new BooleanBuilder();
			
			builder.and(item.itemDetail.like("%" + itemDetail + "%"));
			builder.and(item.price.gt(price));
					
			//데이터베이스안의 SELL과 같은지 비교하여 같아지면, 페이지로 나누려고 합니다.
			if(StringUtils.equals(itemSellStatus, ItemSellStatus.SELL)) {
			//	builder.and(item.itemSellStatus.eq(ItemSellStatus.SELL));
			}
			
			Pageable pageable = PageRequest.of(1, 5);
			
			Page<Item> findAll = itemRepository.findAll(builder, pageable);
			
			System.out.println("전체 갯수 : " + findAll.getTotalElements());
			
			List<Item> content = findAll.getContent();
			
			for (Item item2 : content) {
				System.out.println(item2);
			} 
			 
			
		
		
		}
}
