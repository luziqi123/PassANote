package com.monster.http;

class Builder {

    private Config config;

    Config getConfig() {
        return config;
    }

    void setConfig(Config config) {
        this.config = config;
    }

    private Builder() {
    }

    private volatile static Builder instance;

    static Builder getInstance() {
        if (instance != null) {
            return instance;
        } else {
            synchronized (Builder.class) {
                if (instance == null) {
                    instance = new Builder();
                }
            }
        }
        return instance;
    }


    /**
     * final Dispatcher dispatcher;  //重要：分发器,分发执行和关闭由request构成的Call
     * final Proxy proxy;  //代理
     * final List<Protocol> protocols; //协议
     * final List<ConnectionSpec> connectionSpecs; //传输层版本和连接协议
     * final List<Interceptor> interceptors; //重要：拦截器
     * final List<Interceptor> networkInterceptors; //网络拦截器
     * final ProxySelector proxySelector; //代理选择
     * final CookieJar cookieJar; //cookie
     * final Cache cache; //缓存
     * final InternalCache internalCache;  //内部缓存
     * final SocketFactory socketFactory;  //socket 工厂
     * final SSLSocketFactory sslSocketFactory; //安全套接层socket 工厂，用于HTTPS
     * final CertificateChainCleaner certificateChainCleaner; // 验证确认响应证书 适用 HTTPS 请求连接的主机名。
     * final HostnameVerifier hostnameVerifier;    //  主机名字确认
     * final CertificatePinner certificatePinner;  //  证书链
     * final Authenticator proxyAuthenticator;     //代理身份验证
     * final Authenticator authenticator;      // 本地身份验证
     * final ConnectionPool connectionPool;    //连接池,复用连接
     * final Dns dns;  //域名
     * final boolean followSslRedirects;  //安全套接层重定向
     * final boolean followRedirects;  //本地重定向
     * final boolean retryOnConnectionFailure; //重试连接失败
     * final int connectTimeout;    //连接超时
     * final int readTimeout; //read 超时
     * final int writeTimeout; //write 超时
     */

}
