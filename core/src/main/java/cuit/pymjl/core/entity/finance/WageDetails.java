package cuit.pymjl.core.entity.finance;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

/**
 * @author Riou
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class WageDetails {
    /**
     * id 主键自增
     */
    private Long id;

    /**
     * employeeId
     */
    private Long employeeId;

    /**
     * name 职工姓名
     */
    private String name;

    /**
     * basicWage 基本工资
     */
    private Integer basicWage;

    /**
     * bonus 奖金
     */
    private Integer bonus;

    /**
     * fine 罚款
     */
    private Integer fine;

    /**
     * tax 税收
     */
    private Integer tax;

    /**
     * sumWage 总工资
     */
    private Integer sumWage;

    /**
     * accountNumber 银行卡号
     */
    private String accountNumber;

    /**
     * createTime 创建时间
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT8")
    private Date createTime;

    /**
     * status 发放状态
     */
    private Integer status;

    /**
     * departmentName 部门名称
     */
    private String departmentName;
}
