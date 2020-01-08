package com.guangmai.qiaoQ.service.impl;

import cn.hutool.core.codec.Base64;
import cn.hutool.core.io.IoUtil;
import cn.stylefeng.guns.base.pojo.page.LayuiPageFactory;
import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import cn.stylefeng.guns.base.shiro.ShiroUser;
import cn.stylefeng.guns.sys.core.constant.DefaultAvatar;
import cn.stylefeng.guns.sys.core.exception.enums.BizExceptionEnum;
import cn.stylefeng.guns.sys.core.properties.GunsProperties;
import cn.stylefeng.guns.sys.core.shiro.ShiroKit;
import cn.stylefeng.guns.sys.modular.system.entity.FileInfo;
import cn.stylefeng.guns.sys.modular.system.entity.Role;
import cn.stylefeng.guns.sys.modular.system.entity.User;
import cn.stylefeng.guns.sys.modular.system.service.FileInfoService;
import cn.stylefeng.guns.sys.modular.system.service.RoleService;
import cn.stylefeng.guns.sys.modular.system.service.UserService;
import cn.stylefeng.roses.kernel.model.exception.ServiceException;
import cn.stylefeng.roses.kernel.model.exception.enums.CoreExceptionEnum;
import com.alibaba.druid.util.StringUtils;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.guangmai.qiaoQ.entity.FileProductRelation;
import com.guangmai.qiaoQ.entity.ProductInfos;
import com.guangmai.qiaoQ.entity.RolePrices;
import com.guangmai.qiaoQ.mapper.FileProductRelationMapper;
import com.guangmai.qiaoQ.mapper.ProductInfosMapper;
import com.guangmai.qiaoQ.mapper.RolePriceMapper;
import com.guangmai.qiaoQ.model.FileProductRelationDTO;
import com.guangmai.qiaoQ.model.ProductInfosParam;
import com.guangmai.qiaoQ.model.RolePriceParam;
import com.guangmai.qiaoQ.service.FileProductRelationService;
import  com.guangmai.qiaoQ.service.ProductInfosService;
import cn.stylefeng.roses.core.util.ToolUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.guangmai.qiaoQ.service.RolePriceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author dongyang
 * @since 2019-11-07
 */
@Service
@Primary
public class ProductInfosServiceImpl extends ServiceImpl<ProductInfosMapper, ProductInfos> implements ProductInfosService {

    @Autowired
    private GunsProperties gunsProperties;

    @Autowired
    private FileInfoService fileInfoService;

    @Autowired
    private FileProductRelationService fileProductRelationService;

    @Resource
    private ProductInfosMapper productInfosMapper;

    @Resource
    private RolePriceMapper rolePriceMapper;

    @Resource
    private RoleService roleService;

    @Resource
    private UserService userService;

    /** @Description: 新增产品
    * @Title:  addProductInfos
    * @Parameters [productInfosParam]
    * @return java.lang.Integer
    * @author ludongyang
    * @date 2019/12/16 14:54
    */
    @Transactional
    @Override
    public Integer addProductInfos(ProductInfosParam productInfosParam){
        Integer isSuccess = 1;
        try {
            ProductInfos entity = getEntity(productInfosParam);
            productInfosMapper.addProductInfos(entity);

            // 获取角色ID
            List<Role> roles = roleService.list(new QueryWrapper<>());

            // 添加商品的同时为每个角色添加 商品与角色的 价钱 关系
            for(Role role :roles){
                RolePriceParam rolePriceParam = new RolePriceParam();
                rolePriceParam.setIsParent("0");
                rolePriceParam.setProductId(String.valueOf(entity.getId()));
                rolePriceParam.setRoleId(role.getRoleId());
                isSuccess = rolePriceMapper.addInfos(rolePriceParam);
            }

        } catch (Exception e){
            e.printStackTrace();
            isSuccess = 0;
        }
        return isSuccess;
    }

    @Override
    public String uploadFile(MultipartFile file,String fileId) {

        //获取文件后缀
        String fileSuffix = ToolUtil.getFileSuffix(file.getOriginalFilename());

        //获取文件原始名称
        String originalFilename = file.getOriginalFilename();

        //生成文件的最终名称
        String finalName = fileId + "." + ToolUtil.getFileSuffix(originalFilename);

        try {
            //保存文件到指定目录
            String fileSavePath = gunsProperties.getFileUploadPath();
            File newFile = new File(fileSavePath + finalName);
            file.transferTo(newFile);

            //保存文件信息
            FileInfo fileInfo = new FileInfo();
            fileInfo.setFileId(fileId);
            fileInfo.setFileName(originalFilename);
            fileInfo.setFileSuffix(fileSuffix);
            fileInfo.setFilePath(fileSavePath + finalName);
            fileInfo.setFinalName(finalName);

            //计算文件大小kb
            long kb = new BigDecimal(file.getSize())
                    .divide(BigDecimal.valueOf(1024))
                    .setScale(0, BigDecimal.ROUND_HALF_UP).longValue();
            fileInfo.setFileSizeKb(kb);
            fileInfoService.save(fileInfo);
        } catch (Exception e) {
            System.out.println("上传文件错误！" + e);
            throw new ServiceException(BizExceptionEnum.UPLOAD_ERROR);
        }

        return fileId;
    }

