package com.thangamfrm.usatodayapi.test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.testng.Assert;

public class BaseTest extends Assert {

    protected ApplicationContext applicationContext;

    protected void setUp() {
        applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
    }

}
