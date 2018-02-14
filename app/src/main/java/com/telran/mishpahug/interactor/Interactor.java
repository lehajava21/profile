package com.telran.mishpahug.interactor;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.telran.mishpahug.interactor.interfaces.IPresenterInteractorLoginr;
import com.telran.mishpahug.interactor.interfaces.IPresenterInteractorProfile;
import com.telran.mishpahug.interactor.interfaces.IRepositoryCloudinary;
import com.telran.mishpahug.interactor.interfaces.IRepositoryServer;
import com.telran.mishpahug.interactor.interfaces.IRepositoryStorage;
import com.telran.mishpahug.model.Message;
import com.telran.mishpahug.model.Profile;
import com.telran.mishpahug.model.ServerRequestType;
import com.telran.mishpahug.presenter.login.IInteractorPresenterLogin;
import com.telran.mishpahug.presenter.profile.IInteractorPresenterProfile;
import com.telran.mishpahug.repository.cloudinary.IInteractorCloudinary;
import com.telran.mishpahug.repository.server.IInteractorServer;
import com.telran.mishpahug.repository.storage.IInteractorStorage;

public class Interactor implements IInteractorPresenterLogin,
                                    IInteractorPresenterProfile,
                                    IInteractorServer,
                                    IInteractorStorage,
                                    IInteractorCloudinary {

    private AppCompatActivity activity;
    private IRepositoryServer repositoryServer;
    private IRepositoryStorage repositoryStorage;
    private IRepositoryCloudinary repositoryCloudinary;
    private IPresenterInteractorLoginr presenterLogin;
    private IPresenterInteractorProfile presenterProfile;

    private String token;

    public Interactor(AppCompatActivity activity){
        this.activity = activity;
    }

    public void setRepositoryServer(IRepositoryServer repositoryServer) {
        this.repositoryServer = repositoryServer;
    }

    public void setRepositoryStorage(IRepositoryStorage repositoryStorage) {
        this.repositoryStorage = repositoryStorage;
    }

    public void setRepositoryCloudinary(IRepositoryCloudinary repositoryCloudinary) {
        this.repositoryCloudinary = repositoryCloudinary;
    }

    public void setPresenterLogin(IPresenterInteractorLoginr presenterLogin) {
        this.presenterLogin = presenterLogin;
    }

    public void setPresenterProfile(IPresenterInteractorProfile presenterProfile){
        this.presenterProfile = presenterProfile;
    }

    //START
    public void start(){
        Profile profile = repositoryStorage.loadProfile();
        if(profile == null || profile.getToken().isEmpty()){
            presenterLogin.show();
        }
    }

    @Override
    public void onFbToken(String token) {
        repositoryServer.request(ServerRequestType.LOGIN,token,null);
    }

    @Override
    public void onServerError(ServerRequestType type, Message message) {
        switch (type){
            case LOGIN:
                break;
            case PROFILE:
                break;
            case EVENTS:
                break;
        }
    }

    @Override
    public void onServerResponse(ServerRequestType type, Object object) {
        switch (type){
            case LOGIN:
                Profile profile = (Profile) object;
                token = profile.getToken();
                if(profile.getFullname() == null || profile.getFullname().isEmpty()){
                    profile = new Profile();
                    profile.setToken(token);
                    presenterProfile.show(profile);
                }else {
                    repositoryStorage.saveProfile(profile);
                    repositoryServer.request(ServerRequestType.EVENTS,token,null);
                }
                break;
        }
    }

    @Override
    public void savePhoto(Uri uri) {
        repositoryCloudinary.savePhoto(uri);
    }

    @Override
    public void saveProfile(Profile profile) {
        repositoryStorage.saveProfile(profile);
        repositoryServer.request(ServerRequestType.PROFILE,token,profile);
    }

    @Override
    public void onClaudinaryUrl(String url) {
        presenterProfile.onCloudinaryUrl(url);
    }

    @Override
    public void onCloudinaryError(Message message) {
        presenterProfile.onMessage(message);
    }
}
