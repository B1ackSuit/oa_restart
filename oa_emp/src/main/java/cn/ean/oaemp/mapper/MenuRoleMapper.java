package cn.ean.oaemp.mapper;

import cn.ean.oaemp.model.po.MenuRolePO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * @FileName MenuRoleMapper
 * @Author ean
 * @Date 2023/1/28 00:06
 **/
@Mapper
public interface MenuRoleMapper extends BaseMapper<MenuRolePO> {
    Integer insertRecord(Integer roleId, Integer[] menuIds);
}
