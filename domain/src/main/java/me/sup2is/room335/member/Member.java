package me.sup2is.room335.member;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import me.sup2is.room335.BaseEntity;

import javax.persistence.Entity;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member extends BaseEntity {

    private String email;
    private String username;
    private String mobilePhone;
    private String password;

    @Builder
    private Member(final String email, final  String username, final  String mobilePhone, final  String password) {
        this.email = email;
        this.username = username;
        this.mobilePhone = mobilePhone;
        this.password = password;
    }

}
