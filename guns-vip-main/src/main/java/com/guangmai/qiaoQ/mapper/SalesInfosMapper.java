package com.guangmai.qiaoQ.mapper;

import com.guangmai.qiaoQ.entity.SalesInfos;
import com.guangmai.qiaoQ.model.SalesInfosParam;
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
 * @author ludongyang
 * @since 2019-11-15
 */
public interface SalesInfosMapper extends BaseMapper<SalesInfos> {

    /**
     * 获取列表
     *
     * @author ludongyang
     * @Date 2019-11-15
     */
    List<SalesInfosParam> customList(@Param("paramCondition") SalesInfosParam paramCondition);

    /**
     * 获取map列表
     *
     * @author ludongyang
     * @Date 2019-11-15
     */
    List<Map<String, Object>> customMapList(@Param("paramCondition") SalesInfosParam paramCondition);

    /**
     * 获取分页实体列表
     *
     * @author ludongyang
     * @Date 2019-11-15
     */
    Page<SalesInfosParam> customPageList(@Param("page") Page page, @Param("paramCondition") SalesInfosParam paramCondition);

    /**
     * 获取分页map列表
     *
     * @author ludongyang
     * @Date 2019-11-15
     */
    Page<Map<String, Object>> customPageMapList(@Param("page") Page page, @Param("paramCondition") SalesInfosParam paramCondition);

}
