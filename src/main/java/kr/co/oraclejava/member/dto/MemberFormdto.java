package kr.co.oraclejava.member.dto;

import org.hibernate.validator.constraints.Length;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class MemberFormdto {

	@NotBlank(message = "이름은 필수 항목 입니다.")
	private String name;
	
	@NotEmpty(message = "이름은 필수 항목 입니다.")
	@Email(message = "이메일 형식으로 입력해 주세요.")
	private String email;
	
	@NotNull(message = "비밀번호는 필수 입력 입니다.")
	@Length(min=4, max=15, message = "비번은 4자이상, 15자 이하로 입력 바랍니다.")
	private String password;
	
	@NotBlank(message = "주소는 필수 항목 입니다.")
	private String address;
	
}
