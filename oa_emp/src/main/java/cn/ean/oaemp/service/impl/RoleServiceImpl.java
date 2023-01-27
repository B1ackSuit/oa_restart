package cn.ean.oaemp.service.impl;

import cn.ean.oaemp.mapper.RoleMapper;
import cn.ean.oaemp.model.po.RolePO;
import cn.ean.oaemp.service.IRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * @FileName RoleServiceImpl
 * @Author ean
 * @Date 2023/1/27 23:43
 **/
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, RolePO> implements IRoleService {
}
