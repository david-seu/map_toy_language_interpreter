package src.domain.prgstate;

import src.domain.exception.EmptyStackException;
import src.domain.stmt.IStmt;

import java.util.ArrayList;
import java.util.Stack;

public interface MyIStack<T> {
    void pop() throws EmptyStackException;
    void push(T v);

    T top() throws EmptyStackException;

    boolean isEmpty();

    Stack<T> getStack();
}
