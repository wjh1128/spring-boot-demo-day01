package com.hand.hls.train.springbootdemoday01.mapper;

import com.hand.hls.train.springbootdemoday01.entity.User;

import java.util.List;

/**
 * 用户的接口
 */
public interface IUserMapper {
    /**
     * 根据用户的id查询用户
     *
     * @param id
     * @return
     */
    User getUserById(Integer id);

    /**
     * 创建一个用户信息
     *
     * @param user
     * @return
     */
    int createUser(User user);

    /**
     * 更新用户的信息
     *
     * @param user
     * @return
     */
    int updateUser(User user);

    /**
     * 查询所有的用户
     *
     * @return
     */
    List<User> selectAll();

    /**
     * 根据用户的id来删除用户
     *
     * @param id
     * @return
     */
    int deleteById(Integer id);
}
