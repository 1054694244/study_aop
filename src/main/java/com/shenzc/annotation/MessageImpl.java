package com.shenzc.annotation;

@MyAnnotation
public class MessageImpl implements Message {

	@MyAnnotation(msg = "我是自定义属性信息")
	private String name;

	@Override
	@MyAnnotation(msg = "我是自定义注解信息...")
	public void msg() {
		// TODO Auto-generated method stub
	}
}