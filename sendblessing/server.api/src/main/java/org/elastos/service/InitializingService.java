package org.elastos.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InitializingService implements InitializingBean {

    private static Logger logger = LoggerFactory.getLogger(InitializingService.class);

    @Autowired
    StarService starService;

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("------------In PlatformInitialization----------------");
        starService.initService();
        System.out.println("------------Out PlatformInitialization----------------");
    }

}
