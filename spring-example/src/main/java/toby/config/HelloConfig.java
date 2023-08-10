package toby.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import toby.ioc.ConsolePrinter;
import toby.ioc.Hello;
import toby.ioc.Printer;

@Configuration
public class HelloConfig {
    @Bean
    public Hello hello() {
        Hello hello = new Hello();
        hello.setName("Spring");
        hello.setPrinter(printer());
        return hello;
    }

    @Bean
    public Hello hello2() {
        Hello hello = new Hello();
        hello.setName("Spring2");
        hello.setPrinter(printer());
        return hello;
    }

    @Bean
    public Printer printer() {
        return new ConsolePrinter();
    }
}
