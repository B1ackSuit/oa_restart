package cn.ean.oaemp.service.impl;

import cn.ean.oaemp.mapper.PoliticsStatusMapper;
import cn.ean.oaemp.model.po.PoliticsStatusPO;
import cn.ean.oaemp.service.IPoliticsStatusService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * @FileName PoliticsStatusServiceImpl
 * @Author ean
 * @Date 2023/1/31 17:23
 **/
@Service
public class PoliticsStatusServiceImpl extends ServiceImpl<PoliticsStatusMapper, PoliticsStatusPO>
    implements IPoliticsStatusService {
}
