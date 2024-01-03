package kr.co.oraclejava.member.controller;

import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import kr.co.oraclejava.member.dto.MemberFormdto;
import kr.co.oraclejava.member.entity.Member;
import kr.co.oraclejava.member.service.MemberService;
import lombok.extern.log4j.Log4j2;

@Controller
@Log4j2
@RequestMapping("/member")
public class MemberController {

	@Autowired
	 PasswordEncoder passwordEncoder;

	@Autowired
	 MemberService memberService;

	@GetMapping("/login")
	public String login() {
		return "/member/memberLogin";
	}

	@GetMapping("/new")
	public String memberForm(Model model) {
		model.addAttribute("memberFormdto", new MemberFormdto());
		return "member/memberForm";
	}

	// Restful 방식, 특징이 get/post 요청 형식이 다르면 충돌이 생기지 않고 실행됩니다.
	@PostMapping("/new")
	public String memberForm(@Valid MemberFormdto memberFormdto, BindingResult bindingResult, Model model) {

		if (bindingResult.hasErrors()) {
			return "member/memberForm";
		}

		try {
			Member member = Member.createMember(memberFormdto, passwordEncoder);
			memberService.saveMember(member);
		} catch (IllegalStateException e) {
			model.addAttribute("errorMessage", e.getMessage());
			return "member/memberForm";
		}

		log.info(" =====> post new " + memberFormdto);

		return "redirect:/";
	}

	@GetMapping("/logout")
	public String logout(HttpServletRequest request, HttpServletResponse response) {
		
		org.springframework.security.core.Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		 
		if(authentication != null) {
			new SecurityContextLogoutHandler().logout(request, response, authentication);
		}		
		return "redirect:/";
	}
	
	
	// .failureUrl("/member/login/error")
	@GetMapping("/login/error")
	public String loginError(Model model) {
		model.addAttribute("loginErrorMsg", "전자우편이나 비번이 틀렸습니다.!!!");
		return "member/memberLogin";
	}
	
	
	
	
	
	
}
