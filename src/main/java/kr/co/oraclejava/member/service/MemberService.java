package kr.co.oraclejava.member.service;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import kr.co.oraclejava.member.entity.Member;
import kr.co.oraclejava.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@Transactional //입출력 준비용 
@Log4j2
@RequiredArgsConstructor
public class MemberService implements UserDetailsService {

	private final MemberRepository memberRepository;
	
	// 회원 저장
	public Member saveMember(Member member) {
		ValdateDuplicate(member);
		return memberRepository.save(member); 
	}	
	
	// 중복 회원 체크
	private void ValdateDuplicate(Member member) {
		Member findMember = memberRepository.findByEmail(member.getEmail());
		
		if(findMember != null) {
			throw new IllegalStateException("등록된 사용자 입니다.!!!");
		}		
	}

	@Override  // Username 대신에 email로 체크합니다.
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		
		Member member = memberRepository.findByEmail(email);
		
		if(member == null) {
			throw new UsernameNotFoundException("해당 사용자가 없습니다." + email);
		}
		
		log.info("=================> loadUserByUsername : " + member); 
		
		return User.builder()
				.username(member.getEmail())
				.password(member.getPassword())
				.roles(member.getRole().toString())
				.build();
	}
	
	//
	
	
	
	
	
	
}
