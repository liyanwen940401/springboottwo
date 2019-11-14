package cn.liyw.async;

import cn.liyw.Application;
import cn.liyw.dao.UserDao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.reactive.context.AnnotationConfigReactiveWebApplicationContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.concurrent.ExecutionException;
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
 * @date 2019/11/14 15:16
 */

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class)
public class taskExecutorTest {

    @Autowired
    private AsyncTaskService asyncTaskService;
    @Autowired
    private AsyncTaskFutureService asyncTaskFutureService;
    @Test
    public void executeAsyncTask() {
        for (int i = 0;i<10;i++){
            asyncTaskService.executeAsyncTask(i);
            asyncTaskService.executeAsyncTaskPlus(i);
        }
    }

    @Test
    public void AsyncTaskFutureService() throws InterruptedException, ExecutionException {
        long s = System.currentTimeMillis();
		Future<String> task1 = asyncTaskFutureService.task1();
		Future<String> task2 = asyncTaskFutureService.task2();
        for (;;) {
            // 回调函数 Future 如果执行完毕就会返回这个函数
            if (task1.isDone() && task2.isDone()) {
                System.out.println("task1");
                break;
            }
        }
		System.out.println(task1.get());
		System.out.println(task2.get());
        long e = System.currentTimeMillis();
        System.out.println("task总耗时:" + (e - s));
    }
}