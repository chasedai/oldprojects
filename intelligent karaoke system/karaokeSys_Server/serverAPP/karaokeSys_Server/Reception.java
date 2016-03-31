package karaokeSys_Server;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

import karaokeSys.DAO.loginValidator;
import karaokeSys.DAO.registerAccount;

public class Reception {

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
			switch (Integer.parseInt(inputLine)) {
			case 1:										//1 means register
				String username, password;
				username = r.nextLine();
				password = r.nextLine();
				registerAccount rA = new registerAccount();
				if(rA.register(username, password)){
					p.println("1");
				}else{
					p.println("2");
				}
				break;

			case 2:										//2 means login validation
				String vali_username, vali_password;
				vali_username = r.nextLine();
				vali_password = r.nextLine();
				System.out.println(vali_username);
				System.out.println(vali_password);
				loginValidator lV = new loginValidator();
				if(lV.login(vali_username, vali_password)){
					p.println("1");
				}else{
					p.println("2");
				}
				break;

			default:
				break;

			}
			// send String back to the client
			p.close();
		}
	}
}
