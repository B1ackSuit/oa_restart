package cn.ean.oaemp.mapper;

import cn.ean.oaemp.model.po.DepartmentPO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @FileName DepartmentMapper
 * @Author ean
 * @Date 2023/1/28 14:54
 **/
@Mapper
public interface DepartmentMapper extends BaseMapper<DepartmentPO> {

    /**
     * 获取所有部门
     * @return 所有部门
     */
    List<DepartmentPO> getAllDepartments(Integer topDepartment);

    /**
     * 添加部门
     * @param dep
     */
    void addDep(DepartmentPO dep);

    /**
     * 删除部门
     * @param department
     */
    void deleteDep(DepartmentPO department);
}
