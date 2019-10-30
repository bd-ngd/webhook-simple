package com.aicp.icbc.webhook.service.impl;

import com.aicp.icbc.webhook.service.BusinessService;

import java.util.Map;

/**
 * @Description:
 * @Author: 吴开云
 * @Date: 2019/10/29 0029
 * @Version： 1.0
 */
public class UserInfoService implements BusinessService {
    @Override
    public boolean isServiceBeCalled(Map<String, Object> requestMap) {
        return false;
    }

    @Override
    public Map<String, Object> getResult(Map<String, Object> requestMap) {
        return null;
    }
}
