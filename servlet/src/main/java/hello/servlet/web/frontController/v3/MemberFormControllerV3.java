package hello.servlet.web.frontController.v3;

import hello.servlet.web.frontController.MyModelView;
import hello.servlet.web.frontController.MyView;
import hello.servlet.web.frontController.v3.controller.MemberFormServletV3;
import hello.servlet.web.frontController.v3.controller.MemberListServletV3;
import hello.servlet.web.frontController.v3.controller.MemberSaveServletV3;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@WebServlet(name = "frontControllerServletV3", urlPatterns = "/front-controller/v3/*")
public class MemberFormControllerV3 extends HttpServlet {

    Map<String, ControllerV3> serveletMap = new HashMap<>();

    public MemberFormControllerV3() {
        this.serveletMap.put("/front-controller/v3/new-form", new MemberFormServletV3());
        this.serveletMap.put("/front-controller/v3/save", new MemberSaveServletV3());
        this.serveletMap.put("/front-controller/v3/members", new MemberListServletV3());
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String requestURI = request.getRequestURI();
        ControllerV3 controllerV3 = serveletMap.get(requestURI);
        if (controllerV3 == null) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        Map<String, String> paramMap = createParamMap(request);
        MyModelView modelView = controllerV3.process(paramMap);
        String viewPath = modelView.getViewPath();
        MyView view = viewResolver(viewPath);
        view.render(modelView.getModel(), request, response);
    }

    private Map<String, String> createParamMap(HttpServletRequest request) {
        Map<String, String> paramMap = new HashMap<>();
        request.getParameterNames().asIterator().forEachRemaining(paramName -> paramMap.put(paramName, request.getParameter(paramName)));
        return paramMap;
    }

    private MyView viewResolver(String viewPath) {
        return new MyView("/WEB-INF/views/" + viewPath + ".jsp");
    }
}
