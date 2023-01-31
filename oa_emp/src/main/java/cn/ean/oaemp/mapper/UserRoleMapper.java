package cn.ean.oaemp.mapper;

import cn.ean.oaemp.model.po.UserRolePO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @FileName UserRoleMapper
 * @Author ean
 * @Date 2023/1/28 16:17
 **/
@Mapper
public interface UserRoleMapper extends BaseMapper<UserRolePO> {

    /**
     * 更新用户角色
     * @param userId
     * @param rids
     * @return
     */
    Integer updateUserRole(@Param("userId") Integer userId, @Param("rids") Integer[] rids);
}
