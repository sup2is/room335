package me.sup2is.room335.api.member;

import lombok.RequiredArgsConstructor;
import me.sup2is.room335.api.member.dto.MemberDto;
import me.sup2is.room335.domain.member.MemberRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    public void createMember(final MemberDto.Request request) {
        MemberValidator.checkPassword(request.getPassword(), request.getPasswordCheck());

        memberRepository.save(request.toEntity());
    }
}
