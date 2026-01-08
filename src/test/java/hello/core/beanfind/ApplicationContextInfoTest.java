package hello.core.beanfind;

import hello.core.AppConfig;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ApplicationContextInfoTest
{
    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

    @Test
    @DisplayName("모든 빈 출력하기")
    void findAllBean()
    {
        String[] beanNames = ac.getBeanDefinitionNames();
        for (String beanName : beanNames)
        {
            Object bean = ac.getBean(beanName);
            System.out.println("beanName = " + beanName + " obejct = " + bean);
        }
    }

    @Test
    @DisplayName("어플리케이션 빈 출력하기")
    void findApplicationBean()
    {
        String[] beanNames = ac.getBeanDefinitionNames();
        for (String beanName : beanNames)
        {
            BeanDefinition beanDefinition = ac.getBeanDefinition(beanName);

            //Role ROLE_APPLICATION : 직접 등록한 애플리케이션 빈
            //Role ROLE_INFRASTRUCTURE : 스프링이 내부에서 사용하는 빈.

            if(beanDefinition.getRole() == BeanDefinition.ROLE_APPLICATION) //내가 어플리케이션 개발을 위해 등록한 Bean 목록들을 출력하는 기능
            {
                Object bean = ac.getBean(beanName);
                System.out.println("name = " + beanName + " obejct = " + bean);
            }
        }
    }
}
