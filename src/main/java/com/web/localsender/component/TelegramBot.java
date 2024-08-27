package com.web.localsender.component;


import com.web.localsender.config.BotConfig;
import com.web.localsender.model.Log;
import com.web.localsender.service.LogService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Component
@AllArgsConstructor
public class TelegramBot extends TelegramLongPollingBot {
    private final BotConfig botConfig;
    @Autowired
    LogService logService;

    @Override
    public String getBotUsername() {
        return botConfig.getBotName();
    }

    @Override
    public String getBotToken() {
        return botConfig.getToken();
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText() && update.getMessage().getChatId().equals(botConfig.getChatId())) {
            String msg = update.getMessage().getText();
            if (msg.equals("/start")) {
                sendMessage("# Hello. \nMy commands: /start, /all, /get.");
            } else if (msg.equals("/all")) {
                if (!logService.getAll().isEmpty()) {
                    for (Log log : logService.getAll()) {
                        sendMessage(log.toString());
                    }
                } else {
                    sendMessage("# We haven't got any logs");
                }
            } else if(msg.equals("/get")){
                if (!logService.getAllCreatedLogs().isEmpty()) {
                    for (Log log : logService.getAllCreatedLogs()) {
                        sendMessage(log.toString());
                    }
                    logService.setAllAsDelivered();
                }
                else{
                    sendMessage("# We haven't got any logs");
                }
            }
        }
    }


    private void sendMessage(String textToSend) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(String.valueOf(botConfig.getChatId()));
        sendMessage.setText(textToSend);
        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {

        }
    }
}
