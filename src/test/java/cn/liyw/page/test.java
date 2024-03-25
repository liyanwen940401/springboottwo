package cn.liyw.page;

import cn.liyw.Application;
import org.apache.commons.lang3.concurrent.BasicThreadFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import java.util.*;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class)
public class test {

    /**
     * 线程池
     */
    private final ThreadPoolExecutor executorService = new ScheduledThreadPoolExecutor(20,
            new BasicThreadFactory.Builder().namingPattern("author-reward-schedule-job-%d").daemon(true).build());
    /**
     * 用户
     */
    private class Task implements Runnable, Callable<Integer> {

        private final Integer userGuid;

        public Task(Integer userGuid) {
            this.userGuid = userGuid;
        }

        @Override
        public void run() {
            try {
                this.call();
            } catch (Exception e) {
            }
        }

        @Override
        public Integer call() {
            try {
                int i1=executorService.getActiveCount();
                long i2=executorService.getCompletedTaskCount();
                int i3=executorService.getCorePoolSize();
                long i4=executorService.getKeepAliveTime(TimeUnit.NANOSECONDS);
                int i5=executorService.getLargestPoolSize();
                int i6=executorService.getMaximumPoolSize();
                int i7=executorService.getPoolSize();
                RejectedExecutionHandler i8=executorService.getRejectedExecutionHandler();
                BlockingQueue<Runnable> i9=executorService.getQueue();
                long i0=executorService.getTaskCount();

                System.out.println("t-----"+userGuid+"i1===="+i1+"；i2===="+i2+ "；i3===="+i3+"；i4===="+i4+"；i5===="+i5+
                        "；i6===="+i6+
                        "；i7====" +i7+ "；i8===="+i8.getClass().getName()+"；i9===="+i9.size()+"；i0===="+i0);
                Thread.sleep(10);
            } catch (Exception e) {
            }
            return 0;
        }

    }

    @Test
    public void T_add() throws Exception {
        try {
            List<Callable<Integer>> callableList = new ArrayList<>();
            for (int i=0;i<1000;i++) {
                //生成cdkey并发送消息
                Task task = new Task(i);
                callableList.add(task);
            }
            List<Future<Integer>> futureList = executorService.invokeAll(callableList);
            for (Future<Integer> row : futureList) {
                try {
                    row.get();
                } catch (Exception e) {
                }
            }
            futureList.clear();
            callableList.clear();
        } catch (InterruptedException e) {
        }
    }

}