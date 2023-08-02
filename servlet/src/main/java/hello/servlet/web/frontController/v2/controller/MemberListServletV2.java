package hello.servlet.web.frontController.v2.controller;


import hello.servlet.domain.member.Member;
import hello.servlet.domain.member.MemberRepository;
import hello.servlet.web.frontController.v2.ControllerV2;
import hello.servlet.web.frontController.MyView;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

public class MemberListServletV2 implements ControllerV2 {

    private MemberRepository memberRepository = MemberRepository.getInstance();

    private final String viewPath;

    public MemberListServletV2(String viewPath) {
        this.viewPath = viewPath;
    }

    @Override
    public MyView process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Member> members = memberRepository.findAll();
        request.setAttribute("members", members);
        return new MyView(viewPath);
    }
}
