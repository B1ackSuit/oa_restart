package cn.ean.oaemp.mapper;

import cn.ean.oaemp.model.po.EmployeePO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.time.LocalDate;
import java.util.List;

/**
 * @FileName EmployeeMapper
 * @Author ean
 * @Date 2023/1/28 21:50
 **/
@Mapper
public interface EmployeeMapper extends BaseMapper<EmployeePO> {

    /**
     * 获取所有员工(分页)
     * @param page
     * @param employee
     * @param beginDateScope
     */
    IPage<EmployeePO> getEmployeeByPage(Page<EmployeePO> page, @Param("employee") EmployeePO employee,
                                        @Param("beginDateScope") LocalDate[] beginDateScope);

    /**
     * 查询员工
     * @param id
     * @return 根据id查询的员工
     */
    List<EmployeePO> getEmployee(Integer id);
}
