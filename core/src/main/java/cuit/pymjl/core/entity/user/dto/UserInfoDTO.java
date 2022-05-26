package cuit.pymjl.core.entity.user.dto;

import lombok.Data;

/**
 * @author Pymjl
 * @version 1.0
 * @date 2022/5/26 23:48
 **/
@Data
public class UserInfoDTO {
    private String username;
    private String password;
    private String code;
}
