package cn.ean.oaemp.mapper;


import cn.ean.oaemp.model.po.MenuPO;
import cn.ean.oaemp.model.po.UserPO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @FileName UserMapper
 * @Author ean
 * @Date 2023/1/3 18:30
 **/
@Mapper
public interface UserMapper extends BaseMapper<UserPO> {

    /**
     * 获取所有用户
     *
     * @param keyWords
     * @return 用户
     */
    List<UserPO> getAllUsers(Integer pkId, String keyWords);
}
