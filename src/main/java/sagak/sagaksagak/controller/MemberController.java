package sagak.sagaksagak.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import sagak.sagaksagak.dto.member.MemberDto;
import sagak.sagaksagak.service.MemberService;

@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/sign-up")
    public void signUp(@RequestBody MemberDto memberDto) {
        memberService.signUp(memberDto);
    }
}
