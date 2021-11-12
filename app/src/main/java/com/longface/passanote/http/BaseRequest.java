package com.longface.passanote.http;

import com.monster.http.AbsRequestPage;

public abstract class BaseRequest extends AbsRequestPage {

    public static String key = "tfuQ8MZe35bc8c53c9c8a374f6a9122a6a50b1511748a9";
    public static String baseUrl = "https://api.apishop.net/";

    @Override
    public final String getBaseUrl() {
        return baseUrl;
    }

}
