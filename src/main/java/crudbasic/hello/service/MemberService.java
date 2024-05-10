package crudbasic.hello.service;

import crudbasic.hello.domain.member.Member;
import crudbasic.hello.dto.member.MemberRequestDto;
import crudbasic.hello.dto.member.MemberResponseDto;
import crudbasic.hello.utils.exception.MemberNotFoundException;
import crudbasic.hello.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    @Transactional
    public MemberResponseDto saveMember(MemberRequestDto memberRequestDto) {
        return MemberResponseDto.toDto(memberRepository.save(new Member(memberRequestDto.getUsername(), memberRequestDto.getPassword())));
    }

    public List<MemberResponseDto> findAllMember() {
        return MemberResponseDto.toListDto(memberRepository.findAll());
    }

    public MemberResponseDto findByUsername(String username) {
        return MemberResponseDto.toDto(memberRepository.findByUsername(username).orElseThrow(MemberNotFoundException::new));
    }

    @Transactional
    public void deleteMember(Long id) {
        Member member = memberRepository.findById(id).orElseThrow(MemberNotFoundException::new);
        memberRepository.delete(member);
    }
}
