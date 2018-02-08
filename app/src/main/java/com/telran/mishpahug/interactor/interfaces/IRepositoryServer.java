package com.telran.mishpahug.interactor.interfaces;

import com.telran.mishpahug.model.ServerRequestType;
import com.telran.mishpahug.model.Token;

public interface IRepositoryServer {
    void request(ServerRequestType type, Token token, Object object);
}
