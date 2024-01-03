package kr.co.oraclejava.item.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.thymeleaf.util.StringUtils;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.Wildcard;
import com.querydsl.jpa.impl.JPAQueryFactory;

import jakarta.persistence.EntityManager;
import kr.co.oraclejava.item.ItemSellStatus;
import kr.co.oraclejava.item.dto.ItemSearchDto;
import kr.co.oraclejava.item.entity.Item;
import kr.co.oraclejava.item.entity.QItem;

public class ItemRepositoryCustomImpl implements ItemRepositoryCustom {

	//동적쿼리 생성
	private JPAQueryFactory queryFactory;
	
	//queryFactory의 생성자로 EntityManager 개체를 넣어줍니다.
	public ItemRepositoryCustomImpl(EntityManager em) {
		this.queryFactory  = new JPAQueryFactory(em);
	}
	
	// null이거나 null이 아닌값을 주는...
	 private BooleanExpression searchSellStatusEq(ItemSellStatus searchSellStatus){
	        return searchSellStatus == null ? null : QItem.item.itemSellStatus.eq(searchSellStatus);
	    }
		
	 // 검색 날짜, 타입 : 하루전, 한달전... 등등
	 @SuppressWarnings("unused")
	private BooleanExpression regDtsAfter(String searchDateType){

	        LocalDateTime dateTime = LocalDateTime.now();

	        if(StringUtils.equals("all", searchDateType) || searchDateType == null){
	            return null;
	        } else if(StringUtils.equals("1d", searchDateType)){  //최근 하루동안 등록된 상품
	            dateTime = dateTime.minusDays(1);
	        } else if(StringUtils.equals("1w", searchDateType)){  //최근 일주일동안 등록된 상품
	            dateTime = dateTime.minusWeeks(1);
	        } else if(StringUtils.equals("1m", searchDateType)){  //최근 한달동안 등록된 상품
	            dateTime = dateTime.minusMonths(1);
	        } else if(StringUtils.equals("6m", searchDateType)){  //최근 6개월동안 등록된 상품
	            dateTime = dateTime.minusMonths(6);
	        }

	        return QItem.item.regTime.after(dateTime);
	    }
	 
	 // searchBy의 값에 따라서, 상품명에 검색어를 포함하고 있는 상품이나
	 // 상품 생성자의 아이디에 검색어를 포함하고 상품을 조회하도록 조건값을 반환 합니다. 
	  private BooleanExpression searchByLike(String searchBy, String searchQuery){

	        if(StringUtils.equals("itemNm", searchBy)){  // 상품명
	            return QItem.item.itemNm.like("%" + searchQuery + "%");
	        } else if(StringUtils.equals("createBy", searchBy)){   // 작성자(등록자)
	            return QItem.item.createBy.like("%" + searchQuery + "%");
	        }

	        return null;
	    }

	@Override
	public Page<Item> getAdminItemPage(ItemSearchDto itemSearchDto, Pageable pageable) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<Item> getAdminItemPage(ItemSearchDto itemSearchDto, java.awt.print.Pageable pageable) {
		// TODO Auto-generated method stub
		return null;
	}

	
	

	
	
	
	
}
