package hello.core;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
@ComponentScan(
        basePackages = "hello.core.member", // basePakages의 기능은 이 위치 부터 컴포넌트 스캔을 시작함
        //basePackages = {"hello.core.member","hello.core.member"}, 로 여러개의 패키지를 둘수 있다.
        // 만약 basePackage를 붙이지 않으면 @ComponentScan이 붙은 설정 정보 클래스의 패키지가 시작 위치가 된다.

        /*
        * 권장하는 방법
        * 개인적으로 즐겨 사용하는 방법은 패키지 위치를 지정하지 않고, 설정 정보 클래스의 위치를 프로젝트 최상단에 위치하도록 하는것,
        * 그리고 스프링 부트도 이 방법을 기본적으로 제공
        * */

        /*
        * 컴포넌트 스캔 기본 대상
        * @Component 뿐만 아니라 @Controller, @Service, @Repository, @Configuration도 추가로 대상에 포함함
       * */
        basePackageClasses = AutoAppConfig.class,
        excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION , classes = Configuration.class)) //스캔 할 것중에 뺄것을 지정
public class AutoAppConfig {

}
