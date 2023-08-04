package hello.servlet.web.frontController.v3;

import hello.servlet.web.frontController.ModelView;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class MemberFormControllerV3 implements ControllerV3 {
    @Override
    public ModelView process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        return new ModelView("new-form");
    }
}
