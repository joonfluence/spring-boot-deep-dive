package toby.ioc;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import toby.config.HelloConfig;

public class Main {

    public static void main(String[] args){
        ApplicationContext ac = new AnnotationConfigApplicationContext(HelloConfig.class);
        Hello hello1 = ac.getBean("hello2", Hello.class);
        hello1.print();
    }
}
