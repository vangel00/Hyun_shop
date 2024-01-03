package kr.co.oraclejava.member.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import kr.co.oraclejava.member.entity.Member;

public interface MemberRepository extends JpaRepository<Member, Long> {

	//회원가입시에 중복 회원인지 검사합니다.
	Member findByEmail(String email);
}
