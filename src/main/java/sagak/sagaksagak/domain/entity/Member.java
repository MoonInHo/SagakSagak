package sagak.sagaksagak.domain.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import sagak.sagaksagak.domain.wrapper.member.*;

import static sagak.sagaksagak.domain.wrapper.member.Password.wrapPassword;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;

    @Embedded
    private UserId userId;

    @Embedded
    private Password password;

    @Embedded
    private Name name;

    @Embedded
    private Address address;

    @Embedded
    private Email email;

    private Member(UserId userId, Password password, Name name, Address address, Email email) {
        this.userId = userId;
        this.password = password;
        this.name = name;
        this.address = address;
        this.email = email;
    }

    public static Member createMember(UserId userId, Password password, Name name, Address address, Email email) {
        return new Member(userId, password, name, address, email);
    }

    public void passwordEncrypt(String encodedPassword) {
        this.password = wrapPassword(encodedPassword);
    }
}
