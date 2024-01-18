package com.example.testproject.model.channel;

import com.example.testproject.model.login.LoginUser;
import com.example.testproject.model.user.User;
import com.example.testproject.model.user.UserService;
import com.example.testproject.model.userchannel.UserChannel;
import com.example.testproject.model.userchannel.UserChannelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.EnableMBeanExport;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/channels")
public class ChannelController {
    @Autowired
    private ChannelService channelService;
    @Autowired
    private UserService userService;
    @Autowired
    private UserChannelService userChannelService;

    @GetMapping("/all")
    public List<Channel> getChannels(){
        return channelService.getAllChannels();
    }

    @PostMapping("/add")
    public boolean addChanenl(@RequestBody ChannelRequest channelRequest){

        Channel channel= new Channel();
        String name = channelRequest.getName();
        String login = channelRequest.getLogin();
        String description = channelRequest.getDescription();
        channel.setName(name);
        channel.setDescription(description);
        Channel channelByName = channelService.getChannelByName(name);

        if (channelByName!=null) {
            return false;
        }
        else {
            channelService.save(channel);
            Channel channelByNameAdded = channelService.getChannelByName(name);
            long id_channel = channelByNameAdded.getChannel_id();
            User user = userService.getUsersByEmail(login);
            long id_user = user.getUser_id();
            UserChannel userChannel = new UserChannel();
            userChannel.setChannel_id(id_channel);
            userChannel.setUser_id(id_user);
            userChannelService.save(userChannel);
            return true;
        }
    }
    @PostMapping("/enter")
    public boolean enterChanenl(@RequestBody ChannelRequest channelRequest){

        Channel channel = new Channel();
        String name=channelRequest.getName();
        String login = channelRequest.getLogin();
        channel.setName(name);
        Channel channelByName = channelService.getChannelByName(name);

        if (channelByName!=null) {
            long id_channel = channelByName.getChannel_id();
            User user = userService.getUsersByEmail(login);
            long id_user = user.getUser_id();
            UserChannel userChannel = new UserChannel();
            userChannel.setChannel_id(id_channel);
            userChannel.setUser_id(id_user);
            userChannelService.save(userChannel);
            return true;

        }
        else {

            return false;
        }
    }
    @PostMapping("/user_channels")
    public List<Channel> getUserChannels(@RequestBody User user) {
        String login = user.getEmail();
        User userByLogin = userService.getUsersByEmail(login);
        long user_id = userByLogin.getUser_id();
        List<Long> channel_id = userChannelService.getChannelsOfUser(user_id);
        return channelService.getChannelsById(channel_id);
    }

}

