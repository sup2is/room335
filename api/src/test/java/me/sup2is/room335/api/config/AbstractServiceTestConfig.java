package me.sup2is.room335.api.config;

import me.sup2is.room335.domain.member.MemberRepository;
import me.sup2is.room335.domain.room.RoomRepository;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.TestConstructor;

@SpringBootTest
@TestConstructor(autowireMode = TestConstructor.AutowireMode.ALL)
public abstract class AbstractServiceTestConfig {

    @MockBean
    protected MemberRepository memberRepository;

    @MockBean
    protected RoomRepository roomRepository;

}
