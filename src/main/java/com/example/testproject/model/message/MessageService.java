package com.example.testproject.model.message;

import com.example.testproject.model.channel.Channel;
import com.example.testproject.model.channel.ChannelRepository;
import jakarta.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Streamable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class MessageService {
    @Autowired
    private MessageRepository repository;

    public void save(Message message) {

        repository.save(message);
    }
    public Integer getMessagesCount(long id) {
        return repository.countMessagesByChannel(id);
    }

    public List<Message> getMessagesInChannel(long id) {
        return repository.findMessagesInChannel(id);
    }



}


