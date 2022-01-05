package me.sup2is.room335.api.member;

import lombok.RequiredArgsConstructor;
import me.sup2is.room335.api.config.AbstractServiceTestConfig;
import me.sup2is.room335.api.member.dto.MemberDto;
import org.junit.jupiter.api.Test;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.then;

@RequiredArgsConstructor
class MemberServiceTest extends AbstractServiceTestConfig {

    final MemberService memberService;

    @Test
    void 멤버_생성() {
        //given
        MemberDto.Request request = MemberDto.Request.builder()
                .email("")
                .mobilePhone("")
                .password("")
                .passwordCheck("")
                .username("")
                .build();

        //when
        memberService.createMember(request);

        //then
        then(memberRepository).should().save(any());
    }

}