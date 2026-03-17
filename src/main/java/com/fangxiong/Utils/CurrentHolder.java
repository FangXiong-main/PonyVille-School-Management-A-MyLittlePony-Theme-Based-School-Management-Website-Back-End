package com.fangxiong.Utils;

public class CurrentHolder {
    //ThreadLocal线程Thread的局部变量，每个线程都有自己的一个CURRENT_LOCAL变量
    //CURRENT_LOCAL 是一个静态的 ThreadLocal 实例，它在类加载时被初始化。
    private static final ThreadLocal<Integer> CURRENT_LOCAL = new ThreadLocal<>();

    public static void setCurrentId(Integer employeeId) {
        CURRENT_LOCAL.set(employeeId);
    }

    public static Integer getCurrentId() {
        return CURRENT_LOCAL.get();
    }

    public static void remove() {
        CURRENT_LOCAL.remove();
    }
}
