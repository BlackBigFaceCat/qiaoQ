package com.guangmai.qiaoQ.controller;

import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import com.guangmai.qiaoQ.entity.RolePrices;
import com.guangmai.qiaoQ.model.RolePriceParam;
import com.guangmai.qiaoQ.service.RolePriceService;
import cn.stylefeng.roses.core.base.controller.BaseController;
import cn.stylefeng.roses.core.reqres.response.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * 控制器
 *
 * @author dongyang
 * @Date 2019-12-17 09:38:29
 */
@Controller
@RequestMapping("/rolePrice")
public class RolePriceController extends BaseController {

    private String PREFIX = "/rolePrice";

    @Autowired
    private RolePriceService rolePriceService;

    /**
     * 跳转到主页面
     *
     * @author dongyang
     * @Date 2019-12-17
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "/rolePrice_page.html";
    }

    /**
     * 新增页面
     *
     * @author dongyang
     * @Date 2019-12-17
     */
    @RequestMapping("/add")
    public String add() {
        return PREFIX + "/rolePrice_add.html";
    }

    /**
     * 编辑页面
     *
     * @author dongyang
     * @Date 2019-12-17
     */
    @RequestMapping("/edit")
    public String edit() {
        return PREFIX + "/rolePrice_edit.html";
    }

    /**
     * 编辑页面
     *
     * @author dongyang
     * @Date 2019-12-17
     */
    @RequestMapping("/editDetial")
    public String editDetial() {
        return PREFIX + "/rolePrice_detialPrice.html";
    }

    /**
     * 新增接口
     *
     * @author dongyang
     * @Date 2019-12-17
     */
    @RequestMapping("/addItem")
    @ResponseBody
    public ResponseData addItem(RolePriceParam rolePriceParam) {
        this.rolePriceService.add(rolePriceParam);
        return ResponseData.success();
    }

    /**
     * 编辑接口
     *
     * @author dongyang
     * @Date 2019-12-17
     */
    @RequestMapping("/editItem")
    @ResponseBody
    public ResponseData editItem(RolePriceParam rolePriceParam) {
        this.rolePriceService.update(rolePriceParam);
        return ResponseData.success();
    }

    /**
     * 删除接口
     *
     * @author dongyang
     * @Date 2019-12-17
     */
    @RequestMapping("/delete")
    @ResponseBody
    public ResponseData delete(RolePriceParam rolePriceParam) {
        this.rolePriceService.delete(rolePriceParam);
        return ResponseData.success();
    }

    /**
     * 查看详情接口
     *
     * @author dongyang
     * @Date 2019-12-17
     */
    @RequestMapping("/detail")
    @ResponseBody
    public ResponseData detail(RolePriceParam rolePriceParam) {
        RolePrices detail = this.rolePriceService.getById(rolePriceParam.getId());
        if(detail == null){
            detail = new RolePrices();
            detail.setRoleId(rolePriceParam.getRoleId());
        }
        return ResponseData.success(detail);
    }

    /**
     * 查询列表
     *
     * @author dongyang
     * @Date 2019-12-17
     */
    @ResponseBody
    @RequestMapping("/list")
    public LayuiPageInfo list(RolePriceParam rolePriceParam) {
        return this.rolePriceService.findPageBySpec(rolePriceParam);
    }

    /**
     * 查询列表 里层，含有价格。
     *
     * @author dongyang
     * @Date 2019-12-17
     */
    @ResponseBody
    @RequestMapping("/listWithPrice")
    public LayuiPageInfo listWithPrice(RolePriceParam rolePriceParam) {
        return this.rolePriceService.findPageBySpec(rolePriceParam);
    }

    /**
     * 查询每个商品和每个角色的关系，列表展示
     *
     * @author dongyang
     * @Date 2019-12-17
     */
    @ResponseBody
    @RequestMapping("/listWithDetialPrice")
    public LayuiPageInfo listWithDetialPrice(RolePriceParam rolePriceParam) {
        return this.rolePriceService.findDetialPricePageBySpec(rolePriceParam);
    }
}


