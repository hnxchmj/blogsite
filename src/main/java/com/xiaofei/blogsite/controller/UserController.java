package com.xiaofei.blogsite.controller;

import com.xiaofei.blogsite.Util.ClientException;
import com.xiaofei.blogsite.dao.UserMapper;
import com.xiaofei.blogsite.dto.UserRegisterDTO;
import com.xiaofei.blogsite.dto.UserShowDTO;
import com.xiaofei.blogsite.dto.UserUpdateDTO;
import com.xiaofei.blogsite.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequestMapping("/User")
public class UserController {

    @Autowired
    private UserMapper userMapper;

    @GetMapping("getById")
    public UserShowDTO getById(@RequestParam Integer id) throws ClientException {
        User user = userMapper.selectByPrimaryKey(id);
        if (user == null){
            throw new ClientException("user id doesn't exist");
        }
        UserShowDTO userShowDTO = new UserShowDTO();
        userShowDTO.setId(user.getId());
        userShowDTO.setUsername(user.getUsername());
        userShowDTO.setDisplayName(user.getDisplayName());
        userShowDTO.setEmail(user.getEmail());
        userShowDTO.setMobile(user.getMobile());
        userShowDTO.setIsMale(user.getIsMale());
        userShowDTO.setBirthday(user.getBirthday());
        userShowDTO.setCreateTime(user.getCreateTime());
        return userShowDTO;
    }

    @PostMapping("register")
    public Integer register(@RequestBody UserRegisterDTO userRegisterDTO) throws Exception {
        User userExist = userMapper.selectByUsername(userRegisterDTO.getUsername());
        if (userExist!=null){
            throw new ClientException("username already exist");
        }
        Date createTime = new Date();
        User user = new User();
        user.setUsername(userRegisterDTO.getUsername());
        user.setCreateTime(createTime);
        user.setUpdateTime(createTime );
        user.setEncryptedPassword(DigestUtils.md5DigestAsHex(userRegisterDTO.getPassword().getBytes()));
        userMapper.insert(user);
        User userCreate = userMapper.selectByUsername(user.getUsername());
        return userCreate.getId();
    }

    @PostMapping("update")
    public void update(@RequestBody UserUpdateDTO userUpdateDTO) throws ClientException {
        User user = userMapper.selectByPrimaryKey(userUpdateDTO.getId());
        if (user == null){
            throw new ClientException("user id doesn't exist");
        }
        user.setDisplayName(userUpdateDTO.getDisplayName());
        user.setEmail(userUpdateDTO.getEmail());
        user.setMobile(userUpdateDTO.getMobile());
        user.setIsMale(userUpdateDTO.getIsMale());
        user.setBirthday(userUpdateDTO.getBirthday());
        Date updateTime = new Date();
        user.setUpdateTime(updateTime);
        userMapper.updateByPrimaryKey(user);
    }
}
