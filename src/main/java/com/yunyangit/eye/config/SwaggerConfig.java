package com.yunyangit.eye.config;

import com.google.common.collect.Lists;
import com.yunyangit.eye.filter.TokenFilter;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * swagger文档
 * 
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {
	
	// 是否开启swagger，正式环境一般需要关闭的，可根据springboot的多环境配置进行设置
	@Value(value = "${swagger.enabled}")
	Boolean swaggerEnbled;
	
	@Bean
	public Docket docket() {
		ParameterBuilder builder = new ParameterBuilder();
		builder.parameterType("header").name(TokenFilter.TOKEN_KEY)
				.description("header参数")
				.required(false)
				.modelRef(new ModelRef("string")); // 在swagger里显示header

		return new Docket(DocumentationType.SWAGGER_2).groupName("swagger接口文档")
				.apiInfo(new ApiInfoBuilder().title("swagger接口文档")
						.contact(new Contact("xxx", "", "xxx@xx.com")).version("1.0").build())
						.enable(swaggerEnbled) // 是否开启
				.globalOperationParameters(Lists.newArrayList(builder.build()))
				.select().paths(PathSelectors.any()).build();
	}
}
