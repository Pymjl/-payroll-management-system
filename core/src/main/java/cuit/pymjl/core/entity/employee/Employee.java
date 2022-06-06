package cuit.pymjl.core.entity.employee;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString

public class Employee implements Serializable {
    private static final long serialVersionUID = 2615346421532729379L;

    /**
     * id.主键自增
     */
    private long id;

    /**
     *用户id.
     */
    private long userId;

    /**
     * 系id.
     */
    private long facultyId;

    /**
     * 部门id.
     */
    private long departmentId;

    /**
     * 工作时长（整时长）
     */
    private Integer workingHours;

    /**
     * 状态，-1为早退，0为迟到，1为正常
     */
    private Integer status;

    /**
     * 创建时间. 签到时间
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT8")
    private Date createTime;

    /**
     * 更新时间. 签退时间
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT8")
    private Date updateTime;
}
