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
public class BasicWage {
    /**
     * id.自增
     */
    private Long id;

    /**
     * commonWage 普通员工基本工资
     */
    private Integer commonWage;

    /**
     * managerWage 部门经理基本工资
     */
    private Integer managerWage;

    /**
     * 创建时间
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT8")
    private Date createTime;

    /**
     * 更新时间
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT8")
    private Date updateTime;
}
