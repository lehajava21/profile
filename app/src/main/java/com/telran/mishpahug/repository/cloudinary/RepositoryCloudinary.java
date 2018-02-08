package com.telran.mishpahug.repository.cloudinary;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.cloudinary.android.MediaManager;
import com.cloudinary.android.callback.ErrorInfo;
import com.cloudinary.android.callback.UploadCallback;
import com.telran.mishpahug.interactor.interfaces.IRepositoryCloudinary;
import com.telran.mishpahug.model.Message;

import java.util.HashMap;
import java.util.Map;

public class RepositoryCloudinary implements IRepositoryCloudinary {

    private IInteractorCloudinary interactor;
    private AppCompatActivity activity;

    private static final String cloudName = "dtju3pcuv";
    private static final String apiKey    = "845596556559612";
    private static final String apiSecret = "zRKR5-tr_Zl1WkDr9QBq9bCoYaM";

    public RepositoryCloudinary(IInteractorCloudinary interactor, AppCompatActivity activity) {
        this.interactor = interactor;
        this.activity = activity;
        Map config = new HashMap();
        config.put("cloud_name",cloudName);
        config.put("api_key",apiKey);
        config.put("api_secret",apiSecret);
        MediaManager.init(activity,config);
    }

    @Override
    public void saveImage(Uri uri) {
        MediaManager.get()
                .upload(uri)
                .option("overwrite", true)
                .callback(new UploadCallback() {
            @Override
            public void onStart(String requestId) {
            }
            @Override
            public void onProgress(String requestId, long bytes, long totalBytes) {
                Double progress = (double) bytes/totalBytes;
            }
            @Override
            public void onSuccess(String requestId, Map resultData) {
                String url = (String) resultData.get("url");
                interactor.onClaudinaryUrl(url);
            }
            @Override
            public void onError(String requestId, ErrorInfo error) {
                Message message= new Message("Error:",error.getDescription());
                interactor.onCloudinaryError(message);
            }
            @Override
            public void onReschedule(String requestId, ErrorInfo error) {
            }}).dispatch();
    }
}
