package karaokeSys.Actions;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class registerAccount {

	public boolean register(String username, String password) throws Exception{
		InetAddress inet = InetAddress.getByName("localhost");
		Socket s = new Socket(inet, 2000);
		OutputStream o = s.getOutputStream();
		PrintWriter p = new PrintWriter(o);
		InputStream in = s.getInputStream();
		Scanner r = new Scanner(in);
		p.println("1");                            //1 represent register account
		p.println(username);
		p.println(password);
		p.flush();
		String inputLine = r.nextLine();
		if(Integer.parseInt(inputLine)==1){
			//success
			System.out.println("success");
			return true;
		}else{
			//failure
			System.out.println("failure");
			return false;
		}
		
	}
}
