package com.lg.ze_ren_lian.filter;

import com.lg.ze_ren_lian.Task;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Random;

/**
 * 风控拦截过滤器
 *
 * @author Xulg
 * Created in 2019-11-07 10:16
 */
@Order(1)
@Component
public class RiskFilter implements Filter {

    @Override
    public boolean filter(Task task) {
        boolean randomBoolean = new Random().nextBoolean();
        if (randomBoolean) {
            System.out.println("我是风控拦截过滤器...............放行啦");
            return true;
        } else {
            System.err.println("我是风控拦截过滤器...............拦截@@");
            return false;
        }
    }

}