package cn.liyw.async;

import cn.liyw.Application;
import org.apache.commons.lang3.concurrent.BasicThreadFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * <p></p>
 * <p>
 * <PRE>
 * <BR>	修改记录
 * <BR>-----------------------------------------------
 * <BR>	修改日期			修改人			修改内容
 * </PRE>
 *
 * @author liyw
 * @version V1.0
 * @date 2019/11/14 15:16
 */

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class)
public class taskExecutorTest1 {

    /**
     * 会员发送月票线程池
     */
    private final ScheduledExecutorService executorService = new ScheduledThreadPoolExecutor(20,
            new BasicThreadFactory.Builder().namingPattern("expire-goldenticket-schedule-job-%d").daemon(true).build());
    /**
     * 用户过期充值订单处理
     */
    private class ExpireFastPassTask implements Runnable, Callable<Integer> {

        private final Long userGuid;

        private final Integer pointsTypeId;

        public ExpireFastPassTask(Long userGuid, Integer pointsTypeId ) {
            this.userGuid = userGuid;
            this.pointsTypeId = pointsTypeId;
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
                Long startTime = System.currentTimeMillis();
                Thread.sleep(10000);
                Long end = System.currentTimeMillis();
                System.out.println("-------"+(startTime-end));
            }
            catch (Exception e) {
            }
            return 0;
        }

    }

    @Test
    public void AsyncTaskFutureService() throws InterruptedException, ExecutionException {
        long s = System.currentTimeMillis();

        List<Callable<Integer>> callableList = new ArrayList<>();
        for (int i=0;i<50;i++) {
            ExpireFastPassTask task = new ExpireFastPassTask((long)i,i);
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
    }
}