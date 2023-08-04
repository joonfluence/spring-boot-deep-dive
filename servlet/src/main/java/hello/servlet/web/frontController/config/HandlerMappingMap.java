package hello.servlet.web.frontController.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;

@Getter
public class HandlerMappingMap {
    @Autowired
    private Map<String, Object> handlerMappingMap;

    public void printNameList() {
        System.out.println(handlerMappingMap);
    }
}
