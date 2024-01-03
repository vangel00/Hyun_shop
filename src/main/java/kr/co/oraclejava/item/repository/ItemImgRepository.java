package kr.co.oraclejava.item.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import kr.co.oraclejava.item.entity.ItemImg;

public interface ItemImgRepository extends JpaRepository<ItemImg, Long> {

	/* 매개변수로 넘겨준 상품 아이디를 가지며, 상품 이미지 아이디의
	 * 오름 차순으로 가져오는 쿼리 메소드
	 * 
	 * @Query("select i from ItemImg i where i.item = :itemId")
	 */
	 List<ItemImg> findByItemIdOrderByIdAsc(Long itemId);
}
