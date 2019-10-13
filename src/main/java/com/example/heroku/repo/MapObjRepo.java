package com.example.heroku.repo;

import com.example.heroku.model.MapObject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MapObjRepo extends JpaRepository<MapObject, Long> {
}
