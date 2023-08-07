package sagak.sagaksagak.domain.wrapper.member;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.ThrowableAssert.catchThrowable;

@DisplayName("[회원 아이디] - 유닛 테스트")
public class UserIdTest {

    @ParameterizedTest
    @NullAndEmptySource
    @DisplayName("회원 정보 입력 - 아이디 미입력시 예외 발생")
    void NullAndEmptyUserId_throwException(String userId) {
        //given & when
        Throwable throwable = catchThrowable(() -> UserId.of(userId));

        //then
        assertThat(throwable).isInstanceOf(IllegalArgumentException.class);
        assertThat(throwable).hasMessage("아이디를 입력하세요.");
    }

    @Test
    @DisplayName("회원 정보 입력 - 아이디에 공백 포함시 예외 발생")
    void containsWhitespaceUserId_throwException() {
        //given & when
        String userId = "test 123";
        Throwable throwable = catchThrowable(() -> UserId.of(userId));

        //then
        assertThat(throwable).isInstanceOf(IllegalArgumentException.class);
        assertThat(throwable).hasMessage("아이디엔 공백을 포함할 수 없습니다.");
    }

    @ParameterizedTest
    @ValueSource(strings = {"testUser123", "123", "123abc"})
    @DisplayName("회원 정보 입력 - 올바르지 않은 형식의 아이디 입력시 예외 발생")
    void invalidFormatUserId_throwException(String userId) {
        //given & when
        Throwable throwable = catchThrowable(() -> UserId.of(userId));

        //then
        assertThat(throwable).isInstanceOf(IllegalArgumentException.class);
        assertThat(throwable).hasMessage("아이디 형식이 올바르지 않습니다.");
    }

    @ParameterizedTest
    @ValueSource(strings = {"test1", "testuser1234", "test1234", "testuser1234"})
    @DisplayName("회원 정보 입력 - 올바른 형식의 아이디 입력시 아이디 객체 반환")
    void properFormatUserId_returnUserIdObject(String userId) {
        //given & when
        UserId userIdObject = UserId.of(userId);

        //then
        assertThat(userIdObject).isInstanceOf(UserId.class);
    }
}
