package com.guangmai.qiaoQ.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author ludongyang
 * @since 2019-11-28
 */
@TableName("file_product_relation")
public class FileProductRelation implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 图片和产品关联表ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 产品ID
     */
    @TableField("product_id")
    private Integer productId;

    /**
     * 图片ID
     */
    @TableField("file_id")
    private String fileId;

    /**
     * 用户ID
     */
    @TableField("user_id")
    private Long userId;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public String getFileId() {
        return fileId;
    }

    public void setFileId(String fileId) {
        this.fileId = fileId;
    }

    @Override
    public String toString() {
        return "FileProductRelation{" +
        "id=" + id +
        ", productId=" + productId +
        ", fileId=" + fileId +
        "}";
    }
}
