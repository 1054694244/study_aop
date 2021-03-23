/*
 * Copyright (C) 1997-2020 康成投资（中国）有限公司
 *
 * http://www.rt-mart.com
 *
 * 版权归本公司所有，不得私自使用、拷贝、修改、删除，否则视为侵权
 */
package com.shenzc.aop.service;

import com.shenzc.aop.aspectj.OperationLog;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description:
 * @Author Shenzc
 * @Date 2020/9/1 11:07
 */
@RestController
public class Test {

    @OperationLog(value = "计算",args = "参数")
    @RequestMapping("/math")
    public void math(@RequestParam(value = "x") int x, @RequestParam(value = "y") int y){
        System.out.println(x+y);
    }

}
