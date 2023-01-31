package cn.ean.oaemp.model.bo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @FileName PageBO
 * @Author ean
 * @Date 2023/1/28 20:38
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PageBO {

    /**
     * 总条数
     */
    private Long total;

    /**
     * 数据list
     */
    private List<?> data;
}
