package sagak.sagaksagak.domain.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QMember is a Querydsl query type for Member
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QMember extends EntityPathBase<Member> {

    private static final long serialVersionUID = -441985378L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QMember member = new QMember("member1");

    public final sagak.sagaksagak.domain.wrapper.member.QAddress address;

    public final sagak.sagaksagak.domain.wrapper.member.QEmail email;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final sagak.sagaksagak.domain.wrapper.member.QName name;

    public final sagak.sagaksagak.domain.wrapper.member.QPassword password;

    public final sagak.sagaksagak.domain.wrapper.member.QUserId userId;

    public QMember(String variable) {
        this(Member.class, forVariable(variable), INITS);
    }

    public QMember(Path<? extends Member> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QMember(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QMember(PathMetadata metadata, PathInits inits) {
        this(Member.class, metadata, inits);
    }

    public QMember(Class<? extends Member> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.address = inits.isInitialized("address") ? new sagak.sagaksagak.domain.wrapper.member.QAddress(forProperty("address")) : null;
        this.email = inits.isInitialized("email") ? new sagak.sagaksagak.domain.wrapper.member.QEmail(forProperty("email")) : null;
        this.name = inits.isInitialized("name") ? new sagak.sagaksagak.domain.wrapper.member.QName(forProperty("name")) : null;
        this.password = inits.isInitialized("password") ? new sagak.sagaksagak.domain.wrapper.member.QPassword(forProperty("password")) : null;
        this.userId = inits.isInitialized("userId") ? new sagak.sagaksagak.domain.wrapper.member.QUserId(forProperty("userId")) : null;
    }

}

