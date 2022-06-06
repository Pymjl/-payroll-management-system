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
     * 员工部门id
     */
    private Integer departmentId;

    /**
     * 员工部门所属的系id
     */
    private Integer facultyId;
}
