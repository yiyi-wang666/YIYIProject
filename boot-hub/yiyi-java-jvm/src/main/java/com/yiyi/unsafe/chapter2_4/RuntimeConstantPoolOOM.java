package com.yiyi.unsafe.chapter2_4;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class RuntimeConstantPoolOOM {
    /*** VM Args：-XX:PermSize=6M -XX:MaxPermSize=6M * @author zzm */

        public static void main(String[] args) {
            // 使用Set保持着常量池引用，避免Full GC回收常量池行为
            Set<String> set = new HashSet<String>();
            // 在short范围内足以让6MB的PermSize产生OOM了
            short i = 0;
            while (true) { set.add(String.valueOf(i++).intern()); }
            /*List<RuntimeConstantPoolOOM> list = new ArrayList<RuntimeConstantPoolOOM>();
            while (true) { list.add(new RuntimeConstantPoolOOM()); }*/


        }


}
