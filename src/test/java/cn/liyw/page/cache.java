package cn.liyw.page;

import com.google.common.cache.*;
import org.json.JSONException;
import org.junit.Test;

import java.text.ParseException;
import java.util.Set;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class cache {

        private ScheduledExecutorService scheduledExecutorService= Executors.newScheduledThreadPool(1);

        private LoadingCache<String,String> cahceBuilder= CacheBuilder.newBuilder()
                .maximumSize(10)
                .removalListener(new RemovalListener<String,String>() {
                    @Override
                    public void onRemoval(RemovalNotification removalNotification) {
                        System.out.println("移除："+removalNotification.getKey());
                    }
                })
                .build(new CacheLoader<String, String>() {

                    @Override
                    public String load(String key) throws Exception {
                        System.out.println("load key:"+key);
                        String value=key+"_"+"end";
                        return value;
                    }
                });


        /**
         * 执行定时刷新
         */
        private class RefreshJob implements Runnable{

            @Override
            public void run() {
                System.out.println("begin refresh------------");
                Set<String> keys = cahceBuilder.asMap().keySet();
                for(String key: keys){
                    cahceBuilder.refresh(key);
                }
                System.out.println("end refresh------------");
            }
        }


        @Test
        public void refreshCacheByJob() throws ExecutionException, InterruptedException {
            System.out.println(cahceBuilder.get("lhc"));
            System.out.println(cahceBuilder.get("jay"));
            System.out.println(cahceBuilder.get("jasmine"));
            scheduledExecutorService.scheduleAtFixedRate(new RefreshJob(),0,1000, TimeUnit.MILLISECONDS);
            Thread.sleep(3000);
            System.out.println("again lhc:--"+cahceBuilder.get("lhc"));
        }
}
