package crudbasic.hello.service;

import crudbasic.hello.dto.member.MemberDto;
import crudbasic.hello.utils.exception.MemberNotFoundException;
import crudbasic.hello.repository.MemberRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class MemberService {

    private MemberRepository memberRepository;

    public MemberDto findByMemberId(Long id) {
        return MemberDto.toDto(memberRepository.findById(id).orElseThrow(MemberNotFoundException::new));
    }
}
