package mp3Player;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.WindowConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class Mp3Frame {

	Connection dbConnect;

	public static void main(String[] args) {
		Mp3Frame exec = new Mp3Frame();
		exec.mp3Frame();
	}

	GetInforFromDb getinfo = new GetInforFromDb();
	DefaultListModel<String> Artists = new DefaultListModel<String>();
	DefaultListModel<String> Albums = new DefaultListModel<String>();
	DefaultListModel<String> Tracks = new DefaultListModel<String>();

	public void UpdateArtists() {
		Artists.clear();
		Artists.addAll(getinfo.getArtists());
	}

	public void UpdateAlbums(String name) {
		Albums.clear();
		Albums.addAll(getinfo.getAlbum(name));
	}

	public void UpdateTracks(String album) {
		Tracks.clear();
		Tracks.addAll(getinfo.getTrack(album));
	}

	JList<String> ArtistList = new JList<String>(Artists);
	JList<String> AlbumList = new JList<String>(Albums);
	JList<String> TrackList = new JList<String>(Tracks);

	public void mp3Frame() {
		
		PlayMp player = new PlayMp();
		
		JFrame frame = new JFrame();
		JPanel panel = new JPanel();

		UpdateArtists();

		ArtistList.addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent e) {
				if (ArtistList.getSelectedValue() != null) {
					UpdateAlbums(ArtistList.getSelectedValue());
					ArtistList.clearSelection();
				}
				if (ArtistList.getSelectedValue() == null) {
					ArtistList.clearSelection();
					TrackList.clearSelection();
					AlbumList.clearSelection();
				}

			}
		});

		AlbumList.addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent e) {
				if (AlbumList.getSelectedValue() != null) {
					UpdateTracks(AlbumList.getSelectedValue());
				}
				if (AlbumList.getSelectedValue() == null) {
					AlbumList.clearSelection();
				}

			}
		});

		TrackList.addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent e) {
				if (TrackList != null) {
					String test = getinfo.getPath(TrackList.getSelectedValue());
					if (test != null) {
						player.play(getinfo.getPath(TrackList.getSelectedValue()));
						player.mediaplayer.play();
					}
					if(test == null) {
					}
				}
				if (TrackList == null) {
					TrackList.clearSelection();
				}

			}
		});
		
		JButton play = new JButton("PLAY");
		JButton pause = new JButton("PAUSE");
		 
		pause.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				player.pause();				
			}
		});
		
		play.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				player.unpause();
			}
		});
		
		frame.add(panel);
		panel.add(ArtistList);
		panel.add(AlbumList);
		panel.add(TrackList);
		
		panel.add(play);
		panel.add(pause);
		
		frame.setSize(new Dimension(800, 800));
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}

}
