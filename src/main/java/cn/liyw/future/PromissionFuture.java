package cn.liyw.future;

import cn.liyw.dao.UserDao;
import cn.liyw.domin.User;
import cn.liyw.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

/**
 * YuewenAuthenServerV3
 *
 * @author zhangzaiyuan
 * @date 2018/11/22
 */
@Component("promission")
public class PromissionFuture {
    @Autowired
    private UserService userService;

    public User isSmallAccount(List<Integer> types) {
        return userService.queryUserById(1l);
    }


}
