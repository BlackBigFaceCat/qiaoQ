package com.guangmai.qiaoQ.entity;

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
 * @since 2019-12-17
 */
@TableName("role_price")
public class RolePrices implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 角色 价钱 关联表
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 角色ID
     */
    @TableField("role_id")
    private Long roleId;

    /**
     * 价钱
     */
    @TableField("price")
    private String price;

    /**
     * 商品ID
     */
    @TableField("product_id")
    private String productId;

    /**
     * 商品ID
     */
    @TableField("price_present")
    private String pricePresent;

    /**
     * 是否父节点  0-不是  1-是
     */
    @TableField("is_parent")
    private String isParent;

    public String getIsParent() {
        return isParent;
    }

    public void setIsParent(String isParent) {
        this.isParent = isParent;
    }

    public String getPricePresent() {
        return pricePresent;
    }

    public void setPricePresent(String pricePresent) {
        this.pricePresent = pricePresent;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "RolePrice{" +
        "id=" + id +
        ", roleId=" + roleId +
        ", price=" + price +
        "}";
    }
}
