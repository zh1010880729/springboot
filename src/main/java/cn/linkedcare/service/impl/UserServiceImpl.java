package cn.linkedcare.service.impl;

import cn.linkedcare.dao.UserMapper;
import cn.linkedcare.entity.User;
import cn.linkedcare.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Benji on 2018/5/7.
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;


    @Override
    @Transactional
    public User insert(User user) {
        int count = userMapper.insertSelective(user);
        try {
            int x = count / 0;
        } catch (RuntimeException e) {
            throw new RuntimeException(e.getMessage());
        }
        return user;
    }

    @Override
    public User findById(int id) {
        return userMapper.selectByPrimaryKey(id);
    }
}
