package com.guangmai.qiaoQ.service.impl;

import cn.stylefeng.guns.base.pojo.page.LayuiPageFactory;
import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import cn.stylefeng.guns.sys.modular.system.entity.Role;
import cn.stylefeng.guns.sys.modular.system.service.RoleService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.guangmai.qiaoQ.entity.ProductInfos;
import com.guangmai.qiaoQ.entity.RolePrices;
import com.guangmai.qiaoQ.mapper.RolePriceMapper;
import com.guangmai.qiaoQ.model.RolePriceParam;
import com.guangmai.qiaoQ.service.ProductInfosService;
import  com.guangmai.qiaoQ.service.RolePriceService;
import cn.stylefeng.roses.core.util.ToolUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import net.sf.ehcache.util.ProductInfo;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author dongyang
 * @since 2019-12-17
 */
@Service
@Primary
public class RolePriceServiceImpl extends ServiceImpl<RolePriceMapper, RolePrices> implements RolePriceService {

    @Resource
    private RolePriceMapper rolePriceMapper;

    @Resource
    private RoleService roleService;

    @Resource
    private ProductInfosService productInfosService;

    /** @Description: 查询每个商品和每个角色的关系，列表展示
     * @Title:  findDetialPricePageBySpec
     * @Parameters [param]
     * @return cn.stylefeng.guns.base.pojo.page.LayuiPageInfo
     * @author ludongyang
     * @date 2019/12/28 13:24
     */
    @Override
    public LayuiPageInfo findDetialPricePageBySpec(RolePriceParam param) {

        IPage parentRolePrice = this.baseMapper.listRolePrice(new Page());
        List<RolePriceParam> parentParentRolePrice = parentRolePrice.getRecords();
        Page pageContext = getPageContext();
        IPage page = this.baseMapper.customPageList(pageContext, param);
        List<RolePriceParam> records = page.getRecords();
        for(RolePriceParam parentRolePriceParam : parentParentRolePrice){
            Double pricePresent = Double.valueOf((StringUtils.isEmpty(parentRolePriceParam.getPricePresent()) ? "0" : parentRolePriceParam.getPricePresent()));
            for(RolePriceParam rolePriceParam : records){
                Double price = Double.valueOf((StringUtils.isEmpty(rolePriceParam.getPrice()) ? "0" : rolePriceParam.getPrice()));
                rolePriceParam.setProductImage("/productInfos/previewAvatar?avatar="+rolePriceParam.getProductId());
                if( price != 0 || pricePresent == 0 ){
                    continue;
                } else {
                    double userPrice = new BigDecimal(Double.valueOf(rolePriceParam.getProductPrice()) * (Double.valueOf(pricePresent) / 100 + 1)).setScale(0, RoundingMode.UP).doubleValue();
                    rolePriceParam.setPrice( String.valueOf( userPrice ) );
                }
            }
        }

        return LayuiPageFactory.createPageInfo(page);
    }

    @Override
    public void add(RolePriceParam param){
        RolePrices entity = getEntity(param);
        this.save(entity);
    }

    @Override
    public void delete(RolePriceParam param){
        this.removeById(getKey(param));
    }

    /** @Description: 只有父节点才设置百分比，非父节点不设置百分比
    * @Title:  update
    * @Parameters [param]
    * @return void
    * @author ludongyang
    * @date 2019/12/28 17:22
    */ 
    @Override
    public void update(RolePriceParam param){
        RolePrices oldEntity = getOldEntity(param);
        RolePrices newEntity = getEntity(param);
        ToolUtil.copyProperties(newEntity, oldEntity);
        this.updateById(newEntity);
    }

    @Override
    public RolePriceParam findBySpec(RolePriceParam param){
        return null;
    }

    @Override
    public List<RolePriceParam> findListBySpec(RolePriceParam param){
        return null;
    }

    /** @Description: 展示所有角色
    * @Title:  findPageBySpec
    * @Parameters [param]
    * @return cn.stylefeng.guns.base.pojo.page.LayuiPageInfo
    * @author ludongyang
    * @date 2019/12/28 14:55
    */ 
    @Override
    public LayuiPageInfo findPageBySpec(RolePriceParam param){
        List<Role> roles = roleService.list(new QueryWrapper<>());
        Page pageContext = getPageContext();
//        IPage page = this.baseMapper.customPageList(pageContext, param);
        IPage page = this.baseMapper.listRolePrice(pageContext);


        return LayuiPageFactory.createPageInfo(page);
    }

    private Serializable getKey(RolePriceParam param){
        return param.getId();
    }

    private Page getPageContext() {
        return LayuiPageFactory.defaultPage();
    }

    private RolePrices getOldEntity(RolePriceParam param) {
        return this.getById(getKey(param));
    }

    private RolePrices getEntity(RolePriceParam param) {
        RolePrices entity = new RolePrices();
        ToolUtil.copyProperties(param, entity);
        return entity;
    }

}
