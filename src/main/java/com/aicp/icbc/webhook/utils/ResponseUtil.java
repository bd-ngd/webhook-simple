package com.aicp.icbc.webhook.utils;

import com.aicp.icbc.webhook.vo.ResponseDataVO;
import com.aicp.icbc.webhook.enums.ResponseStatusEnum;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

/**
 * @Description: 返回信息工具类
 * @Author: 吴开云
 * @Date: 2019/8/16 0016
 * @Version： 1.0
 */
@Slf4j
public class ResponseUtil {

    /**
     * 请求成功
     * @param object
     * @return
     */
    public static String success(Object object){
        ObjectMapper objectMapper = new ObjectMapper();
        ResponseDataVO responseVO = new ResponseDataVO();
        responseVO.setCode(ResponseStatusEnum.Success.getStatus());
        responseVO.setMsg(ResponseStatusEnum.Success.getMessage());
        responseVO.setTime(System.currentTimeMillis());
        responseVO.setData(object);
        String resultStr = "";
        try {
            resultStr = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(responseVO);
        }catch (IOException e){
            log.error(e.getMessage());
            resultStr = responseVO.toString();
        }
        return resultStr;
    }

    /**
     * 请求成功-但未匹配到服务
     * @param object
     * @return
     */
    public static String serverNotMatch(Object object){
        ResponseDataVO responseVO = new ResponseDataVO();
        ObjectMapper objectMapper = new ObjectMapper();
        responseVO.setCode(ResponseStatusEnum.ServerNotMatch.getStatus());
        responseVO.setMsg(ResponseStatusEnum.ServerNotMatch.getMessage());
        responseVO.setTime(System.currentTimeMillis());
        responseVO.setData(object);
        String resultStr = "";
        try {
            resultStr = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(responseVO);
        }catch (IOException e){
            log.error(e.getMessage());
            resultStr = responseVO.toString();
        }
        return resultStr;
    }

}
