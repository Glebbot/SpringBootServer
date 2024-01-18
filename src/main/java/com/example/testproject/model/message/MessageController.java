package com.example.testproject.model.message;

import com.example.testproject.model.channel.Channel;
import com.example.testproject.model.channel.ChannelRepository;
import com.example.testproject.model.channel.ChannelRequest;
import com.example.testproject.model.channel.ChannelService;
import com.example.testproject.model.user.User;
import com.example.testproject.model.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
@RequestMapping("/messages")
public class MessageController {
    @Autowired
    private MessageService messageService;
    @Autowired
    private ChannelService channelService;
    @Autowired
    private UserService userService;
    @PostMapping("/save")
    public boolean sendMessage(@RequestBody MessageRequest messageRequest) {
        User user = userService.getUsersByEmail(messageRequest.getSenderName());
        long user_id = user.getUser_id();
        Channel channel = channelService.getChannelByName(messageRequest.getChannelName());
        long channel_id = channel.getChannel_id();
        Message message = new Message();
        message.setSender_id(user_id);
        message.setChannel_id(channel_id);
        message.setMessage(messageRequest.getMessage());
        message.setDateTime(messageRequest.getDatetime());
        messageService.save(message);
        return true;
    }
    @PostMapping("/channel_messages")
    public List<MessageRequest> getMessagesChannel(@RequestBody ChannelRequest channelRequest) {
        Channel channelByName = channelService.getChannelByName(channelRequest.getName());
        long channel_id = channelByName.getChannel_id();
        List<Message> messages = messageService.getMessagesInChannel(channel_id);
        List<MessageRequest> messageRequests = new ArrayList<>();
        long sender_id;
        for (int i=0;i<messages.size();++i) {
            MessageRequest messageRequest = new MessageRequest();
            messageRequest.setMessage((messages.get(i)).getMessage());
            messageRequest.setDatetime((messages.get(i)).getDateTime());
            sender_id = messages.get(i).getSender_id();
            User user =userService.getUsersById(sender_id);
            messageRequest.setSenderName(user.getEmail());
            messageRequest.setUserName(user.getFull_name());
            messageRequests.add(messageRequest);
        }
        return messageRequests;
    }
    @PostMapping("/count")
    public Integer getMessagesCount(@RequestBody ChannelRequest channelRequest) {
       Channel channel = channelService.getChannelByName(channelRequest.getName());
       long channel_id = channel.getChannel_id();
       return messageService.getMessagesCount(channel_id);
    }
}
