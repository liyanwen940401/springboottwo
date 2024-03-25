package cn.liyw.async;

import java.util.concurrent.CompletableFuture;

public class CompletableFutureTest {
    public static void main(String[] args) {
        Integer resultList = 0;
        //常规档位
        System.out.println("-------"+System.currentTimeMillis());
        CompletableFuture<Double> itemInfoByGroupFuture = CompletableFuture
                .supplyAsync(() -> {
                    try {
                        Thread.sleep(1000);
                        System.out.println("-------"+System.currentTimeMillis());
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    return 2.1;
                });
        //获取弹窗活动信息
        CompletableFuture<Integer> scenePopInfoFuture = CompletableFuture
                .supplyAsync(() -> {
                    try {
                        Thread.sleep(1500);
                        System.out.println("-------"+System.currentTimeMillis());
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    return 3;
                });
        CompletableFuture<Integer> future = new CompletableFuture();

        CompletableFuture.allOf(itemInfoByGroupFuture, scenePopInfoFuture).whenComplete((v, e) -> {
            if (e != null) {
                future.complete(resultList);
                return;
            }
            Double list = itemInfoByGroupFuture.getNow(null);
            Integer pop = scenePopInfoFuture.getNow(null);
            if (list == null || pop == null) {
                future.complete(resultList);
                return;
            }
            System.out.println("-------"+System.currentTimeMillis());
            int  i= (int) (list+pop);
            future.complete(i);
        });
//        try {
//            Integer ret = future.get(5, TimeUnit.SECONDS);
//            System.out.println("end=-"+ret+"---"+System.currentTimeMillis());
//        } catch (Exception e) {
//
//        }
        System.out.println("end=----"+resultList+"++"+System.currentTimeMillis());
    }
}
