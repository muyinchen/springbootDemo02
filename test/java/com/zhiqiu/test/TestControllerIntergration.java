package com.zhiqiu.test;

import com.zhiqiu.MyMvcConfig;
import com.zhiqiu.service.DemoService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Author  知秋
 * Created by kauw on 2016/10/2.
 * <p>
 * integration 整合，一体化
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {MyMvcConfig.class})
@WebAppConfiguration("src/main/resources")
public class TestControllerIntergration {
    //模拟MVC对象，通过 MockMvcBuilders.webAppContextSetup(WebApplicationContext context).build()初始化
    private MockMvc mockMvc;
    @Autowired
    private DemoService demoService;
    @Autowired
    WebApplicationContext wac;
    //注入模拟的httpsession，仅作演示，未使用
    @Autowired
    MockHttpSession session;
    //注入模拟的http request，仅作演示，未使用
    @Autowired
    MockHttpServletRequest request;

    @Before
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }

    @Test
    public void testNormalController()throws Exception{
        mockMvc.perform(get("/normal"))  //模拟向/normal发出get请求
            .andExpect(status().isOk())  //预期控制返回状态为200
            .andExpect(view().name("page")) //预期view名称为 page
            .andExpect(forwardedUrl("/WEB-INF/classes/views/page.jsp")) //预期页面转向的真正路径
            .andExpect(model().attribute("msg",demoService.saySomething())); //预期model中此方法返回值为hello

    }

    @Test
    public void testRestController()throws Exception{
        mockMvc.perform(get("/testRest")) //模拟向/testRest发get请求
        .andExpect(status().isOk())
                .andExpect(content().contentType("text/plain;charset=UTF-8")) //预期返回类型
        .andExpect(content().string(demoService.saySomething())); //预期返回值类型见上个test
    }
}
