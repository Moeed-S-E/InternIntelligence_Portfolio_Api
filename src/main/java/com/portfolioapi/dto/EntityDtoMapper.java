package com.portfolioapi.dto;


import com.portfolioapi.entity.Portfolio;
import com.portfolioapi.entity.User;

public class EntityDtoMapper {
    // User -> UserDto
    public static UserDto toUserDto(User user) {
        if (user == null) return null;
        UserDto dto = new UserDto();
        dto.setId(user.getId());
        dto.setUsername(user.getUsername());
        dto.setEmail(user.getEmail());
        dto.setPassword(user.getPassword());
        return dto;
    }

    // UserDto -> User
    public static User toUserEntity(UserDto dto) {
        if (dto == null) return null;
        User user = new User();
        user.setId(dto.getId());
        user.setUsername(dto.getUsername());
        user.setEmail(dto.getEmail());
        user.setPassword(dto.getPassword());
        return user;
    }

    // Portfolio -> PortfolioDto
    public static PortfolioDto toPortfolioDto(Portfolio portfolio) {
        if (portfolio == null) return null;
        PortfolioDto dto = new PortfolioDto();
        dto.setId(portfolio.getId());
        dto.setTitle(portfolio.getTitle());
        dto.setDescription(portfolio.getDescription());
        dto.setUserId(portfolio.getUser() != null ? portfolio.getUser().getId() : null);
        return dto;
    }

    // PortfolioDto + User -> Portfolio
    public static Portfolio toPortfolioEntity(PortfolioDto dto, User user) {
        if (dto == null) return null;
        Portfolio portfolio = new Portfolio();
        portfolio.setId(dto.getId());
        portfolio.setTitle(dto.getTitle());
        portfolio.setDescription(dto.getDescription());
        portfolio.setUser(user);
        return portfolio;
    }
}
