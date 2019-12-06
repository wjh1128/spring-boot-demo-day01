package com.hand.hls.train.springbootdemoday01.controller;

import com.hand.hls.train.springbootdemoday01.entity.User;
import com.hand.hls.train.springbootdemoday01.service.IUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * 控制层
 *
 * @author jinhua.wu@hand-china.com 2019/12/05 13:49
 */
@RestController
@RequestMapping("/user")
@Api(value = "用户操作接口", tags = {"用户操作接口"})
public class UserController {
    @Autowired
    private IUserService userService;

    @ApiOperation(value = "获取用户详情信息", notes = "根据用户的id获取详情信息")
    @GetMapping("/{id}")
    public User getUserById(@PathVariable("id") Integer id) {
        return userService.getUserById(id);
    }

    @ApiOperation(value = "获取所有用户信息", notes = "获取所有的用户信息")
    @GetMapping()
    public List<User> getUsers() {
        return userService.selectAll();
    }

    @ApiOperation(value = "创建用户")
    @PostMapping()
    public User createUser(@RequestBody @Validated User user) {
        userService.create(user);
        return user;
    }

    @ApiOperation(value = "删除用户", notes = "根据用户的id删除")
    @DeleteMapping()
    public void deleteUserById(@RequestBody User user) {
        userService.deleteById(user);
    }

    @ApiOperation(value = "修改用户信息", notes = "根据用户的id获取详情信息")
    @PutMapping()
    public User updateUser(@Validated @RequestBody User user) {
        userService.updateUser(user);
        return user;
    }
}
