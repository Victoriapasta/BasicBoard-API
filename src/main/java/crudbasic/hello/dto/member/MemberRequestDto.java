package crudbasic.hello.dto.member;

import crudbasic.hello.domain.member.Member;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class MemberRequestDto {

    private Long id;
    private String username;
    private String password;

    public MemberRequestDto toDto(Member member) {
        return new MemberRequestDto(
                member.getId(),
                member.getUsername(),
                member.getPassword());
    }
}
