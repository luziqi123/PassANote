package com.longface.jhttp;

import android.util.Pair;

import java.util.ArrayList;

/**
 * 请求页面
 *
 * 同一个App里需要访问多套服务器
 *
 *
 * 分组
 * 名称
 *
 * 请求方式
 * URL
 * UrlParams
 * Aut
 * Header
 * Body
 * Body-Type(4)
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
