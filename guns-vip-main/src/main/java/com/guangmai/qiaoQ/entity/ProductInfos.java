package com.guangmai.qiaoQ.entity;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author dongyang
 * @since 2019-11-07
 */
@TableName("product_infos")
public class ProductInfos implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 产品ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 产品名称
     */
    @TableField("product_name")
    private String productName;

    /**
     * 仓库地址
     */
    @TableField("warehouse_location")
    private String warehouseLocation;

    /**
     * 产品库存数目
     */
    @TableField("product_number")
    private Integer productNumber;

    /**
     * 产品分类
     */
    @TableField("product_classify")
    private String productClassify;

    /**
     * 货号
     */
    @TableField("article_number")
    private Integer articleNumber;

    /**
     * 商品图片地址
     */
    @TableField("product_image")
    private String productImage;

    /**
     * 商品出售价
     */
    @TableField("product_price")
    private BigDecimal productPrice;

    /**
     * 市场价
     */
    @TableField("market_price")
    private BigDecimal marketPrice;

    /**
     * 进货价
     */
    @TableField("pleased_price")
    private BigDecimal pleasedPrice;

    /**
     * 客户进货价(角色:进货价 ，以JSON格式保存)
     */
    @TableField("customer_pleased_price")
    private String customerPleasedPrice;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getWarehouseLocation() {
        return warehouseLocation;
    }

    public void setWarehouseLocation(String warehouseLocation) {
        this.warehouseLocation = warehouseLocation;
    }

    public Integer getProductNumber() {
        return productNumber;
    }

    public void setProductNumber(Integer productNumber) {
        this.productNumber = productNumber;
    }

    public String getProductClassify() {
        return productClassify;
    }

    public void setProductClassify(String productClassify) {
        this.productClassify = productClassify;
    }

    public Integer getArticleNumber() {
        return articleNumber;
    }

    public void setArticleNumber(Integer articleNumber) {
        this.articleNumber = articleNumber;
    }

    public String getProductImage() {
        return productImage;
    }

    public void setProductImage(String productImage) {
        this.productImage = productImage;
    }

    public BigDecimal getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(BigDecimal productPrice) {
        this.productPrice = productPrice;
    }

    public BigDecimal getMarketPrice() {
        return marketPrice;
    }

    public void setMarketPrice(BigDecimal marketPrice) {
        this.marketPrice = marketPrice;
    }

    public BigDecimal getPleasedPrice() {
        return pleasedPrice;
    }

    public void setPleasedPrice(BigDecimal pleasedPrice) {
        this.pleasedPrice = pleasedPrice;
    }

    public String getCustomerPleasedPrice() {
        return customerPleasedPrice;
    }

    public void setCustomerPleasedPrice(String customerPleasedPrice) {
        this.customerPleasedPrice = customerPleasedPrice;
    }

    @Override
    public String toString() {
        return "ProductInfos{" +
        "id=" + id +
        ", productName=" + productName +
        ", warehouseLocation=" + warehouseLocation +
        ", productNumber=" + productNumber +
        ", productClassify=" + productClassify +
        ", articleNumber=" + articleNumber +
        ", productImage=" + productImage +
        ", productPrice=" + productPrice +
        ", marketPrice=" + marketPrice +
        ", pleasedPrice=" + pleasedPrice +
        ", customerPleasedPrice=" + customerPleasedPrice +
        "}";
    }
}
