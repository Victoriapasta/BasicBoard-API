package crudbasic.hello.service;

import crudbasic.hello.domain.board.Board;
import crudbasic.hello.domain.member.Member;
import crudbasic.hello.dto.board.BoardRequestDto;
import crudbasic.hello.dto.board.BoardResponseDto;
import crudbasic.hello.utils.exception.BoardNotFoundException;
import crudbasic.hello.utils.exception.MemberNotFoundException;
import crudbasic.hello.repository.BoardRepository;
import crudbasic.hello.repository.MemberRepository;
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

    public List<BoardResponseDto> findByUsername(String username) {
        List<Board> boards = boardRepository.findByUsername(username);
        return BoardResponseDto.toListDto(boards);
    }

    @Transactional
    public BoardResponseDto boardSave(BoardRequestDto boardRequestDto, String username) { //TODO : 여기 수정해야함
        Member member = memberRepository.findByUsername(username).orElseThrow(MemberNotFoundException::new);
        //TODO : 멤버 받은거 이용해서 보드 저장하기
        Board board = boardRepository.save(new Board(boardRequestDto.getTitle(), boardRequestDto.getContent(), boardRequestDto.getMember()));
        return BoardResponseDto.toDto(board);
    }

    @Transactional
    public BoardResponseDto boardUpdate(BoardRequestDto boardRequestDto, Long id) { //TODO : 여기 수정해야함 시발
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
