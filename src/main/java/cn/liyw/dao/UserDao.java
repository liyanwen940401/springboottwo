package cn.liyw.dao;

import cn.liyw.domin.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserDao {
    int addUser(User user);
    int daleteUserById(Long id);
    int updateUserById(User user);
    User queryUserById(Long id);
    List<User> queryUserList();
}
