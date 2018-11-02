/** 
 * @author Vasily Isaev
 * @version 7.20
*/
/*
Task_11

Добавьте GUI для приложения из Task_10.
*/

import java.awt.Container;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;


public class Task11 extends JFrame  {
	private JButton button = new JButton("выполнить");
	private static JTextField input1 = new JTextField("", 5);
	private JLabel label = new JLabel("<html>Это база данных магазина мебели<br>Доступные комманды :"
			+ "<br>цена имя_товара - узнать цену товара<br>изменить имя_товара новая_цена - изменить цену"
			+ "<br>индекс имя_товара - узнать идекс товара"
			+ "<br>диапазон цена1 цена2 - вывести товары в ценовом диапозоне<html>");

	public Task11() {
		this.setBounds(600, 600, 750, 700);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container container = this.getContentPane();
		container.setLayout(new GridLayout(3, 10, 10, 20));
		container.add(label);
		container.add(input1);
		button.addActionListener(new ButtonEventListener());
		container.add(button);
	}

	class ButtonEventListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {

			if (Task_10.isSubstring(input1.getText(), "цена ")) {
				try {
					JOptionPane.showMessageDialog(null, (Task_10.price(input1.getText().substring(4, input1.getText().length()))),
							"Цена", JOptionPane.PLAIN_MESSAGE);
				} catch (HeadlessException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
// if (isSubstring(input1.getText(), "индекс ")) { 
// String input = ""; 
// JOptionPane.showMessageDialog(null, (getIndex(input1.getText().substring("индекс ".length(),input1.getText().length()))), 
// "Информация", 
// JOptionPane.PLAIN_MESSAGE); 
// } 
			if (Task_10.isSubstring(input1.getText(), "изменить ")) {
				JOptionPane.showMessageDialog(null, Task_10.changePrice(input1.getText()), "Новая цена",
						JOptionPane.PLAIN_MESSAGE);
			}
// if (isSubstring(input1.getText(), "диапазон ")) { 
// priceRange(input1.getText()); 
// } 

			if (Task_10.isSubstring(input1.getText(), "помощь")) {

				JOptionPane.showMessageDialog(null, ("<html>Это база данных магазина мебели<br>Доступные комманды :"
						+ "<br>цена имя_товара - узнать цену товара<br>изменить имя_товара новая_цена - изменить цену"
						+ "<br>индекс имя_товара - узнать идекс товара"
						+ "<br>диапазон цена1 цена2 - вывести товары в ценовом диапозоне<html>"), "Цена",
						JOptionPane.PLAIN_MESSAGE);

			}

		}
	}



	public static void main(String[] args) throws IOException {
		Task11 app = new Task11();
		app.setVisible(true);
		Scanner scan = new Scanner(System.in);
		String input = "";
		FileWriter writer = new FileWriter("base.data", false);

		for (int i = 0; i < Task_10.defProduct.length; i++) {
			writer.write(Task_10.defProduct[i] + "\n");
		}
// input= input1.getText(); 
		while (!input.equals("выход") && !input.equals("Выход")) {
			input = scan.nextLine();

			if (Task_10.isSubstring(input, "цена ")) {
				System.out.println(Task_10.price(input.substring(4, input.length())));
			} else if (Task_10.isSubstring(input, "индекс ")) {
				System.out.println(Task_10.getIndex(input1.getText().substring("индекс ".length(), input1.getText().length())));
			} else if (Task_10.isSubstring(input, "изменить ")) {
				System.out.println(Task_10.changePrice(input));
			} else if (Task_10.isSubstring(input, "диапазон ")) {
				Task_10.priceRange(input);
			} else if (Task_10.isSubstring(input, "помощь")) {
				System.out.println("Это база данных магазина мебели \n" + "Доступные комманды :\n");
				System.out.println("цена имя_товара - узнать цену товара ");
				System.out.println("изменить имя_товара новая_цена - изменить цену ");
				System.out.println("индекс имя_товара - узнать идекс товара");
				System.out.println("диапазон цена1 цена2 - вывести товары в ценовом диапозоне");
				System.out.println("выход - выход ");
			} else {
				System.out.println("Такой команды не существует. \nкоманда \"помощь\" выведет доступные команды");
			}
		}

	}
}
