package com.guangmai.qiaoQ.service;

import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import com.guangmai.qiaoQ.entity.SalesInfos;
import com.guangmai.qiaoQ.model.BuyDetialInfosDTO;
import com.guangmai.qiaoQ.model.SalesInfosParam;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author ludongyang
 * @since 2019-11-15
 */
public interface SalesInfosService extends IService<SalesInfos> {

    void addInfo(BuyDetialInfosDTO buyDetialInfosDTO);

    /**
     * 新增
     *
     * @author ludongyang
     * @Date 2019-11-15
     */
    void add(SalesInfosParam param);

    /**
     * 删除
     *
     * @author ludongyang
     * @Date 2019-11-15
     */
    void delete(SalesInfosParam param);

    /**
     * 更新
     *
     * @author ludongyang
     * @Date 2019-11-15
     */
    void update(SalesInfosParam param);

    /**
     * 查询单条数据，Specification模式
     *
     * @author ludongyang
     * @Date 2019-11-15
     */
    SalesInfosParam findBySpec(SalesInfosParam param);

    /**
     * 查询列表，Specification模式
     *
     * @author ludongyang
     * @Date 2019-11-15
     */
    List<SalesInfosParam> findListBySpec(SalesInfosParam param);

    /**
     * 查询分页数据，Specification模式
     *
     * @author ludongyang
     * @Date 2019-11-15
     */
     LayuiPageInfo findPageBySpec(SalesInfosParam param);

}
