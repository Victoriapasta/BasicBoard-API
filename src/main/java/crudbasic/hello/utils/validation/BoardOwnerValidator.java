package crudbasic.hello.utils.validation;

import crudbasic.hello.domain.member.Member;
import crudbasic.hello.dto.board.BoardDto;
import crudbasic.hello.dto.member.MemberDto;

public class BoardOwnerValidator {

    public boolean isBoardOwner(MemberDto member, BoardDto board) {
        if (isOwner(member, board)) {
            return true;
        }
        return false;
    }

    private static boolean isOwner(MemberDto member, BoardDto board) {
        return member.getId()
                .equals(board.getMember().getId());
    }
}
