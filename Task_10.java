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
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;


public class Task_10 {
	static final String[] defProduct = {"1 кресло 3000" , "2 шкаф 7000" , "3 диван 11000" ,"4 кровать 9000"};
	public static void main(String[] args) throws IOException {
		Scanner scan = new Scanner(System.in);
		String input = "";
		FileWriter writer = new FileWriter("base.data", false);
        for( int i = 0; i < defProduct.length; i ++) {
        	writer.write(defProduct[i] + "\n");
        }
        System.out.println("Это база данных магазина мебели \n" + "Доступные комманды :\n");
        System.out.println ("цена имя_товара - узнать цену товара ");
        System.out.println ("изменить имя_товара новая_цена - изменить цену  ");
        System.out.println ("от цена1 до цена2 - вывести товары в ценовом диапозоне");
        System.out.println ("выход - выход ");
           
            writer.flush();
       
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		try{
			   FileInputStream fstream = new FileInputStream("C:/file.txt");
			   BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
			   String strLine;
			   while ((strLine = br.readLine()) != null){
			      System.out.println(strLine);
			   }
			}catch (IOException e){
			   System.out.println("Ошибка");
			}
	}

}
