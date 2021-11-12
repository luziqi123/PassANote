package com.monster.http;

import androidx.core.util.Pair;

import java.util.ArrayList;

public class ParamsOfKV {

    private ArrayList<Pair<String, String>> params;

    public ParamsOfKV() {
        params = new ArrayList<>();
    }

    public ParamsOfKV add(String key, String value) {
        params.add(new Pair<>(key, value));
        return this;
    }
}
