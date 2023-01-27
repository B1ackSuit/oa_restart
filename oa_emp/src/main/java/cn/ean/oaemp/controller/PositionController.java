package cn.ean.oaemp.controller;

import cn.ean.oaemp.model.bo.ResponseBO;
import cn.ean.oaemp.model.po.PositionPO;
import cn.ean.oaemp.service.IPositionService;
import io.swagger.annotations.ApiOperation;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

/**
 * @FileName PositionController
 * @Author ean
 * @Date 2023/1/27 20:32
 **/
@RestController
@RequestMapping("/system/basic/pos")
public class PositionController {

    private IPositionService positionService;

    @Autowired
    public PositionController(IPositionService positionService) {
        this.positionService = positionService;
    }

    @ApiOperation(value = "获取所有职位信息")
    @GetMapping("/")
    public List<PositionPO> getAllPositions() {
        return positionService.list();
    }

    @ApiOperation(value = "添加职位信息")
    @PostMapping("/")
    public ResponseBO addPosition(@RequestBody PositionPO position) {
        position.setCreateTime(LocalDateTime.now());
        if (positionService.save(position)) {
            return ResponseBO.success("职位信息添加成功");
        }
        return ResponseBO.error("职位信息添加失败");
    }

    @ApiOperation(value = "更新职位信息")
    @PutMapping("/")
    public ResponseBO updatePosition(@RequestBody PositionPO positionPO) {
        if (positionService.updateById(positionPO)) {
            return ResponseBO.success("职位信息更新成功");
        }
        return ResponseBO.error("职位信息更新失败");
    }

    @ApiOperation(value = "删除职位信息")
    @DeleteMapping("/{id}")
    public ResponseBO deletePosition(@PathVariable Integer id) {
        if (positionService.removeById(id)) {
            return ResponseBO.success("职位信息删除成功");
        }
        return ResponseBO.error("职位信息删除失败");
    }

    @ApiOperation(value = "批量删除职位信息")
    @DeleteMapping("/")
    public ResponseBO deletePositionsByIds(Integer[] ids) {
        // positionService.removeByIds() 此接口出错，改为removeBatchByIds()
        // MP3.3.1使用removeByIds()
        if (positionService.removeByIds(Arrays.asList(ids))) {
            return ResponseBO.success("职位批量删除成功");
        }
        return ResponseBO.error("职位批量删除失败");
    }
}
