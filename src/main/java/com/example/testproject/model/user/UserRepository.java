package com.example.testproject.model.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    @Query(value = "SELECT * FROM postgres.public.users WHERE email=?1",nativeQuery = true)
    public User findByEmail(String loginEmail);
    @Query(value = "SELECT * FROM postgres.public.users WHERE id=?1",nativeQuery = true)
    public User findUserById(long id);
    @Query(value = "SELECT * FROM postgres.public.users WHERE id IN ?1",nativeQuery = true)
    public List<User> findUsersByIdList(List<Long> user_id);
}
