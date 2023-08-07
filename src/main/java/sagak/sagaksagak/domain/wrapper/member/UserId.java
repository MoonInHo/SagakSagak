package sagak.sagaksagak.domain.wrapper.member;

import jakarta.persistence.Column;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
public class UserId {

    @Column(unique = true)
    private final String userId;

    private UserId(String userId) {
        this.userId = userId;
    }

    public static UserId of(String userId) {

        if (userId == null || userId.isEmpty()) {
            throw new IllegalArgumentException("아이디를 입력하세요.");
        }
        if (userId.contains(" ")) {
            throw new IllegalArgumentException("아이디엔 공백을 포함할 수 없습니다.");
        }
        // 4~20자리 소문자, 숫자, 첫글자는 숫자 사용불가
        if (!userId.matches("^[a-z]{1}[a-z0-9]{3,19}$")) {
            throw new IllegalArgumentException("아이디 형식이 올바르지 않습니다.");
        }
        return new UserId(userId);
    }
}
