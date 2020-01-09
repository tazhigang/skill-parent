package com.ittzg.dueros.baseutil;

import com.baidu.dueros.bot.BaseBot;
import com.baidu.dueros.certificate.Certificate;
import com.baidu.dueros.data.request.IntentRequest;
import com.baidu.dueros.data.request.LaunchRequest;
import com.baidu.dueros.data.request.SessionEndedRequest;
import com.baidu.dueros.data.response.OutputSpeech;
import com.baidu.dueros.data.response.card.TextCard;
import com.baidu.dueros.model.Request;
import com.baidu.dueros.model.Response;
import com.fasterxml.jackson.databind.JsonMappingException;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @author: tazhigang
 * @date: 2020/1/6 19:57
 * @Email: tazhigang095@163.com
 * @desc:
 */
public class MovieTicketBot extends BaseBot {
    public MovieTicketBot(Request request) throws IOException {
        super(request);
    }

    public MovieTicketBot(String request) throws IOException, JsonMappingException {
        super(request);
    }

    public MovieTicketBot(Certificate certificate) throws IOException {
        super(certificate);
    }

    public MovieTicketBot(HttpServletRequest request) throws IOException, JsonMappingException {
        super(request);
    }

    @Override
    protected Response onLaunch(LaunchRequest launchRequest) {
        // 新建文本卡片
        TextCard textCard = new TextCard("电影票售票中心");
        // 设置链接地址
        textCard.setUrl("www:....");
        // 设置链接内容
        textCard.setAnchorText("setAnchorText");
        // 添加引导话术
        textCard.addCueWord("电影票");
        // 新建返回的语音内容
        OutputSpeech outputSpeech = new OutputSpeech(OutputSpeech.SpeechType.PlainText, "欢迎来到测试案例一的界面、你可以对我说买电影票");
        // 构造返回的Response
        Response response = new Response(outputSpeech, textCard);
        return response;
    }

    /**
     * 重写onInent方法，处理onInent对话事件
     *
     * @param intentRequest
     *            IntentRequest请求体
     * @see BaseBot#onInent(IntentRequest)
     */
    @Override
    protected Response onInent(IntentRequest intentRequest) {
        String movieTicket = getSlot("movie.ticket");
        String confirmFlag = getSlot("movie.confirm");
        // 判断NLU解析的意图名称是否匹配 inquiry_tax
        if ("movie-ticket".equals(intentRequest.getIntentName())) {
            // 判断NLU解析解析后是否存在这个槽位
            if(movieTicket!=null){
                // 构造TextCard
                TextCard textCard = new TextCard("为您推荐《少年的你》，请问是否购买;对我说好的");
                OutputSpeech outputSpeech = new OutputSpeech(OutputSpeech.SpeechType.PlainText, "为您推荐《少年的你》，请问是否购买;对我说好");
                // 构造返回的Response
                this.setExpectSpeech(true);
                Response response = new Response(outputSpeech, textCard);
                return response;
            }
            if(confirmFlag!=null){
                // 构造TextCard
                TextCard textCard = new TextCard("已成功为您购买《少年的你》电影票，您可以继续对我说买电影票");
                OutputSpeech outputSpeech = new OutputSpeech(OutputSpeech.SpeechType.PlainText, "已成功为您购买《少年的你》电影票，您可以继续对我说买电影票");
                // 构造返回的Response
                this.setExpectSpeech(true);
                Response response = new Response(outputSpeech, textCard);
                return response;
            }

        }

        return null;
    }

    /**
     * 重写onSessionEnded事件，处理onSessionEnded对话事件
     *
     * @param sessionEndedRequest
     *            SessionEndedRequest请求体
     * @see BaseBot#onSessionEnded(SessionEndedRequest)
     */
    @Override
    protected Response onSessionEnded(SessionEndedRequest sessionEndedRequest) {
        // 构造OutputSpeech
        OutputSpeech outputSpeech = new OutputSpeech(OutputSpeech.SpeechType.PlainText, "感谢您使用该技能，下次再见");
        // 构造Response
        Response response = new Response(outputSpeech);
        return response;
    }
}
