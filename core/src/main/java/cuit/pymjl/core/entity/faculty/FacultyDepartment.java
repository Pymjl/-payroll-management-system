package cuit.pymjl.core.entity.faculty;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

/*添加⼀个构造函数，该构造函数含有所有已声明字段属性参数*/
@AllArgsConstructor
/*创建⼀个⽆参构造函数*/
@NoArgsConstructor
/*该注解通常用在实体bean上，不需要写出set和get方法，但是具备实体bean所具备的方法，简化编程提高变成速度。*/
@Data
/*需要打印父类的属性,@ToString就是节省了ToString方法*/
@ToString
public class FacultyDepartment {
    /**
     * 系id
     */
    private Long facultyId;
    /**
     * 部门id
     */
    private Integer departmentId;
    /**
     * 部门名
     */
    private String departmentName;
    /**
     * 部门老大id
     */
    private int departmentBossId;
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
