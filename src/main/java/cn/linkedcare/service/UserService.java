package cn.linkedcare.service;

import cn.linkedcare.entity.User;

/**
 * Created by Benji on 2018/5/7.
 */
public interface UserService {

    int insert(User user);

    User findById(int id);
}
