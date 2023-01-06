package cn.ean.oa_emp.mapper;


import cn.ean.oa_emp.model.po.UserPO;
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
