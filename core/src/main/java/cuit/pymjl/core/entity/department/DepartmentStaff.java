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
 * 部门与员工关系类
 */
public class DepartmentStaff {
    /**
     * id.主键自增
     */
    private Long id;
    /**
     * 员工的编号
     */
    private Integer staffId;
    /**
     * 员工的名字
     */
    private String staffName;
    /**
     * 员工的职位
     */
    private Integer staffPosition;
    /**
     * 部门的编号
     */
    private Integer staffDepartmentId;
    /**
     * 部门名字
     */
    private String staffDepartmentName;
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
