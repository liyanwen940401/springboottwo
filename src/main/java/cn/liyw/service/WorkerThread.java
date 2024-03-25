package cn.liyw.service;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class WorkerThread {
    public static void main(String[] args) {
        Helper helper = new Helper();
        helper.init();
        helper.submit("Something.....");
        System.out.println("end_"+System.currentTimeMillis());
    }
    static class Helper{
        private final BlockingQueue<String> workQueue = new ArrayBlockingQueue<String>(100);

        private final Thread workThread = new Thread(){
            @Override
            public void run() {
                String task = null;
                while (true) {
                    try {
                        task = workQueue.take();
                    } catch (Exception e) {
                        break;
                    }
                    System.out.println(doProcess(task));
                }
            }
        };
        public void init(){
            workThread.start();
        }
        protected String doProcess(String task){
            return task+"-processed."+System.currentTimeMillis();
        }
        public void submit(String task){
            try{
                workQueue.put(task);
            }catch (Exception e){
                Thread.currentThread().interrupt();;
            }
        }
    }
}
