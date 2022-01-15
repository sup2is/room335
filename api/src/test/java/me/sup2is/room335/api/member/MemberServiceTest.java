package me.sup2is.room335.api.member;

import lombok.RequiredArgsConstructor;
import me.sup2is.room335.api.config.AbstractServiceTestConfig;
import me.sup2is.room335.api.member.dto.MemberCreateDto;
import me.sup2is.room335.api.member.dto.MemberDto;
import me.sup2is.room335.domain.member.Member;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

@RequiredArgsConstructor
class MemberServiceTest extends AbstractServiceTestConfig {

    final MemberService memberService;

    @Test
    void 멤버_생성() {
        //given
        MemberCreateDto.Request request = MemberCreateDto.Request.builder()
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

    @Test
    void 멤버_조회() {
        //given
        long memberId = 1L;

        Member member = Member.builder()
                .email("")
                .username("")
                .mobilePhone("")
                .password("")
                .build();

        given(memberRepository.findById(anyLong()))
                .willReturn(Optional.of(member));

        //when
        MemberDto.Response response = memberService.getMember(memberId);

        //then
        assertThat(response.getEmail()).isEqualTo(member.getEmail());
        assertThat(response.getUsername()).isEqualTo(member.getUsername());
        assertThat(response.getMobilePhone()).isEqualTo(member.getMobilePhone());
    }

}