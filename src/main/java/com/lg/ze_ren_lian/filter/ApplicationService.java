package com.lg.ze_ren_lian.filter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author Xulg
 * Created in 2019-11-07 10:26
 */
@Component
public class ApplicationService {

    @Autowired
    private List<Filter> filters;

    public void mockedClient() {
        System.out.println("拦截器列表:");
        for (Filter filter : filters) {
            System.out.println(filter.toString());
        }

        // TODO: 前置操作

        // 过滤拦截操作
        // 这里task一般是通过数据库查询得到的
        Task task = new Task();
        for (Filter filter : filters) {
            if (!filter.filter(task)) {
                return;
            }
        }

        // 过滤完成，后续是执行任务的逻辑
        // TODO: 后续操作
    }
}