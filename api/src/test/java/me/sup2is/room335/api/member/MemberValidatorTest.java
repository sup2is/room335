package me.sup2is.room335.api.member;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MemberValidatorTest {

    @Test
    void 두개의_패스워드가_같으면_성공() {
        //when
        //then
        MemberValidator.checkPassword("a", "a");

    }

    @Test
    void 두개의_패스워드가_다르면_IllegalArgumentException_발생() {
        //when
        //then
        assertThrows(IllegalArgumentException.class,
                () -> MemberValidator.checkPassword("a", "b"));
    }

}