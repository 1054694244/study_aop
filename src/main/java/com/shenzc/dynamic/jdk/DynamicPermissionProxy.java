package com.shenzc.dynamic.jdk;

import com.shenzc.staticproxy.extend.OrderService;
import com.shenzc.staticproxy.extend.OrderServiceImpl;
import lombok.extern.slf4j.Slf4j;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 基于JDK动态代理实现的权限认证代理类
 */
@Slf4j
public class DynamicPermissionProxy implements InvocationHandler{
    private Object target;
 
    public DynamicPermissionProxy(Object target) {
        this.target = target;
    }
 
    /**
     * @param obj    被代理对象
     * @param method 对象方法
     * @param args   方法参数
     * @return
     * @throws Throwable
     */
    public Object invoke(Object obj, Method method, Object[] args) throws Throwable {
        log.info("这里是权限认证切面，开始验证……");
        Object invoke = method.invoke(target,args);
        log.info("这里是权限认证切面，结束验证……");
        return invoke;
    }

    public static void main(String[] args) {
        OrderServiceImpl orderService = new OrderServiceImpl();
        Class<?> clazz = orderService.getClass();
        DynamicLogProxy logProxyHandler = new DynamicLogProxy(orderService);
        OrderService os = (OrderService) Proxy.newProxyInstance(clazz.getClassLoader(), clazz.getInterfaces(), logProxyHandler);
        //注：这里把日志代理类实例对象传入权限认证代理类中
        DynamicPermissionProxy dynamicPermissionProxy = new DynamicPermissionProxy(os);
        OrderService os2 = (OrderService)Proxy.newProxyInstance(os.getClass().getClassLoader(),os.getClass().getInterfaces(),dynamicPermissionProxy);
        os2.reduceStock();
    }
}