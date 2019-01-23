package com.ixx.conf.thread;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ixx.entity.sys.LoginNoticeDo;
import com.ixx.service.LoginNoticeService;
import com.ixx.util.HttpUtils;
import com.ixx.util.SpringContextHolder;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

/**
 * Description: IP地址转 物理地址
 * User: purui_zhang
 * Date: 2019-01-03
 * Time: 19:15
 */
@Getter
@Setter
@Slf4j
public class IP2AddressThread extends Thread{

    private String address = "0:0:0:0:0:0:0:1";

    public IP2AddressThread(){

    }

    @Override
    public void run() {
        LoginNoticeService loginNoticeService = SpringContextHolder.getBean(LoginNoticeService.class);
        QueryWrapper<LoginNoticeDo> loginNoticeDoQueryWrapper = new QueryWrapper<>();
        loginNoticeDoQueryWrapper.isNull("address");
        List<LoginNoticeDo> list = loginNoticeService.list(loginNoticeDoQueryWrapper);
        list.forEach(loginNoticeDo -> {
            if(!StringUtils.equals(address,loginNoticeDo.getIp())){
                String result = HttpUtils.getMethod("http://ip.taobao.com/service/getIpInfo.php?ip=" + loginNoticeDo.getIp(), 20000);
                JSONObject object = JSON.parseObject(result);
                int code = object.getIntValue("code");
                if(code == 0){
                    JSONObject data = object.getJSONObject("data");
                    String country = data.getString("country");
                    String city = data.getString("city");
                    String region = data.getString("region");
                    String isp = data.getString("isp");
                    loginNoticeDo.setAddress(country.concat("-").concat(city).concat("-").concat(region).concat("-").concat(isp));
                }else{
                    log.error("{}","请求Ip---》》》 地址失败" );
                }
            }else{
                loginNoticeDo.setAddress("局域网地址");
            }
            loginNoticeService.updateById(loginNoticeDo);
        });
    }
}
