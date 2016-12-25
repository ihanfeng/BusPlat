package com.github.q120011676.spring.j2cache.test;

import com.github.q120011676.spring.j2cache.server.TUtil;
import com.github.q120011676.spring.j2cache.server.TestService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by say on 3/21/16.
 */
@RunWith(value = SpringJUnit4ClassRunner.class)
@ContextConfiguration("/applicationContext.xml")
public class J2CacheSpringCTest {

    @Autowired
    private TestService ts;

    @Autowired
    private TUtil t;

    @Test
    public void testInject() {
        Assert.assertNotNull(ts);
    }

    @Test
    public void testGetAccountByName() throws Exception {
        String n = "199";
        System.out.println(this.t.getN(n));
        this.ts.setC(n);
        Assert.assertEquals(n, this.t.getN(n));
        Assert.assertEquals(n, this.ts.getC());
        System.out.println(this.t.getN(n));
        System.out.println(this.ts.getC());
    }
}
