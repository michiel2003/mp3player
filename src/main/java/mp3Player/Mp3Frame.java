package mp3Player;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Mp3Frame  {
	
	Connection dbConnect;

	public static void main(String[] args) {
		Mp3Frame exec = new Mp3Frame();
		exec.mp3Frame();
	}
	
	public void mp3Frame() {
		PlayMp player = new PlayMp();
		JFrame frame = new JFrame();
		JPanel panel = new JPanel();
		JButton button = new JButton();
		button.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					dbConnect =  DriverManager.getConnection("jdbc:mysql://localhost:3307/Mp3Tracks?user=root&password=password123");
					PreparedStatement prepstate = dbConnect.prepareStatement("select * from track");
					prepstate.execute();
					ResultSet result = prepstate.getResultSet();
					while(result.next()) {
						System.out.println(result.getString(4));
						String file = new File(result.getString(4)).toURI().toString();
						player.play(file);
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		
		frame.add(panel);
		panel.add(button);
		frame.pack();
		frame.setVisible(true);
	}
	
}
