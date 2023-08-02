package hello.servlet.web.frontController.v3.controller;

import hello.servlet.web.frontController.v3.ControllerV3;
import hello.servlet.web.frontController.MyModelView;
import jakarta.servlet.ServletException;

import java.io.IOException;
import java.util.Map;

public class MemberFormServletV3 implements ControllerV3 {

    public MemberFormServletV3() {
    }

    @Override
    public MyModelView process(Map<String, String> paramMap) throws ServletException, IOException {
        return new MyModelView("new-form");
    }
}
