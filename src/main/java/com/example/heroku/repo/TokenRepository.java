package com.example.heroku.repo;

import com.example.heroku.model.TokenCookies;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TokenRepository extends JpaRepository<TokenCookies, Long> {
    TokenCookies findBySeries(String series);
    List<TokenCookies> findByUsername(String username);
}
