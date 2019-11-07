package com.lg.ze_ren_lian.pipeline;

/**
 * 拦截器
 *
 * @author Xulg
 * Created in 2019-11-07 14:26
 */
public interface HandlerInterceptor {

    /**
     * 拦截器前置处理
     *
     * @param request the request
     * @return 是否放行
     */
    default boolean preHandle(Request request) {
        return true;
    }

    /**
     * 中间处理
     *
     * @param request the request
     */
    default void postHandle(Request request) {
    }

    /**
     * 后置处理
     *
     * @param request the request
     */
    default void afterCompletion(Request request) {
    }

}