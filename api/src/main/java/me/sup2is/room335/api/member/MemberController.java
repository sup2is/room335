package me.sup2is.room335.api.member;

import lombok.RequiredArgsConstructor;
import me.sup2is.room335.api.constant.EndPoints;
import me.sup2is.room335.api.member.dto.MemberCreateDto;
import me.sup2is.room335.api.member.dto.MemberDto;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @PostMapping(EndPoints.MEMBER_ROOT)
    public void createMember(@Valid @RequestBody final MemberCreateDto.Request request) {
        this.memberService.createMember(request);
    }

    @GetMapping(EndPoints.MEMBER_GET)
    public MemberDto.Response getMember(@PathVariable("memberId") final Long memberId) {
        return this.memberService.getMember(memberId);
    }

}
