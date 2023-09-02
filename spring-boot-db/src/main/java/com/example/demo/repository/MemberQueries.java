package com.example.demo.repository;

public abstract class MemberQueries {
    public static String CreateMember = "insert into member(id, money) values(?, ?)";
    public static String FindMemberById = "select * from member where id = ?";
    public static String UpdateMemberById = "update member set money=? where id = ?";
    public static String DeleteMemberById = "delete from member where id = ?";
}
