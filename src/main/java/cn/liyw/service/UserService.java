package cn.liyw.service;

import cn.liyw.domin.User;

import java.util.List;


public interface UserService {
    int addUser(User user);
    int daleteUserById(Long id);
    int updateUserById(User user);
    User queryUserById(Long id);
    List<User> queryUserList();
}
