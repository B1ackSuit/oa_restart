package cn.ean.oaemp.mapper;

import cn.ean.oaemp.model.po.RolePO;
import cn.ean.oaemp.model.po.UserPO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @FileName RoleMapper
 * @Author ean
 * @Date 2023/1/26 22:19
 **/
@Mapper
public interface RoleMapper extends BaseMapper<RolePO> {

    /**
     * 根据用户id查询角色列表
     * @param userId
     * @return
     */
    List<RolePO> getRoles(Integer userId);

}
