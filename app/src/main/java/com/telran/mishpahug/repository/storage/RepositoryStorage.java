package com.telran.mishpahug.repository.storage;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;

import com.google.gson.Gson;
import com.telran.mishpahug.interactor.interfaces.IRepositoryStorage;
import com.telran.mishpahug.model.Profile;

import static android.content.Context.MODE_PRIVATE;

public class RepositoryStorage implements IRepositoryStorage{

    private final static String STORAGE = "STORAGE";
    private SharedPreferences sPref;
    private IInteractorStorage interactor;
    private AppCompatActivity activity;

    public RepositoryStorage(IInteractorStorage interactor,AppCompatActivity activity){
        this.interactor = interactor;
        this.activity = activity;
        sPref = activity.getSharedPreferences(STORAGE,MODE_PRIVATE);
    }

    @Override
    public void saveProfile(Profile profile) {
        if(profile == null){
            return;
        }
        Gson gson = new Gson();
        String content = gson.toJson(profile);
        SharedPreferences.Editor editor = sPref.edit();
        editor.putString("PROFILE",content);
        editor.commit();
    }

    @Override
    public Profile loadProfile() {
        String content = sPref.getString("PROFILE","");
        Gson gson = new Gson();
        Profile profile = gson.fromJson(content,Profile.class);
        return profile;
    }
}
