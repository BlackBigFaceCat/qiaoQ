package com.guangmai.qiaoQ.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.guangmai.qiaoQ.entity.FileProductRelation;
import com.guangmai.qiaoQ.model.FileProductRelationDTO;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author ludongyang
 * @since 2019-11-28
 */
public interface FileProductRelationMapper extends BaseMapper<FileProductRelation> {

    /** @Description: 显示传入ID 最后添加的那条数据
    * @Title:  listMaxIdRelation
    * @Parameters [userId]
    * @return java.util.Map<java.lang.String,java.lang.Object>
    * @author ludongyang
    * @date 2019/11/28 17:01
    */ 
    Map<String,String> listMaxIdRelation(@Param(value = "userId")Long userId);
    
    /**
     * 获取列表
     *
     * @author ludongyang
     * @Date 2019-11-28
     */
    List<FileProductRelationDTO> customList(@Param("paramCondition") FileProductRelationDTO paramCondition);

    /**
     * 获取map列表
     *
     * @author ludongyang
     * @Date 2019-11-28
     */
    List<Map<String, Object>> customMapList(@Param("paramCondition") FileProductRelationDTO paramCondition);

    /**
     * 获取分页实体列表
     *
     * @author ludongyang
     * @Date 2019-11-28
     */
    Page<FileProductRelationDTO> customPageList(@Param("page") Page page, @Param("paramCondition") FileProductRelationDTO paramCondition);

    /**
     * 获取分页map列表
     *
     * @author ludongyang
     * @Date 2019-11-28
     */
    Page<Map<String, Object>> customPageMapList(@Param("page") Page page, @Param("paramCondition") FileProductRelationDTO paramCondition);

}
