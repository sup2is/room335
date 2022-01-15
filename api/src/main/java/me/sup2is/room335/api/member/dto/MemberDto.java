package me.sup2is.room335.api.member.dto;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import me.sup2is.room335.domain.member.Member;

public class MemberDto {

    @Builder
    @Getter
    @EqualsAndHashCode
    public static class Response {

        private String email;
        private String username;
        private String mobilePhone;

        public static Response of(final Member member) {
            return Response.builder()
                    .email(member.getEmail())
                    .mobilePhone(member.getMobilePhone())
                    .username(member.getUsername())
                    .build();
        }
    }

}
