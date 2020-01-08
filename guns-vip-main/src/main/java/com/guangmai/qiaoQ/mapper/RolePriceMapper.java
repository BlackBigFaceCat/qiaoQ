package com.guangmai.qiaoQ.mapper;

import com.guangmai.qiaoQ.entity.RolePrices;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.guangmai.qiaoQ.model.RolePriceParam;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author dongyang
 * @since 2019-12-17
 */
public interface RolePriceMapper extends BaseMapper<RolePrices> {
    
    /** @Description: 新增关系
    * @Title:  addInfos
    * @Parameters [rolePriceParam]
    * @return java.lang.Integer
    * @author ludongyang
    * @date 2019/12/28 18:17
    */ 
    Integer addInfos(@Param("rolePriceParam") RolePriceParam rolePriceParam);


    /** @Description: 显示商品id和角色id对应的 价格记录
    * @Title:  listProductPriceByRoleIdAndProductId
    * @Parameters [roleId, productId]
    * @return com.guangmai.qiaoQ.model.RolePriceParam
    * @author ludongyang
    * @date 2019/12/29 19:34
    */ 
    RolePriceParam listProductPriceByRoleIdAndProductId(@Param("roleId") Long roleId,@Param("productId") Long productId);
    
    /** @Description: 选择角色id对应父ID的记录。
    * @Title:  listProductPriceParentByRoleId
    * @Parameters [roleId]
    * @return com.guangmai.qiaoQ.model.RolePriceParam
    * @author ludongyang
    * @date 2019/12/29 19:34
    */ 
    RolePriceParam listProductPriceParentByRoleId(@Param("roleId") Long roleId);


    /**
     * 获取列表
     *
     * @author dongyang
     * @Date 2019-12-17
     */
    List<RolePriceParam> customList(@Param("paramCondition") RolePriceParam paramCondition);

    /**
     * 获取map列表
     *
     * @author dongyang
     * @Date 2019-12-17
     */
    List<Map<String, Object>> customMapList(@Param("paramCondition") RolePriceParam paramCondition);

    /**
     * 显示所有父节点
     * @return
     */
    Page<RolePriceParam> listRolePrice(@Param("page") Page page);

    /**
     * 获取分页实体列表
     *
     * @author dongyang
     * @Date 2019-12-17
     */
    Page<RolePriceParam> customPageList(@Param("page") Page page, @Param("paramCondition") RolePriceParam paramCondition);

    /**
     * 获取分页map列表
     *
     * @author dongyang
     * @Date 2019-12-17
     */
    Page<Map<String, Object>> customPageMapList(@Param("page") Page page, @Param("paramCondition") RolePriceParam paramCondition);


}
