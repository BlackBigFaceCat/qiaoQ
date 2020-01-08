package com.guangmai.qiaoQ.controller;

import cn.hutool.core.bean.BeanUtil;
import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import cn.stylefeng.guns.sys.core.shiro.ShiroKit;
import cn.stylefeng.guns.sys.modular.system.entity.User;
import cn.stylefeng.guns.sys.modular.system.service.UserService;
import com.guangmai.qiaoQ.entity.ProductInfos;
import com.guangmai.qiaoQ.entity.SalesInfos;
import com.guangmai.qiaoQ.mapper.ProductInfosMapper;
import com.guangmai.qiaoQ.mapper.RolePriceMapper;
import com.guangmai.qiaoQ.model.BuyDetialInfosDTO;
import com.guangmai.qiaoQ.model.RolePriceParam;
import com.guangmai.qiaoQ.model.SalesInfosParam;
import com.guangmai.qiaoQ.service.ProductInfosService;
import com.guangmai.qiaoQ.service.SalesInfosService;
import cn.stylefeng.roses.core.base.controller.BaseController;
import cn.stylefeng.roses.core.reqres.response.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Map;


/**
 * 控制器
 *
 * @author ludongyang
 * @Date 2019-11-15 10:27:33
 */
@Controller
@RequestMapping("/salesInfos")
public class SalesInfosController extends BaseController {

    private String PREFIX = "/salesInfos";

    @Autowired
    private SalesInfosService salesInfosService;

    @Autowired
    private ProductInfosService productInfosService;

    @Autowired
    private UserService userService;

    @Resource
    private ProductInfosMapper productInfosMapper;

    @Resource
    private RolePriceMapper rolePriceMapper;

    @RequestMapping(value = "/getMoney")
    @ResponseBody
    public Object getMoney(SalesInfos salesInfos){
        SalesInfos salesInfo = new SalesInfos();
        salesInfo.setState("1");
        salesInfo.setId(salesInfos.getId());
        salesInfosService.updateById(salesInfo);
        return ResponseData.success();
    }

    /** @Description: 加载购买页面信息
    * @Title:  buyDetailInfos
    * @Parameters [id 商品ID]
    * @return cn.stylefeng.roses.core.reqres.response.ResponseData
    * @author ludongyang
    * @date 2019/11/15 12:01
    */
    @RequestMapping("/buyDetailInfos")
    @ResponseBody
    public ResponseData buyDetailInfos(Integer id) {
        User userInfos = userService.getById(ShiroKit.getUser().getId());
        BuyDetialInfosDTO buyDetialInfosDTO = new BuyDetialInfosDTO();
        ProductInfos productInfos = productInfosService.getById(id);
        buyDetialInfosDTO.setSendUserPhone(userInfos.getPhone());                   // 发货人电话
        buyDetialInfosDTO.setSendUserName(ShiroKit.getUser().getName());            // 发货人姓名
        buyDetialInfosDTO.setProductId(productInfos.getId());       // 商品ID
        buyDetialInfosDTO.setProductName(productInfos.getProductName());            // 商品名称
        buyDetialInfosDTO.setProductImage(productInfos.getProductImage());          // 图片地址
        buyDetialInfosDTO.setProductNumber(productInfos.getProductNumber());        // 库存
        buyDetialInfosDTO.setMarketPrice(String.valueOf(productInfos.getMarketPrice()));            // 市场价


        /**
         * 单独设置价格
         */
        User user = userService.getById(ShiroKit.getUser().getId());
        RolePriceParam parentRolePriceParam = rolePriceMapper.listProductPriceParentByRoleId(Long.valueOf(user.getRoleId()));
        Double pricePresent = Double.valueOf((org.springframework.util.StringUtils.isEmpty(parentRolePriceParam.getPricePresent()) ? "0" : parentRolePriceParam.getPricePresent()));
        RolePriceParam rolePriceParam = rolePriceMapper.listProductPriceByRoleIdAndProductId(Long.valueOf(user.getRoleId()), Long.valueOf(productInfos.getId()));
        Double price = Double.valueOf((org.springframework.util.StringUtils.isEmpty(rolePriceParam.getPrice()) ? "0" : rolePriceParam.getPrice()));
        if( price != 0 || pricePresent == 0 ){
            productInfos.setProductPrice( BigDecimal.valueOf(price));
        } else {
            double userPrice = new BigDecimal(productInfos.getProductPrice().doubleValue() * (Double.valueOf(pricePresent) / 100 + 1)).setScale(0, RoundingMode.UP).doubleValue();
            productInfos.setProductPrice( BigDecimal.valueOf(userPrice) );
        }
        buyDetialInfosDTO.setProductPrice(String.valueOf(productInfos.getProductPrice()));          // 产品售价
        return ResponseData.success(buyDetialInfosDTO);
    }

