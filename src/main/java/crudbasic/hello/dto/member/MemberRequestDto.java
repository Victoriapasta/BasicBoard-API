package crudbasic.hello.dto.member;

import crudbasic.hello.domain.member.Member;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class MemberRequestDto {

    private Long id;
    @NotBlank(message = "id를 입력해주세요.")
    private String username;
    @NotBlank(message = "비밀번호를 입력해주세요.")
    private String password;

    public MemberRequestDto toDto(Member member) {
        return new MemberRequestDto(
                member.getId(),
                member.getUsername(),
                member.getPassword());
    }
}
