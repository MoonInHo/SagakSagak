package sagak.sagaksagak.domain.wrapper.member;

import jakarta.persistence.Column;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
public class Email {

    @Column(unique = true)
    private final String email;

    private Email(String email) {
        this.email = email;
    }

    public static Email of(String email) {

        if (email == null || email.isEmpty()) {
            throw new IllegalArgumentException("이메일을 입력하세요.");
        }
        if (email.contains(" ")) {
            throw new IllegalArgumentException("이메일엔 공백을 포함할 수 없습니다.");
        }
        if (!email.matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$")) {
            throw new IllegalArgumentException("이메일 형식이 올바르지 않습니다.");
        }
        return new Email(email);
    }
}
