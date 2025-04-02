package com.portfolioapi.service;


import com.portfolioapi.dto.PortfolioDto;
import com.portfolioapi.entity.Portfolio;

import java.util.List;


public interface PortfolioService {

    Portfolio save(PortfolioDto portfolioDto);

    List<Portfolio> getPortfoliosByUserId(Long userId);

    List<Portfolio> getAll();

    Portfolio update(Long id, PortfolioDto dto);

    void delete(Long id);
}
