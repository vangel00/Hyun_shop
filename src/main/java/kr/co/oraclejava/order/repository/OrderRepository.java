package kr.co.oraclejava.order.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import kr.co.oraclejava.order.entity.Order;

public interface OrderRepository extends JpaRepository<Order, Long>{

}
