package cn.linkedcare.service.impl;

import cn.linkedcare.dao.UserMapper;
import cn.linkedcare.entity.User;
import cn.linkedcare.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Benji on 2018/5/7.
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;


    @Override
    public int insert(User user) {
        return userMapper.insertSelective(user);
    }

    @Override
    public User findById(int id) {
        return userMapper.selectByPrimaryKey(id);
    }
}
