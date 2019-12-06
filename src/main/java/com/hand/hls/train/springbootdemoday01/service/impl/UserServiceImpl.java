package com.hand.hls.train.springbootdemoday01.service.impl;

import com.hand.hls.train.springbootdemoday01.entity.User;
import com.hand.hls.train.springbootdemoday01.mapper.IUserMapper;
import com.hand.hls.train.springbootdemoday01.redis.UserRedis;
import com.hand.hls.train.springbootdemoday01.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * service层
 *
 * @author jinhua.wu@hand-china.com 2019/12/05 13:44
 */
@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private IUserMapper userMapper;

    private UserRedis userRedis;

    public UserServiceImpl(UserRedis userRedis) {
        this.userRedis = userRedis;
        userRedis.init();
    }

    @Override
    public User getUserById(Integer id) {
        User user = userRedis.get(id.toString());
        if (user != null) {
            return user;
        }
        return userMapper.getUserById(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int create(User user) {
        int result = userMapper.createUser(user);
        userRedis.set(user);
        return result;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int updateUser(User user) {
        int result = userMapper.updateUser(user);
        userRedis.set(user);
        return result;
    }

    @Override
    public List<User> selectAll() {
        List<User> userList = userRedis.getAll();
        if (userList != null) {
            return userList;
        }
        return userMapper.selectAll();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int deleteById(User user) {
        int result = userMapper.deleteById(user.getId());
        userRedis.remove(user);
        return result;
    }
}
