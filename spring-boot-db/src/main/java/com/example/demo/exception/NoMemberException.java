package com.example.demo.exception;

import java.util.NoSuchElementException;

public class NoMemberException extends NoSuchElementException {
    public NoMemberException() {
        super();
    }

    public NoMemberException(String s, Throwable cause) {
        super(s, cause);
    }

    public NoMemberException(Throwable cause) {
        super(cause);
    }

    public NoMemberException(String s) {
        super(s);
    }
}
