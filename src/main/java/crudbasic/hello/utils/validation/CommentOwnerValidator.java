package crudbasic.hello.utils.validation;

import crudbasic.hello.dto.comment.CommentResponseDto;
import crudbasic.hello.dto.member.MemberResponseDto;

public class CommentOwnerValidator {

    public static boolean isCommentOwner(MemberResponseDto member, CommentResponseDto comment) {
        if (isOwner(member, comment)) {
            return true;
        }
        return false;
    }

    private static boolean isOwner(MemberResponseDto member, CommentResponseDto comment) {
        return member.getId()
                .equals(comment.getMember().getId());
    }
}
