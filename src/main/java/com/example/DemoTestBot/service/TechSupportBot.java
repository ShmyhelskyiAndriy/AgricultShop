package com.example.DemoTestBot.service;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

/**
 * Клас створенний для функціонування телеграм бота техпідтримки
 * за допомогою якого можна забезпечити зв'язок між користувачами
 * та модераторами платформи
 */
@Component
public class TechSupportBot extends TelegramLongPollingBot {

    @Override
    public String getBotUsername() {
        return "@AgriShop_bot";
    }

    @Override
    public String getBotToken() {
        return "6557682989:AAHswNBrja5vTGNxUt76kFES_a7cuTVk5sg";
    }

    private final long adminChatID = 795973878;

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            long userChatID;
            if(update.getMessage().getChatId()==adminChatID){
                String reply = update.getMessage().getText();
                userChatID = Long.parseLong(reply.split("\n")[0]);
                SendMessage sendMessage = new SendMessage();
                sendMessage.setChatId(userChatID);
                sendMessage.setText("Дякуємо, за ваше звернення, ми розглянемо його та зв'яжимося з вами, якщо буде потреба");
                try{
                    execute(sendMessage);
                }catch (TelegramApiException e){
                    e.printStackTrace();
                }


                reply  = reply.replace(reply.split("\n")[0], " ");
                sendMessage.setChatId(userChatID);
                sendMessage.setText("Надійшла відповідь: " + reply + "\nВід модератора: " + update.getMessage().getFrom().getFirstName());
                try{
                    execute(sendMessage);
                }catch (TelegramApiException e){
                    e.printStackTrace();
                }
            }
            else {
                userChatID =update.getMessage().getChatId();
                String request = update.getMessage().getText();

                SendMessage sendRequest = new SendMessage();
                sendRequest.setChatId(adminChatID);
                sendRequest.setText("Username: " + update.getMessage().getFrom().getUserName() +"\nChatID: "+ userChatID +"\n "+ request);
                try {
                    execute(sendRequest);
                }catch (TelegramApiException e){
                    e.printStackTrace();
                }
            }
        }
    }
}

