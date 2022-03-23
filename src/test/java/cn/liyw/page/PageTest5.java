package cn.liyw.page;

import cn.liyw.Application;
import cn.liyw.domin.User;
import cn.liyw.service.GsonUtil;
import cn.liyw.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.CollectionUtils;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class)
public class PageTest5 {
    @Autowired
    private UserService userService;

    private static LoadingCache<String, Map<Long,List<User>>> tbTbPropCdkeyInfoConfByCdkey = null;
    @PostConstruct
    public void init() {
        tbTbPropCdkeyInfoConfByCdkey = CacheBuilder.newBuilder()
                /* 最大的超时时间是60*60*24秒，60*60*24秒后会去数据库读 */
                .expireAfterWrite(30, TimeUnit.SECONDS)
                /* 创建空值侦听 */
                .build(new CacheLoader<String, Map<Long,List<User>>>() {
                    @Override
                    public Map<Long,List<User>> load(String key) {
                        return loadTbPropCdkeyInfoConfByCdkey(key);
                    }
                });
    }
    private Map<Long,List<User>> loadTbPropCdkeyInfoConfByCdkey(String key) {
        List<User> user = null;
        Map<Long,List<User>> result = null;
        try {
            user = userService.queryUserList();
            result = user
                    .stream()
                    .collect(Collectors.groupingBy(User::getId));
            System.out.println("load=========="+key);
        } catch (Exception e) {

        }
        return result;

    }
    public User getTbPropCdkeyInfoConfCached(Long propInfotitle) throws ExecutionException, InterruptedException {

        String key = "tbPropCdkeyInfoConf_propInfotitles";
        Map<Long, List<User>> tbPropCdkeyInfoConf = new HashMap<>();
        try {
            if (tbTbPropCdkeyInfoConfByCdkey != null) {
                tbPropCdkeyInfoConf = tbTbPropCdkeyInfoConfByCdkey.get("propInfotitles");
            }
            System.out.println("loadTbPropCdkeyInfoConfByCdkey==========tbTbPropCdkeyInfoConfByCdkey" + GsonUtil.toJson(tbTbPropCdkeyInfoConfByCdkey));
        } catch (Exception e) {
            return null;
        }
        if(CollectionUtils.isEmpty(tbPropCdkeyInfoConf) || CollectionUtils.isEmpty(tbPropCdkeyInfoConf.get(propInfotitle)) ){
            return null;
        }
        return tbPropCdkeyInfoConf.get(propInfotitle).get(0);

    }



    @Test
    public void T_add() throws ExecutionException, InterruptedException {

        for(int i=0;i<200;i++) {
            User s = this.getTbPropCdkeyInfoConfCached(2l);
            System.out.println("loadTbPropCdkeyInfoConfByCdkey=========="+i + GsonUtil.toJson(s));
            Thread.sleep(1000);
            System.out.println("1111"+GsonUtil.toJson(tbTbPropCdkeyInfoConfByCdkey));

        }


    }

}