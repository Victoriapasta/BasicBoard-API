package crudbasic.hello.validation;

import crudbasic.hello.domain.member.Member;
import crudbasic.hello.dto.board.BoardDto;
import crudbasic.hello.dto.member.MemberDto;

public class BoardOwnerValidator {

    public boolean isBoardOwner(MemberDto member, BoardDto board) {
        if (member.getId().equals(board.getMember().getId())) {
            return true;
        }
        return false;
    }
}
