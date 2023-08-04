package hello.servlet.web.frontController;

import java.util.HashMap;
import java.util.Map;

public class ModelView {
    private Map<String, Object> model = new HashMap<>();

    private String viewPath;

    public ModelView(String viewPath) {
        this.viewPath = viewPath;
    }

    public Map<String, Object> getModel() {
        return model;
    }

    public void setModel(Map<String, Object> model) {
        this.model = model;
    }

    public String getViewPath() {
        return viewPath;
    }

    public void setViewPath(String viewPath) {
        this.viewPath = viewPath;
    }
}
