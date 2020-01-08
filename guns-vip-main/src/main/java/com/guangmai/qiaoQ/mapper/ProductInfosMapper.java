package com.guangmai.qiaoQ.mapper;

import com.guangmai.qiaoQ.entity.ProductInfos;
import com.guangmai.qiaoQ.model.ProductInfosParam;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author dongyang
 * @since 2019-11-07
 */
public interface ProductInfosMapper extends BaseMapper<ProductInfos> {

    Integer addProductInfos(ProductInfos productInfos);

    /**
     * 获取列表
     *
     * @author dongyang
     * @Date 2019-11-07
     */
    List<ProductInfosParam> customList(@Param("paramCondition") ProductInfosParam paramCondition);

    /**
     * 获取map列表
     *
     * @author dongyang
     * @Date 2019-11-07
     */
    List<Map<String, Object>> customMapList(@Param("paramCondition") ProductInfosParam paramCondition);

    /**
     * 获取分页实体列表
     *
     * @author dongyang
     * @Date 2019-11-07
     */
    Page<ProductInfosParam> customPageList(@Param("page") Page page, @Param("paramCondition") ProductInfosParam paramCondition);

    /**
     * 获取分页map列表
     *
     * @author dongyang
     * @Date 2019-11-07
     */
    Page<Map<String, Object>> customPageMapList(@Param("page") Page page, @Param("paramCondition") ProductInfosParam paramCondition);

}
