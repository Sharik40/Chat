package academy.prog;

import java.io.IOException;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		Users user = new Users();
		try {
			System.out.println("Enter your login: ");
			String login = scanner.nextLine();
			user.addUser(login);
			Thread th = new Thread(new GetThread());
			th.setDaemon(true);
			th.start();

            System.out.println("Enter your message: ");
				while (true) {
					String text = scanner.nextLine();
					if (text.equals("/show"))
						System.out.println(user.getUsers());
					else if (text.startsWith("@")) {
						if (text.isEmpty()) break;
						String to = text.substring(1, text.indexOf(' '));
						Message m = new Message(login, text.substring(text.indexOf(' ')), to);
						int res = m.send(Utils.getURL() + "/add");

						if (res != 200) { // 200 OK
							System.out.println("HTTP error occurred: " + res);
							return;
						}
					}
					else {
						if (text.isEmpty()) break;

						Message m = new Message(login, text);
						int res = m.send(Utils.getURL() + "/add");

						if (res != 200) { // 200 OK
							System.out.println("HTTP error occurred: " + res);
							return;
						}
					}
				}

		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			scanner.close();
		}
	}
}
