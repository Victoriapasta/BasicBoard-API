package crudbasic.hello.utils.validation;

import crudbasic.hello.dto.board.BoardResponseDto;
import crudbasic.hello.dto.member.MemberResponseDto;

public class BoardOwnerValidator {

    public static boolean isBoardOwner(MemberResponseDto member, BoardResponseDto board) {
        if (isOwner(member, board)) {
            return true;
        }
        return false;
    }

    private static boolean isOwner(MemberResponseDto member, BoardResponseDto board) {
        return member.getId()
                .equals(board.getMember().getId());
    }
}
