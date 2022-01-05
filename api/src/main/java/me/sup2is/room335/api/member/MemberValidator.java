package me.sup2is.room335.api.member;

public class MemberValidator {

    public static void checkPassword(final String password, final String check) {
        if(!password.equals(check)) {
            throw new IllegalArgumentException("패스워드가 일치하지 않습니다.");
        }
    }

}
