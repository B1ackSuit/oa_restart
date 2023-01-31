package cn.ean.oaemp.service.impl;

import cn.ean.oaemp.mapper.EmployeeMapper;
import cn.ean.oaemp.model.bo.PageBO;
import cn.ean.oaemp.model.bo.ResponseBO;
import cn.ean.oaemp.model.po.EmployeePO;
import cn.ean.oaemp.service.IEmployeeService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Map;

/**
 * @FileName EmployeeServiceImpl
 * @Author ean
 * @Date 2023/1/28 22:35
 **/
@Service
public class EmployeeServiceImpl extends ServiceImpl<EmployeeMapper, EmployeePO> implements IEmployeeService {

    private EmployeeMapper employeeMapper;

    @Autowired
    public EmployeeServiceImpl(EmployeeMapper employeeMapper) {
        this.employeeMapper = employeeMapper;
    }

    /**
     * 获取所有员工(分页)
     *
     * @param currentPage
     * @param size
     * @param employee
     * @param beginDateScope
     * @return
     */
    @Override
    public PageBO getEmployeeByPage(Integer currentPage, Integer size, EmployeePO employee, LocalDate[] beginDateScope) {
        Page<EmployeePO> page = new Page<>(currentPage, size);
        IPage<EmployeePO> employeePOIPage = employeeMapper.getEmployeeByPage(page, employee, beginDateScope);
        PageBO pageBO = new PageBO(employeePOIPage.getTotal(), employeePOIPage.getRecords());
        return pageBO;
    }

    /**
     * 获取工号
     *
     * @return ResponseBO
     */
    @Override
    public ResponseBO maxWorkId() {
        List<Map<String, Object>> maps = employeeMapper.selectMaps(
                new QueryWrapper<EmployeePO>().select("max(work_id)")
        );
        return ResponseBO.success(null,
                String.format("%08d", Integer.parseInt(maps.get(0).get("max(work_id").toString()) + 1));
    }

    /**
     * 添加员工
     *
     * @param employee
     * @return ResponseBO
     */
    @Override
    public ResponseBO insertEmployee(EmployeePO employee) {
        LocalDate beginContract = employee.getBeginContract();
        LocalDate endContract = employee.getEndContract();
        long days = beginContract.until(endContract, ChronoUnit.DAYS);
        DecimalFormat decimalFormat = new DecimalFormat("##.00");
        employee.setContractTerm(Double.parseDouble(decimalFormat.format(days/365.00)));
        if (1 == employeeMapper.insert(employee)) {
            return ResponseBO.success("人员添加成功");
        }
        return ResponseBO.error("人员添加失败");
    }

    /**
     * 查询员工
     *
     * @return 根据id查询的员工
     */
    @Override
    public List<EmployeePO> getEmployee(Integer id) {
        return employeeMapper.getEmployee(id);
    }

}
