package com.longface.jhttp;

/**
 * 请求记录回调接口
 */
public interface RequestRecorder {

    /**
     * 每当产生一次(请求-返回) , 都会调用该方法
     * 包含了请求信息和返回信息
     * 你需要自行保存
     */
    void onHasNewRequest();

    /**
     * 当你调用 JHttp.sendRecorder()方法时调用
     * 你可以将之前的所有记录发送至你的目标
     */
    void needSendRecorder();

}
