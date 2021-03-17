package hello.core.singleton;

import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

import static org.assertj.core.api.Assertions.assertThat;

class StatefulServiceTest {

    @Test
    void statefulServiceSingleton(){
        ApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);

        StatefulService statefulService1 = ac.getBean(StatefulService.class);
        StatefulService statefulService2 = ac.getBean(StatefulService.class);

        // A 사용자(ThreadA)가 10000원을 주문
        statefulService1.order("userA", 10000);

        // B 사용자(ThreadB)가 20000원을 주문
        statefulService2.order("userB", 20000);

        /*
        * A 사용자(ThreadA)가 주문 금액 조회
        *
        * 10000원 이길 기대 했지만 결과는 20000원이다.
        * 왜? statefulService1, 2는 같은 객체이기 때문이다.
        * */
        int price = statefulService1.getPrice();
        System.out.println("price = " + price);

        assertThat(statefulService1.getPrice()).isEqualTo(20000);
    }

    static class TestConfig{
        @Bean
        public StatefulService statefulService(){
            return new StatefulService();
        }
    }
}