    /** @Description: 下单
    * @Title:  buy
    * @Parameters [param]
    * @return void
    * @author ludongyang
    * @date 2019/11/15 11:14
    */ 
    @Override
    public void buy(ProductInfosParam param){

    }

    @Override
    public void add(ProductInfosParam param){
        ProductInfos entity = getEntity(param);
        this.save(entity);
    }

    @Override
    public void delete(ProductInfosParam param){

        QueryWrapper<RolePrices> roleWrapper = new QueryWrapper<>();
        roleWrapper.eq("product_id",param.getId());
        rolePriceMapper.delete(roleWrapper);
        this.removeById(getKey(param));
    }

    @Override
    public void update(ProductInfosParam param){
        ProductInfos oldEntity = getOldEntity(param);
        ProductInfos newEntity = getEntity(param);
        ToolUtil.copyProperties(newEntity, oldEntity);
        this.updateById(newEntity);
    }

    @Override
    public ProductInfosParam findBySpec(ProductInfosParam param){
        return null;
    }

    @Override
    public List<ProductInfosParam> findListBySpec(ProductInfosParam param){
        return null;
    }

    /** @Description: 巧巧看到的商品展示页面
    * @Title:  findPageBySpec
    * @Parameters [param]
    * @return cn.stylefeng.guns.base.pojo.page.LayuiPageInfo
    * @author ludongyang
    * @date 2019/12/29 19:18
    */ 
    @Override
    public LayuiPageInfo findPageBySpec(ProductInfosParam param){
        Page pageContext = getPageContext();
        IPage page = this.baseMapper.customPageList(pageContext, param);

        List<ProductInfosParam> records = page.getRecords();
        for(ProductInfosParam productInfosParam : records){
            productInfosParam.setProductImage("/productInfos/previewAvatar?avatar="+productInfosParam.getId());
        }

        return LayuiPageFactory.createPageInfo(page);
    }

    /** @Description: 顾客看到的商品展示页面
    * @Title:  findPageBySpecForCustomer
    * @Parameters [param]
    * @return cn.stylefeng.guns.base.pojo.page.LayuiPageInfo
    * @author ludongyang
    * @date 2019/12/29 19:18
    */ 
    @Override
    public LayuiPageInfo findPageBySpecForCustomer(ProductInfosParam param){
        Page pageContext = getPageContext();
        IPage page = this.baseMapper.customPageList(pageContext, param);

        User user = userService.getById(ShiroKit.getUser().getId());

        RolePriceParam parentRolePriceParam = rolePriceMapper.listProductPriceParentByRoleId(Long.valueOf(user.getRoleId()));
        Double pricePresent = Double.valueOf((org.springframework.util.StringUtils.isEmpty(parentRolePriceParam.getPricePresent()) ? "0" : parentRolePriceParam.getPricePresent()));
        List<ProductInfosParam> records = page.getRecords();
        for(ProductInfosParam productInfosParam : records){
            RolePriceParam rolePriceParam = rolePriceMapper.listProductPriceByRoleIdAndProductId(Long.valueOf(user.getRoleId()), Long.valueOf(productInfosParam.getId()));
            Double price = Double.valueOf((org.springframework.util.StringUtils.isEmpty(rolePriceParam.getPrice()) ? "0" : rolePriceParam.getPrice()));
            if( price != 0 || pricePresent == 0 ){
                productInfosParam.setProductPrice( BigDecimal.valueOf(price));
            } else {
                double userPrice = new BigDecimal(productInfosParam.getProductPrice().doubleValue() * (Double.valueOf(pricePresent) / 100 + 1)).setScale(0, RoundingMode.UP).doubleValue();
                productInfosParam.setProductPrice( BigDecimal.valueOf(userPrice) );
            }
            productInfosParam.setProductImage("/productInfos/previewAvatar?avatar="+productInfosParam.getId());
        }

        return LayuiPageFactory.createPageInfo(page);
    }

    @Override
    public byte[] previewProductImage(String avatar) {
        //如果头像id为空就返回默认的
        if (StringUtils.equals("0",avatar)) {
            return Base64.decode(DefaultAvatar.BASE_64_AVATAR);
        } else {

            //文件id不为空就查询文件记录
            FileInfo fileInfo = fileInfoService.getById(avatar);
            if (fileInfo == null) {
                return Base64.decode(DefaultAvatar.BASE_64_AVATAR);
            } else {
                try {
                    String filePath = fileInfo.getFilePath();
                    return IoUtil.readBytes(new FileInputStream(filePath));
                } catch (FileNotFoundException e) {

                    return Base64.decode(DefaultAvatar.BASE_64_AVATAR);
                }
            }
        }
    }

    private Serializable getKey(ProductInfosParam param){
        return param.getId();
    }

    private Page getPageContext() {
        return LayuiPageFactory.defaultPage();
    }

    private ProductInfos getOldEntity(ProductInfosParam param) {
        return this.getById(getKey(param));
    }

    private ProductInfos getEntity(ProductInfosParam param) {
        ProductInfos entity = new ProductInfos();
        ToolUtil.copyProperties(param, entity);
        return entity;
    }

}
