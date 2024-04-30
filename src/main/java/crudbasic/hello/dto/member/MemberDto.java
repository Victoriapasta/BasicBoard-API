package crudbasic.hello.dto.member;

import crudbasic.hello.domain.member.Member;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class MemberDto {

    private String username;
    private String password;

    public static MemberDto toDto(Member member) {
        return new MemberDto(member.getUsername(), member.getPassword());
    }
}
