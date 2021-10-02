package com.yiyi;

import com.yiyi.user.User;

import java.util.ArrayList;
import java.util.List;

public class OOMTest {

    public static void main(String[] args) {
        List<User> list = new ArrayList<>();
        while(true){
            list.add(new User());
        }
    }
}
