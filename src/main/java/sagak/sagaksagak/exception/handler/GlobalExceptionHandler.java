package sagak.sagaksagak.exception.handler;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import sagak.sagaksagak.exception.dto.ErrorResponseDto;
import sagak.sagaksagak.exception.enums.ErrorCode;
import sagak.sagaksagak.exception.exception.member.DuplicateEmailException;
import sagak.sagaksagak.exception.exception.member.DuplicateUserIdException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(DuplicateUserIdException.class)
    public ErrorResponseDto duplicateUserId(DuplicateUserIdException e) {
        return new ErrorResponseDto(ErrorCode.DUPLICATE_USERID, e.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(DuplicateEmailException.class)
    public ErrorResponseDto duplicateEmail(DuplicateEmailException e) {
        return new ErrorResponseDto(ErrorCode.DUPLICATE_EMAIL, e.getMessage());
    }
}
