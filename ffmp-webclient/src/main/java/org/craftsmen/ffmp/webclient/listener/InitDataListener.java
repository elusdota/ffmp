package org.craftsmen.ffmp.webclient.listener;

import com.jrtech.templates.services.SystemInitializeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Service;

/**
 * Created by suelmer on 2016/7/1.
 */
@Service
public class InitDataListener  implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    private SystemInitializeService initService;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
//        initService.clearData();
//        initService.createData();
    }
}
