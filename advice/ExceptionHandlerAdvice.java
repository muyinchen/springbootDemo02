package com.zhiqiu.advice;

import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

/**
 * Author  知秋
 * Created by kauw on 2016/10/1.
 */
@ControllerAdvice //将控制器的全局配置放在有这个注解的位置 源码包含了@Component，所以会自动注册Bean
//可以在注解了@controller的类的方法上使用@ExceptionHandler，@InitBinder,@ModelAttribute注解方法，对所有注解了@RequestMapping
//的控制器内的方法都有效
public class ExceptionHandlerAdvice {
    @ExceptionHandler(value = Exception.class)
    public ModelAndView exception(Exception exception, WebRequest webRequest){
        ModelAndView modelAndView=new ModelAndView("error");//error页面
        modelAndView.addObject("errorMessage",exception.getMessage());
        return modelAndView;
    }

    @ModelAttribute //将键值对添加到全局，所以注解有@RequestMapping的方法都可以获得
    public void addAttributes(Model model){
        model.addAttribute("msg","积极扩大");
    }

    @InitBinder //定制WebDataBinder
    public void initBinder(WebDataBinder webDataBinder){
        webDataBinder.setDisallowedFields("id"); //顾名思义，此处忽视request里的参数id
    }
}
