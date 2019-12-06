package com.hand.hls.train.springbootdemoday01.redis;

import com.hand.hls.train.springbootdemoday01.entity.User;

import java.util.List;

/**
 * <p>
 * Redis的服务类 是一个接口
 * </p>
 *
 * @author jinhua.wu@hand-china.com 2019/12/05 19:42
 */
public interface RedisCache<T> {

    /**
     * 初始化
     *
     * @return
     */
    List<T> init();

    /**
     * 根据key取值
     *
     * @param key string类型的键名
     * @return T  返回类型
     */
    T get(String key);

    /**
     * 根据key来设值
     *
     * @param o 设置的值
     */
    void set(T o);

    /**
     * 刷新缓存
     */
    void reload();

    /**
     * 移除缓存里面的元素
     *
     * @param user 根据key值删除元素
     */
    void remove(User user);

    /**
     * 清空缓存
     */
    void clear();

    /**
     * 查询全部信息
     *
     * @return 返回list集合
     */
    List<T> getAll();
}
