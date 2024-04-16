package com.example.DemoTestBot;

import com.example.DemoTestBot.service.TestBot;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;


@SpringBootApplication
public class DemoTestBotApplication {

	public static void main(String[] args) {


//		SpringApplication.run(DemoTestBotApplication.class, args);
//		try {
//			TelegramBotsApi botsApi = new TelegramBotsApi(DefaultBotSession.class);
//			botsApi.registerBot(new TestBot());
//		} catch (TelegramApiException e) {
//			e.printStackTrace();
//		}
	}
}
