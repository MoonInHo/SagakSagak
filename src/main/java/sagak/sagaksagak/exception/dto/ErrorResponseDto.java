package sagak.sagaksagak.exception.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import sagak.sagaksagak.exception.enums.ErrorCode;

@Getter
@AllArgsConstructor
public class ErrorResponseDto {

    private ErrorCode errorCode;
    private String errorMessage;
}
