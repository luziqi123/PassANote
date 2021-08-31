package com.longface.jhttp;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import com.longface.simpleutils.Logger;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.nio.charset.Charset;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import okhttp3.logging.HttpLoggingInterceptor;
import okio.Buffer;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Converter;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class MainTest {

    public static void main(String[] args) {

        /**
         * 可以切换baseUrl
         *
         * 可以缓存发送失败的请求 , 手动发起重新发送
         *
         * 打印LOG
         *
         * 提高Gson容错性和性能
         *
         */
//        JHttp.init(application);
//
//        JHttp.baseUrl("http://152.3.2.1/");
//
//        JHttp.addHeader("", "");
//
//        LoginApi api = JHttp.getApi(LoginApi.class);
//        api.login(bean).enqueue(new Callback());
//
//        new ChainCall()
//                .addCall(new Call<Void>() {
//                    void run() {
//                        api.login(bean);
//                    }
//                }).addCall(new Call<LoginResult> {
//                    void run (LoginResult result) {
//                        api.regist(result.getID);
//                    }
//                });


        send();

    }

    public static void send() {
        String input = "https://gank.io/api/";
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
//        builder.addInterceptor(new LogInterceptor());
        builder.addInterceptor(new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(String message) {
                if (message.contains("<-- END")) {
                    Logger.out("Tag", message);
                } else {
                    Logger.put("Tag" , message);
                }
            }
        }).setLevel(HttpLoggingInterceptor.Level.BODY));
        OkHttpClient httpClient = builder.build();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(input)
                .client(httpClient)
//                .addConverterFactory(new MyConverterFactory(new Gson()))
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .addConverterFactory(ScalarsConverterFactory.create())
                .build();
        Api api = retrofit.create(Api.class);
        Call<String> stringCall = api.getGirls("GanHuo" , "Android" ,"10");
        stringCall.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
//                System.out.println("onResponse = " + response.body());
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                System.out.println("onFailure = " + t.getMessage());
            }
        });
    }

    public static int[] merge(int[] A, int[] B) {
        // 如果长度相等
        if (A.length == B.length) {
            // 对比是否完全一致
            boolean isAgreement = true;
            for (int i = 0; i < A.length; i++) {
                if (A[i] != B[i]) {
                    isAgreement = false;
                    break;
                }
            }
            // 如果完全一致 , 直接返回
            if (isAgreement) {
                return A;
            }
        }

        int aPoint = 0, bPoint = 0;
        int length = Math.max(A.length, B.length);
        int pointer = 0;
//        int[] C = new int[];
        for (int i = 0; i < length; i++) {

        }
        return null;
    }

    public static class MyClassLoader extends ClassLoader {

        public MyClassLoader(ClassLoader parent) {
            super(null);
        }

        @Override
        protected Class<?> loadClass(String name, boolean resolve) throws ClassNotFoundException {
            return MainTest.class;
        }

        @Override
        protected Class<?> findClass(String name) throws ClassNotFoundException {
            return MainTest.class;
        }

    }


    public static String nearestPalindromic(String n) {
        if (n == null) {
            return null;
        }
        if (n.length() == 1) {
            // 处理个位数
            if (n.equals("0")) {
                return "2";
            }
            return String.valueOf((char) n.toCharArray()[0] - 1);
        }
        // 处理
        int length = n.length();
        int mid = length >> 1;
        length -= 1;
        for (int i = 0; i <= mid; i++) {
            n = n.replace(n.charAt(length - i), n.charAt(i));
            System.out.println(n);
        }
        return n;
    }


    public static class MyConverterFactory extends Converter.Factory {

        private final Gson gson;

        public MyConverterFactory(Gson gson) {
            if (gson == null) throw new NullPointerException("gson == null");
            this.gson = gson;
        }

        @Override
        public Converter<ResponseBody, ?> responseBodyConverter(Type type, Annotation[] annotations,
                                                                Retrofit retrofit) {
            TypeAdapter<?> adapter = gson.getAdapter(TypeToken.get(type));
            System.out.println("MyConverterFactory = " + "responseBodyConverter");
            return new GsonResponseBodyConverter<>(gson, adapter);
        }

        @Override
        public Converter<?, RequestBody> requestBodyConverter(Type type,
                                                              Annotation[] parameterAnnotations, Annotation[] methodAnnotations, Retrofit retrofit) {
            TypeAdapter<?> adapter = gson.getAdapter(TypeToken.get(type));
            System.out.println("MyConverterFactory = " + "requestBodyConverter");
            return new GsonRequestBodyConverter<>(gson, adapter);
        }

        @Override
        public Converter<?, String> stringConverter(Type type, Annotation[] annotations, Retrofit retrofit) {
            System.out.println("stringConverter");
            return super.stringConverter(type, annotations, retrofit);
        }

    }

    public static class GsonResponseBodyConverter<T> implements Converter<ResponseBody, T> {
        private final Gson gson;
        private final TypeAdapter<T> adapter;

        GsonResponseBodyConverter(Gson gson, TypeAdapter<T> adapter) {
            this.gson = gson;
            this.adapter = adapter;
        }

        @Override
        public T convert(ResponseBody value) throws IOException {
            System.out.println("GsonResponseBodyConverter = " + "convert");
            JsonReader jsonReader = gson.newJsonReader(value.charStream());
            try {
                return adapter.read(jsonReader);
            } finally {
                value.close();
            }
        }
    }

    public static class GsonRequestBodyConverter<T> implements Converter<T, RequestBody> {
        private static final MediaType MEDIA_TYPE = MediaType.parse("application/json; charset=UTF-8");
        private static final Charset UTF_8 = Charset.forName("UTF-8");

        private final Gson gson;
        private final TypeAdapter<T> adapter;

        GsonRequestBodyConverter(Gson gson, TypeAdapter<T> adapter) {
            this.gson = gson;
            this.adapter = adapter;
        }

        @Override
        public RequestBody convert(T value) throws IOException {
            System.out.println("GsonRequestBodyConverter = " + "convert");
            Buffer buffer = new Buffer();
            Writer writer = new OutputStreamWriter(buffer.outputStream(), UTF_8);
            JsonWriter jsonWriter = gson.newJsonWriter(writer);
            adapter.write(jsonWriter, value);
            jsonWriter.close();
            return RequestBody.create(MEDIA_TYPE, buffer.readByteString());
        }
    }
}
