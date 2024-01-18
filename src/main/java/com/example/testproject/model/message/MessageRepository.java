package com.example.testproject.model.message;

import com.example.testproject.model.message.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MessageRepository extends JpaRepository<Message, Integer> {
    @Query(value = "SELECT * FROM postgres.public.messages WHERE channel_id=?1",nativeQuery = true)
    public List<Message> findMessagesInChannel(long channel_id);
    @Query(value = "SELECT COUNT(*) FROM postgres.public.messages WHERE channel_id=?1",nativeQuery = true)
    public Integer countMessagesByChannel(long channel_id);
}
