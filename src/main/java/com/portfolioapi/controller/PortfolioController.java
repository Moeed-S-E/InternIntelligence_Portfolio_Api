package com.portfolioapi.controller;

import com.portfolioapi.dto.EntityDtoMapper;
import com.portfolioapi.dto.PortfolioDto;
import com.portfolioapi.entity.Portfolio;
import com.portfolioapi.service.PortfolioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/portfolios")
public class PortfolioController {
    @Autowired
    private PortfolioService portfolioService;

    @PostMapping
    public ResponseEntity<Portfolio> createPortfolio(@RequestBody PortfolioDto portfolioDto) {
        Portfolio saved = portfolioService.save(portfolioDto);
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<PortfolioDto>> getPortfoliosByUserId(@PathVariable Long userId) {
        List<Portfolio> portfolios = portfolioService.getPortfoliosByUserId(userId);
        List<PortfolioDto> dtoList = portfolios.stream()
                .map(EntityDtoMapper::toPortfolioDto)
                .toList();
        return ResponseEntity.ok(dtoList);
    }

    @GetMapping
    public ResponseEntity<List<PortfolioDto>> getAllPortfolios() {
        List<Portfolio> portfolios = portfolioService.getAll();
        List<PortfolioDto> dtoList = portfolios.stream()
                .map(EntityDtoMapper::toPortfolioDto)
                .toList();
        return ResponseEntity.ok(dtoList);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Portfolio> updatePortfolio(@PathVariable Long id, @RequestBody PortfolioDto dto) {
        Portfolio updated = portfolioService.update(id, dto);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePortfolio(@PathVariable Long id) {
        portfolioService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
