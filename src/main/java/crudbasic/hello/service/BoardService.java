package crudbasic.hello.service;

import crudbasic.hello.domain.board.Board;
import crudbasic.hello.domain.member.Member;
import crudbasic.hello.dto.board.BoardRequestDto;
import crudbasic.hello.dto.board.BoardResponseDto;
import crudbasic.hello.dto.member.MemberResponseDto;
import crudbasic.hello.utils.exception.BoardNotFoundException;
import crudbasic.hello.utils.exception.MemberNotFoundException;
import crudbasic.hello.repository.BoardRepository;
import crudbasic.hello.repository.MemberRepository;
import crudbasic.hello.utils.validation.BoardOwnerValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;
    private final MemberRepository memberRepository;

    public BoardResponseDto findByBoardId(Long id) {
        return BoardResponseDto.toDto(boardRepository.findById(id).orElseThrow(BoardNotFoundException::new));
    }

    public List<BoardResponseDto> findAllByMemberId(Long memberId) {
        List<Board> boards = boardRepository.findAllByMemberId(memberId);
        return BoardResponseDto.toListDto(boards);
    }

    @Transactional
    public BoardResponseDto boardSave(Long id, BoardRequestDto boardRequestDto) {
        Member member = memberRepository.findById(id).orElseThrow(MemberNotFoundException::new);
        boardRequestDto.setMember(member);
        Board board = boardRepository.save(new Board(boardRequestDto.getTitle(), boardRequestDto.getContent(), boardRequestDto.getMember()));
        return BoardResponseDto.toDto(board);
    }

    @Transactional
    public BoardResponseDto boardUpdate(Long id, BoardRequestDto boardRequestDto) {
        Member member = memberRepository.findById(boardRequestDto.getMember().getId()).orElseThrow(MemberNotFoundException::new);
        Board board = boardRepository.findById(id).orElseThrow(BoardNotFoundException::new);
        if (BoardOwnerValidator.isBoardOwner(MemberResponseDto.toDto(member), BoardResponseDto.toDto(board))) {
            board.updateBoard(boardRequestDto);
        }
        return BoardResponseDto.toDto(board);
    }

    @Transactional
    public void deleteBoard(Long id) {
        Board board = boardRepository.findById(id).orElseThrow(BoardNotFoundException::new);
        boardRepository.delete(board);
    }
}
