package com.example.heroku.repo;

import com.example.heroku.model.Check1;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CheckRepo extends JpaRepository<Check1, Long> {

    Check1 getCheckById(long id);

}