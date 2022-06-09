package cuit.pymjl.core.entity.department;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class StaffAttendenceDTO {
    /**
     * 头像
     */
    private String avatar;
    /**
     * 用户ID
     */
    private Long id;
    /**
     * 用户姓名
     */
    @JsonProperty("nickname")
    private String username;
    /**
     * 部门名称
     */
    private String departmentName;
    /**
     * 缺勤天数
     */
    private Integer leaveNum;
    /**
     * 早退天数
     */
    private Integer lateNum;
    /**
     * 银行卡号
     */
    private String account;
}
