package hello.servlet.web.frontController.v3.controller;

import hello.servlet.domain.member.Member;
import hello.servlet.domain.member.MemberRepository;
import hello.servlet.web.frontController.v3.ControllerV3;
import hello.servlet.web.frontController.MyModelView;
import jakarta.servlet.ServletException;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public class MemberListServletV3 implements ControllerV3 {

    private MemberRepository memberRepository = MemberRepository.getInstance();

    public MemberListServletV3() {
    }

    @Override
    public MyModelView process(Map<String, String> paramMap) throws ServletException, IOException {
        MyModelView modelView = new MyModelView("members");
        List<Member> members = memberRepository.findAll();
        modelView.getModel().put("members", members);
        return modelView;
    }
}
