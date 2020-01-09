package com.ittzg.dueros;

import com.baidubce.faas.core.FaasContext;
import com.baidubce.faas.core.InvokeHandler;
import org.apache.commons.io.IOUtils;

import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

/**
 * @author: ittzg
 * @date: 2020/1/6 18:55
 * @Email: tazhigang095@163.com
 * @desc:
 */
public class App implements InvokeHandler {
    @Override
    public void invoke(InputStream inputStream, OutputStream outputStream, FaasContext faasContext) throws Exception {
        try {
            String result = IOUtils.toString(inputStream, StandardCharsets.UTF_8);
            Bot bot = new Bot(result);
            bot.disableVerify();
            //调用bot的run方法
            String responseJson = bot.run();
            outputStream.write(responseJson.getBytes());
        } catch (Exception e) {
            e.printStackTrace();
            outputStream.write("{\"status\":1,\"msg\":\"function error\"}".getBytes());
        }
    }
}
