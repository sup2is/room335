package me.sup2is.room335.domain.member;

import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.TestConstructor;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@TestConstructor(autowireMode = TestConstructor.AutowireMode.ALL)
@RequiredArgsConstructor
class MemberRepositoryTest {

    final MemberRepository memberRepository;

    @Test
    void 멤버_생성() {
        //given
        Member member = Member.builder()
                .email("")
                .password("")
                .mobilePhone("")
                .username("")
                .build();

        //when
        memberRepository.save(member);

        //then
        Member findMember = memberRepository.findById(member.getId()).get();
        assertThat(member).isEqualTo(findMember);
    }
}