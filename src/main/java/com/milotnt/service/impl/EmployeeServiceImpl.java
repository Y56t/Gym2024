package com.milotnt.service.impl;

import com.milotnt.entity.Employee;
import com.milotnt.mapper.EmployeeMapper;
import com.milotnt.service.IEmployeeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author author
 * @since 2024-12-23
 */
@Service
public class EmployeeServiceImpl extends ServiceImpl<EmployeeMapper, Employee> implements IEmployeeService {
    @Autowired
    private EmployeeMapper employeeMapper;
    //新增
    @Override
    public Boolean add(Employee employee) {
        return employeeMapper.add(employee);
    }

    //删除
    @Override
    public Boolean delete(int employeeAccount) {
        return employeeMapper.delete(employeeAccount);
    }

    //修改
    public Boolean update(Employee employee) {
        return employeeMapper.update(employee);
    }

    //查询
    @Override
    public List<Employee> selectAll() {
        List<Employee> Employees = employeeMapper.selectAll();
        if (Employees !=null){
            return Employees;
        }else {
            return null;
        }
    }

    @Override
    public List<Employee> selectByEmployeeAccount(Integer employeeAccount) {
        return employeeMapper.selectByEmployeeAccount(employeeAccount);
    }
}
