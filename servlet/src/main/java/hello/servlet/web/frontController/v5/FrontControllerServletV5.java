package hello.servlet.web.frontController.v5;

import hello.servlet.web.frontController.MyModelView;
import hello.servlet.web.frontController.MyView;
import hello.servlet.web.frontController.v3.controller.MemberFormServletV3;
import hello.servlet.web.frontController.v3.controller.MemberListServletV3;
import hello.servlet.web.frontController.v3.controller.MemberSaveServletV3;
import hello.servlet.web.frontController.v4.controller.MemberFormServletV4;
import hello.servlet.web.frontController.v4.controller.MemberListServletV4;
import hello.servlet.web.frontController.v4.controller.MemberSaveServletV4;
import hello.servlet.web.frontController.v5.adpater.ControllerV3HandlerAdapter;
import hello.servlet.web.frontController.v5.adpater.ControllerV4HandlerAdapter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet(name = "frontControllerServletV5", urlPatterns = "/front-controller/v5/*")
public class FrontControllerServletV5 extends HttpServlet {

    private final Map<String, Object> handlerMappingMap = new HashMap<>();
    private final List<MyHandlerAdapter> handlerAdapters = new ArrayList<>();

    public FrontControllerServletV5() {
        initHandlerMappingMap();
        initHandlerAdapters();
    }

    private void initHandlerMappingMap() {
        // v3
        handlerMappingMap.put("/front-controller/v5/v3/members/new-form", new MemberFormServletV3());
        handlerMappingMap.put("/front-controller/v5/v3/members/save", new MemberSaveServletV3());
        handlerMappingMap.put("/front-controller/v5/v3/members", new MemberListServletV3());

        // v4
        handlerMappingMap.put("/front-controller/v5/v4/members/new-form", new MemberFormServletV4());
        handlerMappingMap.put("/front-controller/v5/v4/members/save", new MemberSaveServletV4());
        handlerMappingMap.put("/front-controller/v5/v4/members", new MemberListServletV4());
    }

    private void initHandlerAdapters(){
        handlerAdapters.add(new ControllerV3HandlerAdapter());
        handlerAdapters.add(new ControllerV4HandlerAdapter());
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // MemberFormServletV4
        Object handler = getHandler(request);
        if (handler == null) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        // ControllerV4HandlerAdapter
        MyHandlerAdapter adapter = getHandlerAdapter(handler);

        MyModelView modelView = adapter.handle(request, response, handler);

        String viewPath = modelView.getViewPath();
        MyView view = viewResolver(viewPath);
        view.render(modelView.getModel(), request, response);
    }

    private MyHandlerAdapter getHandlerAdapter(Object handler) {
        for (MyHandlerAdapter adapter : handlerAdapters) {
            if (adapter.suppports(handler)){
                return adapter;
            }
        }
        throw new IllegalArgumentException("handler adapter를 찾을 수 없습니다. handler = " + handler);
    }

    private Object getHandler(HttpServletRequest request) {
        String requestURI = request.getRequestURI();
        return handlerMappingMap.get(requestURI);
    }

    private MyView viewResolver(String viewPath) {
        return new MyView("/WEB-INF/views/" + viewPath + ".jsp");
    }
}
