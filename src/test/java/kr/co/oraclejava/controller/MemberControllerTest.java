package kr.co.oraclejava.controller;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.formLogin;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.bind.annotation.GetMapping;

import jakarta.transaction.Transactional;
import kr.co.oraclejava.member.dto.MemberFormdto;
import kr.co.oraclejava.member.entity.Member;
import kr.co.oraclejava.member.service.MemberService;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
@TestPropertySource(locations = "classpath:application-test.properties")
public class MemberControllerTest {

	@Autowired
	private MemberService memberService;

	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
	PasswordEncoder passwordEncoder;

	@GetMapping("/login")
	public String login() {
		return "/member/memberLogin";
	}
	
	
	//login전에 회원 등록 생성하기
	public Member createMember(String email, String password) {
		  MemberFormdto memberFormdto = new MemberFormdto();
		  
		  memberFormdto.setName(email);
		  memberFormdto.setName("홍길동");
		  memberFormdto.setAddress("서울시 은평구 구산동");
		  memberFormdto.setPassword(password);
		  
		  Member member = Member.createMember(memberFormdto, passwordEncoder);	
		  
		  return memberService.saveMember(member);	  
		  }
	
	@Test
	@DisplayName("--- login success test ---")
	public void loginSuccessTest() throws Exception {
		String email = "hyuntest@email.com";
		String password = "1234";
		
		this.createMember(email, password);
		mockMvc.perform(
				formLogin()
				.userParameter(email)
				.loginProcessingUrl("/member/login")
				.user(email).password(password))
		          // login이 성공하여 인증되면 통과 하도록 설정
				.andExpect(SecurityMockMvcResultMatchers.authenticated());				
		}
	
	@Test
	@DisplayName("--- login failuer test ---")
	public void loginFailTest() throws Exception {
		String email = "hyuntest@email.com";
		String password = "1234";
		
		this.createMember(email, password);
		mockMvc.perform(
				formLogin()
				.userParameter(email)
				.loginProcessingUrl("/member/login")
				.user(email).password(password))
		          // login이 성공하여 인증되면 통과 하도록 설정
				.andExpect(SecurityMockMvcResultMatchers.authenticated());				
		}
	
	
	
	
	
	
	
	
	
	
	
	
}
