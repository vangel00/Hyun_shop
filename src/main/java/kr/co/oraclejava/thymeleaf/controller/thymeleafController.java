package kr.co.oraclejava.thymeleaf.controller;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.data.geo.Point;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.co.oraclejava.item.dto.Itemdto;

@Controller
@RequestMapping(value = "/thymeleaf")
public class thymeleafController {

	@GetMapping(value="/ex1")
	public String ex1(Model model) {
		
		model.addAttribute("data", "SpringBoot~~~");
		
		return "thymeleaf/ex1";
	}
	
	@GetMapping(value="/ex2")
	public String ex2(Model model) {
		
		Point p = new Point(100, 200);
		model.addAttribute("data", p);
		
		return "thymeleaf/ex1";
	}
	
	@GetMapping(value="/ex3")
	public String ex3(Model model) {
		
		Itemdto itemdto = new Itemdto();
		itemdto.setItemNm("테스트 상품 1");
		itemdto.setItemDetail("상품 상세 설명");
		itemdto.setPrice(10000);
		itemdto.setRegTime(LocalDateTime.now());
		
		model.addAttribute("itemdto", itemdto);
		
		return "thymeleaf/ex3";
	}
	
	@GetMapping(value="/ex4")
	public String ex4(Model model) {
		
		@SuppressWarnings({ "unchecked", "rawtypes" })
		List<Itemdto> list = new ArrayList();
		
		for(int i=1; i<=10; i++) {
			
			Itemdto itemdto = new Itemdto();
			itemdto.setItemNm("테스트 상품");
			itemdto.setItemDetail("상품 상세 설명");
			itemdto.setPrice(10000);
			itemdto.setRegTime(LocalDateTime.now());	
			list.add(itemdto);
		}
		model.addAttribute("list", list);		
		return "thymeleaf/ex4";
	}
	
	@GetMapping(value="/ex5")
	public String ex5(Model model) {
		
		@SuppressWarnings({ "unchecked", "rawtypes" })
		List<Itemdto> list = new ArrayList();
		
		for(int i=1; i<=10; i++) {
			
			Itemdto itemdto = new Itemdto();
			itemdto.setItemNm("테스트 상품");
			itemdto.setItemDetail("상품 상세 설명");
			itemdto.setPrice(10000);
			itemdto.setRegTime(LocalDateTime.now());	
			list.add(itemdto);
		}
		model.addAttribute("list", list);		
		return "thymeleaf/ex5";
	}
	
	@GetMapping(value="/ex6")
	public String ex6(Model model) {
		
		@SuppressWarnings({ "unchecked", "rawtypes" })
		List<Itemdto> list = new ArrayList();
		
		for(int i=1; i<=10; i++) {
			
			Itemdto itemdto = new Itemdto();
			itemdto.setItemNm("테스트 상품");
			itemdto.setItemDetail("상품 상세 설명");
			itemdto.setPrice(10000);
			itemdto.setRegTime(LocalDateTime.now());	
			list.add(itemdto);
		}
		model.addAttribute("list", list);		
		return "thymeleaf/ex6";
	}
	
	//사이트 이동
	@GetMapping(value="/ex7")
	public String ex7(Model model) {
	
		return "thymeleaf/ex7"; // ex7.html
	}
	
	//매개변수 값 전달
		@GetMapping(value="/ex8")
		public String ex8(String param1, String param2, Model model) {
		
			model.addAttribute("param1", param1);
			model.addAttribute("param2", param2);
			
			return "thymeleaf/ex8"; // ex8.html
		}
	
	// js, css, html 적용되는 부분임.
		@GetMapping(value="/ex9")
		public void ex9() {
		
			
		}	
		
}
