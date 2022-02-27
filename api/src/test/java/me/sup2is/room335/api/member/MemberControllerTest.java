package me.sup2is.room335.api.member;

import lombok.RequiredArgsConstructor;
import me.sup2is.room335.api.config.AbstractControllerTestConfig;
import me.sup2is.room335.api.constant.EndPoints;
import me.sup2is.room335.api.member.dto.MemberCreateDto;
import me.sup2is.room335.api.member.dto.MemberDto;
import me.sup2is.room335.domain.member.Member;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RequiredArgsConstructor
class MemberControllerTest extends AbstractControllerTestConfig {

    final MemberService memberService;

    @Test
    void 멤버_생성() throws Exception {
        //given
        final MemberCreateDto.Request request = MemberCreateDto.Request.builder()
                .username("")
                .passwordCheck("")
                .password("")
                .mobilePhone("")
                .email("")
                .build();

        //when
        final ResultActions resultActions = this.mockMvc.perform(
                post(EndPoints.MEMBER_ROOT)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(this.objectMapper.writeValueAsString(request))
        ).andDo(print());

        //then
        resultActions.andExpect(status().isOk())
                .andDo(
                        document("member/create")
                );
    }

    @Test
    void 멤버_조회() throws Exception {
        //given
        final Member member = Member.builder()
                .email("")
                .username("")
                .mobilePhone("")
                .password("")
                .build();

        given(this.memberService.getMember(anyLong()))
                .willReturn(MemberDto.Response.of(member));

        //when
        final ResultActions resultActions = this.mockMvc.perform(
                get(EndPoints.MEMBER_GET, 1L)
                        .contentType(MediaType.APPLICATION_JSON)
        ).andDo(print());

        //then
        resultActions.andExpect(status().isOk())
                .andDo(
                        document("member/get")
                );

        resultActions.andExpect(jsonPath("$.email").value(member.getEmail()));
        resultActions.andExpect(jsonPath("$.mobilePhone").value(member.getMobilePhone()));
        resultActions.andExpect(jsonPath("$.username").value(member.getUsername()));
    }

}