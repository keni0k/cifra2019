package com.example.heroku.service;

import com.example.heroku.model.Message;

import java.util.List;

/**
 * Created by Keni0k on 25.07.2018.
 */

public interface MessageService extends BaseService<Message> {

    void editText(Long id, String text);

}