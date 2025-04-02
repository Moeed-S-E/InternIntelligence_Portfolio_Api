package com.portfolioapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.portfolioapi.entity.Portfolio;
import com.portfolioapi.entity.User;

import java.util.List;

public interface PortfolioRepository extends JpaRepository <Portfolio, Long>{

    List<Portfolio> findByUser(User user);
}
