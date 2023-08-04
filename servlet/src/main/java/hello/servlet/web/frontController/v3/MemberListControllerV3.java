package hello.servlet.web.frontController.v3;

import hello.servlet.domain.member.Member;
import hello.servlet.domain.member.MemberRepository;
import hello.servlet.web.frontController.ModelView;
import hello.servlet.web.frontController.MyView;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public class MemberListControllerV3 implements ControllerV3 {
    private MemberRepository memberRepository = MemberRepository.getInstance();

    @Override
    public ModelView process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ModelView modelView = new ModelView("members");
        Map<String, Object> model = modelView.getModel();
        List<Member> members = memberRepository.findAll();
        model.put("members", members);
        return modelView;
    }
}
