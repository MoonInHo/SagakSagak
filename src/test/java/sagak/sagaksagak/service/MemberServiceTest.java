package sagak.sagaksagak.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import sagak.sagaksagak.domain.entity.Member;
import sagak.sagaksagak.domain.repository.MemberQueryRepository;
import sagak.sagaksagak.domain.repository.MemberRepository;
import sagak.sagaksagak.dto.member.MemberDto;
import sagak.sagaksagak.exception.exception.member.DuplicateEmailException;
import sagak.sagaksagak.exception.exception.member.DuplicateUserIdException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.ThrowableAssert.catchThrowable;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@DisplayName("[회원 서비스] - 유닛 테스트")
public class MemberServiceTest {

    private final MemberRepository memberRepository = mock(MemberRepository.class);
    private final MemberQueryRepository memberQueryRepository = mock(MemberQueryRepository.class);
    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    private final MemberService memberService = new MemberService(memberRepository, memberQueryRepository, passwordEncoder);

    @Test
    @DisplayName("회원 가입 - 이미 존재하는 아이디로 중복체크 시도시 예외 발생")
    void existUserId_duplicateCheck_throwException() {
        //given
        String userId = "test123";
        given(memberQueryRepository.isUserIdExist(any())).willReturn(true);

        //when
        Throwable throwable = catchThrowable(() -> memberService.confirmUserIdDuplication(userId));

        //then
        assertThat(throwable).isInstanceOf(DuplicateUserIdException.class);
    }

    @Test
    @DisplayName("회원 가입 - 이미 존재하는 이메일로 중복체크 시도시 예외 발생")
    void existEmail_duplicateCheck_throwException() {
        //given
        String email = "test123@gmail.com";
        given(memberQueryRepository.isEmailExist(any())).willReturn(true);

        //when
        Throwable throwable = catchThrowable(() -> memberService.confirmEmailDuplication(email));

        //then
        assertThat(throwable).isInstanceOf(DuplicateEmailException.class);
    }

    @Test
    @DisplayName("회원 가입 - 올바른 형식의 비밀번호로 암호화 시도시 암호화된 비밀번호 반환")
    void properFormatPassword_encryption_returnEncryptedPassword() {
        //given
        String password = "testPassword123!";

        //when
        String encodedPassword = passwordEncoder.encode(password);

        //then
        assertThat(password).isNotEqualTo(encodedPassword);
        assertThat(passwordEncoder.matches(password, encodedPassword)).isTrue();
    }

    @Test
    @DisplayName("회원 가입 - 올바른 사용자 정보로 회원가입 시도시 회원 생성")
    void properUserInfo_signUp_createMember() {

        //given
        MemberDto memberDto = MemberDto.builder()
                .userId("test123")
                .password("testPassword123!")
                .name("김개발")
                .address("서울특별시 강남구 강남대로")
                .addressDetail("지하 396")
                .email("test123@gmail.com")
                .build();

        Member member = memberDto.toEntity();

        given(memberRepository.save(any())).willReturn(member);

        //when
        Member createdMember = memberRepository.save(member);

        //then
        assertThat(createdMember).isNotNull();
    }
}
