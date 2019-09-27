package com.example.heroku.repo;

import com.example.heroku.model.Point;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PointRepo extends JpaRepository<Point, Long> {

    Point getPointById(long id);

}
