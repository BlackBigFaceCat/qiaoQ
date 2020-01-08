package com.guangmai.qiaoQ.model;

import lombok.Data;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author ludongyang
 * @since 2019-11-28
 */
@Data
public class FileProductRelationDTO implements Serializable {

    private static final long serialVersionUID = 1L;


    /**
     * 图片和产品关联表ID
     */
    private Integer id;

    /**
     * 产品ID
     */
    private Integer productId;

    /**
     * 图片ID
     */
    private String fileId;

    /**
     *  用户ID
     */
    private Long userId;

}
