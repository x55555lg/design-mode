package com.lg;

import com.lg.ze_ren_lian.filter.ApplicationService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class DesignModeApplicationTests {

    @Autowired
    private ApplicationService applicationService;

    @Test
    void contextLoads() {
        applicationService.mockedClient();
    }

}
