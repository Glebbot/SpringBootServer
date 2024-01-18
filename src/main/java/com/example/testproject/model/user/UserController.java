package com.example.testproject.model.user;

import com.example.testproject.model.channel.Channel;
import com.example.testproject.model.channel.ChannelRequest;
import com.example.testproject.model.channel.ChannelService;
import com.example.testproject.model.login.LoginUser;
import com.example.testproject.model.userchannel.UserChannelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private ChannelService channelService;
    @Autowired
    private UserChannelService userChannelService;

    @GetMapping("/users/all")
    public List<User> getUsers(){
        return userService.getAllUsers();
    }

    @PostMapping("/users/add")
    public void addUser(@RequestBody User user){
        userService.save(user);
    }

    @PostMapping("/users/login")
    public boolean loginUser(@RequestBody LoginUser loginUser){

        String email = loginUser.getEmail();
        String password = loginUser.getPassword();
        User userByEmail=userService.getUsersByEmail(email);
        if (userByEmail!=null) {
            if (password.equals(userByEmail.getPassword())) {
                return true;
            }
            else {return false;}
        }
        else {return false;}

    }
    @PostMapping("/users/channel")
    public List<User> getUsersInChannel(@RequestBody ChannelRequest channelRequest) {
        Channel channel =channelService.getChannelByName(channelRequest.getName());
        long channel_id = channel.getChannel_id();
        List<Long> user_id= userChannelService.getUsersInChannel(channel_id);
        return userService.getUsersByIdList(user_id);
    }
}
