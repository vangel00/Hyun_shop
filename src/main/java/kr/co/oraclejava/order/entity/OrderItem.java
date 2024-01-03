package kr.co.oraclejava.order.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import kr.co.oraclejava.cart.entity.Cart;
import kr.co.oraclejava.common.entity.BaseEntity;
import kr.co.oraclejava.item.entity.Item;
import kr.co.oraclejava.member.entity.Member;
import kr.co.oraclejava.order.constant.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
//@NoArgsConstructor
//@AllArgsConstructor
@Builder
public class OrderItem extends BaseEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="order_item_id")
	private Long id;
	
	@ManyToOne(fetch=FetchType.LAZY) // cart 1개와, 여러개 상품 매핑 설정
	@JoinColumn(name = "order_id")
	private Order order;
	
	// 하나의 상품, 여러 장바구니에 담기는, 중간 테이블 이용 
	@ManyToOne(fetch=FetchType.LAZY) 
	@JoinColumn(name = "item_id")
	private Item item;
	
	private int orderPrice;
	
	private int count;
	
}
