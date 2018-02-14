package com.telran.mishpahug.interactor.interfaces;

import com.telran.mishpahug.model.ServerRequestType;

public interface IRepositoryServer {
    void request(ServerRequestType type, String token, Object object);
}
