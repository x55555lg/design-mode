package com.lg.ze_ren_lian.pipeline;

/**
 * @author Xulg
 * Created in 2019-11-07 13:43
 */
public interface Handler<RESULT> {

    RESULT handler(Request request);

}