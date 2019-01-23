package com.ixx;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceAutoConfigure;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.baomidou.mybatisplus.extension.plugins.PerformanceInterceptor;
import com.ixx.conf.properties.RedisProperties;
import com.ixx.conf.thread.IP2AddressThread;
import com.ixx.service.ScheduleService;
import com.ixx.util.SpringContextHolder;
import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
/**
 * Description:
 * User: purui_zhang
 * Date: 2018-11-12
 * Time: 14:39
 */
@EnableConfigurationProperties(RedisProperties.class)
@SpringBootApplication(exclude = DruidDataSourceAutoConfigure.class)
@ServletComponentScan
@MapperScan("com.ixx.dao")
@Slf4j
public class Application extends SpringApplication {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
        log.info("{}", "服务加载完成!");
        startIp2Address();
    }

    /**
     *  开启Ip 转 物理地址 定时任务
     */
    public static void startIp2Address(){
        ScheduleService scheduleService = SpringContextHolder.getBean(ScheduleService.class);
        scheduleService.executScheduleWithFixedDelay(ScheduleService.scheduler,1000,60000,new IP2AddressThread());
    }

    /**
     * SQL执行效率插件
     */
    @Bean
    @Profile({"dev","test"})// 设置 dev test 环境开启
    public PerformanceInterceptor performanceInterceptor() {
        return new PerformanceInterceptor();
    }

    /**
     * 分页插件
     */
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        return new PaginationInterceptor();
    }


}
