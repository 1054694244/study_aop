package com.shenzc.annotation;

@MyAnnotation //使用自定义注解
public interface Message {
	
	@MyAnnotation
	public void msg();
}