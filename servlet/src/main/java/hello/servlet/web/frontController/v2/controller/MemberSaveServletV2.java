package hello.servlet.web.frontController.v2.controller;

import hello.servlet.domain.member.Member;
import hello.servlet.domain.member.MemberRepository;
import hello.servlet.web.frontController.v2.ControllerV2;
import hello.servlet.web.frontController.MyView;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class MemberSaveServletV2 implements ControllerV2 {

    private MemberRepository memberRepository = MemberRepository.getInstance();

    private final String viewPath;

    public MemberSaveServletV2(String viewPath) {
        this.viewPath = viewPath;
    }

    @Override
    public MyView process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        int age = Integer.parseInt(request.getParameter("age"));
        Member member = new Member(username, age);
        Member savedMember = memberRepository.save(member);
        request.setAttribute("member", savedMember);
        return new MyView(viewPath);
    }
}
