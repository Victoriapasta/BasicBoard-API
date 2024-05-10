package crudbasic.hello.controller;

import crudbasic.hello.dto.member.MemberRequestDto;
import crudbasic.hello.dto.member.MemberResponseDto;
import crudbasic.hello.service.MemberService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/members")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @PostMapping
    public ResponseEntity<MemberResponseDto> memberSave(@Valid @RequestBody MemberRequestDto memberRequestDto) {
        MemberResponseDto memberResponseDto = memberService.saveMember(memberRequestDto);
        return new ResponseEntity<>(memberResponseDto, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<MemberResponseDto>> getAllMembers() {
        List<MemberResponseDto> memberResponseDtoList = memberService.findAllMember();
        return new ResponseEntity<>(memberResponseDtoList, HttpStatus.OK);
    }

    @GetMapping("/{username}")
    public ResponseEntity<MemberResponseDto> getMemberByUsername(@PathVariable String username) {
        MemberResponseDto memberResponseDto = memberService.findByUsername(username);
        return new ResponseEntity<>(memberResponseDto, HttpStatus.OK);
    }

    @DeleteMapping
    public void deleteMember(Long id) {
        memberService.deleteMember(id);
    }
}
