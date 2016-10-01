package com.zhiqiu;

import com.zhiqiu.interceptor.DemoInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

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

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
       registry.addViewController("/index").setViewName("/index");
    }
}
