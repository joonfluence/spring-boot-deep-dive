package hello.servlet.web.frontController.v3;

import hello.servlet.web.frontController.ModelView;
import hello.servlet.web.frontController.MyView;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "frontControllerServletV3", urlPatterns = "/front-controller/v3/*")
public class FrontControllerServletV3 extends HttpServlet {
    Map<String, ControllerV3> controllerMap = new HashMap<>();

    public FrontControllerServletV3() {
        controllerMap.put("/front-controller/v3/members/new-form", new MemberFormControllerV3());
        controllerMap.put("/front-controller/v3/members/save", new MemberSaveControllerV3());
        controllerMap.put("/front-controller/v3/members", new MemberListControllerV3());
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String requestURI = request.getRequestURI();
        ControllerV3 controller = controllerMap.get(requestURI);

        if (controllerMap == null){
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        ModelView myView = controller.process(request, response);
        MyView view = viewResolver(myView.getViewPath());
        view.render(myView.getModel(), request, response);
    }

    private MyView viewResolver(String viewPath) {
        return new MyView("/WEB-INF/views/" + viewPath + ".jsp");
    }
}
