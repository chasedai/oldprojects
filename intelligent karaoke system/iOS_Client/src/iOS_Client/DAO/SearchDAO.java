package iOS_Client.DAO;

import iOS_Client.Model.Song;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SearchDAO extends connectionBuilder{

	public List<Song> search(String song_name){
		ResultSet rs;
		List<Song> songs = new ArrayList<Song>(); 
		try {
			connectDatabase();
			rs=stmt.executeQuery("select * from songs where title='"+song_name+"';"); 
			while(rs.next()){
				Song ts = new Song();
				ts.setTitle(rs.getString("title"));
				ts.setLanguage(rs.getString("language"));
				ts.setPath(rs.getString("path"));
				ts.setSinger(rs.getString("singer"));
				songs.add(ts);
			}
			stmt.close();
			conn.close();
			return songs;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		
	}
}
