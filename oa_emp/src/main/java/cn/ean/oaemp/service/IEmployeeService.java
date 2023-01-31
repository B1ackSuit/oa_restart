package cn.ean.oaemp.service;

import cn.ean.oaemp.model.bo.PageBO;
import cn.ean.oaemp.model.bo.ResponseBO;
import cn.ean.oaemp.model.po.EmployeePO;
import com.baomidou.mybatisplus.extension.service.IService;

import java.time.LocalDate;
import java.util.List;

/**
 * @FileName IEmployeeService
 * @Author ean
 * @Date 2023/1/28 22:33
 **/
public interface IEmployeeService extends IService<EmployeePO> {

    /**
     * 获取所有员工(分页)
     * @param currentPage
     * @param size
     * @param employee
     * @param beginDateScope
     * @return PageBO
     */
    PageBO getEmployeeByPage(Integer currentPage, Integer size, EmployeePO employee, LocalDate[] beginDateScope);

    /**
     * 获取工号
     * @return ResponseBO
     */
    ResponseBO maxWorkId();

    /**
     * 添加员工
     * @param employee
     * @return ResponseBO
     */
    ResponseBO insertEmployee(EmployeePO employee);

    /**
     * 查询员工
     * @return 根据id查询的员工
     */
    List<EmployeePO> getEmployee(Integer id);
}
