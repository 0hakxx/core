package hello.core.beanfind;

import hello.core.AppConfig;
import hello.core.member.MemberService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import static org.assertj.core.api.Assertions.assertThat;

public class ApplicationContextInfoTest {
    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

    @Test
    @DisplayName("모든 빈 출력하기")
    public void showAllbean(){
        String[] beanDifinitionNames = ac.getBeanDefinitionNames();
        for (String beanDifinitionName : beanDifinitionNames) {
            Object bean = ac.getBean(beanDifinitionName);
            System.out.println("name = " + beanDifinitionName + "object " + bean);
        }
    }
    @Test
    @DisplayName("애플리케이션 빈 출력하기")
    void findApplicationBean() {
        String[] beanDefinitionNames = ac.getBeanDefinitionNames();
        for (String beanDefinitionName : beanDefinitionNames) {
            BeanDefinition beanDefinition = ac.getBeanDefinition(beanDefinitionName);

            if(beanDefinition.getRole() == BeanDefinition.ROLE_APPLICATION){ // 사용자가 정의한 빈으로 필터
                Object bean = ac.getBean(beanDefinitionName);
                System.out.println("name=" + beanDefinitionName + " object=" + bean);
        }
    }
}
    @Test
    @DisplayName("빈 이름 조회 X")
    void findByNameX() {
        try {
            Object bean = ac.getBean("xxxxx", MemberService.class);
        }catch (NoSuchBeanDefinitionException e){
            System.out.println(e.getMessage());
        }
    }
}
