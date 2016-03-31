package iOS_Client.Actions;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

import iOS_Client.Model.Song;

public class CommandCollection {

	public boolean AddToPlayList(int Action, Song song){
		try{
			String title=song.getTitle();
			String language=song.getLanguage();
			String path=song.getPath();
			String singer=song.getSinger();
			InetAddress inet = InetAddress.getByName("192.168.23.1");
			Socket s = new Socket(inet, 2000);
			OutputStream o = s.getOutputStream();
			PrintWriter p = new PrintWriter(o);
			InputStream in = s.getInputStream();
			Scanner r = new Scanner(in);
			p.println(1);
			p.println(title);
			p.println(language);
			p.println(path);
			p.println(singer);
			p.flush();
			return true;
		}catch(Exception e){
			
			return false;
		}
		
	}
}
