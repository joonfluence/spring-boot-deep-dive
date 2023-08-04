package hello.servlet.web.frontController.v4;

import hello.servlet.web.frontController.MyView;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "frontControllerServletV4", urlPatterns = "/front-controller/v4/*")
public class FrontControllerServletV4 extends HttpServlet {
    Map<String, ControllerV4> controllerMap = new HashMap<>();

    public FrontControllerServletV4() {
        controllerMap.put("/front-controller/v4/members/new-form", new MemberFormControllerV4());
        controllerMap.put("/front-controller/v4/members/save", new MemberSaveControllerV4());
        controllerMap.put("/front-controller/v4/members", new MemberListControllerV4());
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String requestURI = request.getRequestURI();
        ControllerV4 controller = controllerMap.get(requestURI);

        if (controllerMap == null){
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        HashMap<String, String> paramMap = new HashMap<>();
        HashMap<String, Object> model = new HashMap<>();
        request.getParameterNames().asIterator().forEachRemaining(paramName -> paramMap.put(paramName, request.getParameter(paramName)));
        String viewPath = controller.process(paramMap, model);
        MyView view = viewResolver(viewPath);
        view.render(model, request, response);
    }

    private MyView viewResolver(String viewPath) {
        return new MyView("/WEB-INF/views/" + viewPath + ".jsp");
    }
}
