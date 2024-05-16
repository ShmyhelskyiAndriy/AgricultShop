/**
 * Курсова робота з ООП. Індивідуальне завдання:"Oн-лайн платформа для торгівлі сільськогосподарською технікою"
 * @author Shmyhelskyi Andrii
 */
package com.example.DemoTestBot;

import com.example.DemoTestBot.service.TechSupportBot;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

/**
  * Клас запускає функцію main() і починається виконання програми
  * @see TechSupportBot
  * @see Menu
  */
@SpringBootApplication
public class AgricultShop {

	public static void main(String[] args){
		SpringApplication.run(TechSupportBot.class, args);
		try {
			TelegramBotsApi botsApi = new TelegramBotsApi(DefaultBotSession.class);
			botsApi.registerBot(new TechSupportBot());
		} catch (TelegramApiException e) {
			e.printStackTrace();
		}

		new Menu().menu();
	}
}

