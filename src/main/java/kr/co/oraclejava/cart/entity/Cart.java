package kr.co.oraclejava.cart.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import kr.co.oraclejava.common.entity.BaseEntity;
import kr.co.oraclejava.item.ItemSellStatus;
import kr.co.oraclejava.member.entity.Member;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
public class Cart extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="cart_id")
	private Long id;
	
	@OneToOne(fetch=FetchType.LAZY) // cart와 member 1:1 단방향 관계매핑 설정
	@JoinColumn(name = "member_id")
	private Member member;
	
	
}
