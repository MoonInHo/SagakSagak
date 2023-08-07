package sagak.sagaksagak.dto.member;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import sagak.sagaksagak.domain.entity.Member;
import sagak.sagaksagak.domain.wrapper.member.*;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MemberDto {

    private String userId;
    private String password;
    private String name;
    private String address;
    private String addressDetail;
    private String email;

    public Member toEntity() {
        return Member.createMember(
                UserId.of(userId),
                Password.of(password),
                Name.of(name),
                Address.of(address, addressDetail),
                Email.of(email)
        );
    }
}
