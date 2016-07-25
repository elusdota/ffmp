package com.jrtech.ffmp.data.config;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import static org.junit.Assert.assertNotNull;

/**
 * Created by suelmer on 2016/6/29.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {com.jrtech.ffmp.data.test.config.Config.class})
public class ConfigTest {

    @PersistenceContext
    private EntityManager manager;

    @Test
    public void test() {
        assertNotNull(manager);
    }


}
