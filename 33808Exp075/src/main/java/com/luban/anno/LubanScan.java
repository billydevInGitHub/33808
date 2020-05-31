package com.luban.anno;

import com.luban.test.MyImportBeanDefinitionRegistrar;
import org.springframework.context.annotation.Import;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


@Import(MyImportBeanDefinitionRegistrar.class)
@Retention(RetentionPolicy.RUNTIME)
public @interface LubanScan {
}
