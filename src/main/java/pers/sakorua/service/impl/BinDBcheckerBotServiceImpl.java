package pers.sakorua.service.impl;

import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.telegram.telegrambots.meta.api.methods.send.SendAnimation;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import pers.sakorua.pojo.Bin;
import pers.sakorua.service.BinDBcheckerBotService;

/**
 * @author SaKoRua
 * @date 2022-02-28 1:43 AM
 * @Description //TODO
 */
@Service
public class BinDBcheckerBotServiceImpl implements BinDBcheckerBotService {
    @Override
    public SendMessage checker(Update update) {

        Integer messageId = update.getMessage().getMessageId();
        String textReq = update.getMessage().getText();
        String bin4check = textReq.replace("/bin ", "");
        RestTemplate restTemplate = new RestTemplate();
        Bin binObj = null;
        try {
            String forObject = restTemplate.getForObject("https://lookup.binlist.net/" + bin4check, String.class);

            binObj = JSONObject.parseObject(forObject, Bin.class);
        } catch (RestClientException e) {
            return new SendMessage(String.valueOf(update.getMessage().getChatId()),"请确认您发送的指令");
        }

        String prepaid = binObj.isPrepaid() ? "是" : "否";

        String binInfo = "<b>BIN</b> : <strong>[" + bin4check + "]</strong>\n" +
                "<b>发卡组织</b> : <strong>[" + binObj.getScheme() + "]</strong>\n" +
                "<b>卡等级</b> : <strong>[" + binObj.getBrand() + "]</strong>\n" +
                "<b>卡类型</b> : <strong>[" + binObj.getType() + "]</strong>\n" +
                "<b>预付</b> : <strong>[" + prepaid + "]</strong>\n" +
                "<b>国家</b> : <strong>[" + binObj.getCountry().getEmoji() + binObj.getCountry().getName() + "]</strong>\n" +
                "<b>银行</b> : <strong>[" + binObj.getBank().getName() + "]</strong>\n" +
                "<b>官网</b> : <strong>[" + binObj.getBank().getUrl() + "]</strong>\n" +
                "<b>电话</b> : <strong>[" + binObj.getBank().getPhone() + "]</strong>";

        String chatId = String.valueOf(update.getMessage().getChatId());
        SendMessage sendMessage = new SendMessage(chatId, binInfo);
        sendMessage.setReplyToMessageId(messageId);
        sendMessage.setParseMode("html");

        return sendMessage;
    }

    @Override
    public SendMessage start(Update update) {
        return new SendMessage(String.valueOf(update.getMessage().getChatId()),"Hello! If you want to contact the author,@sakorua_bot plz :)");
    }
}
