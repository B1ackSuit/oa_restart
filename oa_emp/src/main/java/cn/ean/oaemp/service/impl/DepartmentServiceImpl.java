package cn.ean.oaemp.service.impl;

import cn.ean.oaemp.mapper.DepartmentMapper;
import cn.ean.oaemp.model.bo.ResponseBO;
import cn.ean.oaemp.model.po.DepartmentPO;
import cn.ean.oaemp.service.IDepartmentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @FileName DepartmentServiceImpl
 * @Author ean
 * @Date 2023/1/28 14:55
 **/
@Service
public class DepartmentServiceImpl extends ServiceImpl<DepartmentMapper, DepartmentPO> implements IDepartmentService {

    /**
     * 获取部门时的最上级部门id
     */
    private static final Integer TOP_DEPARTMENT = -1;

    /**
     * 添加和删除存储过程的返回值之一
     * 根据存储过程设计，1表示操作成功
     */
    public static final Integer SUCCESS_CODE = 1;

    /**
     * 删除部门存储过程的返回值之一
     * 根据存储过程设计，-2表示要删除的部门下还有子部门
     */
    public static final Integer CHILDREN_ERROR_CODE = -2;

    /**
     * 删除部门存储过程的返回值之一
     * 根据存储过程设计，-1表示要删除的部门内还有员工
     */
    public static final Integer EMP_ERROR_CODE = -1;

    private DepartmentMapper departmentMapper;

    @Autowired
    public DepartmentServiceImpl(DepartmentMapper departmentMapper) {
        this.departmentMapper = departmentMapper;
    }

    public DepartmentServiceImpl() {
    }

    /**
     * 获取所有部门
     *
     * @return 所有部门
     */
    @Override
    public List<DepartmentPO> getAllDepartments() {
        return departmentMapper.getAllDepartments(TOP_DEPARTMENT);
    }

    /**
     * 添加部门
     *
     * @param dep
     * @return ResponseBO
     */
    @Override
    public ResponseBO addDep(DepartmentPO dep) {
        dep.setEnabled(true);
        departmentMapper.addDep(dep);
        if (SUCCESS_CODE.equals(dep.getResult())){
            return ResponseBO.success("部门添加成功", dep);
        }
        return ResponseBO.error("部门添加失败");
    }

    /**
     * 删除部门
     *
     * @param id
     * @return ResponseBO
     */
    @Override
    public ResponseBO deleteDep(Integer id) {
        DepartmentPO department = new DepartmentPO();
        department.setPkId(id);
        departmentMapper.deleteDep(department);

        if (CHILDREN_ERROR_CODE.equals(department.getResult())) {
            return ResponseBO.error("部门删除失败，此部门下还有子部门");
        } else if (EMP_ERROR_CODE.equals(department.getResult())) {
            return ResponseBO.error("部门删除失败，此部门内还有员工");
        } else if (SUCCESS_CODE.equals(department.getResult())) {
            return ResponseBO.success("部门删除成功");
        }

        return ResponseBO.error("部门删除失败");
    }
}
