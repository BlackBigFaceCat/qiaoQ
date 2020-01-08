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
 * @since 2019-11-07
 */
@Data
public class ProductInfosParam implements Serializable, BaseValidatingParam {

    private static final long serialVersionUID = 1L;


    /**
     * 产品ID
     */
    private Integer id;

    /**
     * 产品名称
     */
    private String productName;

    /**
     * 仓库地址
     */
    private String warehouseLocation;

    /**
     * 产品库存数目
     */
    private Integer productNumber;

    /**
     * 产品分类
     */
    private String productClassify;

    /**
     * 货号
     */
    private Integer articleNumber;

    /**
     * 商品图片地址
     */
    private String productImage;

    /**
     * 商品出售价
     */
    private BigDecimal productPrice;

    /**
     * 市场价
     */
    private BigDecimal marketPrice;

    /**
     * 进货价
     */
    private BigDecimal pleasedPrice;

    /**
     * 客户进货价(角色:进货价 ，以JSON格式保存)
     */
    private String customerPleasedPrice;

    @Override
    public String checkParam() {
        return null;
    }

}
