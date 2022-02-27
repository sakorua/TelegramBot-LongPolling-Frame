package pers.sakorua.service;

import org.telegram.telegrambots.meta.api.methods.send.SendAnimation;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

public interface BinDBcheckerBotService {
    SendMessage checker(Update update);

    SendMessage start(Update update);

}