    /** @Description: 
    * @Title:  buy
    * @Parameters [salesInfosParam]
    * @return cn.stylefeng.roses.core.reqres.response.ResponseData
    * @author ludongyang
    * @date 2019/11/15 11:44
    */ 
    @RequestMapping("/buy")
    @ResponseBody
    public ResponseData buy(BuyDetialInfosDTO buyDetialInfosDTO) {
        salesInfosService.addInfo(buyDetialInfosDTO);

        return ResponseData.success();
    }

    /**
     * 跳转到主页面
     *
     * @author ludongyang
     * @Date 2019-11-15
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "/salesInfos.html";
    }

    /**
     * 新增页面
     *
     * @author ludongyang
     * @Date 2019-11-15
     */
    @RequestMapping("/add")
    public String add() {
        return PREFIX + "/salesInfos_add.html";
    }

    /**
     * 编辑页面
     *
     * @author ludongyang
     * @Date 2019-11-15
     */
    @RequestMapping("/edit")
    public String edit() {
        return PREFIX + "/salesInfos_edit.html";
    }

    /**
     * 新增接口
     *
     * @author ludongyang
     * @Date 2019-11-15
     */
    @RequestMapping("/addItem")
    @ResponseBody
    public ResponseData addItem(SalesInfosParam salesInfosParam) {
        this.salesInfosService.add(salesInfosParam);
        return ResponseData.success();
    }

    /**
     * 编辑接口
     *
     * @author ludongyang
     * @Date 2019-11-15
     */
    @RequestMapping("/editItem")
    @ResponseBody
    public ResponseData editItem(SalesInfosParam salesInfosParam) {
        this.salesInfosService.update(salesInfosParam);
        return ResponseData.success();
    }

    /**
     * 删除接口
     *
     * @author ludongyang
     * @Date 2019-11-15
     */
    @RequestMapping("/delete")
    @ResponseBody
    public ResponseData delete(SalesInfosParam salesInfosParam) {
        this.salesInfosService.delete(salesInfosParam);
        return ResponseData.success();
    }

    /**
     * 查看详情接口
     *
     * @author ludongyang
     * @Date 2019-11-15
     */
    @RequestMapping("/detail")
    @ResponseBody
    public ResponseData detail(SalesInfosParam salesInfosParam) {
        SalesInfos detail = this.salesInfosService.getById(salesInfosParam.getId());

        Map<String, Object> map = BeanUtil.beanToMap(detail);
        map.put("state",(map.get("state") == "0" ? "未付款" :"已付款"));
        ProductInfos productInfos = productInfosService.getById((Integer) map.get("productId"));
        map.put("productName",productInfos.getProductName());
        map.put("productPrice",productInfos.getProductPrice());
        map.put("productImage",productInfos.getProductImage());
        map.put("productNumber",productInfos.getProductNumber());
        return ResponseData.success(map);
    }

    /**
     * 查询列表
     *
     * @author ludongyang
     * @Date 2019-11-15
     */
    @ResponseBody
    @RequestMapping("/list")
    public LayuiPageInfo list(SalesInfosParam salesInfosParam) {
        return this.salesInfosService.findPageBySpec(salesInfosParam);
    }

}


