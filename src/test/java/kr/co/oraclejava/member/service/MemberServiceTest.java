package kr.co.oraclejava.member.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.annotation.Rollback;

import jakarta.transaction.Transactional;
import kr.co.oraclejava.member.dto.MemberFormdto;
import kr.co.oraclejava.member.entity.Member;

@SpringBootTest  //DB연동시에 테스트하는 동안에는 데이터가 삽입이 되었다가 사라집니다.
@Transactional
@Rollback(value = false)
class MemberServiceTest {

	@Autowired
	MemberService memberService;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	@Test
	@DisplayName("회원 가입 테스트")
	public void saveMemberTest() {	
		Member member = createMember(); 
		Member saveMember = memberService.saveMember(member);		
		assertEquals(member.getName(), saveMember.getName()); 
		assertEquals(member.getEmail(), saveMember.getEmail()); 
		assertEquals(member.getAddress(), saveMember.getAddress()); 
		assertEquals(member.getPassword(), saveMember.getPassword()); 
		assertEquals(member.getRole(), saveMember.getRole()); 				
	}
		
	  public Member createMember() {
	  MemberFormdto memberFormdto = new MemberFormdto();	  
	  memberFormdto.setName("현대빈");
	  memberFormdto.setEmail("hyun@hyun.co.kr");
	  memberFormdto.setAddress("서울시 은평구 역촌동");
	  memberFormdto.setPassword("1234");
	  
	  return Member.createMember(memberFormdto, passwordEncoder);	  
	  }
	 
	
	@Test
	void test() {
		fail("Not yet implemented");
	}

}
