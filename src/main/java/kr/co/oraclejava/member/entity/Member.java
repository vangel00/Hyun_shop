package kr.co.oraclejava.member.entity;

import org.springframework.security.crypto.password.PasswordEncoder;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import kr.co.oraclejava.common.entity.BaseEntity;
import kr.co.oraclejava.member.constant.Role;
import kr.co.oraclejava.member.dto.MemberFormdto;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name="member")
@Getter
@Setter
@ToString
public class Member extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="member_id")
	private Long id;
	
	private String name;
	
	@Column(unique = true)
	private String email;
		
	private String password;
	
	private String address;
	
	@Enumerated(EnumType.STRING) //숫자형이 문자형으로 들어오도록 설정
	private Role role; //kr.co.oraclejava.member.constant.Role 적용
	
	//회원정보 생성, Member를 Entity선언하므로 @Entity을 넣어줍니다.
	public static Member createMember(MemberFormdto memberFormdto, PasswordEncoder passwordEncoder) {
		
		Member member = new Member();
		
		member.setName(memberFormdto.getName()); 
		member.setEmail(memberFormdto.getEmail());
		//member.setPassword(passwordEncoder.encode(memberFormdto.getPassword()));
		member.setAddress(memberFormdto.getAddress());
		member.setRole(Role.USER); 		
		
		String password = passwordEncoder.encode(memberFormdto.getPassword());
		member.setPassword(password);
		
		return member;
	}
	
	
	
	
	
	
	
}
