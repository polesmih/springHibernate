package com.example.hibernate.repository;


import com.example.hibernate.domain.ShopUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ShopUserRepository extends JpaRepository<ShopUser, Long> {

    Optional<ShopUser> findShopUserByLogin(String login);
}
