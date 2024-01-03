package kr.co.oraclejava.test.controller;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import kr.co.oraclejava.test.dto.TestDto;

@RestController
public class TestController {

	/*
	 * @RequestMapping(value = "/", method = RequestMethod.GET)
	 * //@GetMapping(value="/") public String hello() { return
	 * "현대빈 hello world Hyun1234~~~"; }
	 */
	
	@GetMapping("/test")
	public TestDto test() {
		TestDto dto = new TestDto();
		dto.setName("홍길동");
		dto.setAge(45);
		
		return dto;
	}
}
