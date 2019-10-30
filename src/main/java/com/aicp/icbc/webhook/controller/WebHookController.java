/*
 * Copyright (C) 2018 Baidu, Inc. All Rights Reserved.
 */
package com.aicp.icbc.webhook.controller;

import com.aicp.icbc.webhook.dto.UserInfoDto;
import com.aicp.icbc.webhook.utils.RequestUtils;
import com.aicp.icbc.webhook.utils.ResponseUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@RestController
@Slf4j
public class WebHookController{

    @RequestMapping("webhook/api")
    public String getUserInfo(@RequestBody String requestBody) throws IOException {
        //获取request的key-value map
        Map<String, Object> request = RequestUtils.getRequest(requestBody);
        Map<String, Object> data = new HashMap<>();
        Map<String, Object> responseContext = new HashMap<>();
        //根据指令判断业务调用情况
        String action = (String) request.get("action");
        if(action== null || (!"getUserInfo".equals(action))){
            // 没有业务被调用,根据实际需要进行返回,以下只提供参考
            // 返回当前节点中配置的value，如果webhook异常将这个话术返回给用户
            String value = (String) request.get("value");
            responseContext.put("api_response_msg", "请求匹配指令失败");
            responseContext.put("api_response_status", false);
            data.put("context", responseContext);
            return ResponseUtil.serverNotMatch(data);
        }
        //取性别字段
        Map<String,Object> requestContext =  (Map<String,Object>)request.get("context");
        //构造用户信息实体
        UserInfoDto man = new UserInfoDto("张三","男","13301234567");
        UserInfoDto woman = new UserInfoDto("刘婷","女","13307654321");
        UserInfoDto others = new UserInfoDto("其它","暂不知晓","未知");
        UserInfoDto userInfo = null;
        if(requestContext.containsKey("sexStr") && requestContext.get("sexStr")!= null){
            if("男".equals(requestContext.get("sexStr"))){
                userInfo = man;
            }else if("女".equals(requestContext.get("sexStr"))){
                userInfo = woman;
            }else {
                userInfo = others;
            }
        }
        //返回体中设值
        responseContext.put("userName", userInfo.getUserName());
        responseContext.put("sex", userInfo.getSex());
        responseContext.put("phoneNum", userInfo.getPhoneNum());
        //将返回体设值到返回数据的  context域中
        data.put("context",responseContext);
        return ResponseUtil.success(data);
    }

}
