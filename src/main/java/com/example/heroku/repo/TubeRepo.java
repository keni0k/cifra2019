package com.example.heroku.repo;

import com.example.heroku.model.Tube;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TubeRepo extends JpaRepository<Tube, Long> {

    Tube getTubeById(long id);

}