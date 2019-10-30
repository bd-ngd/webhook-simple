package com.aicp.icbc.webhook.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @Description:
 * @Author: 吴开云
 * @Date: 2019/10/29 0029
 * @Version： 1.0
 */
@Data
@Accessors(chain = true)
@AllArgsConstructor
public class UserInfoDto {
    private String userName;
    private String sex;
    private String phoneNum;
}
