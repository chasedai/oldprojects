package karaokeSys.Frames;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class reception {
	

	public static void main(String[] args) throws Exception {
		Socket s;
		ServerSocket ss = new ServerSocket(2000);
		while (true) {
			System.out.println("Server: waiting for connection ..");
			s = ss.accept();
			InputStream in = s.getInputStream();
			Scanner r = new Scanner(in);
			OutputStream o = s.getOutputStream();
			PrintWriter p = new PrintWriter(o);
			String inputLine;
			inputLine = r.nextLine();
			System.out.println(inputLine);
			switch (Integer.parseInt(inputLine)) {
			case 1:
				break;

			case 2:
				System.out.println("收到消息");
				mainFrame.pause();
				p.println(1);
				p.flush();
				
				break;

			default:
				break;

			}
			// send String back to the client
			p.close();
		}
	}
}