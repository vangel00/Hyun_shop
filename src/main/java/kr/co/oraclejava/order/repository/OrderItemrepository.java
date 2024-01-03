package kr.co.oraclejava.order.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import kr.co.oraclejava.order.entity.OrderItem;

public interface OrderItemrepository extends JpaRepository<OrderItem, Long> {

}
