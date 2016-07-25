package com.jrtech.ffmp.data.repositories;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertNotNull;

/**
 * Created by suelmer on 2016/6/30.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {com.jrtech.ffmp.data.test.config.Config.class})
public class AccountRepositoryTest {

    @Autowired
    private AccountRepository accountRepository;
    @Test
    public void test(){
        assertNotNull(accountRepository.findOneByName("aa"));
    }

}
