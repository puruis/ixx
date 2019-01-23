package com.ixx.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.concurrent.*;
/**
 * Description:
 * User: purui_zhang
 * Date: 2019-01-03
 * Time: 18:51
 */
@Component
@Slf4j
public class ScheduleService {

    public static final ScheduledExecutorService scheduler =
            Executors.newScheduledThreadPool(10);


    /**
     * 在上一个任务完成的基础上 间隔3秒执行一次
     * @param executorService
     * @throws InterruptedException
     * @throws ExecutionException
     */
    public void executScheduleWithFixedDelay(ScheduledExecutorService executorService,Thread thread){
        try {
            executScheduleWithFixedDelay(executorService,1000,3000,thread);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("{}", "定时任务出错！");
        }
    }
    /**
     * 在上一个任务完成的基础上 间隔 delay 秒执行一次
     * @param executorService
     * @throws InterruptedException
     * @throws ExecutionException
     */
    public void executScheduleWithFixedDelay(ScheduledExecutorService executorService,int initialDelay,int delay,Thread thread){
        ScheduledFuture<?> result = executorService.scheduleWithFixedDelay(thread,initialDelay, delay, TimeUnit.MILLISECONDS);
    }

    /**
     * 上一个个任务开始执行之后延迟
     * 多少秒之后再执行， 是从上一个任务开始时开始计算
     * 但是还是会等上一个任务执行完之后，下一个任务才开始执行，最后的结果，就是感觉延迟失去
     * 了作用
     * @param executorService
     * @throws InterruptedException
     * @throws ExecutionException
     */
    public void executScheduleAtFixedRate(ScheduledExecutorService executorService,Thread thread){
        try {
            executScheduleAtFixedRate(executorService,1000,3000,thread);
        } catch (Exception e) {
            log.error("{}", "定时任务出错！");
        }
    }

    /**
     *上一个个任务开始执行之后延迟
     * 多少秒之后再执行， 是从上一个任务开始时开始计算
     * 但是还是会等上一个任务执行完之后，下一个任务才开始执行，最后的结果，就是感觉延迟失去
     * 了作用
     * @param executorService
     * @param initialDelay
     * @param delay
     * @throws InterruptedException
     * @throws ExecutionException
     */
    public void executScheduleAtFixedRate(ScheduledExecutorService executorService,int initialDelay,int delay,Thread thread) throws InterruptedException,ExecutionException {
        ScheduledFuture<?> result = executorService.scheduleWithFixedDelay(thread,initialDelay, delay, TimeUnit.MILLISECONDS);
        result.get();
    }


}
