package com.guangmai.qiaoQ.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.stylefeng.guns.base.pojo.page.LayuiPageFactory;
import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import cn.stylefeng.guns.sys.core.shiro.ShiroKit;
import cn.stylefeng.guns.sys.modular.system.entity.User;
import cn.stylefeng.guns.sys.modular.system.service.UserService;
import com.alibaba.druid.util.StringUtils;
import com.guangmai.qiaoQ.entity.ProductInfos;
import com.guangmai.qiaoQ.entity.SalesInfos;
import com.guangmai.qiaoQ.mapper.SalesInfosMapper;
import com.guangmai.qiaoQ.model.BuyDetialInfosDTO;
import com.guangmai.qiaoQ.model.SalesInfosParam;
import com.guangmai.qiaoQ.service.ProductInfosService;
import  com.guangmai.qiaoQ.service.SalesInfosService;
import cn.stylefeng.roses.core.util.ToolUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author ludongyang
 * @since 2019-11-15
 */
@Service
@Primary
public class SalesInfosServiceImpl extends ServiceImpl<SalesInfosMapper, SalesInfos> implements SalesInfosService {

    @Autowired
    private ProductInfosService productInfosService;

    @Autowired
    private UserService userService;

    @Override
    @Transactional
    public void addInfo(BuyDetialInfosDTO buyDetialInfosDTO) {
        SalesInfosParam salesInfosParam = new SalesInfosParam();
        salesInfosParam.setState("0");
        salesInfosParam.setPhone(buyDetialInfosDTO.getUserPhone());         // 收货人手机号码
        salesInfosParam.setSaleNum(buyDetialInfosDTO.getBuyNumber());       // 购买数量
        salesInfosParam.setPayMoney(buyDetialInfosDTO.getPayMoney());       // 支付总额
        salesInfosParam.setProductId(buyDetialInfosDTO.getProductId());     // 商品ID
        salesInfosParam.setSaleName(buyDetialInfosDTO.getReciveUserName());    // 收获人姓名
        salesInfosParam.setSendUserId(ShiroKit.getUser().getId());          // 发货人ID
        salesInfosParam.setLocation(buyDetialInfosDTO.getUsersLocation());  // 收货地址

        ProductInfos productInfos = new ProductInfos();
        productInfos.setId(buyDetialInfosDTO.getProductId());
        productInfos.setProductNumber(buyDetialInfosDTO.getProductNumber());
        productInfosService.updateById(productInfos);
        this.add(salesInfosParam);

    }

    @Override
    public void add(SalesInfosParam param){
        SalesInfos entity = getEntity(param);

        this.save(entity);
    }

    @Override
    public void delete(SalesInfosParam param){
        this.removeById(getKey(param));
    }

    @Override
    @Transactional
    public void update(SalesInfosParam param){
        SalesInfos oldEntity = getOldEntity(param);
        SalesInfos newEntity = getEntity(param);
        Integer newSaleNum = param.getSaleNum();   // 修改后的数量

        Integer oldSaleNum = oldEntity.getSaleNum();
        if(oldSaleNum != newSaleNum){
            // 出售数量被修改
            Integer saleNum = newSaleNum - oldSaleNum;
            ProductInfos productInfos = productInfosService.getById(param.getProductId());
            if(saleNum < 0){
                // 改少了
                productInfos.setProductNumber(productInfos.getProductNumber() + Math.abs(saleNum));
            } else {
                // 增多了
                productInfos.setProductNumber(productInfos.getProductNumber() - Math.abs(saleNum));
            }
            productInfosService.updateById(productInfos);
        }
        this.updateById(newEntity);
    }

    @Override
    public SalesInfosParam findBySpec(SalesInfosParam param){
        return null;
    }

    @Override
    public List<SalesInfosParam> findListBySpec(SalesInfosParam param){
        return null;
    }

    @Override
    public LayuiPageInfo findPageBySpec(SalesInfosParam param){
        Page pageContext = getPageContext();

        // 获取当前用户的销售单
        param.setSendUserId(ShiroKit.getUser().getId());
        IPage page = this.baseMapper.customPageList(pageContext, param);
        List<SalesInfosParam> records = page.getRecords();
        List<Map<String,Object>> recordsMap = new ArrayList<>();
        for(SalesInfosParam salesInfosParam : records){
            Map<String,Object> map = BeanUtil.beanToMap(salesInfosParam);
            map.put("state",((StringUtils.equals(salesInfosParam.getState(),"0") ? "未付款":"已付款")));
            ProductInfos productInfos = productInfosService.getById(salesInfosParam.getProductId()); // TODO  数据量大的时候批处理  怎么也只有二三十个勒。  用户量也不大。

            /** @Description: 赋值 收货人 和 产品ID
            */
            if(!BeanUtil.isEmpty(productInfos)){
                User user = userService.getById(salesInfosParam.getSendUserId());
                map.put("sendUserName",user.getName());
                map.put("productId",productInfos.getProductName());
                map.put("productImage",("/productInfos/previewAvatar?avatar="+productInfos.getId()));
            }
            recordsMap.add(map);
        }
        page.setRecords(recordsMap);
        return LayuiPageFactory.createPageInfo(page);
    }

    private Serializable getKey(SalesInfosParam param){
        return param.getId();
    }

    private Page getPageContext() {
        return LayuiPageFactory.defaultPage();
    }

    private SalesInfos getOldEntity(SalesInfosParam param) {
        return this.getById(getKey(param));
    }

    private SalesInfos getEntity(SalesInfosParam param) {
        SalesInfos entity = new SalesInfos();
        ToolUtil.copyProperties(param, entity);
        return entity;
    }

}
