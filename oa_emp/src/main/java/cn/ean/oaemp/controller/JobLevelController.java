package cn.ean.oaemp.controller;

import cn.ean.oaemp.model.bo.ResponseBO;
import cn.ean.oaemp.model.po.JobLevelPO;
import cn.ean.oaemp.service.IJobLevelService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

/**
 * @FileName JobLevelController
 * @Author ean
 * @Date 2023/1/27 23:14
 **/
@RestController
@RequestMapping("/system/basic/joblevel")
public class JobLevelController {

    private IJobLevelService jobLevelService;

    @Autowired
    public JobLevelController(IJobLevelService jobLevelService) {
        this.jobLevelService = jobLevelService;
    }

    @ApiOperation(value = "获取所有职称")
    @GetMapping("/")
    public List<JobLevelPO> getAllJobLevels() {
        return jobLevelService.list();
    }

    @ApiOperation(value = "添加职称")
    @PostMapping("/")
    public ResponseBO addJobLevel(@RequestBody JobLevelPO jobLevel) {
        jobLevel.setCreateTime(LocalDateTime.now());

        if (jobLevelService.save(jobLevel)) {
            return ResponseBO.success("职称信息添加成功");
        }
        return ResponseBO.error("职称信息添加失败");
    }

    @ApiOperation(value = "删除职称信息")
    @DeleteMapping("/{id}")
    public ResponseBO deleteJobLevel(@PathVariable Integer id) {
        if (jobLevelService.removeById(id)) {
            return ResponseBO.success("职称信息删除成功");
        }
        return ResponseBO.error("职称信息删除失败");
    }

    @ApiOperation(value = "批量删除职称信息")
    @DeleteMapping("/")
    public ResponseBO deleteJobLevelsByIds(Integer[] ids) {
        // MP3.5.1 : joblevelService.removeByIds() 此接口出错，改为removeBatchByIds()
        // MP3.3.1使用removeByIds()
        if (jobLevelService.removeByIds(Arrays.asList(ids))) {
            return ResponseBO.success("批量删除职称成功");
        }
        return ResponseBO.error("批量删除职称失败");
    }
}
