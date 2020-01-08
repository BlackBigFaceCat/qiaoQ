package com.guangmai.qiaoQ.controller;

import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import cn.stylefeng.guns.sys.core.shiro.ShiroKit;
import cn.stylefeng.roses.core.util.ToolUtil;
import cn.stylefeng.roses.kernel.model.exception.RequestEmptyException;
import cn.stylefeng.roses.kernel.model.exception.ServiceException;
import cn.stylefeng.roses.kernel.model.exception.enums.CoreExceptionEnum;
import com.guangmai.qiaoQ.entity.ProductInfos;
import com.guangmai.qiaoQ.model.ProductInfosParam;
import com.guangmai.qiaoQ.service.ProductInfosService;
import cn.stylefeng.roses.core.base.controller.BaseController;
import cn.stylefeng.roses.core.reqres.response.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;


/**
 * 控制器
 *
 * @author dongyang
 * @Date 2019-11-07 22:55:58
 */
@Controller
@RequestMapping("/productInfos")
public class ProductInfosController extends BaseController {

    private String PREFIX = "/productInfos";

    @Autowired
    private ProductInfosService productInfosService;

    /** @Description: 上传商品图片
    * @Title:  layuiUpload
    * @Parameters [file]
    * @return cn.stylefeng.roses.core.reqres.response.ResponseData
    * @author ludongyang
    * @date 2019/11/28 16:04
    */ 
    @RequestMapping(method = RequestMethod.POST, path = "/upload")
    @ResponseBody
    public ResponseData layuiUpload(@RequestPart("file") MultipartFile file, HttpSession session) {

        String productInfosId = String.valueOf(session.getAttribute(String.valueOf(ShiroKit.getUser().getId())));


        String fileId = this.productInfosService.uploadFile(file,productInfosId);

        HashMap<String, Object> map = new HashMap<>();
        map.put("fileId", fileId);

        return ResponseData.success(0, "上传成功", map);
    }


    /** @Description: 显示产品图片
    * @Title:  previewAvatar
    * @Parameters [response]
    * @return java.lang.Object
    * @author ludongyang
    * @date 2019/11/28 16:03
    */ 
    @RequestMapping(value = "/previewAvatar")
    @ResponseBody
    public Object previewAvatar(HttpServletResponse response,@RequestParam("avatar") String avatar) {

        //输出图片的文件流
        try {
            response.setContentType("image/jpeg");
            byte[] decode = this.productInfosService.previewProductImage(avatar);
            response.getOutputStream().write(decode);
        } catch (IOException e) {
            throw new ServiceException(CoreExceptionEnum.SERVICE_ERROR);
        }

        return null;
    }


    /** @Description: 随机生成产品和图片ID
    * @Title:  getProductId
    * @Parameters []
    * @return cn.stylefeng.roses.core.reqres.response.ResponseData
    * @author ludongyang
    * @date 2019/12/9 10:23
    */ 
    @RequestMapping(value = "/getProductId")
    @ResponseBody
    public ResponseData getProductId(HttpSession session){
        String currentTime = System.currentTimeMillis()+"";
        currentTime = currentTime.substring(currentTime.length()-2);
        Integer id = Integer.parseInt((int)(Math.random() * 100000) +currentTime);

        session.setAttribute(String.valueOf(ShiroKit.getUser().getId()),id);
        return ResponseData.success(id);
    }

    /** @Description: 下单
    * @Title:  buy
    * @Parameters [productInfosParam]
    * @return cn.stylefeng.roses.core.reqres.response.ResponseData
    * @author ludongyang
    * @date 2019/11/15 11:14
    */ 
    @RequestMapping("/buy")
    @ResponseBody
    public ResponseData buy(ProductInfosParam productInfosParam) {
        this.productInfosService.buy(productInfosParam);
        return ResponseData.success();
    }

    /** @Description: 前往下单页面
    * @Title:  buyPage
    * @Parameters []
    * @return java.lang.String
    * @author ludongyang
    * @date 2019/11/15 11:33
    */ 
    @RequestMapping("/buyPage")
    public String buyPage() {
        return "/salesInfos/salesInfos_salePage.html";
    }

    /**
     * 跳转到主页面
     *
     * @author dongyang
     * @Date 2019-11-07
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "/productInfos.html";
    }

    /**
     * 跳转到主页面
     *
     * @author dongyang
     * @Date 2019-11-07
     */
    @RequestMapping("/customer")
    public String customerIndex() {
        return PREFIX + "/productInfos_customer.html";
    }

    /**
     * 新增页面
     *
     * @author dongyang
     * @Date 2019-11-07
     */
    @RequestMapping("/add")
    public String add() {
        return PREFIX + "/productInfos_add.html";
    }

    /**
     * 编辑页面
     *
     * @author dongyang
     * @Date 2019-11-07
     */
    @RequestMapping("/edit")
    public String edit() {
        return PREFIX + "/productInfos_edit.html";
    }

    /**
     * 新增接口
     *
     * @author dongyang
     * @Date 2019-11-07
     */
    @RequestMapping("/addItem")
    @ResponseBody
    public ResponseData addItem(ProductInfosParam productInfosParam) {
        this.productInfosService.addProductInfos(productInfosParam);
        return ResponseData.success();
    }

    /**
     * 编辑接口
     *
     * @author dongyang
     * @Date 2019-11-07
     */
    @RequestMapping("/editItem")
    @ResponseBody
    public ResponseData editItem(ProductInfosParam productInfosParam) {
        this.productInfosService.update(productInfosParam);
        return ResponseData.success();
    }

    /**
     * 删除接口
     *
     * @author dongyang
     * @Date 2019-11-07
     */
    @RequestMapping("/delete")
    @ResponseBody
    public ResponseData delete(ProductInfosParam productInfosParam) {
        this.productInfosService.delete(productInfosParam);
        return ResponseData.success();
    }

    /**
     * 查看详情接口
     *
     * @author dongyang
     * @Date 2019-11-07
     */
    @RequestMapping("/detail")
    @ResponseBody
    public ResponseData detail(ProductInfosParam productInfosParam) {
        ProductInfos detail = this.productInfosService.getById(productInfosParam.getId());
        return ResponseData.success(detail);
    }

    /**
     * 查询列表
     *
     * @author dongyang
     * @Date 2019-11-07
     */
    @ResponseBody
    @RequestMapping("/list")
    public LayuiPageInfo list(ProductInfosParam productInfosParam) {
        return this.productInfosService.findPageBySpec(productInfosParam);
    }

    /**
     * 查询列表 ------- 分销商看到的页面
     *
     * @author dongyang
     * @Date 2019-11-07
     */
    @ResponseBody
    @RequestMapping("/list_customer")
    public LayuiPageInfo listCustomer(ProductInfosParam productInfosParam) {
        return this.productInfosService.findPageBySpecForCustomer(productInfosParam);
    }

}


