package cn.liyw.service.impl;

import cn.liyw.dao.UserDao;
import cn.liyw.domin.User;
import cn.liyw.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;


    @Override
    @Transactional(rollbackFor = Exception.class)
    public int addUser(User user) {
        int i = userDao.addUser(user);
        return i;
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
    public int updateCount(Long id) {
        return userDao.updateCount(id);
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
