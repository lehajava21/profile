package com.telran.mishpahug.repository.storage;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;

import com.telran.mishpahug.interactor.interfaces.IRepositoryStorage;
import com.telran.mishpahug.model.Token;

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
    public void saveToken(Token token) {
        SharedPreferences.Editor editor = sPref.edit();
        editor.putString("TOKEN",token.getToken());
        editor.commit();
    }

    @Override
    public Token loadToken() {
        String token = sPref.getString("TOKEN","");
        if(token.isEmpty()){
            return null;
        }
        return new Token(token);
    }
}
