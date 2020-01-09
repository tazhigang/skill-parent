package com.ittzg.dueros.controller;

import com.ittzg.dueros.baseutil.MovieTicketBot;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author: tazhigang
 * @date: 2020/1/6 19:55
 * @Email: tazhigang095@163.com
 * @desc:
 */
@RestController
@RequestMapping(value = "movie-ticket")
public class MovieTicketV1Controller {

    private static final Logger LOGGER = LoggerFactory.getLogger(MovieTicketV1Controller.class);

    @RequestMapping(value = "/bot", method = RequestMethod.POST)
    public void startBot(HttpServletRequest request, HttpServletResponse response) throws IOException {
        LOGGER.info("启动技能......");
        // 根据request创建Bot
        MovieTicketBot bot;

        // 测试空白技能
//        UnionpayBotV2 bot;
        try {
            bot = new MovieTicketBot(request);
            // 线下调试时，可以关闭签名验证
            // bot.enableVerify();
            bot.disableVerify();

            // 调用bot的run方法
            String responseJson = bot.run();
            LOGGER.info(responseJson);
            // 设置response的编码UTF-8
            response.setCharacterEncoding("UTF-8");
            response.setContentType("application/json; charset=utf-8");
            // 返回response
            response.getWriter().append(responseJson);

            // 打开签名验证
            // bot.enableVerify();

        } catch (Exception e) {
            if (LOGGER.isErrorEnabled()) {
                LOGGER.error("技能启动失败，失败原因:{[}]", e.toString());
            }
            e.printStackTrace();
            response.getWriter().append(e.toString());
        }
    }
}
