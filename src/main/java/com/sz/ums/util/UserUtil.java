package com.sz.ums.util;

import com.sz.ums.domain.User;

public class UserUtil {
    static ThreadLocal<User> userLocal=new InheritableThreadLocal<>();
    public static void setUserLocal(User user){
        userLocal.set(user);
    }
    public static User getUserLocal(){
        return userLocal.get();
    }
    public static void removeUserLocal(){
        userLocal.remove();
    }
}
