package com.shenzc.annotation;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class MyTest {

	Annotation[] annotation = null;
	
	public static void main(String[] args) throws ClassNotFoundException {
		new MyTest().getAnnotation();
	}

	private void getAnnotation() throws ClassNotFoundException {
		Class<?> clazz = Class.forName("com.shenzc.annotation.MessageImpl"); //
		boolean isEmpty = clazz.isAnnotationPresent(MyAnnotation.class); //判断clazz是否使用了MyAnnotation自定义用注解
		if (isEmpty) {
			annotation = clazz.getAnnotations(); //获取注解接口
			for (Annotation a : annotation) {
				MyAnnotation my = (MyAnnotation) a; //强制转换成MyAnnotation类型
				System.out.println(clazz + "--" + my.msg());
			}
		}
		
		Method[] method = clazz.getMethods();
		System.out.println("Method");
		for (Method m : method) {
			boolean ismEmpty = m.isAnnotationPresent(MyAnnotation.class);
			if (ismEmpty) {
				Annotation[] aa = m.getAnnotations();
				for (Annotation a : aa) {
					MyAnnotation my = (MyAnnotation) a;
					System.out.println(m + "--" + my.msg());
				}
			}
		}

        Field[] fields = clazz.getDeclaredFields();
        System.out.println("Field");
        for (Field field:fields){
            boolean isfEmpty = field.isAnnotationPresent(MyAnnotation.class);
            if (isfEmpty){
                Annotation[] bb = field.getAnnotations();
                for (Annotation a : bb){
                    MyAnnotation fy = (MyAnnotation)a;
                    System.out.println(field+"--"+fy.msg());
                }
            }
        }
    }
}