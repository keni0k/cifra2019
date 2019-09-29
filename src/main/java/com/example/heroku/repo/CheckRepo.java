package com.example.heroku.repo;

import com.example.heroku.model.Check;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CheckRepo extends JpaRepository<Check, Long> {

    Check getCheckById(long id);

}