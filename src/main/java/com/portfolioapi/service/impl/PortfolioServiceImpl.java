package com.portfolioapi.service.impl;

import com.portfolioapi.dto.EntityDtoMapper;
import com.portfolioapi.dto.PortfolioDto;
import com.portfolioapi.entity.Portfolio;
import com.portfolioapi.entity.User;
import com.portfolioapi.repository.PortfolioRepository;
import com.portfolioapi.repository.UserRepository;
import com.portfolioapi.service.PortfolioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PortfolioServiceImpl implements PortfolioService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PortfolioRepository portfolioRepository;

    @Override
    public Portfolio save(PortfolioDto portfolioDto) {
        User user = userRepository.findById(portfolioDto.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));
        Portfolio portfolio = EntityDtoMapper.toPortfolioEntity(portfolioDto, user);
        return portfolioRepository.save(portfolio);
    }

    @Override
    public List<Portfolio> getPortfoliosByUserId(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return portfolioRepository.findByUser(user);
    }

    @Override
    public List<Portfolio> getAll() {
        return portfolioRepository.findAll();
    }

    @Override
    public Portfolio update(Long id, PortfolioDto dto) {
        Portfolio existing = portfolioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Portfolio not found"));
        existing.setTitle(dto.getTitle());
        existing.setDescription(dto.getDescription());

        if (dto.getUserId() != null) {
            User user = userRepository.findById(dto.getUserId())
                    .orElseThrow(() -> new RuntimeException("User not found"));
            existing.setUser(user);
        }

        return portfolioRepository.save(existing);
    }

    @Override
    public void delete(Long id) {
        if (!portfolioRepository.existsById(id)) {
            throw new RuntimeException("Portfolio not found");
        }
        portfolioRepository.deleteById(id);
    }
}