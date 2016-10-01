package com.zhiqiu;

import com.zhiqiu.interceptor.DemoInterceptor;
import com.zhiqiu.messageconverter.MyMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

import java.util.List;

/**
 * Author  知秋
 * Created by kauw on 2016/9/29.
 */
@Configuration
@EnableWebMvc //只有开启了这个下面的WebMvcConfigurerAdapter重写的方法才会生效
@EnableScheduling
@ComponentScan("com.zhiqiu")
public class MyMvcConfig extends WebMvcConfigurerAdapter {
    @Bean
    public InternalResourceViewResolver viewResolver() {
        InternalResourceViewResolver viewResolver =
                new InternalResourceViewResolver();
        viewResolver.setPrefix("/WEB-INF/classes/views/");
        viewResolver.setSuffix(".jsp");
        viewResolver.setViewClass(JstlView.class);
        return viewResolver;
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/assets/**")
                .addResourceLocations("classpath:/assets/");
    }

    //将定义好的拦截器加入到容器中
    @Bean
    public DemoInterceptor demoInterceptor(){
        return new DemoInterceptor();
    }
    //将自定义的拦截器进行注册
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
       registry.addInterceptor(demoInterceptor());
    }

    @Bean
    public MultipartResolver multipartResolver(){
        CommonsMultipartResolver multipartResolver=
                new CommonsMultipartResolver();
        multipartResolver.setMaxUploadSize(1048576);
        return multipartResolver;
    }
    @Override //addViewController来简化没有业务的页面转向
    public void addViewControllers(ViewControllerRegistry registry) {
       registry.addViewController("/index").setViewName("/index");
        registry.addViewController("/onupload").setViewName("/upload");
        registry.addViewController("/converter").setViewName("/converter");
    }

    @Override //通过此设置可以不忽略路径中的. 比如http：//abc/bc.cc
    public void configurePathMatch(PathMatchConfigurer configurer) {
       configurer.setUseSuffixPatternMatch(false);
    }

    @Bean
    public MyMessageConverter myMessageConverter(){
        return new MyMessageConverter();
    }
    @Override //添加一个自定义的httpmessageconverter
    public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
        converters.add(myMessageConverter());
    }
}
