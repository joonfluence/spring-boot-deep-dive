package hello.servlet.web.frontController.v3;

import hello.servlet.domain.member.Member;
import hello.servlet.domain.member.MemberRepository;
import hello.servlet.web.frontController.ModelView;
import hello.servlet.web.frontController.MyView;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Map;

public class MemberSaveControllerV3 implements ControllerV3 {
    private MemberRepository memberRepository = MemberRepository.getInstance();

    @Override
    public ModelView process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ModelView modelView = new ModelView("save-result");
        String username = request.getParameter("username");
        int age = Integer.parseInt(request.getParameter("age"));
        Member member = new Member(username, age);
        Member savedMember = memberRepository.save(member);
        Map<String, Object> model = modelView.getModel();
        model.put("member", savedMember);
        return modelView;
    }
}
