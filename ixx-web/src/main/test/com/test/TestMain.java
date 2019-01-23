package com.test;

import com.ixx.Application;
import com.ixx.conf.thread.IP2AddressThread;
import com.ixx.service.ScheduleService;
import com.ixx.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

/**
 * Description:
 * User: purui_zhang
 * Date: 2019-01-02
 * Time: 19:06
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class)
public class TestMain {
    @Autowired
    private JavaMailSender mailSender;
    @Autowired
    private ScheduleService scheduleService;
    @Autowired
    private StudentService studentService;
    @Autowired
    private UserService userService;

    @Test
    public void sendSimpleMail() throws Exception {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("18217486827@163.com");
        message.setTo("854554762@qq.com");
        message.setSubject("主题：简单邮件");
        message.setText("测试邮件内容");

        mailSender.send(message);
    }
    @Test
    public void testSchedule(){
        ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
        scheduleService.executScheduleWithFixedDelay(executorService,new IP2AddressThread());
    }

    @Test
    public void testMultipleDataSource(){
        studentService.save();
    }

}
