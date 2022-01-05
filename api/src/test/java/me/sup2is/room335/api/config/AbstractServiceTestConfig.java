package me.sup2is.room335.api.config;

import lombok.RequiredArgsConstructor;
import me.sup2is.room335.domain.member.MemberRepository;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.TestConstructor;

@SpringBootTest
@TestConstructor(autowireMode = TestConstructor.AutowireMode.ALL)
public abstract class AbstractServiceTestConfig {

    @MockBean
    protected MemberRepository memberRepository;

}
