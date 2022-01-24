package me.sup2is.room335.api.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import me.sup2is.room335.api.member.MemberService;
import me.sup2is.room335.api.order.OrderService;
import me.sup2is.room335.api.room.RoomService;
import me.sup2is.room335.domain.room.Room;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.TestConstructor;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest
@TestConstructor(autowireMode = TestConstructor.AutowireMode.ALL)
@Import(ApplicationConfig.class)
@AutoConfigureRestDocs
public class AbstractControllerTestConfig {

    @Autowired
    protected MockMvc mockMvc;

    @Autowired
    protected ObjectMapper objectMapper;

    @MockBean
    protected MemberService memberService;

    @MockBean
    protected RoomService roomService;

    @MockBean
    protected OrderService orderService;


}
