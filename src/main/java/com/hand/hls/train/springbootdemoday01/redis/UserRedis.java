package com.hand.hls.train.springbootdemoday01.redis;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hand.hls.train.springbootdemoday01.entity.User;
import com.hand.hls.train.springbootdemoday01.mapper.IUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * UserRedis的实现类
 * </p>
 *
 * @author jinhua.wu@hand-china.com 2019/12/05 20:27
 */
@Component
public class UserRedis implements RedisCache<User> {
    /**
     * 作为仓库的名称
     */
    public final String USER_DB_NAME = "user_redis_db";

    @Autowired
    private RedisTemplate<String, User> redisTemplate;
    @Autowired
    private IUserMapper userMapper;

    /**
     * 定义一个集合,对象的CRUD更新进去,供查询所有专用
     */
    private Map<Integer, User> map = new HashMap<>();

    @Override
    public List<User> init() {
        List<User> userList = userMapper.selectAll();
        if (userList != null) {
            for (User user : userList) {
                map.put(user.getId(), user);
                set(user);
            }
        }
        return userList;
    }

    @Override
    public User get(String key) {
        String o = (String) redisTemplate.opsForHash().get(USER_DB_NAME, key);
        try {
            if (o != null) {
                return new ObjectMapper().readValue(o, User.class);
            }
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void set(User o) {
        if (o.getId() != null) {
            try {
                redisTemplate.opsForHash().put(USER_DB_NAME, o.getId().toString(), new ObjectMapper().writeValueAsString(o));
                map.put(o.getId(), o);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void reload() {
        clear();
        init();
    }

    @Override
    public void remove(User user) {
        redisTemplate.opsForHash().delete(USER_DB_NAME, user.getId().toString());
        map.remove(user.getId());
    }

    @Override
    public void clear() {
        redisTemplate.delete(USER_DB_NAME);
        // 删除map里面的集合
        map.clear();
    }

    @Override
    public List<User> getAll() {
        return new ArrayList<>(map.values());
    }
}
