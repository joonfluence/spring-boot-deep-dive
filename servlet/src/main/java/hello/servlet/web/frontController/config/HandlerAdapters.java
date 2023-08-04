package hello.servlet.web.frontController.config;

import hello.servlet.web.frontController.v5.MyHandlerAdapter;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Getter
public class HandlerAdapters {
    @Autowired
    private List<MyHandlerAdapter> handlerAdapters;

    public void printNameList() {
        System.out.println(handlerAdapters);
    }
}
