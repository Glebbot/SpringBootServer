package com.example.testproject.model.channel;

import com.example.testproject.model.user.User;
import com.example.testproject.model.user.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Streamable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class ChannelService {
    @Autowired
    private ChannelRepository repository;

    public void save(Channel channel) {
        repository.save(channel);
    }
    public List<Channel> getAllChannels() {
        List<Channel> channels =new ArrayList<>();
        Streamable.of(repository.findAll())
                .forEach(channels::add);
        return channels;
    }
    public List<Channel> getChannelsById(List<Long> id) {
        return repository.findChannelsById(id);
    }
    public void delete(Channel channel) {
        repository.delete(channel);
    }

    public Channel getChannelByName(String name) {
        return repository.findByName(name);
    }

}

