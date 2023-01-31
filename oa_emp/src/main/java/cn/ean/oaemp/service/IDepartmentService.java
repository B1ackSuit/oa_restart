package cn.ean.oaemp.service;

import cn.ean.oaemp.model.bo.ResponseBO;
import cn.ean.oaemp.model.po.DepartmentPO;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * @FileName IDepartmentService
 * @Author ean
 * @Date 2023/1/28 14:55
 **/
public interface IDepartmentService extends IService<DepartmentPO> {

    /**
     * 获取所有部门
     * @return 所有部门
     */
    List<DepartmentPO> getAllDepartments();

    /**
     * 添加部门
     * @param dep
     * @return ResponseBO
     */
    ResponseBO addDep(DepartmentPO dep);

    /**
     * 删除部门
     * @param id
     * @return ResponseBO
     */
    ResponseBO deleteDep(Integer id);
}
