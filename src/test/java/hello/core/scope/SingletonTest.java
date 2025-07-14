package hello.core.scope;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SingletonTest {
    @Test
    public void singletonBeanFind() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(PrototypeBean.class);
        PrototypeBean prototypeBean = ac.getBean(PrototypeBean.class);
        prototypeBean.addCount();
        Assertions.assertThat(prototypeBean.getCount()).isEqualTo(1);

        PrototypeBean prototypeBean2 = ac.getBean(PrototypeBean.class);
        prototypeBean2.addCount();
        Assertions.assertThat(prototypeBean2.getCount()).isEqualTo(1);

    }

    @Configuration
    @Scope("prototype")
    static class PrototypeBean {
        private int count = 0;
        public void addCount(){
            this.count++;
        }
        public int getCount(){
            return this.count;
        }
        @PostConstruct
        public void init(){
            System.out.println("init 메서드입니다. PrototypeBean.init " + this.count);
        }
        @PreDestroy
        public void destroy(){
            System.out.println("destory 메서드입니다. PrototypeBean.destroy " + this);
        }
    }
}
