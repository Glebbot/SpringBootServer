package com.example.testproject.model.userchannel;

import com.example.testproject.model.channel.Channel;
import com.example.testproject.model.channel.ChannelRepository;
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
public class UserChannelService {
    @Autowired
    private UserChannelRepository repository;

    public void save(UserChannel userChannel) {
        repository.save(userChannel);
    }
    public List<UserChannel> getAllConnections() {
        List<UserChannel> channels =new ArrayList<>();
        Streamable.of(repository.findAll())
                .forEach(channels::add);
        return channels;
    }
    public List<Long> getChannelsOfUser(long user_id) {
        List<Long> channels = repository.findChannelsOfUser(user_id);

        return channels;
    }
    public List<Long> getUsersInChannel(long channel_id) {
        List<Long> users = repository.findUsersInChannel(channel_id);

        return users;
    }
    public void delete(UserChannel userChannel) {
        repository.delete(userChannel);
    }



}
