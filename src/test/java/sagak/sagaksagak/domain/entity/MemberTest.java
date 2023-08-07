package sagak.sagaksagak.domain.entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import sagak.sagaksagak.dto.member.MemberDto;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("[회원 도메인] - 유닛 테스트")
class MemberTest {

    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    private MemberDto memberDto;

    @BeforeEach
    public void settings() {
        memberDto = MemberDto.builder()
                .userId("test123")
                .password("testPassword123!")
                .name("김개발")
                .address("서울특별시 강남구 강남대로")
                .addressDetail("지하 396")
                .email("test123@gmail.com")
                .build();
    }

    @Test
    @DisplayName("회원 객체 - 올바른 형식의 유저 정보 입력시 회원 객체 반환")
    void properFormatUserInfo_returnMemberObject() {
        //given & when
        Member member = memberDto.toEntity();

        //then
        assertThat(member).isInstanceOf(Member.class);
    }

    @Test
    @DisplayName("회원 객체 - 암호화된 비밀번호를 회원 객체에 저장")
    void encryptedPassword_setPassword_setMemberObject() {
        //given
        Member member = memberDto.toEntity();
        String encodedPassword = passwordEncoder.encode(memberDto.getPassword());

        //when
        member.passwordEncrypt(encodedPassword);

        //then
        assertThat(encodedPassword).isNotEqualTo(memberDto.getPassword());
    }
}