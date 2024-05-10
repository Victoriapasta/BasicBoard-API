package crudbasic.hello.dto.member;

import crudbasic.hello.domain.member.Member;
import crudbasic.hello.dto.comment.CommentResponseDto;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@AllArgsConstructor
public class MemberResponseDto {

    private Long id;
    private String username;
    private String password;

    public static MemberResponseDto toDto(Member member) {
        return new MemberResponseDto(
                member.getId(),
                member.getUsername(),
                member.getPassword());
    }

    public static List<MemberResponseDto> toListDto(List<Member> members) {
        return members.stream()
                .map(member -> MemberResponseDto.toDto(member))
                .collect(Collectors.toList());
    }
}
