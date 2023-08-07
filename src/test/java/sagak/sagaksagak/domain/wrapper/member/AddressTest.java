package sagak.sagaksagak.domain.wrapper.member;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.as;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.ThrowableAssert.catchThrowable;

@DisplayName("[회원 주소] - 유닛 테스트")
public class AddressTest {

    @ParameterizedTest
    @NullAndEmptySource
    @DisplayName("회원 정보 입력 - 주소 미입력시 예외 발생")
    void NullAndEmptyAddress_throwException(String address) {
        //given & when
        Throwable throwable = catchThrowable(() -> Address.of(address, "지하 396"));

        //then
        assertThat(throwable).isInstanceOf(IllegalArgumentException.class);
        assertThat(throwable).hasMessage("주소를 입력하세요.");
    }

    @ParameterizedTest
    @NullAndEmptySource
    @DisplayName("회원 정보 입력 - 상세 주소 미입력시 예외 발생")
    void NullAndEmptyAddressDetail_throwException(String addressDetail) {
        //given & when
        Throwable throwable = catchThrowable(() -> Address.of("서울특별시 강남구 강남대로", addressDetail));

        //then
        assertThat(throwable).isInstanceOf(IllegalArgumentException.class);
        assertThat(throwable).hasMessage("상세주소를 입력하세요.");
    }

//    @ParameterizedTest
//    @ValueSource(strings = {""})
//    @DisplayName("회원 정보 입력 - 올바르지 않은 형식의 주소 입력시 예외 발생")
//    void invalidFormatAddress_throwException(String address) {
//        //given & when
//        Throwable throwable = catchThrowable(() -> Address.of(address, "개발 아파트 404동 1010호"));
//
//        //then
//        assertThat(throwable).isInstanceOf(IllegalArgumentException.class);
//        assertThat(throwable).hasMessage("주소 형식이 올바르지 않습니다.");
//    }

    @ParameterizedTest
    @ValueSource(strings = {"서울특별시 강남구 강남대로", "서울특별시 강서구 곰달래로", "인천광역시 부평구 안남로"})
    @DisplayName("회원 정보 입력 - 올바른 형식의 주소 입력시 주소 객체 반환")
    void properFormatAddress_returnAddressObject(String address) {
        //given & when
        Address addressObject = Address.of(address, "개발 아파트 404동 1010호");

        //then
        assertThat(addressObject).isInstanceOf(Address.class);
    }
}
