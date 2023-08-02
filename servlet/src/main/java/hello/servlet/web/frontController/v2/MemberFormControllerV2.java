package hello.servlet.web.frontController.v2;

import hello.servlet.web.frontController.MyView;
import hello.servlet.web.frontController.v2.controller.MemberFormServletV2;
import hello.servlet.web.frontController.v2.controller.MemberListServletV2;
import hello.servlet.web.frontController.v2.controller.MemberSaveServletV2;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "frontControllerServletV2", urlPatterns = "/front-controller/v2/*")
public class MemberFormControllerV2 extends HttpServlet {

    Map<String, ControllerV2> paramMap = new HashMap<>();

    public MemberFormControllerV2() {
        this.paramMap.put("/front-controller/v2/members/new-form", new MemberFormServletV2("new-form"));
        this.paramMap.put("/front-controller/v2/members/save", new MemberSaveServletV2("save-result"));
        this.paramMap.put("/front-controller/v2/members", new MemberListServletV2("members"));
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String requestURI = request.getRequestURI();
        ControllerV2 controllerV2 = paramMap.get(requestURI);
        MyView view = controllerV2.process(request, response);
        view.render(request, response);
    }
}
