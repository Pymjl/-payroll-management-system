package cuit.pymjl.core.service.faculty;

import cuit.pymjl.core.entity.faculty.FacultyDetails;
import cuit.pymjl.core.entity.faculty.Faculty;

import java.util.List;

public interface FacultyService {
    /**
     * 增加部门
     */
    void addDepartment(FacultyDetails facultyDetails);

    /**
     * 删除部门
     */
    void deleteDepartment(int departmentId, Long facultyId);
}
