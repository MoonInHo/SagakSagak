package sagak.sagaksagak.domain.wrapper.member;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
public class Password {

    private final String password;

    private Password(String password) {
        this.password = password;
    }

    public static Password of(String password) {

        if (password == null || password.isEmpty()) {
            throw new IllegalArgumentException("비밀번호를 입력하세요.");
        }
        if (password.contains(" ")) {
            throw new IllegalArgumentException("비밀번호엔 공백을 포함할 수 없습니다.");
        }
        // 최소 8자, 최대 25자, 대문자 하나 이상, 소문자 하나, 숫자 하나 및 특수 문자 하나 이상
        if (!password.matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[$@$!%*?&])[A-Za-z\\d$@$!%*?&]{8,25}")) {
            throw new IllegalArgumentException("비밀번호 형식이 올바르지 않습니다.");
        }
        return new Password(password);
    }

    public static Password wrapPassword(String password) {
        return new Password(password);
    }
}
