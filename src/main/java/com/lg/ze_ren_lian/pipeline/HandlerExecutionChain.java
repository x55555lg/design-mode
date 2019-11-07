package com.lg.ze_ren_lian.pipeline;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Xulg
 * Created in 2019-11-07 14:25
 */
@SuppressWarnings({"WeakerAccess", "FieldCanBeLocal"})
public class HandlerExecutionChain<RESULT> {

    private final Handler<RESULT> handler;

    private List<HandlerInterceptor> interceptorList;

    /**
     * 用于记录执行到哪个拦截器
     */
    private int interceptorIndex = -1;

    public HandlerExecutionChain(Handler<RESULT> handler, HandlerInterceptor... interceptors) {
        this(handler, new ArrayList<>(Arrays.asList(interceptors)));
    }

    public HandlerExecutionChain(Handler<RESULT> handler, List<HandlerInterceptor> interceptorList) {
        this.handler = handler;
        this.interceptorList = interceptorList;
    }

    public void addInterceptor(HandlerInterceptor interceptor) {
        interceptorList.add(interceptor);
    }

    boolean applyPreHandle(Request request) {
        for (int i = 0; i < interceptorList.size(); i++) {
            HandlerInterceptor interceptor = interceptorList.get(i);
            if (!interceptor.preHandle(request)) {
                triggerAfterCompletion(request);
                return false;
            }
            // 记录执行到哪一个拦截器(-1,0,1,2,3,4,5......)
            this.interceptorIndex = i;
        }
        return true;
    }

    void applyPostHandle(Request request) {
        for (int i = interceptorList.size() - 1; i >= 0; i--) {
            HandlerInterceptor interceptor = interceptorList.get(i);
            interceptor.postHandle(request);
        }
    }

    void triggerAfterCompletion(Request request) {
        // 根据applyPreHandle倒叙执行
        for (int i = this.interceptorIndex; i >= 0; i--) {
            HandlerInterceptor interceptor = interceptorList.get(i);
            try {
                interceptor.afterCompletion(request);
            } catch (Throwable t) {
                t.printStackTrace();
            }
        }
    }

    public RESULT execute(Request request) {
        //  前置
        if (!applyPreHandle(request)) {
            return null;
        }
        try {
            RESULT result = this.handler.handler(request);
            // 中间
            applyPostHandle(request);
            return result;
        } finally {
            // 后置
            triggerAfterCompletion(request);
        }
    }
}