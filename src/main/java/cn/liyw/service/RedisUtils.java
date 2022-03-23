package cn.liyw.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import javax.annotation.PostConstruct;


/**
 * Date:2019-07_08 13:20
 * Description: redis 工具类
 */
@Component
public class RedisUtils {


    private static JedisPool jedisPool;

    @Autowired
    private RedisConfig redisConfig;

    /**
     * JedisPool 无法通过@Autowired注入，可能由于是方法bean的原因，此处可以先注入RedisConfig，
     * 然后通过@PostConstruct初始化的时候将factory直接赋给jedisPool
     */
    @PostConstruct
    public void init() {
        jedisPool = redisConfig.redisPoolFactory();
    }

    public static String get(String key, int indexdb) {
        Jedis jedis = null;
        String value = null;

        try {
            jedis = jedisPool.getResource();//获取一个jedis实例
            jedis.select(indexdb);
            value = jedis.get(key);
        } catch (Exception e) {
            System.out.println("错误日志："+e.getMessage());
        } finally {
            jedis.close();
        }
        return value;
    }
    public static String set(String key, int indexdb,String value) {
        Jedis jedis = null;
        String ret = null;

        try {
            jedis = jedisPool.getResource();//获取一个jedis实例
            jedis.select(indexdb);
            ret = jedis.set(key,value);
        } catch (Exception e) {
            System.out.println("错误日志："+e.getMessage());
        } finally {
            jedis.close();
        }
        return ret;
    }
    public static String lpop(String key) {
        String result = null;
        Jedis jedis = jedisPool.getResource();
        jedis.select(0);
        if (null != jedis) {
            try {
                result = jedis.lpop(key);
            } catch (Exception e) {
            } finally {
                if (jedis != null) {
                    jedis.close();
                }
            }
        }
        return result;
    }
    public static long rpush(String key, String target) {
        long result = -1;
        Jedis jedis = jedisPool.getResource();
        jedis.select(0);
        if (null != jedis) {
            try {
                result = jedis.rpush(key, target);
            } catch (Exception e) {
            } finally {
                if (jedis != null) {
                    jedis.close();
                }
            }
        }
        return result;
    }
}
