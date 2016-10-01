package com.zhiqiu.service;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.async.DeferredResult;

/**
 * Author  知秋
 * Created by kauw on 2016/10/2.
 */
@Service
public class PushService {
    //通过这个service产生DeferredResult给控制器使用 （deferred 延期的，延缓，推迟）
    //对Spring DeferredResult的理解:将请求线程与后台执行线程分离，异步开来
    private DeferredResult<String> deferredResult;
    public DeferredResult<String> getAsyncUpdate(){
        deferredResult=new DeferredResult<>();
        return deferredResult;
    }
    //通过定时注解来定时更新deferredResult
    @Scheduled(fixedDelay = 5000)
    public void refresh(){
        if (deferredResult!=null){
            deferredResult.setResult(new Long(System.currentTimeMillis())
            .toString());

        }
    }
}
