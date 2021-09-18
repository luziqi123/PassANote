package com.longface.jhttp;

import android.util.Pair;

import java.util.ArrayList;

/**
 * 请求页面
 *
 * 有哪些情况 ??
 * 同一个App里需要访问多套服务器
 *
 * 什么会变 ??
 * 后台接口发生本质性变化 , 例如所有接口的参数类型由form-data变为raw-json
 * 数据结构 , 统一处理的地方需要变化
 *
 * 都有哪些元素 ??
 * 可分组
 * 名称
 * 请求方式
 * URL
 * UrlParams
 * Aut
 * Header
 * Body
 * Body-Type(4)
 * 返回数据
 *
 *
 *
 */
public interface IRequestPage {

    /**
     * 返回请求方法
     * @return 方法
     */
    Method getMethod();

    /**
     * 返回你的host
     * @return http://www.api.com/
     */
    String getHost();

    /**
     * 返回你的URL
     * @return login/api
     */
    String getUrl();

    /**
     * 获取Url的参数
     * @return
     */
    void setUrlParams(ArrayList<Pair<String , String>> urlParams);

    /**
     * 这个暂时还不知道怎么写
     * @return
     */
    Authorization getAuthorization();

    /**
     * 返回请求头
     * @return
     */
    ArrayList<Pair<String, String>> getHeaders();

}
