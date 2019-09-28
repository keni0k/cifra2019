package com.example.heroku.repo;

import com.example.heroku.model.Building;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BuildingRepo extends JpaRepository<Building, Long> {

    Building getBuildingById(long id);

}
