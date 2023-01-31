package cn.ean.oaemp.controller;

import cn.ean.oaemp.model.bo.ResponseBO;
import cn.ean.oaemp.model.po.DepartmentPO;
import cn.ean.oaemp.service.IDepartmentService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @FileName DepartmentController
 * @Author ean
 * @Date 2023/1/28 14:58
 **/
@RestController
@RequestMapping("/system/basic/department")
public class DepartmentController {

    private IDepartmentService departmentService;

    @Autowired
    public DepartmentController(IDepartmentService departmentService) {
        this.departmentService = departmentService;
    }


    @ApiOperation(value = "获取所有部门")
    @GetMapping("/")
    public List<DepartmentPO> getAllDepartments() {
        return departmentService.getAllDepartments();
    }

    @ApiOperation(value = "添加部门")
    @PostMapping("/")
    public ResponseBO addDep(@RequestBody DepartmentPO dep) {
        return departmentService.addDep(dep);
    }

    @ApiOperation(value = "删除部门")
    @DeleteMapping("/{id}")
    public ResponseBO deleteDep(@PathVariable Integer id) {
        return departmentService.deleteDep(id);
    }
}
