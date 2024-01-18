package com.example.testproject.model.userchannel;

import com.example.testproject.model.channel.Channel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserChannelRepository extends JpaRepository<UserChannel, UserChannelId> {
    @Query(value = "SELECT channel_id FROM postgres.public.user_channel WHERE user_id=?1",nativeQuery = true)
    public List<Long> findChannelsOfUser(long user_id);
    @Query(value = "SELECT user_id FROM postgres.public.user_channel WHERE channel_id=?1",nativeQuery = true)
    public List<Long> findUsersInChannel(long channel_id);
}
