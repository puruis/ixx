package com.ixx.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ixx.entity.sys.UserDo;
import com.ixx.util.HttpUtils;
import com.ixx.vo.UserOnline;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.apache.shiro.subject.support.DefaultSubjectContext;
import org.crazycake.shiro.RedisSessionDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
/**
 * Description: 在线会话管理
 * User: purui_zhang
 * Date: 2019-01-08
 * Time: 17:29
 */
@Slf4j
@Component
public class SessionService {
    private String address = "0:0:0:0:0:0:0:1";
    @Autowired
    private RedisSessionDAO redisSessionDAO;

    public List<UserOnline> list()
    {
        List <UserOnline> list = new ArrayList<>();
        Collection<Session> sessions = redisSessionDAO.getActiveSessions();
        for (Session session : sessions)
        {
            UserOnline userOnline = new UserOnline();
            UserDo userDO = new UserDo();
            SimplePrincipalCollection principalCollection = new SimplePrincipalCollection();
            if (session.getAttribute(DefaultSubjectContext.PRINCIPALS_SESSION_KEY) == null)
            {
                continue;
            }
            else
            {
                principalCollection = (SimplePrincipalCollection) session.getAttribute(DefaultSubjectContext.PRINCIPALS_SESSION_KEY);
                userDO = (UserDo) principalCollection.getPrimaryPrincipal();
                userOnline.setUsername(userDO.getUserName());
            }
            userOnline.setId((String) session.getId());
            userOnline.setHost(session.getHost());
            userOnline.setStartTimestamp(session.getStartTimestamp());
            userOnline.setLastAccessTime(session.getLastAccessTime());
            userOnline.setTimeout(session.getTimeout());
            if(!StringUtils.equals(address,session.getHost())){
                String result = HttpUtils.getMethod("http://ip.taobao.com/service/getIpInfo.php?ip=" + session.getHost(), 20000);
                JSONObject object = JSON.parseObject(result);
                int code = object.getIntValue("code");
                if(code == 0){
                    JSONObject data = object.getJSONObject("data");
                    String country = data.getString("country");
                    String city = data.getString("city");
                    String region = data.getString("region");
                    String isp = data.getString("isp");
                    userOnline.setSystemHost(country.concat("-").concat(city).concat("-").concat(region).concat("-").concat(isp));
                }else{
                    log.error("查询在线用户{}","请求Ip---》》》 地址失败" );
                }
            }else{
                userOnline.setSystemHost("局域网地址");
            }
            list.add(userOnline);
        }
        return list;
    }

    public Collection <Session> sessionList()
    {
        return redisSessionDAO.getActiveSessions();
    }

    public UserOnline findSessionIdByUserName(String userName) {
        List <UserOnline> list = new ArrayList <>();
        Collection <Session> sessions = redisSessionDAO.getActiveSessions();
        for (Session session : sessions)
        {
            UserOnline userOnline = new UserOnline();
            UserDo userDO = new UserDo();
            SimplePrincipalCollection principalCollection = new SimplePrincipalCollection();
            if (session.getAttribute(DefaultSubjectContext.PRINCIPALS_SESSION_KEY) == null)
            {
                continue;
            }
            else
            {
                principalCollection = (SimplePrincipalCollection) session.getAttribute(DefaultSubjectContext.PRINCIPALS_SESSION_KEY);
                userDO = (UserDo) principalCollection.getPrimaryPrincipal();
                userOnline.setUsername(userDO.getUserName());
            }
            userOnline.setId((String) session.getId());
            userOnline.setHost(session.getHost());
            userOnline.setStartTimestamp(session.getStartTimestamp());
            userOnline.setLastAccessTime(session.getLastAccessTime());
            userOnline.setTimeout(session.getTimeout());
            list.add(userOnline);
        }
        for(UserOnline user : list){
            if(StringUtils.equals(user.getUsername(),userName)){
                return user;
            }
        }
        return null;
    }

    public boolean forceLogout(String sessionId)
    {
        Session session = redisSessionDAO.readSession(sessionId);
        session.setTimeout(0);
        return true;
    }
}
