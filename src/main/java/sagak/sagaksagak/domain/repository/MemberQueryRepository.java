package sagak.sagaksagak.domain.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import sagak.sagaksagak.domain.wrapper.member.Email;
import sagak.sagaksagak.domain.wrapper.member.UserId;

import static sagak.sagaksagak.domain.entity.QMember.member;

@Repository
@RequiredArgsConstructor
public class MemberQueryRepository {

    private final JPAQueryFactory queryFactory;

    public boolean isUserIdExist(UserId userId) {
        Integer result = queryFactory
                .selectOne()
                .from(member)
                .where(member.userId.eq(userId))
                .fetchFirst();

        return result != null;
    }

    public boolean isEmailExist(Email email) {
        Integer result = queryFactory
                .selectOne()
                .from(member)
                .where(member.email.eq(email))
                .fetchFirst();

        return result != null;
    }
}
