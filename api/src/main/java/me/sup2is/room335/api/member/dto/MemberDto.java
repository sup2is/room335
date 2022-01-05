package me.sup2is.room335.api.member.dto;

import lombok.Builder;
import lombok.Getter;
import me.sup2is.room335.domain.member.Member;

public class MemberDto {

    @Builder
    @Getter
    public static class Request {
        private String email;
        private String username;
        private String mobilePhone;
        private String password;
        private String passwordCheck;

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
