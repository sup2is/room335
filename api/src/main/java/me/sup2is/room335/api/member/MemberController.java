package me.sup2is.room335.api.member;

import lombok.RequiredArgsConstructor;
import me.sup2is.room335.api.constant.EndPoints;
import me.sup2is.room335.api.member.dto.MemberCreateDto;
import me.sup2is.room335.api.member.dto.MemberDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @PostMapping(EndPoints.MEMBER_ROOT)
    public void createMember(final MemberCreateDto.Request request) {
        memberService.createMember(request);
    }

    @GetMapping(EndPoints.MEMBER_GET)
    public MemberDto.Response getMember(@PathVariable("memberId") Long memberId) {
        return memberService.getMember(memberId);
    }

}
