package com.example.DemoTestBot.service;import com.example.DemoTestBot.Const;import org.springframework.stereotype.Component;import org.telegram.telegrambots.bots.TelegramLongPollingBot;import org.telegram.telegrambots.meta.api.methods.commands.SetMyCommands;import org.telegram.telegrambots.meta.api.methods.send.SendMessage;import org.telegram.telegrambots.meta.api.objects.Message;import org.telegram.telegrambots.meta.api.objects.PhotoSize;import org.telegram.telegrambots.meta.api.objects.Update;import org.telegram.telegrambots.meta.api.objects.commands.BotCommand;import org.telegram.telegrambots.meta.api.objects.commands.scope.BotCommandScopeDefault;import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;import org.telegram.telegrambots.meta.exceptions.TelegramApiException;import java.util.ArrayList;import java.util.Collections;import java.util.List;@Componentpublic class TestBot extends TelegramLongPollingBot {    @Override    public String getBotUsername() {        return Const.BOT_USERNAME;    }    @Override    public String getBotToken() {        return Const.BOT_TOKEN;    }    public TestBot(){        List<BotCommand> listofCommands = new ArrayList<>();        listofCommands.add(new BotCommand("/start", "get a welcome message"));        listofCommands.add(new BotCommand("/name", "get your name"));        listofCommands.add(new BotCommand("/registration", "sign up me"));        listofCommands.add(new BotCommand("/settings", "set your preferences"));        try {            this.execute(new SetMyCommands(listofCommands, new BotCommandScopeDefault(), null));        } catch (TelegramApiException e) {            e.printStackTrace();        }    }    @Override    public void onUpdateReceived(Update update) {        if (update.hasMessage() && update.getMessage().hasText()) {            String messageText = update.getMessage().getText();            long chatId = update.getMessage().getChatId();            switch (messageText) {                case "/start":                    startCommand(chatId, update.getMessage().getChat().getFirstName());                    break;                case "/name":                    sendMessage(chatId, update.getMessage().getChat().getFirstName());                    break;                case "/registration":                    break;                default:                    sendMessage(chatId, "команда не робе");                    break;            }        }    }    private void startCommand(long chatId, String name) {        String answer = "Hi, " + name + ", nice to meet you!";        sendMessage(chatId, answer);    }    private void sendMessage(long chatId, String textToSend) {        SendMessage message = new SendMessage();        message.setChatId(String.valueOf(chatId));        message.setText(textToSend);        try {            execute(message);        } catch (TelegramApiException e) {            sendMessage(chatId, "error");        }    }}