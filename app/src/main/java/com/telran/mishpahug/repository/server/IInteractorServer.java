package com.telran.mishpahug.repository.server;

import com.telran.mishpahug.model.Message;
import com.telran.mishpahug.model.ServerRequestType;

public interface IInteractorServer {
    void onServerError(ServerRequestType type, Message message);
    void onServerResponse(ServerRequestType type, Object object);
}
