package com.example.demo.test;


import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners({com.example.demo.test.AssertionListener.class})
public class TestngTest {
    @Test
    public void test(){
        Assertion.verifyEquals("1","2","比较两个值是否相等");
        Assertion.verifyEquals("3","3","比较两个值是否相等");
    }
    @Test
    public void test1(){
        Assertion.verifyEquals("4","2","比较两个值是否相等");
        Assertion.verifyEquals("1","8","比较两个值是否相等");
    }

}
