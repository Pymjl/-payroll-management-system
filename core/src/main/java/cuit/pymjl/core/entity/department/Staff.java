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
public class Staff {
    /**
     * id.主键自增
     */
    private Long id;
    /**
     * 员工编号
     */
    private Integer staffId;
    /**
     * 员工姓名
     */
    private String staffName;
    /**
     * 员工性别
     */
    private String staffSex;
    /**
     * 员工年龄
     */
    private Integer staffAge;
    /**
     * 员工所属部门编号
     */
    private Integer staffDepartmentId;
    /**
     * 员工电话号码
     */
    private String staffPhoneNumber;
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
