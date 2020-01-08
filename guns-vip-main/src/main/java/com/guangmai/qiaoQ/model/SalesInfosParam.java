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
 * @author ludongyang
 * @since 2019-11-15
 */
@Data
public class SalesInfosParam implements Serializable, BaseValidatingParam {

    private static final long serialVersionUID = 1L;


    /**
     * 销售单ID
     */
    private Integer id;

    /**
     * 发货人ID (分销商ID)
     */
    private Long sendUserId;

    /**
     * 姓名
     */
    private String saleName;

    /**
     * 电话
     */
    private String phone;

    /**
     * 地址
     */
    private String location;

    /**
     * 货物名称
     */
    private Integer productId;

    /**
     * 购买数量
     */
    private Integer saleNum;

    /**
     * 付款金额
     */
    private String payMoney;

    /**
     * 付款状态 0:未付款，1:已付款
     */
    private String state;

    @Override
    public String checkParam() {
        return null;
    }

}
