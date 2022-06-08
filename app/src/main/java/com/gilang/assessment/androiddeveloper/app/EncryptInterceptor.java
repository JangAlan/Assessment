package com.gilang.assessment.androiddeveloper.app;

import android.text.TextUtils;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okio.Buffer;


public class EncryptInterceptor implements Interceptor {

    private static final String TAG = EncryptInterceptor.class.getSimpleName();

    private static final boolean DEBUG = true;
    TripleDesEncDec tripleDes = new TripleDesEncDec();
    String strNewBody;

    @Override
    public Response intercept(Chain chain) throws IOException {

        Request request = chain.request();
        RequestBody oldBody = request.body();
        Buffer buffer = new Buffer();
        oldBody.writeTo(buffer);
        String strOldBody = buffer.readUtf8();
        Log.d("OldBody", strOldBody);
        MediaType mediaType = MediaType.parse("text/plain; charset=UTF-8");
        try {
            strNewBody = tripleDes.encryptText(strOldBody);
            Log.d("NewBody", strNewBody);
        }catch (Exception e){
            e.printStackTrace();
        }
        RequestBody body = RequestBody.create(mediaType, strNewBody);
        request = request.newBuilder()
                .header("Content-Type", body.contentType().toString())
                .header("Content-Length", String.valueOf(body.contentLength()))
                .method(request.method(), body).build();

        Response response = chain.proceed(request);
        if (response.isSuccessful()) {
            Response.Builder newResponse = response.newBuilder();
            String contentType = response.header("Content-Type");
            if (TextUtils.isEmpty(contentType)) contentType = "application/json";
            InputStream cryptedStream = response.body().byteStream();
            String crypted = convertStreamToString(cryptedStream);
            Log.d("Crypted", crypted);
            String decrypted = "";

            try{
                decrypted = tripleDes.decryptText(crypted);
                Log.d("Decrypted", decrypted);
            }catch (Exception e){
                e.printStackTrace();
            }
            newResponse.body(ResponseBody.create(MediaType.parse(contentType), decrypted));
            return newResponse.build();
        }
        return response;


    }

    private String convertStreamToString(InputStream is) {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
            StringBuilder sb = new StringBuilder();

            String line = null;
            try {
                while ((line = reader.readLine()) != null) {
                    sb.append(line);
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return sb.toString();
        }catch (Exception e){
            Log.e("Buffer Error", "Error converting result " + e.toString());
        }

        return null;
    }

}
