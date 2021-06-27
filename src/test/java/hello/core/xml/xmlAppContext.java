package hello.core.xml;

import hello.core.member.MemberService;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import static org.assertj.core.api.Assertions.assertThat;

public class xmlAppContext {
    @Test
    void xmlAppcontext() {
        //설정 정보가 class파일에서 xml로 바뀐것.. 위의 Appconfig와 동일
        ApplicationContext ac = new GenericXmlApplicationContext("appconfig.xml");
        MemberService memberService = ac.getBean("memberService", MemberService.class);
        assertThat(memberService).isInstanceOf(MemberService.class);
    }
}
