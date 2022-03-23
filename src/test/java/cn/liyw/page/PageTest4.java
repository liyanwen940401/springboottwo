package cn.liyw.page;

import cn.liyw.Application;
import cn.liyw.service.RedisUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.PostConstruct;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class)
public class PageTest4 {



    protected ThreadPoolExecutor executorService = new ThreadPoolExecutor(10, 10, 0L,
            TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(10000));


    @PostConstruct
    public void init() {
        try {

            for (int i = 0; i < 2; i++) {
                executorService.execute(new GiftRetryTask());
            }
        } catch (Exception e) {
        }


    }
        /**
         * 重试机制
         *
         * @author liujianqin
         *
         */
        private  class GiftRetryTask implements Runnable {

            @Override
            public void run() {
                while (true) {

                    System.out.println("lpop.giftMsg====");
                    String giftMsg = RedisUtils.lpop("retry.list");
                    try {

                        if(giftMsg == null){
                            break;
                        }
                        System.out.println("lpop.giftMsg sleep start===="+Thread.currentThread().getName());
                        Thread.sleep(10000);
                        System.out.println("lpop.giftMsg sleep end===="+giftMsg);
                    } catch (Throwable e) {

                    }
                }

            }

        }




    @Test
    public void T_add() throws InterruptedException {
//            for(int i = 0;i<100000;i++){
//                RedisUtils.rpush("retry.list","apple"+i);
//                System.out.println("put======"+i+Thread.currentThread().getName());
//            }
            Thread.sleep(60*1000*5);

    }

}