package kr.co.oraclejava.cart.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import kr.co.oraclejava.cart.entity.Cart;

public interface CartRepository extends JpaRepository<Cart, Long>{

	
}
