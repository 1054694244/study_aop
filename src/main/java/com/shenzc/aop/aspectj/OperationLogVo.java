/*
 * Copyright (C) 1997-2020 康成投资（中国）有限公司
 *
 * http://www.rt-mart.com
 *
 * 版权归本公司所有，不得私自使用、拷贝、修改、删除，否则视为侵权
 */
package com.shenzc.aop.aspectj;

import lombok.Data;

import java.util.Date;

/**
 * @Description:
 * @Author Shenzc
 * @Date 2020/9/1 11:16
 */
@Data
public class OperationLogVo {
    //代理主键
    private Long id;

    //操作名称
    private String operationName;

    //请求路径
    private String url;

    //参数
    private String param;

    //0-RequestBody、1-RequestParam
    private Integer paramType;

    //来源
    private String source;

    //创建人
    private String createId;

    //创建时间
    private Date createTime;

    //更新人
    private String updateId;

    //修改时间
    private Date updateTime;
}
