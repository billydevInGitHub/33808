package com.luban.test;

import org.apache.ibatis.annotations.Select;
import org.springframework.beans.factory.FactoryBean;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class MyFactoryBean implements FactoryBean, InvocationHandler {
	Class clazz;

	public MyFactoryBean(Class clazz){
		this.clazz=clazz;
	}
	public Object getObject() throws Exception {
		Class[] clazzs= new Class[]{clazz};
		Object proxy	= Proxy.newProxyInstance(this.getClass().getClassLoader(), clazzs, this);
		return proxy;
	}

	public Class<?> getObjectType() {
		return clazz;
	}

	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		System.out.println("Proxy");
		//hardcode the arges type
		Method method1=proxy.getClass().getInterfaces()[0].getMethod(method.getName(),String.class);
		Select select=method1.getDeclaredAnnotation(Select.class);
		System.out.println(	"Select SQL is:"+select.value()[0]);
		return null;
	}
}
