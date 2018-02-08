package com.telran.mishpahug.interactor.interfaces;

import com.telran.mishpahug.model.Token;

public interface IRepositoryStorage {
    void saveToken(Token token);
    Token loadToken();
}
