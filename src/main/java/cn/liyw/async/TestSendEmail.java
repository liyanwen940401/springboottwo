package cn.liyw.async;

import javafx.util.Pair;
import org.apache.commons.lang3.time.DateUtils;

import javax.xml.crypto.Data;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class TestSendEmail {

    private final static ExecutorService thread_pool = Executors.newFixedThreadPool(10);

    public static void main(String[] args) {
        Long start = System.currentTimeMillis();
        TestSendEmail testSendEmail = new TestSendEmail();
        for (int i = 0; i < 4; i++) {
            int finalI = i + 2023030100;
            new Thread(() -> testSendEmail.sendActivityEmail(finalI,start)).start();
        }
        System.out.println(System.currentTimeMillis()+":"+Thread.currentThread().getName() + "Date" +
            ":" + (System.currentTimeMillis() - start));
    }

    public int sendActivityEmail(Integer activityId,Long start) {
        Long startDate = System.currentTimeMillis();
        System.out.println(System.currentTimeMillis()+":"+Thread.currentThread().getName() + "Date:" + (startDate- start));
        int i = 3;
        int successNum = 0;
        int fpNumTotal = 0;
        while (i > 0) {
            //处理
            List<Callable<Pair<Integer, Integer>>> callableList = new ArrayList<Callable<Pair<Integer, Integer>>>();
            for (int j = 0; j < 15; j++) {
                callableList.add(new sendEmailTask(j, 4, activityId));
            }
            try {
                List<Future<Pair<Integer, Integer>>> listFuture = thread_pool.invokeAll(callableList);
                /* wait all thread is done */
                for (final Future<Pair<Integer, Integer>> row : listFuture) {
                    try {
                        successNum += row.get().getKey();
                        fpNumTotal += row.get().getValue();
                    } catch (Throwable t) {
                        System.out.println("error sendEmailByPreRecord");
                    }
                }
                listFuture.clear();
            } catch (Exception e) {
                System.out.println("error in sendEmailByPreRecord");

            }
            callableList.clear();
            i--;
        }
        System.out.println(System.currentTimeMillis()+":"+"activityId:" + activityId + "successNum:" + successNum + ",fpNumTotal:" + fpNumTotal + "," +
                "time:" + (System.currentTimeMillis() - startDate));
        return 0;
    }

    /**
     * 发送邮件及奖励
     *
     * @author liyanwen
     */
    private class sendEmailTask implements Runnable, Callable<Pair<Integer, Integer>> {

        Integer platformAppId;
        Integer fpNum;
        Integer activityId;

        public sendEmailTask(Integer platformAppId, Integer fpNum, Integer activityId) {
            this.platformAppId = platformAppId;
            this.fpNum = fpNum;
            this.activityId = activityId;
        }

        @Override
        public void run() {

            try {
                this.call();
            } catch (Exception e) {
                System.out.println("error in HistoryUserConsume");
            }
        }

        @Override
        public Pair<Integer, Integer> call() throws Exception {
            long begin = System.currentTimeMillis();
            Pair<Integer, Integer> result = sendActivityEmail(platformAppId, fpNum, activityId);
            if (result.getKey() == 0) {
                // 失败
//                System.out.println(Thread.currentThread().getName() + "sendEmailTask error!!!");
            } else if (result.getKey() == 1 && result.getValue() == 0) {
//                System.out.println(Thread.currentThread().getName() + "sendEmailTask sendReward error!!!");
            } else {
//                System.out.println(Thread.currentThread().getName() + "sendEmailTask sendReward success!!!");
            }
            // sleep
//            Thread.sleep(1000);// 每次休息片刻，以免影响正常的用户
            return result;
        }
    }


    private Pair<Integer, Integer> sendActivityEmail(Integer i, Integer fpNum, Integer activityId) {
        int sendRet = 0;
        Integer sendRewardRet = 0;
        try {
            Thread.sleep(10);
            if (i != 50) {
                sendRet += 1;
                //发送邮件成功-修改发送状态
                Thread.sleep(10);
                if (i != 20) {
                    //修改礼包发送状态
                    sendRewardRet += fpNum;
                }
                System.out.println(System.currentTimeMillis()+":"+Thread.currentThread().getName() + "activityId:," + activityId +
                "sendEmailTask:" + i + "," +
                        "sendRet:" + sendRet + ",sendRewardRet：" + sendRewardRet);
                return new Pair<>(sendRet, sendRewardRet);
            } else {
                //500是失败的
                System.out.println(System.currentTimeMillis()+":"+Thread.currentThread().getName() + "activityId:," + activityId +
                "sendEmailTask:" + i + "," +
                        "sendRet:" + sendRet + ",sendRewardRet：" + sendRewardRet);
                return new Pair<>(sendRet, sendRewardRet);
            }
        } catch (Exception e) {
//            System.out.println(Thread.currentThread().getName() + "activityId:," + activityId + "sendEmailTask:" +
//            i + "," +
//                    "sendRet:" + sendRet + ",sendRewardRet：" + sendRewardRet);
            return new Pair<>(sendRet, sendRewardRet);
        }
    }

}
