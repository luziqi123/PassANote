package com.monster.http;

import androidx.core.util.Pair;

import java.util.ArrayList;

public class ParamsOfBody {

    private ArrayList<Pair<String, Object>> params;

    private String jsonBody;

    public ParamsOfBody(Object body) {
        jsonBody = Builder.getInstance().getConfig().getGson().toJson(body);
    }

    public ParamsOfBody() {
        params = new ArrayList<>();
    }

    public ParamsOfBody add(String key, String value) {
        params.add(new Pair<>(key, value));
        return this;
    }

}
