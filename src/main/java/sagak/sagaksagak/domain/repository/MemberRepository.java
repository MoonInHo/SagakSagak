package sagak.sagaksagak.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sagak.sagaksagak.domain.entity.Member;

public interface MemberRepository extends JpaRepository<Member, Long> {
}
