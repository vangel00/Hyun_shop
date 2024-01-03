package kr.co.oraclejava.test.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

//@Data 잦은 문제발생으로 사용 안합니다.
@Getter
@Setter
@NoArgsConstructor   // default Constructor
@AllArgsConstructor  // Name, age을 받는 생성자 역할 
//@ToString , JPA에서 잦은 분제 발생으로 사용 자제
public class TestDto {

	private String name;
	private int age;
	
}
