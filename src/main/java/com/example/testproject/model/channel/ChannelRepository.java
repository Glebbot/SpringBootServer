package com.example.testproject.model.channel;

import com.example.testproject.model.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChannelRepository extends JpaRepository<Channel, Integer> {
    @Query(value = "SELECT * FROM postgres.public.channels WHERE name=?1",nativeQuery = true)
    public Channel findByName(String name);
    @Query(value = "SELECT * FROM postgres.public.channels WHERE channel_id IN ?1",nativeQuery = true)
    public List<Channel> findChannelsById(List<Long> channel_id);

}
