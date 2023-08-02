package hello.servlet.web.frontController.v3.controller;

import hello.servlet.domain.member.Member;
import hello.servlet.domain.member.MemberRepository;
import hello.servlet.web.frontController.v3.ControllerV3;
import hello.servlet.web.frontController.MyModelView;
import jakarta.servlet.ServletException;

import java.io.IOException;
import java.util.Map;

public class MemberSaveServletV3 implements ControllerV3 {

    private MemberRepository memberRepository = MemberRepository.getInstance();

    public MemberSaveServletV3() {
    }

    @Override
    public MyModelView process(Map<String,String> paramMap) throws ServletException, IOException {
        MyModelView modelView = new MyModelView("save-result");
        String username = paramMap.get("username");
        int age = Integer.parseInt(paramMap.get("age"));
        Member member = new Member(username, age);
        Member savedMember = memberRepository.save(member);
        modelView.getModel().put("member", savedMember);
        return modelView;
    }
}
