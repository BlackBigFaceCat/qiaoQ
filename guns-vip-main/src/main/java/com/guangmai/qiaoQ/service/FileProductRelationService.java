package com.guangmai.qiaoQ.service;

import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import com.guangmai.qiaoQ.entity.FileProductRelation;
import com.baomidou.mybatisplus.extension.service.IService;
import com.guangmai.qiaoQ.model.FileProductRelationDTO;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author ludongyang
 * @since 2019-11-28
 */
public interface FileProductRelationService extends IService<FileProductRelation> {

    /**
     * 新增
     *
     * @author ludongyang
     * @Date 2019-11-28
     */
    void add(FileProductRelationDTO param);

    /**
     * 删除
     *
     * @author ludongyang
     * @Date 2019-11-28
     */
    void delete(FileProductRelationDTO param);

    /**
     * 更新
     *
     * @author ludongyang
     * @Date 2019-11-28
     */
    void update(FileProductRelationDTO param);

    /**
     * 查询单条数据，Specification模式
     *
     * @author ludongyang
     * @Date 2019-11-28
     */
    FileProductRelationDTO findBySpec(FileProductRelationDTO param);

    /**
     * 查询列表，Specification模式
     *
     * @author ludongyang
     * @Date 2019-11-28
     */
    List<FileProductRelationDTO> findListBySpec(FileProductRelationDTO param);

    /**
     * 查询分页数据，Specification模式
     *
     * @author ludongyang
     * @Date 2019-11-28
     */
     LayuiPageInfo findPageBySpec(FileProductRelationDTO param);

}
