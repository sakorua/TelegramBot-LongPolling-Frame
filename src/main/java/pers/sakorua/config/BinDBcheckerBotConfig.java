package pers.sakorua.config;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author SaKoRua
 * @date 2022-02-28 1:26 AM
 * @Description //TODO
 */
@Component
@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BinDBcheckerBotConfig {
    @Value("${telegrambot.userName}")
    String userName;
    @Value("${telegrambot.botToken}")
    String botToken;
}
