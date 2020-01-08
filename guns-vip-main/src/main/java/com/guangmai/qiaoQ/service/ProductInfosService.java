package com.guangmai.qiaoQ.service;

import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import cn.stylefeng.roses.core.reqres.response.ResponseData;
import com.guangmai.qiaoQ.entity.ProductInfos;
import com.guangmai.qiaoQ.model.ProductInfosParam;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author dongyang
 * @since 2019-11-07
 */
public interface ProductInfosService extends IService<ProductInfos> {

    Integer addProductInfos(ProductInfosParam productInfosParam);

    String uploadFile(MultipartFile file,String productInfosId);

    /**
     * 下单
     *
     * @author dongyang
     * @Date 2019-11-15
     */
    void buy(ProductInfosParam param);

    /**
     * 新增
     *
     * @author dongyang
     * @Date 2019-11-07
     */
    void add(ProductInfosParam param);

    /**
     * 删除
     *
     * @author dongyang
     * @Date 2019-11-07
     */
    void delete(ProductInfosParam param);

    /**
     * 更新
     *
     * @author dongyang
     * @Date 2019-11-07
     */
    void update(ProductInfosParam param);

    /**
     * 查询单条数据，Specification模式
     *
     * @author dongyang
     * @Date 2019-11-07
     */
    ProductInfosParam findBySpec(ProductInfosParam param);

    /**
     * 查询列表，Specification模式
     *
     * @author dongyang
     * @Date 2019-11-07
     */
    List<ProductInfosParam> findListBySpec(ProductInfosParam param);

    /**
     * 查询分页数据，Specification模式
     *
     * @author dongyang
     * @Date 2019-11-07
     */
     LayuiPageInfo findPageBySpec(ProductInfosParam param);

     LayuiPageInfo findPageBySpecForCustomer(ProductInfosParam param);

    byte[] previewProductImage(String avatar);
}
