package sagak.sagaksagak.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sagak.sagaksagak.domain.entity.Member;
import sagak.sagaksagak.domain.repository.MemberQueryRepository;
import sagak.sagaksagak.domain.repository.MemberRepository;
import sagak.sagaksagak.domain.wrapper.member.Email;
import sagak.sagaksagak.domain.wrapper.member.UserId;
import sagak.sagaksagak.dto.member.MemberDto;
import sagak.sagaksagak.exception.exception.member.DuplicateEmailException;
import sagak.sagaksagak.exception.exception.member.DuplicateUserIdException;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final MemberQueryRepository memberQueryRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public void confirmUserIdDuplication(String userId) {
        boolean userIdExist = memberQueryRepository.isUserIdExist(UserId.of(userId));
        if (userIdExist) {
            throw new DuplicateUserIdException();
        }
    }

    @Transactional
    public void confirmEmailDuplication(String email) {
        boolean emailExist = memberQueryRepository.isEmailExist(Email.of(email));
        if (emailExist) {
            throw new DuplicateEmailException();
        }
    }

    @Transactional
    public void signUp(MemberDto memberDto) {
        confirmUserIdAndEmailDuplication(memberDto);

        Member member = memberDto.toEntity();
        member.passwordEncrypt(passwordEncoder.encode(memberDto.getPassword()));

        memberRepository.save(member);
    }

    private void confirmUserIdAndEmailDuplication(MemberDto memberDto) {
        confirmUserIdDuplication(memberDto.getUserId());
        confirmEmailDuplication(memberDto.getEmail());
    }
}
