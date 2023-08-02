package hello.servlet.web.frontController.v2.controller;

import hello.servlet.web.frontController.v2.ControllerV2;
import hello.servlet.web.frontController.MyView;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class MemberFormServletV2 implements ControllerV2 {

    private final String viewPath;

    public MemberFormServletV2(String viewPath) {
        this.viewPath = viewPath;
    }

    @Override
    public MyView process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        return new MyView(this.viewPath);
    }
}
