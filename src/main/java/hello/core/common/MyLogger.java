package hello.core.common;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.UUID;

@Component
@Scope(value = "request")
public class MyLogger {

    private String uuid;
    private String requestURL;

    public void setRequestURL(String requestURL) {
        this.requestURL = requestURL;
    }

    public void log(String message) {
        System.out.println("uuid + requestURL + message = " + uuid + requestURL + message);
    }

    @PostConstruct
    public void init() {
        uuid = UUID.randomUUID().toString();
        System.out.println("request scope bean create:" + this);
    }

    @PreDestroy
    public void close() {
        System.out.println("request scope bean close:" + this);
    }
}
/*
* 로그를 출력하기 위한 "MyLogger" 클래스이다
* @Scope(value = "request")를 사용해서 request 스코프로 지정했다. 이게 이 빈은 HTTP 요청 하나당 하나씩 생성되고, HTTP 요청이 끝나는 시점에 소명된다.
* 이 빈이 생성되는 시점에 자동으로 @PreConstruct 초기화 메서드를 사용해서 uuid를 생성해서 저장해둔다. 이 빈은 HTTP 요청 당 하나씩 생성되므로, uuid를 정장해두면 다른 HTTP요청과 구분할 수 있다.
* 이빈이 소명되는 시점에 @PreDestroy를 사용해서 종료 메시지를 남긴다.
* requestURL 은 이 빈이 생성되는 시점에는 알 수 없으므로 외부에서 setter로  입력 받는다. url정보를 어디선가 넣어 줘야 한다.
* */