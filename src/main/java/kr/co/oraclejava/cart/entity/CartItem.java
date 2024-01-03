package kr.co.oraclejava.cart.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import kr.co.oraclejava.common.entity.BaseEntity;
import kr.co.oraclejava.item.ItemSellStatus;
import kr.co.oraclejava.item.entity.Item;
import kr.co.oraclejava.member.entity.Member;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
public class CartItem extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="cart_item_id")
	private Long id;
	
	@ManyToOne(fetch=FetchType.LAZY) // cart 1개와, 여러개 상품 매핑 설정
	@JoinColumn(name = "cart_id")
	private Cart cart;
	
	@ManyToOne(fetch=FetchType.LAZY) // 하나의 상품, 여러 장바구니에 담기는 
	@JoinColumn(name = "item_id")
	private Item item;
	
	private int count;
	
}
