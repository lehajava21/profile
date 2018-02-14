package com.telran.mishpahug.interactor.interfaces;

import com.telran.mishpahug.model.Profile;

public interface IRepositoryStorage {
    void saveProfile(Profile profile);
    Profile loadProfile();
}
