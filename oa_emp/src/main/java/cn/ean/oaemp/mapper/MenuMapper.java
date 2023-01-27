package cn.ean.oaemp.mapper;

import cn.ean.oaemp.model.po.MenuPO;
import cn.ean.oaemp.model.po.UserPO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @FileName MenuMapper
 * @Author ean
 * @Date 2023/1/22 23:08
 **/
@Mapper
public interface MenuMapper extends BaseMapper<MenuPO> {

    List<MenuPO> getMenusByUserId(Integer pkId);

    /**
     * 根据角色获取菜单列表
     * @return 菜单
     */
    List<MenuPO> getMenusWithRole();
}
