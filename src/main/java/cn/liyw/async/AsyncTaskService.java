package cn.liyw.async;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

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
 * @date 2019/11/14 15:11
 */
@Service
public class AsyncTaskService {

    @Async
    // 表明是异步方法
    // 无返回值
    public void executeAsyncTask(Integer integer){
        System.out.println( Thread.currentThread().getName()+"执行异步任务："+integer);
    }

    @Async
    public void executeAsyncTaskPlus(Integer integer){
        int sum = 0;
        for(int i=0;i<30000;i++){
            sum+=i;
        }
        System.out.println( Thread.currentThread().getName()+"执行异步任务+1:"+integer);
    }

}
