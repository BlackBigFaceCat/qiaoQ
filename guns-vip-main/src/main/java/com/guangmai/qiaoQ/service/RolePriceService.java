package com.guangmai.qiaoQ.service;

import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import com.guangmai.qiaoQ.entity.RolePrices;
import com.guangmai.qiaoQ.model.RolePriceParam;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author dongyang
 * @since 2019-12-17
 */
public interface RolePriceService extends IService<RolePrices> {

    /**
     * 新增
     *
     * @author dongyang
     * @Date 2019-12-17
     */
    void add(RolePriceParam param);

    /**
     * 删除
     *
     * @author dongyang
     * @Date 2019-12-17
     */
    void delete(RolePriceParam param);

    /**
     * 更新
     *
     * @author dongyang
     * @Date 2019-12-17
     */
    void update(RolePriceParam param);

    /**
     * 查询单条数据，Specification模式
     *
     * @author dongyang
     * @Date 2019-12-17
     */
    RolePriceParam findBySpec(RolePriceParam param);

    /**
     * 查询列表，Specification模式
     *
     * @author dongyang
     * @Date 2019-12-17
     */
    List<RolePriceParam> findListBySpec(RolePriceParam param);

    /**
     * 查询分页数据，Specification模式
     *
     * @author dongyang
     * @Date 2019-12-17
     */
     LayuiPageInfo findPageBySpec(RolePriceParam param);

    /**
     * 每个角色与价钱的关系详细
     * @param param
     * @return
     */
     LayuiPageInfo findDetialPricePageBySpec(RolePriceParam param);

}
