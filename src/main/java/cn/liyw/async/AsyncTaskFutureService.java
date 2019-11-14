package cn.liyw.async;

import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.Future;

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
 * @date 2019/11/14 15:41
 */
@Service
//带有返回值的异步方法
public class AsyncTaskFutureService {
    @Async
    public Future<String> task1() throws InterruptedException {
        Long currentTimeMills = System.currentTimeMillis();
        Thread.sleep(0);
        Long currentTimeMills1 = System.currentTimeMillis();
        System.out.println("task1任务耗时："+(currentTimeMills1-currentTimeMills));
        return new AsyncResult<String>("task1执行完毕");

    }
    @Async
    public Future<String> task2() throws InterruptedException {
        Long currentTimeMills = System.currentTimeMillis();
        Thread.sleep(0);
        Long currentTimeMills1 = System.currentTimeMillis();
        System.out.println("task2任务耗时："+(currentTimeMills1-currentTimeMills));
        return new AsyncResult<String>("task1执行完毕");

    }
}
