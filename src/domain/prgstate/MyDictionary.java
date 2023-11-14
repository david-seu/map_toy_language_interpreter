package src.domain.prgstate;

import src.domain.exception.MyException;
import src.domain.type.Type;

import java.util.ArrayList;
import java.util.HashMap;

public class MyDictionary<T,V> implements MyIDictionary<T,V>{

    private final HashMap<T,V> dict = new HashMap<>();

    public MyDictionary() {

    }

    @Override
    public V lookup(T id) {
        for(T key:dict.keySet()){
            if(key.equals(id))
                return dict.get(key);
        }
        return null;
    }

    @Override
    public Boolean isDefined(T id) {
        for(T key:dict.keySet()){
            if(key.equals(id))
                return true;
        }
        return false;
    }

    @Override
    public void update(T id, V val) throws MyException {
        dict.put(id,val);
    }

    @Override
    public void add(T id, Type type) throws MyException {
        if(dict.get(id) == null)
            if(type != null)
            {
                //noinspection unchecked
                dict.put(id, (V)type.defaultValue());
            }
        else throw new MyException("Variable " + id + " already declared");
    }

    @Override
    public void add(T id, V val) throws MyException {
        for(T key:dict.keySet()){
            if(key.equals(id))
                throw new MyException("Variable " + id + " already declared");
        }
        dict.put(id,val);
    }

    @Override
    public void remove(T id) {
        for(T key:dict.keySet()){
            if(key.equals(id))
                dict.remove(key);
        }
    }

    public ArrayList<T> getKeys() {
        return new ArrayList<>(dict.keySet());
    }

    @Override
    public String toString() {
        if(dict.isEmpty())
            return "Empty dictionary";
        return dict.toString();
    }
}
