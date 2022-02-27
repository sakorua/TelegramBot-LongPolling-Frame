package pers.sakorua.bots;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import pers.sakorua.config.BinDBcheckerBotConfig;
import pers.sakorua.service.BinDBcheckerBotService;

import javax.annotation.PostConstruct;

/**
 * @author SaKoRua
 * @date 2022-02-28 1:27 AM
 * @Description //TODO
 */
@Component
public class BinDBcheckerBot extends TelegramLongPollingBot {
    private static final Logger logger = LoggerFactory.getLogger(BinDBcheckerBot.class);

    @Value("${telegrambot.botToken}")
    private String token;

    @Value("${telegrambot.userName}")
    private String username;

    @Autowired
    private BinDBcheckerBotService binDBcheckerBotService;


    @Override
    public String getBotUsername() {
        return username;
    }

    @Override
    public String getBotToken() {
        return token;
    }

    @Override
    public void onUpdateReceived(Update update) {

        if (update.hasMessage() && update.getMessage().hasText()) {

            String msgText = update.getMessage().getText();


            if (msgText.equals("/start")) {
                try {
                    execute(binDBcheckerBotService.start(update));
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
            }

            if (msgText.matches("/bin \\d{6}\\b")) {


                try {
                    execute(binDBcheckerBotService.checker(update));
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                    logger.error("❌发送消息异常 =======》 原因：{}",e.getMessage());
                }
            }
        }
    }
}
