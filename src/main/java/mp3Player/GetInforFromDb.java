package mp3Player;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mysql.cj.protocol.Resultset;

public class GetInforFromDb {
	
//	public static void main(String[] args) {
//		GetInforFromDb exec = new GetInforFromDb();
//		for(String a: exec.getArtists()) {
//			System.out.println(a);
//		}
//	}
	
	
	
	
	
	public List<String> getArtists(){
		try {
			List<String> results = new ArrayList<String>();
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3307/mp3tracks","root","password123");
			PreparedStatement prepstate = con.prepareStatement("Select * from Artist");
			prepstate.execute();
			ResultSet result = prepstate.getResultSet();
			while(result.next()) {
				results.add(result.getString(2));
			}
			return results;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public List<String> getAlbum(String name){
		try {
			List<String> results = new ArrayList<String>();
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3307/mp3tracks","root","password123");
			PreparedStatement prepstate = con.prepareStatement("select album.Title from album join artist on album.ArtistId = artist.ArtistId where artist.Name like \"" + name + "\";");
			prepstate.execute();
			ResultSet result = prepstate.getResultSet();
			while(result.next()) {
				results.add(result.getString(1));
			}
			return results;
		}catch(SQLException e) {
			e.printStackTrace();
		} return null;
	}
	
	public List<String> getTrack(String name){
		try {
			List<String> results = new ArrayList<String>();
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3307/mp3tracks","root","password123");
			PreparedStatement prepstate = con.prepareStatement("select track.Title from track join album on track.AlbumId = album.AlbumId where album.Title like \"" + name + "\"");
			prepstate.execute();
			ResultSet result = prepstate.getResultSet();
			while(result.next()) {
				results.add(result.getString(1));
			} return results;
		} catch(SQLException e) {
			e.printStackTrace();
		}return null;
	}
	
	public String getPath(String name) {
		try {
			String path = "";
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3307/mp3tracks","root","password123");
			PreparedStatement prepstate = con.prepareStatement("select track.SavePath from track where track.Title like \"" + name + "\"");
			prepstate.execute();
			ResultSet result = prepstate.getResultSet();
			while(result.next()) {
				path = result.getString(1);
			} 
			if(path == "") {
				return null;
			}
			return path;
		} catch(SQLException e) {
			e.printStackTrace();
		} return null;
	}

}
