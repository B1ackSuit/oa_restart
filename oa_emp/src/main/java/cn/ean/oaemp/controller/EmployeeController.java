package cn.ean.oaemp.controller;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import cn.afterturn.easypoi.excel.entity.enmus.ExcelType;
import cn.ean.oaemp.model.bo.PageBO;
import cn.ean.oaemp.model.bo.ResponseBO;
import cn.ean.oaemp.model.po.*;
import cn.ean.oaemp.service.*;
import io.swagger.annotations.ApiOperation;
import io.swagger.models.auth.In;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.util.List;

/**
 * @FileName EmployeeController
 * @Author ean
 * @Date 2023/1/28 22:41
 **/
@RestController
@RequestMapping("/employee/basic")
public class EmployeeController {

    private IEmployeeService employeeService;

    private INationService nationService;

    private IPoliticsStatusService politicsStatusService;

    private IJobLevelService jobLevelService;

    private IPositionService positionService;

    private IDepartmentService departmentService;

    @Autowired
    public EmployeeController(IEmployeeService employeeService,
                              INationService nationService,
                              IPoliticsStatusService politicsStatusService,
                              IJobLevelService jobLevelService,
                              IPositionService positionService,
                              IDepartmentService departmentService) {
        this.employeeService = employeeService;
        this.nationService = nationService;
        this.politicsStatusService = politicsStatusService;
        this.jobLevelService = jobLevelService;
        this.positionService = positionService;
        this.departmentService = departmentService;
    }

    @ApiOperation(value = "获取所有员工(分页)")
    @GetMapping("/")
    public PageBO getEmployeeByPage(@RequestParam(defaultValue = "1") Integer currentPage,
                                    @RequestParam(defaultValue = "10") Integer size,
                                    EmployeePO employeePO,
                                    LocalDate[] beginDateScope) {
        return employeeService.getEmployeeByPage(currentPage, size, employeePO, beginDateScope);
    }

    @ApiOperation(value = "添加员工")
    @PostMapping("/")
    public ResponseBO addEmp(@RequestBody EmployeePO employeePO) {
        return employeeService.insertEmployee(employeePO);
    }

    @ApiOperation(value = "获取所有民族")
    @GetMapping("/nations")
    public List<NationPO> getAllNations() {
        return nationService.list();
    }

    @ApiOperation(value = "获取所有政治面貌")
    @GetMapping("/politicsstatus")
    public List<PoliticsStatusPO> getAllPoliticsStatus() {
        return politicsStatusService.list();
    }

    @ApiOperation(value = "获取所有职称")
    @GetMapping("/joblevels")
    public List<JobLevelPO> getAllJobLevels() {
        return jobLevelService.list();
    }

    @ApiOperation(value = "获取所有职位")
    @GetMapping("/positions")
    public List<PositionPO> getAllPositions() {
        return positionService.list();
    }

    @ApiOperation(value = "获取所有部门")
    @GetMapping("/deps")
    public List<DepartmentPO> getAllDepartments() {
        return departmentService.getAllDepartments();
    }

    @ApiOperation(value = "获取工号")
    @GetMapping("/maxWorkId")
    public ResponseBO maxWorkId() {
        return employeeService.maxWorkId();
    }

    @ApiOperation(value = "删除员工")
    @DeleteMapping("/{id}")
    public ResponseBO deleteEmp(@PathVariable Integer id) {
        if (employeeService.removeById(id)) {
            return ResponseBO.success("员工删除成功");
        }
        return ResponseBO.error("员工删除失败");
    }

    @ApiOperation(value = "更新员工")
    @PutMapping("/")
    public ResponseBO updateEmp(@RequestBody EmployeePO employeePO) {
        if (employeeService.updateById(employeePO)) {
            return ResponseBO.success("员工更新成功");
        }
        return ResponseBO.error("员工更新失败");
    }

    @ApiOperation(value = "导出员工数据")
    @GetMapping(value = "/export", produces = "application/octet-stream")
    public void exportEmployee(HttpServletResponse response) {
        List<EmployeePO> list = employeeService.getEmployee(null);
        ExportParams params = new ExportParams("员工表", "员工表", ExcelType.HSSF);
        Workbook book = ExcelExportUtil.exportExcel(params, EmployeePO.class, list);
        ServletOutputStream out = null;
        try {
            response.setHeader("Content-Type", "application/octet-stream");
            response.setHeader("Content-Disposition", "attachment; filename=" +
                    URLEncoder.encode("员工表.xls", StandardCharsets.UTF_8));
            out = response.getOutputStream();
            book.write(out);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (null != out) {
                try {
                    out.flush();
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @ApiOperation(value = "导入员工数据")
    @PostMapping("/import")
    public ResponseBO importEmployeePO(MultipartFile file) {
        ImportParams params = new ImportParams();
        params.setTitleRows(1);
        List<NationPO> nationPOList = nationService.list();
        List<PoliticsStatusPO> politicsStatusPOList = politicsStatusService.list();
        List<DepartmentPO> departmentPOList = departmentService.list();
        List<JobLevelPO> jobLevelPOList = jobLevelService.list();
        List<PositionPO> positionPOList = positionService.list();
        try {
            List<EmployeePO> list = ExcelImportUtil.importExcel(file.getInputStream(), EmployeePO.class, params);
            list.forEach(employee -> {
                // 此处可以后续完善逻辑外键关联
                // 民族id
                employee.setNationId(
                        nationPOList.get(
                                nationPOList.indexOf(
                                        new NationPO(
                                                employee.getNationPO().getNationName()
                                        )
                                )
                        ).getPkId()
                );
                // 政治面貌id
                employee.setPoliticId(
                        politicsStatusPOList.get(
                                politicsStatusPOList.indexOf(
                                        new PoliticsStatusPO(
                                                employee.getPoliticsStatusPO().getPoliticsStatusName()
                                        )
                                )
                        ).getPkId()
                );
                // 部门id
                DepartmentPO departmentPO = new DepartmentPO(employee.getDepartmentPO().getDepName());
            //    int i = departmentPOList.indexOf(employee.getDepartmentPO());
                for (DepartmentPO department : departmentPOList) {
                    if (department.getDepName().equals(departmentPO.getDepName())){
                        employee.setDepartmentId(department.getPkId());
                    }
                }
                // 职称id
                employee.setJobLevelId(
                        jobLevelPOList.get(
                                jobLevelPOList.indexOf(
                                        new JobLevelPO(employee.getJobLevelPO().getLevelName())
                                )
                        ).getPkId()
                );
                // 职位id
                employee.setPosId(
                        positionPOList.get(
                                positionPOList.indexOf(
                                        new PositionPO(employee.getPositionPO().getPositionName())
                                )
                        ).getPkId()
                );
            });
            if (employeeService.saveBatch(list)){
                return ResponseBO.success("导入成功!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseBO.error("导入失败！");
    }
}
