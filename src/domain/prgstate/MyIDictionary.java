package src.domain.prgstate;

import src.domain.exception.MyException;
import src.domain.type.Type;

import java.util.ArrayList;

public interface MyIDictionary<T,V> {
    V lookup(T id);

    Boolean isDefined(T id);
    void update(T id, V val) throws MyException;
    void add(T id, Type type) throws MyException;

    void add(T id, V val) throws MyException;

    ArrayList<T> getKeys();

    void remove(T key);
}
