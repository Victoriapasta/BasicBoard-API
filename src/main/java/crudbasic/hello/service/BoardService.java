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

    public List<BoardResponseDto> findAllByUsername(String username) {
        Member member = memberRepository.findByUsername(username).orElseThrow(MemberNotFoundException::new);
        List<Board> boards = boardRepository.findAllByMemberId(member.getId());
        return BoardResponseDto.toListDto(boards);
    }

    public List<BoardResponseDto> findAllBoards() {
        return BoardResponseDto.toListDto(boardRepository.findAll());
    }

    @Transactional
    public BoardResponseDto boardSave(BoardRequestDto boardRequestDto) {
        Board board = boardRepository.save(new Board(boardRequestDto.getTitle(), boardRequestDto.getContent()));
        return BoardResponseDto.toDto(board);
    }

    @Transactional
    public BoardResponseDto boardUpdate(Long id, BoardRequestDto boardRequestDto) {
        Board board = boardRepository.findById(id).orElseThrow(BoardNotFoundException::new);
        board.updateBoard(boardRequestDto);
        return BoardResponseDto.toDto(board);
    }

    @Transactional
    public void deleteBoard(Long id) {
        Board board = boardRepository.findById(id).orElseThrow(BoardNotFoundException::new);
        boardRepository.delete(board);
    }
}
