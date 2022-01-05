package me.sup2is.room335.api.member;

import lombok.RequiredArgsConstructor;
import me.sup2is.room335.api.config.AbstractControllerTestConfig;
import me.sup2is.room335.api.constant.EndPoints;
import me.sup2is.room335.api.member.dto.MemberDto;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RequiredArgsConstructor
class MemberControllerTest extends AbstractControllerTestConfig {

    final MemberService memberService;

    @Test
    void 멤버_생성() throws Exception {
        //given
        MemberDto.Request request = MemberDto.Request.builder()
                .username("")
                .passwordCheck("")
                .password("")
                .mobilePhone("")
                .email("")
                .build();

        //when
        ResultActions resultActions = mockMvc.perform(
                post(EndPoints.MEMBER_ROOT)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request))
        ).andDo(print());

        //then
        resultActions.andExpect(status().isOk());
    }

}