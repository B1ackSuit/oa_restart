package cn.ean.oaemp.service.impl;

import cn.ean.oaemp.mapper.NationMapper;
import cn.ean.oaemp.model.po.NationPO;
import cn.ean.oaemp.service.INationService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * @FileName NationServiceImpl
 * @Author ean
 * @Date 2023/1/31 17:14
 **/
@Service
public class NationServiceImpl extends ServiceImpl<NationMapper, NationPO> implements INationService {
}
