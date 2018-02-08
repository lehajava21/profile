package com.telran.mishpahug.repository.cloudinary;

import com.telran.mishpahug.model.Message;

public interface IInteractorCloudinary {
    void onClaudinaryUrl(String url);
    void onCloudinaryError(Message message);
}
