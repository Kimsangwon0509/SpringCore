package hello.core.singelton;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

import static org.assertj.core.api.Assertions.assertThat;


class StatefulServiceTest {
    @Test
    void statefulServiceSingleton() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);
        StatefulService statefulService1 = ac.getBean(StatefulService.class);
        StatefulService statefulService2 = ac.getBean(StatefulService.class);

        //ThreadA : A사용자 10000원 주문
        statefulService1.order("userA",10000);

        //ThreadB : B사용자 20000원 주문
        statefulService1.order("userB",20000);
        
        //ThreadA: 사용자 A 주문 금액 조회
        int price = statefulService1.getPrice();
        System.out.println("price = " + price);

        //사용자 A가 주문하고 가격을 나중에 출력 하려는데 중간에 B가 주문해서 결과적으로 10000원을 예상한 결과가 아님20000이 나온다


        assertThat(statefulService1.getPrice()).isEqualTo(20000);

        //위와 같이 특정 클라이언트가 값을 변경할수 있으면 안된다.
    }

    static class TestConfig {

        @Bean
        public StatefulService statefulService() {
            return new StatefulService();
        }
    }

}