package sagak.sagaksagak.domain.wrapper.member;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.ThrowableAssert.catchThrowable;

@DisplayName("[회원 비밀번호] - 유닛 테스트")
public class PasswordTest {

    @ParameterizedTest
    @NullAndEmptySource
    @DisplayName("회원 정보 입력 - 비밀번호 미입력시 예외 발생")
    void NullAndEmptyPassword_throwException(String password) {
        //given & when
        Throwable throwable = catchThrowable(() -> Password.of(password));

        //then
        assertThat(throwable).isInstanceOf(IllegalArgumentException.class);
        assertThat(throwable).hasMessage("비밀번호를 입력하세요.");
    }

    @Test
    @DisplayName("회원 정보 입력 - 비밀번호에 공백 포함시 예외 발생")
    void containsWhiteSpacePassword_throwException() {
        //given & when
        String password = "test Password123!";
        Throwable throwable = catchThrowable(() -> Password.of(password));

        //then
        assertThat(throwable).isInstanceOf(IllegalArgumentException.class);
        assertThat(throwable).hasMessage("비밀번호엔 공백을 포함할 수 없습니다.");
    }

    @ParameterizedTest
    @ValueSource(strings = {"test", "test123", "test123!"})
    @DisplayName("회원 정보 입력 - 올바르지 않은 형식의 비밀번호 입력시 예외 발생")
    void invalidFormatPassword_throwException(String password) {
        //given & when
        Throwable throwable = catchThrowable(() -> Password.of(password));

        //then
        assertThat(throwable).isInstanceOf(IllegalArgumentException.class);
        assertThat(throwable).hasMessage("비밀번호 형식이 올바르지 않습니다.");
    }

    @ParameterizedTest
    @ValueSource(strings = {"testPassword123!", "testPass123@", "testPassWord@123"})
    @DisplayName("회원 정보 입력 - 올바른 형식의 비밀번호 입력시 비밀번호 객체 반환")
    void properFormatPassword_returnPasswordObject(String password) {
        //given & when
        Password passwordObject = Password.of(password);

        //then
        assertThat(passwordObject).isInstanceOf(Password.class);
    }
}
