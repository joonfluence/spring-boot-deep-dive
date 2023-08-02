package hello.servlet.web.frontController.v1;

import hello.servlet.web.frontController.v1.controller.MemberListServletV1;
import hello.servlet.web.frontController.v1.controller.MemberFormServletV1;
import hello.servlet.web.frontController.v1.controller.MemberSaveServletV1;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "frontControllerServletV1", urlPatterns = "/front-controller/v1/*")
public class MemberFormControllerV1 extends HttpServlet {

    Map<String, ControllerV1> paramMap = new HashMap<>();

    public MemberFormControllerV1() {
        this.paramMap.put("/front-controller/v1/members/new-form", new MemberFormServletV1());
        this.paramMap.put("/front-controller/v1/members/save", new MemberSaveServletV1());
        this.paramMap.put("/front-controller/v1/members", new MemberListServletV1());
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String requestURI = request.getRequestURI();
        ControllerV1 controllerV1 = paramMap.get(requestURI);
        controllerV1.process(request, response);
    }
}
