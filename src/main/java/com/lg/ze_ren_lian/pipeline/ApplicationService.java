package com.lg.ze_ren_lian.pipeline;

import org.springframework.stereotype.Component;

/**
 * @author Xulg
 * Created in 2019-11-07 10:26
 */
@Component
public class ApplicationService {

    public static void main(String[] args) {
        HandlerExecutionChain<String> chain = new HandlerExecutionChain<>(new OrderHandler(),
                new HandlerInterceptor1(), new HandlerInterceptor2());
        String result = chain.execute(new Request());
        System.out.println(result);
    }

    private static class HandlerInterceptor1 implements HandlerInterceptor {
        @Override
        public boolean preHandle(Request request) {
            System.out.println("HandlerInterceptor1在执行preHandle......");
            return true;
        }

        @Override
        public void postHandle(Request request) {
            System.out.println("HandlerInterceptor1在执行postHandle......");
        }

        @Override
        public void afterCompletion(Request request) {
            System.out.println("HandlerInterceptor1在执行afterCompletion......");
        }
    }

    private static class HandlerInterceptor2 implements HandlerInterceptor {
        @Override
        public boolean preHandle(Request request) {
            System.out.println("HandlerInterceptor2在执行preHandle......");
            return false;
        }

        @Override
        public void postHandle(Request request) {
            System.out.println("HandlerInterceptor2在执行postHandle......");
        }

        @Override
        public void afterCompletion(Request request) {
            System.out.println("HandlerInterceptor2在执行afterCompletion......");
        }
    }

    private static class OrderHandler implements Handler<String> {
        @Override
        public String handler(Request request) {
            return "处理订单咯======" + request;
        }
    }
}