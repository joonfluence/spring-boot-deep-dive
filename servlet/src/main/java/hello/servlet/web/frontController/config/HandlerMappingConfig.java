package hello.servlet.web.frontController.config;

import hello.servlet.web.frontController.v3.MemberFormControllerV3;
import hello.servlet.web.frontController.v3.MemberListControllerV3;
import hello.servlet.web.frontController.v3.MemberSaveControllerV3;
import hello.servlet.web.frontController.v4.MemberFormControllerV4;
import hello.servlet.web.frontController.v4.MemberListControllerV4;
import hello.servlet.web.frontController.v4.MemberSaveControllerV4;
import hello.servlet.web.frontController.v5.MyHandlerAdapter;
import hello.servlet.web.frontController.v5.adapter.ControllerV3HandlerAdapter;
import hello.servlet.web.frontController.v5.adapter.ControllerV4HandlerAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Configuration
public class HandlerMappingConfig {

    @Bean
    public HandlerMappingMap getHandlerMappingMap() {
        return new HandlerMappingMap();
    }

    @Bean
    public Map<String, Object> handlerMappingMap() {
        Map<String, Object> handlerMappingMap = new HashMap<>();
        handlerMappingMap.put("/front-controller/v5/v3/members/new-form", new MemberFormControllerV3());
        handlerMappingMap.put("/front-controller/v5/v3/members/save", new MemberSaveControllerV3());
        handlerMappingMap.put("/front-controller/v5/v3/members", new MemberListControllerV3());
        handlerMappingMap.put("/front-controller/v5/v4/members/new-form", new MemberFormControllerV4());
        handlerMappingMap.put("/front-controller/v5/v4/members/save", new MemberSaveControllerV4());
        handlerMappingMap.put("/front-controller/v5/v4/members", new MemberListControllerV4());
        return handlerMappingMap;
    }

    @Bean
    public HandlerAdapters getHandlerAdapters() {
        return new HandlerAdapters();
    }

    @Bean
    public List<MyHandlerAdapter> handlerAdapters() {
        List<MyHandlerAdapter> handlerAdapters = new ArrayList<>();
        handlerAdapters.add(new ControllerV3HandlerAdapter());
        handlerAdapters.add(new ControllerV4HandlerAdapter());
        return handlerAdapters;
    }
}
