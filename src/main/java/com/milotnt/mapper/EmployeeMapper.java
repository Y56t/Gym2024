package com.milotnt.mapper;

import com.milotnt.entity.Employee;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author author
 * @since 2024-12-23
 */
public interface EmployeeMapper extends BaseMapper<Employee> {
    /**
     * 新增功能
     */
    Boolean add(Employee employee);
    /**
     * 删除功能
     */
    Boolean delete(int employeeAccount);
    /**
     * 根据员工账号修改员工信息功能
     */
    Boolean update(Employee employee);
    /**
     * 查询功能
     */
    List<Employee> selectAll();


}
