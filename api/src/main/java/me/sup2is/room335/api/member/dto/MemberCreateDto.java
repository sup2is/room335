package me.sup2is.room335.api.member.dto;

import lombok.Builder;
import lombok.Getter;
import me.sup2is.room335.domain.member.Member;

import javax.validation.constraints.NotNull;

public class MemberCreateDto {

    @Builder
    @Getter
    public static class Request {

        @NotNull
        private final String email;
        @NotNull
        private final String username;
        @NotNull
        private final String mobilePhone;
        @NotNull
        private final String password;
        @NotNull
        private final String passwordCheck;

        public Member toEntity() {
            return Member.builder()
                    .email(this.email)
                    .mobilePhone(this.mobilePhone)
                    .password(this.password)
                    .username(this.username)
                    .build();
        }
    }

}
