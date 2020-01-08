package com.guangmai.qiaoQ.model;

import lombok.Data;
import cn.stylefeng.roses.kernel.model.validator.BaseValidatingParam;
import java.util.Date;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * <p>
 * 
 * </p>
 *
 * @author dongyang
 * @since 2019-12-17
 */
@Data
public class RolePriceParam implements Serializable, BaseValidatingParam {

    private static final long serialVersionUID = 1L;


    /**
     * 角色 价钱 关联表
     */
    private Integer id;

    /**
     * 角色ID
     */
    private Long roleId;

    /**
     * 价钱
     */
    private String price;

    /**
     * 商品ID
     */
    private String productId;

    /**
     * 产品名称
     */
    private String productName;

    /**
     * 商品价钱
     */
    private String productPrice;

    /**
     * 角色名称
     */
    private String roleName;

    /**
     * 价钱百分比
     */
    private String pricePresent;

    private String isParent;

    private String productImage;

    @Override
    public String checkParam() {
        return null;
    }

}
