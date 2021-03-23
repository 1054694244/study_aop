package com.shenzc.staticproxy.aggregation;

import com.shenzc.staticproxy.extend.OrderService;
import com.shenzc.staticproxy.extend.OrderServiceImpl;
import lombok.extern.slf4j.Slf4j;

/**
 * 聚合方式实现静态代理：代理类中引入业务类
 */
@Slf4j
public class OrderServiceLogProxy2 implements OrderService {
 
    private OrderServiceImpl orderService;
 
    public OrderServiceLogProxy2(OrderServiceImpl orderService) {
        this.orderService = orderService;
    }

    public void reduceStock() {
        log.info("预减库存开始……");
        orderService.reduceStock();
        log.info("预减库存结束……");
    }

    public static void main(String[] args) {
        OrderServiceLogProxy2 proxy2 = new OrderServiceLogProxy2(new OrderServiceImpl());
        proxy2.reduceStock();
    }
}