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
 * @author ludongyang
 * @since 2019-11-15
 */
@TableName("sales_infos")
public class SalesInfos implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 销售单ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 发货人ID (分销商ID)
     */
    @TableField("send_user_id")
    private Integer sendUserId;

    /**
     * 姓名
     */
    @TableField("sale_name")
    private String saleName;

    /**
     * 电话
     */
    @TableField("phone")
    private String phone;

    /**
     * 地址
     */
    @TableField("location")
    private String location;

    /**
     * 货物名称
     */
    @TableField("product_id")
    private Integer productId;

    /**
     * 购买数量
     */
    @TableField("sale_num")
    private Integer saleNum;

    /**
     * 付款金额
     */
    @TableField("pay_money")
    private String payMoney;

    /**
     * 付款状态 0:未付款，1:已付款
     */
    @TableField("state")
    private String state;

    public Integer getSendUserId() {
        return sendUserId;
    }

    public void setSendUserId(Integer sendUserId) {
        this.sendUserId = sendUserId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSaleName() {
        return saleName;
    }

    public void setSaleName(String saleName) {
        this.saleName = saleName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Integer getSaleNum() {
        return saleNum;
    }

    public void setSaleNum(Integer saleNum) {
        this.saleNum = saleNum;
    }

    public String getPayMoney() {
        return payMoney;
    }

    public void setPayMoney(String payMoney) {
        this.payMoney = payMoney;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return "SalesInfos{" +
        "id=" + id +
        ", saleName=" + saleName +
        ", phone=" + phone +
        ", location=" + location +
        ", productId=" + productId +
        ", saleNum=" + saleNum +
        ", payMoney=" + payMoney +
        ", state=" + state +
        "}";
    }
}
