package com.milotnt.service;

import com.milotnt.entity.Employee;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author author
 * @since 2024-12-23
 */
public interface IEmployeeService extends IService<Employee> {
    /**
     * 新增功能
     */
    Boolean add(Employee employee);
    /**
     * 删除功能
     */
    Boolean delete(int employeeAccount);
    /**
     * 修改功能
     */
    Boolean update(Employee employee);
    /**
     * 查询功能
     */
    List<Employee> selectAll();

    //根据员工账号查询员工
    List<Employee> selectByEmployeeAccount(Integer employeeAccount);


}
