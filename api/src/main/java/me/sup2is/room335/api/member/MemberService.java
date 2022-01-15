package me.sup2is.room335.api.member;

import lombok.RequiredArgsConstructor;
import me.sup2is.room335.api.member.dto.MemberCreateDto;
import me.sup2is.room335.api.member.dto.MemberDto;
import me.sup2is.room335.domain.member.Member;
import me.sup2is.room335.domain.member.MemberRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberService {

    private final MemberRepository memberRepository;

    @Transactional
    public void createMember(final MemberCreateDto.Request request) {
        MemberValidator.checkPassword(request.getPassword(), request.getPasswordCheck());

        memberRepository.save(request.toEntity());
    }

    public MemberDto.Response getMember(final Long memberId) {
        Member member = memberRepository.findById(memberId).orElseThrow(
                () -> new IllegalArgumentException()
        );

        return MemberDto.Response.of(member);
    }
}
