package com.guangmai.qiaoQ.service.impl;

import cn.stylefeng.guns.base.pojo.page.LayuiPageFactory;
import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import com.guangmai.qiaoQ.entity.FileProductRelation;
import com.guangmai.qiaoQ.mapper.FileProductRelationMapper;
import com.guangmai.qiaoQ.model.FileProductRelationDTO;
import  com.guangmai.qiaoQ.service.FileProductRelationService;
import cn.stylefeng.roses.core.util.ToolUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author ludongyang
 * @since 2019-11-28
 */
@Service
@Primary
public class FileProductRelationServiceImpl extends ServiceImpl<FileProductRelationMapper, FileProductRelation> implements FileProductRelationService {

    @Override
    public void add(FileProductRelationDTO param){
        FileProductRelation entity = getEntity(param);
        this.save(entity);
    }

    @Override
    public void delete(FileProductRelationDTO param){
        this.removeById(getKey(param));
    }

    @Override
    public void update(FileProductRelationDTO param){
        FileProductRelation oldEntity = getOldEntity(param);
        FileProductRelation newEntity = getEntity(param);
        ToolUtil.copyProperties(newEntity, oldEntity);
        this.updateById(newEntity);
    }

    @Override
    public FileProductRelationDTO findBySpec(FileProductRelationDTO param){
        return null;
    }

    @Override
    public List<FileProductRelationDTO> findListBySpec(FileProductRelationDTO param){
        return null;
    }

    @Override
    public LayuiPageInfo findPageBySpec(FileProductRelationDTO param){
        Page pageContext = getPageContext();
        IPage page = this.baseMapper.customPageList(pageContext, param);
        return LayuiPageFactory.createPageInfo(page);
    }

    private Serializable getKey(FileProductRelationDTO param){
        return param.getId();
    }

    private Page getPageContext() {
        return LayuiPageFactory.defaultPage();
    }

    private FileProductRelation getOldEntity(FileProductRelationDTO param) {
        return this.getById(getKey(param));
    }

    private FileProductRelation getEntity(FileProductRelationDTO param) {
        FileProductRelation entity = new FileProductRelation();
        ToolUtil.copyProperties(param, entity);
        return entity;
    }

}
