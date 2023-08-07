package sagak.sagaksagak.domain.wrapper.member;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.ThrowableAssert.catchThrowable;

@DisplayName("[회원 이름] - 유닛 테스트")
public class NameTest {

    @ParameterizedTest
    @NullAndEmptySource
    @DisplayName("회원 정보 입력 - 이름 미입력시 예외 발생")
    void NullAndEmptyName_throwException(String name) {
        //given & when
        Throwable throwable = catchThrowable(() -> Name.of(name));

        //then
        assertThat(throwable).isInstanceOf(IllegalArgumentException.class);
        assertThat(throwable).hasMessage("이름을 입력하세요.");
    }

    @Test
    @DisplayName("회원 정보 입력 - 이름에 공백 포함시 예외 발생")
    void containsWhitespaceName_throwException() {
        //given & when
        String name = "김 개발";
        Throwable throwable = catchThrowable(() -> Name.of(name));

        //then
        assertThat(throwable).isInstanceOf(IllegalArgumentException.class);
        assertThat(throwable).hasMessage("이름엔 공백을 포함할 수 없습니다.");
    }

    @Test
    @DisplayName("회원 정보 입력 - 올바르지 않은 형식의 이름 입력시 예외 발생")
    void invalidFormatName_throwException() {
        //given & when
        String name = "김수환무거북";
        Throwable throwable = catchThrowable(() -> Name.of(name));

        //then
        assertThat(throwable).isInstanceOf(IllegalArgumentException.class);
        assertThat(throwable).hasMessage("이름 형식이 올바르지 않습니다.");
    }

    @ParameterizedTest
    @ValueSource(strings = {"김개", "김개발", "김수환무", "김수환무거"})
    @DisplayName("회원 정보 입력 - 올바른 형식의 이름 입력시 이름 객체 반환")
    void properFormatName_returnNameObject(String name) {
        //given & when
        Name nameObject = Name.of(name);

        //then
        assertThat(nameObject).isInstanceOf(Name.class);
    }
}
