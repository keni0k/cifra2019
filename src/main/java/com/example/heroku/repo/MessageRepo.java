package com.example.heroku.repo;

import com.example.heroku.model.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MessageRepo extends JpaRepository<Message, Long> {

    Message getMessageById(long id);

}
