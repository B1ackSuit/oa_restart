package cn.ean.oaemp.mapper;


import cn.ean.oaemp.model.po.UserPO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * @FileName UserMapper
 * @Author ean
 * @Date 2023/1/3 18:30
 **/
@Mapper
public interface UserMapper extends BaseMapper<UserPO> {
}