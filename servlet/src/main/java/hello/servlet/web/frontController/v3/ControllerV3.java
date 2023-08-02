package hello.servlet.web.frontController.v3;

import hello.servlet.web.frontController.MyModelView;
import jakarta.servlet.ServletException;

import java.io.IOException;
import java.util.Map;

public interface ControllerV3 {
    MyModelView process(Map<String, String> paramMap) throws ServletException, IOException;
}
