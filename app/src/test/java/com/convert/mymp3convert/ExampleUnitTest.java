package com.convert.mymp3convert;

import org.junit.Test;

import java.util.concurrent.ConcurrentHashMap;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }
    @Test
    public void test(){
        ConcurrentHashMap map = new ConcurrentHashMap<String,Integer>();
        map.put("m1",1);
        map.put("m2",2);
        map.put("m3",3);
        map.put("m4",4);
        map.put("m5",5);
        map.put("m6",6);
        System.out.println(map);
        map.remove("m1");
        System.out.println(map);
        System.out.println(map.contains(2));
        System.out.println(map.containsValue(2));
        System.out.println(map.containsKey("m2"));
        System.out.println(map.contains(1));
        System.out.println(map.containsValue(1));
        System.out.println(map.containsKey("m1"));
        System.out.println(map.get("m1"));
        map.put("m3",100);
        System.out.println(map.get("m3"));
        System.out.println(map);
    }
}