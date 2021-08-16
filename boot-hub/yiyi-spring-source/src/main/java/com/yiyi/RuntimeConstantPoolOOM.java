package com.yiyi;

import java.util.HashSet;
import java.util.Set;

public class RuntimeConstantPoolOOM {

    public static void main(String[] args) {
        // 使用Set保持着常量池引用，避免Full GC回收常量池行为
        Set<String> set = new HashSet<String>();
        // 在short范围内足以让6MB的PermSize产生OOM了
        Long i = 0L;
        while (true) {
            System.out.println(i);
            set.add(String.valueOf(i++).intern());
        }
    }

}
