package com.example.heroku.service;

import com.example.heroku.model.Point;

/**
 * Created by Keni0k on 25.07.2018.
 */

public interface MessageService extends BaseService<Point> {

    void editText(Long id, String text);

}