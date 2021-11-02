package com.devsuperior.JpaRepository.repositories;

import com.devsuperior.JpaRepository.entities.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<User, Long> {

    // query written in JPQL, but it could've been SQL as well
    @Query("SELECT obj FROM User obj WHERE obj.salary >= :minSalary AND obj.salary <= :maxSalary")
    Page<User> searchSalary(Double minSalary, Double maxSalary, Pageable pageable);

    @Query("SELECT obj FROM User obj WHERE LOWER(obj.name) LIKE LOWER(CONCAT('%', :name, '%'))")
    Page<User> searchName(String name, Pageable pageable);

    /*
    Spring Data JPA has some pre defined query methods, so the above methods could be replaced by:

    Page<User> findBySalaryBetween(Double minSalary, Double maxSalary, Pageable pageable);
    Page<User> findByNameContainingIgnoreCase(String name, Pageable pageable);
     */
}
