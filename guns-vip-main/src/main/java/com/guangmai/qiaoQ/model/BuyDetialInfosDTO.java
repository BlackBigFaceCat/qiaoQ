package com.guangmai.qiaoQ.model;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @Authod: dongyang
 * @Description:    购买业详细信息填充实体类
 * @Date: 创建于 12:03 2019/11/15
 * @Modified By:
 */
public class BuyDetialInfosDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer productId;           // 商品ID

    private String reciveUserName;      // 收货人姓名

    private String sendUserName;        // 发货人

    private Integer sendUserId;          // 发货人ID

    private String userPhone;           // 收货人电话

    private String saleState;           // 支付状态

    private Integer buyNumber;          // 购买数量

    private String productName;         // 产品名

    private String productImage;        // 图片地址

    private String usersLocation;       // 用户收货地址

    private Integer productNumber;      // 库存

    private String marketPrice;     // 市场价

    private String productPrice;    // 进价

    private String sendUserPhone;       // 发货人电话

    private String payMoney;

    public Integer getSendUserId() {
        return sendUserId;
    }

    public void setSendUserId(Integer sendUserId) {
        this.sendUserId = sendUserId;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public String getPayMoney() {
        return payMoney;
    }

    public void setPayMoney(String payMoney) {
        this.payMoney = payMoney;
    }

    public String getSendUserPhone() {
        return sendUserPhone;
    }

    public void setSendUserPhone(String sendUserPhone) {
        this.sendUserPhone = sendUserPhone;
    }

    public String getReciveUserName() {
        return reciveUserName;
    }

    public void setReciveUserName(String reciveUserName) {
        this.reciveUserName = reciveUserName;
    }

    public String getSendUserName() {
        return sendUserName;
    }

    public void setSendUserName(String sendUserName) {
        this.sendUserName = sendUserName;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public String getSaleState() {
        return saleState;
    }

    public void setSaleState(String saleState) {
        this.saleState = saleState;
    }

    public Integer getBuyNumber() {
        return buyNumber;
    }

    public void setBuyNumber(Integer buyNumber) {
        this.buyNumber = buyNumber;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductImage() {
        return productImage;
    }

    public void setProductImage(String productImage) {
        this.productImage = productImage;
    }

    public String getUsersLocation() {
        return usersLocation;
    }

    public void setUsersLocation(String usersLocation) {
        this.usersLocation = usersLocation;
    }

    public Integer getProductNumber() {
        return productNumber;
    }

    public void setProductNumber(Integer productNumber) {
        this.productNumber = productNumber;
    }

    public String getMarketPrice() {
        return marketPrice;
    }

    public void setMarketPrice(String marketPrice) {
        this.marketPrice = marketPrice;
    }

    public String getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(String productPrice) {
        this.productPrice = productPrice;
    }
}
