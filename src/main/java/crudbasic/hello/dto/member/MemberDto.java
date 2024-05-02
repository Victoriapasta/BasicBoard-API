package crudbasic.hello.dto.member;

import crudbasic.hello.domain.member.Member;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class MemberDto {

    private Long id;
    private String username;
    private String password;

    public static MemberDto toDto(Member member) {
        return new MemberDto(member.getId(), member.getUsername(), member.getPassword());
    }
}
