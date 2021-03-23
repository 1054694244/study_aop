package com.shenzc.aop.aspectj;

import com.alibaba.fastjson.JSON;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.util.*;
import java.util.stream.Stream;

@Aspect
@Component
public class LogAspect {

    private static final Integer RequestBodyType = 0;
    private static final Integer RequestParamType = 1;

    /**
     * Pointcut：切点：定义发生的地方
     * 1.注解，注解加在哪个方法上就在哪里生效
     * 2.不是注解，表达式定位在哪里就在哪里生效，比如："execution(void com.shenzc.aop.service.Test.math(int,int))"，就在math方法上生效
     */
    @Pointcut("@annotation(com.shenzc.aop.aspectj.OperationLog)")
    public void log() {

    }

    @Before("log()")
    public void doBeforeController(JoinPoint joinPoint) {

        try {
            OperationLogVo operationLogVo = new OperationLogVo();

            MethodSignature signature = (MethodSignature) joinPoint.getSignature();
            Method method = signature.getMethod();
            OperationLog operationLog = method.getAnnotation(OperationLog.class);

            ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
            HttpServletRequest request = attributes.getRequest();

            operationLogVo.setOperationName(operationLog.value());
            operationLogVo.setUrl(request.getRequestURL().toString());
            //operationLogVo.setCreateId(request.getAttribute("SSO_USERNAME").toString());
            //operationLogVo.setUpdateId(request.getAttribute("SSO_USERNAME").toString());
            operationLogVo.setSource("shop-invite-backend");

            String param = "";
            if ("".equals(operationLog.args())) {
                Object[] orgArgs = joinPoint.getArgs();
                List<Object> argList = new ArrayList<Object>();
                for (Object arg : orgArgs) {
                    if (!(arg instanceof HttpServletRequest) && !(arg instanceof HttpServletResponse)) {
                        argList.add(arg);
                    }
                }
                if (argList.size() == 1) {
                    param = JSON.toJSONString(argList.get(0));
                } else {
                    param = JSON.toJSONString(argList);
                }

                operationLogVo.setParamType(RequestBodyType);
            } else {
                String[] args = operationLog.args().split(",");
                Object[] values = joinPoint.getArgs();
                List<Object> valueList = new ArrayList<Object>();
                for (Object value : values) {
                    if (!(value instanceof HttpServletRequest) && !(value instanceof HttpServletResponse)) {
                        valueList.add(value);
                    }
                }
                if (args.length == valueList.size()) {
                    Map<String, Object> paramMap = new HashMap<String, Object>();
                    for (int i = 0; i < args.length; i++) {
                        paramMap.put(args[i], valueList.get(i));
                    }
                    param = JSON.toJSONString(paramMap);
                } else {
                    Map<String, Object> map = new HashMap<String, Object>();
                    map.put("info", "入参和参数数量不等");
                    map.put("params", Arrays.asList(args).toString());
                    map.put("values", valueList.toString());
                    param = JSON.toJSONString(map);
                }

                operationLogVo.setParamType(RequestParamType);

            }
            operationLogVo.setParam(param);
        } catch (Exception e){
            e.printStackTrace();
        }

    }

}

/*
* ···Signature signature1 = joinPoint.getSignature();
        // 获取目标方法名
        System.out.println(signature1.getName());
        // 获取目标方法所属类的简单类名
        System.out.println(signature1.getDeclaringType().getSimpleName());
        // 获取目标方法所属类的全类名
        System.out.println(signature1.getDeclaringTypeName());
        // 获取目标方法声明类型(public、private、protected)
        System.out.println(signature1.getModifiers());
        // 获取传入目标方法的参数，返回一个数组
        Stream.of(joinPoint.getArgs()).forEach(System.out::println);
        // 获取被代理的对象
        System.out.println(joinPoint.getTarget());
        //  获取代理对象自己
        System.out.println(joinPoint.getTarget());

        //获取目标方法上的注解
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        Method method1 = methodSignature.getMethod();
        OperationLog annotation = method1.getAnnotation(OperationLog.class);
        System.out.println(annotation);
        //获取注解的参数
        System.out.println(annotation.args());
        System.out.println(annotation.value());
* */