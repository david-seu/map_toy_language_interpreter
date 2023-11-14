package src.utils;

import src.domain.exception.EmptyStackException;
import src.domain.prgstate.MyIStack;
import src.domain.prgstate.MyStack;

public class AddressBuilder {
    private Integer address = 1;
    private static final MyIStack<Integer> freeAddress = new MyStack<>();

    public Integer getFreeAddress() throws EmptyStackException {
        if(freeAddress.isEmpty())
            return address++;
        Integer free = freeAddress.top();
        freeAddress.pop();
        return free;

    }
}
