package com.zhiqiu.messageconverter;

import com.zhiqiu.domain.DemoObj;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.converter.AbstractHttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.util.StreamUtils;

import java.io.IOException;
import java.nio.charset.Charset;

/**
 * Author  知秋
 * Created by kauw on 2016/10/1.
 */
public class MyMessageConverter extends AbstractHttpMessageConverter<DemoObj>{
    /**
     * 表明只处理DemoObj这个类
     * Class类的isAssignableFrom(Class cls)方法，
     * 如果调用这个方法的class或接口 与 参数cls表示的类或接口相同，
     * 或者是参数cls表示的类或接口的父类，则返回true。
     * 形象地：自身类.class.isAssignableFrom(自身类或子类.class) 返回true
     * @param clazz
     * @return
     */
    @Override
    protected boolean supports(Class<?> clazz) {
        return DemoObj.class.isAssignableFrom(clazz) ;
    }

    /**
     *
     *处理请求数据
     */
    @Override
    protected DemoObj readInternal(Class<? extends DemoObj> clazz, HttpInputMessage inputMessage) throws IOException, HttpMessageNotReadableException {
        String temp= StreamUtils.copyToString(inputMessage.getBody(),
                Charset.forName("utf-8"));
        String[] tempArr=temp.split("-");
        return new DemoObj(new Long(tempArr[0]),tempArr[1]);
    }
    /**
    * @author kauw 2016/10/1
    * @time 23:45
    * 处理如何输出数据到response
    */
    @Override
    protected void writeInternal(DemoObj obj, HttpOutputMessage outputMessage) throws IOException, HttpMessageNotWritableException {

        String out="hello:"+obj.getId()+"-"+obj.getName();
        outputMessage.getBody().write(out.getBytes());
    }
}
