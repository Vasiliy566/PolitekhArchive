
/*
 Task_10
  Работа с БД. 
  Сформировать таблицу товаров (id, prodid, title, cost) запросом из Java-приложения. 
  При запуске приложения очищать таблицу и заполнять N товаров вида: id_товара 1 товар1 10 
  Написать консольное приложение, которое позволяет узнать цену товара по его имени, 
  либо если такого товара нет, то должно быть выведено сообщение "Такого товара нет". 
  Пример: /price товар777
 */

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class Task_10 {

	static final String[] defProduct = { "1 кресло 3000", "2 шкаф 7000", "3 диван 11000", "4 кровать 9000" };

	public static boolean isSubstring(String origin, String sub) {
		for (int i = 0; i <= origin.length() - sub.length(); i++) {
			if (sub.charAt(0) == origin.charAt(i)) {
				for (int j = i; j < i + sub.length(); j++) {
					if (sub.charAt(j - i) != origin.charAt(j))
						break;
					if (j == i + sub.length() - 1)
						return true;
				}

			}
		}
		return false;
	}

	static String price(String productName) {
		try {
			FileInputStream fstream = new FileInputStream("base.data");
			BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
			String strLine;
			while ((strLine = br.readLine()) != null) {
				if (isSubstring(strLine, productName)) {
					String price = "";
					for (int i = 2 + productName.length(); i < strLine.length() && strLine.charAt(i) != ' '; i++) {
						price += strLine.charAt(i);
					}
					return price;
				}
			}
		} catch (IOException e) {
			System.out.println("Ошибка");
		}
		return "can't fund " + productName + " in our Base";
	}

	static int getIndex(String name) throws IOException {
		FileInputStream fstream = new FileInputStream("base.data");
		BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
		String strLine;
		String ind = "";
		while ((strLine = br.readLine()) != null) {
			if (isSubstring(strLine, name)) {

				for (int i = 0; i < strLine.length(); i++) {
					if (strLine.charAt(i) == ' ') {
						break;
					}
					ind += strLine.charAt(i);
				}
			}
		}
		return Integer.valueOf(ind);
	}

	static void priceRange(String input) throws IOException {
		String price1 = "";
		String price2 = "";
		for (int i = 2; i < input.length(); i++) {
			if (input.charAt(i) == ' ')
				break;
			price2 += input.charAt(i);
		}
		price2 = input.substring(2 + price1.length() + 1, input.length());
		FileInputStream fstream = new FileInputStream("base.data");
		BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
		String strLine;
		while ((strLine = br.readLine()) != null) {
			String name = "";
			for (int i = 2; i < strLine.length(); i++) {
				if (strLine.charAt(i) == ' ')
					break;
				name += strLine.charAt(i);
			}
			System.out.println((name));// + "|" + price1 + "|" + price2);
			// if (Integer.valueOf(price(name)) >= Integer.valueOf(price1)
			// && Integer.valueOf(price(name)) <= Integer.valueOf(price2)) {
			// System.out.println(strLine);
		}
	}

	static String changePrice(String input) {
		String name = "";
		String price = "";
		for (int i = "изменить ".length(); i < input.length(); i++) {
			if (input.charAt(i) == ' ') {
				price = input.substring(i + 1, input.length());
				break;
			}
			name += input.charAt(i);
		}

		try {
			FileInputStream fstream = new FileInputStream("base.data");
			BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
			String strLine;
			StringBuilder sb = new StringBuilder();
			while ((strLine = br.readLine()) != null) {

				sb.append(strLine.replace(getIndex(name) + " " + name + price(name),
						getIndex(name) + " " + name + " " + price)).append("\r\n");
			}
			try (FileWriter fileWriter = new FileWriter("base.data")) {
				fileWriter.write(sb.toString());
			}
		} catch (IOException e) {

			System.out.println("Ошибка");

		}
		return "теперь цена товара \"" + name + "\" - " + price;

	}

	public static void main(String[] args) throws IOException {
		Scanner scan = new Scanner(System.in);
		String input = "";
		FileWriter writer = new FileWriter("base.data", false);

		for (int i = 0; i < defProduct.length; i++) {
			writer.write(defProduct[i] + "\n");
		}
		writer.flush();
		System.out.println("Это база данных магазина мебели \n" + "Доступные комманды :\n");
		System.out.println("цена имя_товара - узнать цену товара ");
		System.out.println("изменить имя_товара новая_цена - изменить цену  ");
		System.out.println("индекс имя_товара - узнать идекс товара");
		System.out.println("диапазон цена1 цена2 - вывести товары в ценовом диапозоне");
		System.out.println("выход - выход ");

		while (!input.equals("выход") && !input.equals("Выход")) {
			input = scan.nextLine();
			if (isSubstring(input, "цена ")) {
				System.out.println(price(input.substring(4, input.length())));
			}
			else if (isSubstring(input, "индекс ")) {
				System.out.println(getIndex(input.substring("индекс ".length(), input.length())));
			}
			else if (isSubstring(input, "изменить ")) {
				System.out.println(changePrice(input));
			}
			else if (isSubstring(input, "диапазон ")) {
				priceRange(input);
			}else if (isSubstring(input, "помощь")) {
				System.out.println("Это база данных магазина мебели \n" + "Доступные комманды :\n");
				System.out.println("цена имя_товара - узнать цену товара ");
				System.out.println("изменить имя_товара новая_цена - изменить цену  ");
				System.out.println("индекс имя_товара - узнать идекс товара");
				System.out.println("диапазон цена1 цена2 - вывести товары в ценовом диапозоне");
				System.out.println("выход - выход ");	
			} else {System.out.println("Такой команды не существует. \nкоманда \"помощь\" выведет доступные команды");}
		}
	}

}
