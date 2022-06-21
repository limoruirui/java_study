package com.company.mhl.service;

import com.company.mhl.dao.EmployeeDAO;
import com.company.mhl.domain.Employee;

//完成对employee的各种操作 通过employeedao
public class EmployeeService {
    private EmployeeDAO employeeDAO = new EmployeeDAO();

    //根据empid 和pwd 返回对象
    public Employee getEmployeeByIdAndPwd(String empId, String pwd) {
        Employee employee = employeeDAO.querySingle("select * from employee where empId = ? and pwd = md5(?)", Employee.class, empId, pwd);
        return employee;
    }
}
