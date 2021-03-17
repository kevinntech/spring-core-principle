package hello.core.singleton;

import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

import static org.assertj.core.api.Assertions.assertThat;

class StatelessServiceTest {

    @Test
    void statelessServiceSingleton(){
        ApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);

        StatelessService statelessService1 = ac.getBean(StatelessService.class);
        StatelessService statelessService2 = ac.getBean(StatelessService.class);

        // A 사용자(ThreadA)가 10000원을 주문
        int userAPrice = statelessService1.order("userA", 10000);

        // B 사용자(ThreadB)가 20000원을 주문
        int userBPrice = statelessService2.order("userB", 20000);

        /*
        * A 사용자(ThreadA)가 주문 금액 조회
        *
        * 기대 했던 대로 결과는 10000원으로 출력된다.
        * 왜? 무상태(stateless)로 설계 했기 때문이다. (지역 변수는 공유되지 않는다.)
        * */
        System.out.println("price = " + userAPrice);
    }

    static class TestConfig{
        @Bean
        public StatelessService statelessService(){
            return new StatelessService();
        }
    }
}