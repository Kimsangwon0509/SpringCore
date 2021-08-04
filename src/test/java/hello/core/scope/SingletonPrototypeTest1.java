package hello.core.scope;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.inject.Provider;

import static org.assertj.core.api.Assertions.assertThat;

public class SingletonPrototypeTest1 {

    @Test
    void prototypeFind() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(PrototypeBean.class);
        PrototypeBean prototypeBean1 = ac.getBean(PrototypeBean.class);
        prototypeBean1.addCount();
        assertThat(prototypeBean1.getCount()).isEqualTo(1);

        PrototypeBean prototypeBean2 = ac.getBean(PrototypeBean.class);
        prototypeBean2.addCount();
        assertThat(prototypeBean2.getCount()).isEqualTo(1);
    }

    @Test
    void singletonClientUsePrototype() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(PrototypeBean.class, ClientBean.class);

        ClientBean clinetBean1 = ac.getBean(ClientBean.class);
        int count1 = clinetBean1.logic();
        assertThat(count1).isEqualTo(1);

        ClientBean clientBean2 = ac.getBean(ClientBean.class);
        int count2 = clientBean2.logic();
        assertThat(count2).isEqualTo(1);
    }

    @Scope("singleton")
    static class ClientBean{
        //private final PrototypeBean prototypeBean; //생성시점에 주입

        @Autowired
        private Provider<PrototypeBean> prototypeBeanProvider;

        public int logic() {
            PrototypeBean prototypeBean = prototypeBeanProvider.get();
            prototypeBean.addCount();
            int count = prototypeBean.getCount();
            return count;
        }
        /*
        * 실행해보면 'provider.get()'을 통해서 항상 새로운 프로토타입 빈이 생성되는 것을 확인할수 있다.
        * 'provider'의 'get()'을 호출하면 내부에서는 스프링 컨테이너를 통해 해당 빈을 찾아서 반환한다.("DL")
        *  자바 표준이고, 기능이 단순하므로 단위테스트를 만들거나 mock코드를 만들기는 쉽ㄴ다
        * 'Procider'는 지금 딱 필요한 DL 정도의 기능만 제공한다.
        *
        * --특징--
        * 'get()' 메서드 하나로 기능이 매우 단순하다.
        * 별도의 라이브러리가 필요
        * 자바 표준이므로 스프링이 아닌 다른 컨테이너에서도 사용 가능
        *
        * --정리--
        * 그러면 프로토타입 빈을 언제 사용할까? 매번 사용할 때 마다 의존관계 주입이 완료된 새로운 객체가 필요하면 사용하면 된다. 그런데 실무에서 웹
        * 어플리케이션을 개발하면, 싱글톤 빈으로 대부분의 문제를 해결할 수 있으므로 프로토타입 빈을 직접적으로 사용하는 일은 드물다.
        * 'ObjectProvide','JSR303 Provider'등은 프로토타입 뿐만 아니라 DL이 필요한 경우는 언제든지 사용할 수 있다.
        *
        * */




/*

        @Autowired
        private ObjectProvider<PrototypeBean> prototypeBeanProvider;


//        public ClientBean(PrototypeBean prototypeBean) {
//            this.prototypeBean = prototypeBean;
//        }

        public int logic() {
            PrototypeBean prototypeBean = prototypeBeanProvider.getObject();
            prototypeBean.addCount();
            int count = prototypeBean.getCount();
            return count;
        }
*/

        /*
        * PrototypeBean는 getObject를 할때 그 때 서야 Bean을 생성해서 반환해 준다.
        * 그래서 필요할때마다 생성해서 반환할때 쓰면 된다. ObjectFactory와 ObjectProvider랑 비슷한데 ObjectProvider를 선호
        * */
    }

    @Scope("prototype")
    static class PrototypeBean {
        private int count = 0 ;

        public void addCount() {
            count++;
        }

        public int getCount() {
            return count;
        }

        @PostConstruct
        public void init() {
            System.out.println("this = " + this);
        }

        @PreDestroy
        public void destory() {
            System.out.println("PrototypeBean.destroy");
        }
    }



}
