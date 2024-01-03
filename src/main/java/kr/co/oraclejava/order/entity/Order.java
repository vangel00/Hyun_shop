package kr.co.oraclejava.order.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import kr.co.oraclejava.common.entity.BaseEntity;
import kr.co.oraclejava.member.entity.Member;
import kr.co.oraclejava.order.constant.OrderStatus;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
@Table(name = "orders")
@NoArgsConstructor
public class Order extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="order_id")
	private Long id;
	
	@OneToOne(fetch=FetchType.LAZY) // cart와 member 1:1 단방향 관계매핑 설정
	@JoinColumn(name = "member_id")
	private Member member;
	
	private LocalDateTime orderDate;
	
	@Enumerated(EnumType.STRING)
	private OrderStatus status;
	
	/* 외래키(order_id)가 order_item 테이블에 있으므로 연관 관계의 주인은 OrderItem 엔티티이므로
	 * "mappedBy" 속성으로 연관 관계의 주인(order)을 설정 합니다.
	 * 속성의 값으로 "order"를 준 이유는 OrderItem에 있는 Order에 의해 관리된다는 의미임.
	 * 즉, 연관관계의 주인 필드인 order를 mappedBy의 값으로 설정 합니다.
	 * 
	 * Order엔티티(부모)가 삭제되었을때, 해당 엔티티와 연관되어 있는 OrderItem 엔티티(자식)가 함께 삭제 되거나,
	 * Order 엔티티를 저장 할 때, Order 엔티티에 담겨있는 OrderItem 엔티티를 모두 저장 합니다.
	 */
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true) //양방향 매핑
	private List<OrderItem> orderItems = new ArrayList<>();
	
	
}
