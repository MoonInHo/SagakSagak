package sagak.sagaksagak.domain.wrapper.member;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.ThrowableAssert.catchThrowable;

@DisplayName("[회원 이메일] - 유닛 테스트")
public class EmailTest {

    @ParameterizedTest
    @NullAndEmptySource
    @DisplayName("회원 정보 입력 - 이메일 미입력시 예외 발생")
    void NullAndEmptyEmail_throwException(String email) {
        //given & when
        Throwable throwable = catchThrowable(() -> Email.of(email));

        //then
        assertThat(throwable).isInstanceOf(IllegalArgumentException.class);
        assertThat(throwable).hasMessage("이메일을 입력하세요.");
    }

    @Test
    @DisplayName("회원 정보 입력 - 이메일에 공백 포함시 예외 발생")
    void containsWhitespaceEmail_throwException() {
        //given & when
        String email = "testuser@gm ail.com";
        Throwable throwable = catchThrowable(() -> Email.of(email));

        //then
        assertThat(throwable).isInstanceOf(IllegalArgumentException.class);
        assertThat(throwable).hasMessage("이메일엔 공백을 포함할 수 없습니다.");
    }

    @ParameterizedTest
    @ValueSource(strings = {"test123", "test@gamil"})
    @DisplayName("회원 정보 입력 - 올바르지 않은 형식의 이메일 입력시 예외 발생")
    void invalidFormatEmail_throwException(String email) {
        //when & then
        Throwable throwable = catchThrowable(() -> Email.of(email));

        //then
        assertThat(throwable).isInstanceOf(IllegalArgumentException.class);
        assertThat(throwable).hasMessage("이메일 형식이 올바르지 않습니다.");
    }

    @ParameterizedTest
    @ValueSource(strings = {"test123@gmail.com", "testuser1@naver.com", "testemail@daum.net"})
    @DisplayName("회원 정보 입력 - 올바른 형식의 이메일 입력시 이메일 객체 반환")
    void properFormatEmail_returnEmailObject(String email) {
        //given & when
        Email emailObject = Email.of(email);

        //then
        assertThat(emailObject).isInstanceOf(Email.class);
    }
}
