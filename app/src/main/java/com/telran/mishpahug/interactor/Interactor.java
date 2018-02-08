package com.telran.mishpahug.interactor;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.telran.mishpahug.interactor.interfaces.IPresenterInteractorLoginr;
import com.telran.mishpahug.interactor.interfaces.IPresenterInteractorProfile;
import com.telran.mishpahug.interactor.interfaces.IRepositoryCloudinary;
import com.telran.mishpahug.interactor.interfaces.IRepositoryServer;
import com.telran.mishpahug.interactor.interfaces.IRepositoryStorage;
import com.telran.mishpahug.model.Message;
import com.telran.mishpahug.model.Profile;
import com.telran.mishpahug.model.ServerLoginResponse;
import com.telran.mishpahug.model.ServerRequestType;
import com.telran.mishpahug.model.TestToken;
import com.telran.mishpahug.model.Token;
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

    private Token token;

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
        Token token = repositoryStorage.loadToken();
        if(token == null){
            presenterLogin.show();
        }else {
            this.token = token;
            repositoryServer.request(ServerRequestType.LOAD_EVENTS,token,null);
        }
    }

    @Override
    public void onFbToken(Token token) {
        repositoryServer.request(ServerRequestType.LOGIN,token,null);
    }

    @Override
    public void onServerError(ServerRequestType type, Message message) {
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

    @Override
    public void onServerResponse(ServerRequestType type, Object object) {
        switch (type){
            case LOGIN:
                ServerLoginResponse serverLoginResponse = (ServerLoginResponse) object;
                Token token = new Token(serverLoginResponse.getToken());
                repositoryStorage.saveToken(token);
                presenterProfile.show(null);
                break;
            case SAVE_PROFILE:
                repositoryServer.request(ServerRequestType.LOAD_EVENTS,this.token,null);
                break;
            case LOAD_PROFILE:
                break;
            case LOAD_EVENTS:
                break;
        }
    }

    @Override
    public void savePhoto(Uri uri) {
        repositoryCloudinary.saveImage(uri);
    }

    @Override
    public void saveProfile(Profile profile) {
        repositoryServer.request(ServerRequestType.SAVE_PROFILE,this.token,profile);
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
