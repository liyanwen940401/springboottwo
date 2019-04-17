package cn.liyw.service.impl;

import cn.liyw.dao.UserDao;
import cn.liyw.domin.User;
import cn.liyw.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;
    @Override
    public int addUser(User user) {
        return userDao.addUser(user);
    }

    @Override
    public int daleteUserById(Long id) {
        return userDao.daleteUserById(id);
    }

    @Override
    public int updateUserById(User user) {
        return userDao.updateUserById(user);
    }

    @Override
    public User queryUserById(Long id) {
        return userDao.queryUserById(id);
    }

    @Override
    public List<User> queryUserList() {
        return userDao.queryUserList();
    }
}
