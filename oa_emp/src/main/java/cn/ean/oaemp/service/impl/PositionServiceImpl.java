package cn.ean.oaemp.service.impl;

import cn.ean.oaemp.mapper.PositionMapper;
import cn.ean.oaemp.model.po.PositionPO;
import cn.ean.oaemp.service.IPositionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @FileName PositionServiceImpl
 * @Author ean
 * @Date 2023/1/27 20:35
 **/
@Service
public class PositionServiceImpl extends ServiceImpl<PositionMapper, PositionPO> implements IPositionService {

    private PositionMapper positionMapper;

    @Autowired
    public PositionServiceImpl(PositionMapper positionMapper) {
        this.positionMapper = positionMapper;
    }


}
