package com.telran.mishpahug.repository.server;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.telran.mishpahug.interactor.interfaces.IRepositoryServer;
import com.telran.mishpahug.model.ServerRequestType;
import com.telran.mishpahug.model.Token;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class RepositoryServer implements IRepositoryServer{

    private IInteractorServer interactor;
    private AppCompatActivity activity;
    private final OkHttpClient client;
    //TEST
    private static final String BASE_URL = "https://telranstudentsproject.appspot.com/_ah/api/contactsApi/v1";

    private static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");

    public RepositoryServer(IInteractorServer interactor,AppCompatActivity activity){
        this.interactor = interactor;
        this.activity = activity;
        client = new OkHttpClient.Builder()
                .readTimeout(15, TimeUnit.SECONDS)
                .connectTimeout(15,TimeUnit.SECONDS)
                .build();
    }

    @Override
    public void request(final ServerRequestType type, Token token, Object object) {

        Request.Builder builder = new Request.Builder();
        StringBuilder url = new StringBuilder(BASE_URL);
        switch (type) {
            case LOGIN:
                break;
            case SAVE_PROFILE:
                break;
            case LOAD_PROFILE:
                break;
            case LOAD_EVENTS:
                break;
        }
        Request request = builder.build();
        new Task(client,request,type).execute();
    }
}

class Task extends AsyncTask {

    private Response response;
    private OkHttpClient client;
    private Request request;
    private ServerRequestType type;

    public Task(OkHttpClient client, Request request, ServerRequestType type) {
        this.client = client;
        this.request = request;
        this.type = type;
    }

    @Override
    protected Object doInBackground(Object[] objects) {
        try {
            response = client.newCall(request).execute();
        } catch (IOException e) {}
        return null;
    }

    @Override
    protected void onPostExecute(Object o) {
        switch (type){
            case LOGIN:
                break;
            case SAVE_PROFILE:
                break;
            case LOAD_PROFILE:
                break;
            case LOAD_EVENTS:
                break;
        }
    }
}

