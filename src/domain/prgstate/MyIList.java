package src.domain.prgstate;

import src.domain.value.Value;

import java.util.ArrayList;

public interface MyIList<T>{
    void add(T v);

    ArrayList<T> getValues();
}
