package hello.servlet.web.frontController.config;

import hello.servlet.web.frontController.v3.ControllerV3;
import hello.servlet.web.frontController.v5.MyHandlerAdapter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;
import java.util.Map;

@SpringBootTest
class HandlerMappingConfigTest {

    @Test
    void getHandlerMappingMap() {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(HandlerMappingConfig.class);
        HandlerMappingConfig handlerMappingConfig = applicationContext.getBean(HandlerMappingConfig.class);
        Map<String, Object> handlerMappingMap = handlerMappingConfig.handlerMappingMap();
        List<MyHandlerAdapter> handlerAdapters = handlerMappingConfig.handlerAdapters();
        Object o = handlerMappingMap.get("/front-controller/v5/v3/members/new-form");
        MyHandlerAdapter adapter = handlerAdapters.get(0);
        System.out.println("o = " + o);
        Assertions.assertTrue(o instanceof ControllerV3);
        Assertions.assertNotNull(adapter);
    }

}