package com.luban.test;

import com.luban.dao.CardDao;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;

public class MyImportBeanDefinitionRegistrar implements ImportBeanDefinitionRegistrar {
	public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {

		//first use the interface to create Bean definition
		BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.genericBeanDefinition(CardDao.class);
		GenericBeanDefinition beanDefinition= (GenericBeanDefinition) beanDefinitionBuilder.getBeanDefinition();
		//then use FactoryBean to set the beanName and object , so beanName meet autowired requirement and object is from proxy
		beanDefinition.getConstructorArgumentValues().addGenericArgumentValue("com.luban.dao.CardDao");
		beanDefinition.setBeanClass(MyFactoryBean.class);
		//finally we can register bean definition
		registry.registerBeanDefinition("cardDao",beanDefinition);
	}
}
