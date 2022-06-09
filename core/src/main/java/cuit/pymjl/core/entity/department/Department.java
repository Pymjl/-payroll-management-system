package cuit.pymjl.core.entity.department;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
/**
 * 部门类
 */
public class Department {
    /**
     * id.主键自增
     */
    private Long id;
    /**
     * 部门编号
     */
    private Integer departmentId;
    /**
     * 部门的名字
     */
    private String departmentName;
    /**
     * 部门员工总数
     */
    private Integer departmentNum;
    /**
     * 部门老大编号
     */
    private Integer departmentBossId;
    /**
     * 创建时间
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;
    /**
     * 更新时间
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date updateTime;
}
