package main.common;

import main.dao.HashTab;

public class BaseContext {
    private static ThreadLocal<HashTab> threadLocal = new ThreadLocal<>();
    public static HashTab getCurrent(){
        return threadLocal.get();
    }
    public static void setCurrent(HashTab hashTab){
        threadLocal.set(hashTab);
    }
}
