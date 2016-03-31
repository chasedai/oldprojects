package karaokeSys.Frames;

import java.awt.EventQueue;

import javax.swing.JFileChooser;

import java.io.BufferedReader;
import java.io.File;   
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.PrintWriter;

import javax.swing.JFrame;

import java.awt.Color;

import javax.swing.JList;

import java.awt.BorderLayout;

import javax.swing.AbstractListModel;

import java.awt.FileDialog;
import java.awt.TextArea;
import java.awt.Window.Type;
import java.awt.Dialog.ModalExclusionType;
import java.awt.FlowLayout;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JFileChooser;
import javax.swing.border.BevelBorder;
import javax.swing.UIManager;
import javax.swing.border.SoftBevelBorder;
import javax.swing.JMenuBar;
import javax.swing.JMenu;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.event.MenuListener;
import javax.swing.event.MenuEvent;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;
import java.util.Vector;

import javax.swing.JTable;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.border.LineBorder;
import javax.swing.JTabbedPane;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;
import com.sun.corba.se.impl.orbutil.ObjectWriter;

import javax.swing.JLabel;


public class mainFrame implements java.io.Serializable {

	private JFrame frmChasesKaraokeApp;
	private int row;
	private int row2;
	private int row3;
	private static Process proc;
	private static String[] cmd;
	private static boolean isPlay = false;
	private static boolean isPause = false;
	private static boolean isLeft = true;
	private static String[] randomStrArray;
	private Thread t1;
	private Thread t2;
	private int volume = 80;
	//已点歌曲
	//private Vector<Song> songs = new Vector<Song>();
	//private Map<String, Integer> orderMap = new HashMap<String, Integer>();
	//private Map<String, Integer> switchMap = new HashMap<String, Integer>();

	/**
	 * Launch the application.
	 */
	
//	private FileDialog load;
	JFileChooser fc = new JFileChooser();
	private JTextField filepathText;
	private JButton btnPlay;
	private JButton btnSwitch;
	private JButton btnAddSong;
	private JTable table;
	private JScrollPane scrollPane_1;
	private JTable table_1;
	private JScrollPane scrollPane;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					mainFrame window = new mainFrame();
					window.frmChasesKaraokeApp.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public mainFrame() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmChasesKaraokeApp = new JFrame();
		frmChasesKaraokeApp.setModalExclusionType(ModalExclusionType.TOOLKIT_EXCLUDE);
		frmChasesKaraokeApp.setTitle("Karaoke APP");
		frmChasesKaraokeApp.getContentPane().setBackground(Color.WHITE);
		
		filepathText = new JTextField();
		filepathText.setBounds(180, 28, 97, 24);
		filepathText.setColumns(10);
		Connection conn=null;
		Statement stmt;
		String url = "jdbc:mysql://localhost:3306/chaseKTV";
		Vector data = new Vector();  
		Vector v = new Vector();  Vector names = new Vector();  
	    
		  names.add("Title");  
		  names.add("Language");  
		  names.add("Path");  
		  names.add("Singer");
		try {
			// 调用Class.forName()方法加载驱动程序
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("success");
			conn = (Connection) DriverManager.getConnection(url, "root",
					"admin");
			stmt = (Statement) conn.createStatement();
			System.out.print("Success");
			String sql = "select * from songs";  
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next())  
			  {  
			    v.clear();  
			    v.add(rs.getObject(1));  
			    v.add(rs.getObject(2));  
			    v.add(rs.getObject(3));  
			    v.add(rs.getObject(4));  
			    data.add(v.clone());    //注意此处不能用 data.add(v);
			   }
			stmt.close();
			conn.close();
		} catch (Exception e1) {
			System.out.println("Driver not found");
			e1.printStackTrace();
		}

		JButton btnChooseSong = new JButton("Search");
		btnChooseSong.setBounds(280, 27, 81, 27);
		btnChooseSong.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//create an instance of jfilechooser
				JFileChooser fileChooser = new JFileChooser();
				//set home directory, otherwise MyDocuments
				fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
				int result = fileChooser.showOpenDialog(null);
				//
				if (result == JFileChooser.APPROVE_OPTION) {
				    filepathText.setText(fileChooser.getSelectedFile().getAbsolutePath()); 
				}
			
			}
		});
		
		btnPlay = new JButton("Start");
		btnPlay.setBounds(30, 200, 81, 27);
		btnPlay.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DefaultTableModel tableModel_playList = (DefaultTableModel) table_1.getModel();
				String path = (String) tableModel_playList.getValueAt(0, 2);
				play(path);
			}
		});
		
		btnSwitch = new JButton("Switch");
		btnSwitch.setBounds(118, 200, 81, 27);
		btnSwitch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				balance();
			}
		});
		
		btnAddSong = new JButton("Add to playlist");
		btnAddSong.setBounds(180, 236, 181, 27);
		btnAddSong.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser fileChooser = new JFileChooser();
				//set home directory, otherwise MyDocuments
				//fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
				//int result = fileChooser.showOpenDialog(null);
				//这个属性不懂
				//if (result == JFileChooser.APPROVE_OPTION) {
					int r = table.getSelectedRow();
					System.out.println(r);
					DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
					DefaultTableModel tableModel_playList = (DefaultTableModel) table_1.getModel();
					String title = (String) tableModel.getValueAt(r, 0);
					String language = (String) tableModel.getValueAt(r, 1);
					String path = (String) tableModel.getValueAt(r, 2);
					String singer = (String) tableModel.getValueAt(r, 3);
					tableModel_playList.addRow(new Object[]{title, language, path,singer});
				//}
			}
		});
		frmChasesKaraokeApp.getContentPane().setLayout(null);
		frmChasesKaraokeApp.getContentPane().add(filepathText);
		frmChasesKaraokeApp.getContentPane().add(btnPlay);
		frmChasesKaraokeApp.getContentPane().add(btnSwitch);
		frmChasesKaraokeApp.getContentPane().add(btnChooseSong);
		frmChasesKaraokeApp.getContentPane().add(btnAddSong);
		
		scrollPane_1 = new JScrollPane(table);
		scrollPane_1.setBounds(30, 270, 331, 117);
		frmChasesKaraokeApp.getContentPane().add(scrollPane_1);
		
		table = new JTable(data,names);
		scrollPane_1.setViewportView(table);
		table.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(30, 67, 331, 120);
		frmChasesKaraokeApp.getContentPane().add(scrollPane);
		
		table_1 = new JTable(null,names);
		scrollPane.setViewportView(table_1);
		
		JLabel lblPlayList = new JLabel("Play List:");
		lblPlayList.setBounds(30, 34, 86, 18);
		frmChasesKaraokeApp.getContentPane().add(lblPlayList);
		
		JLabel lblAllSongs = new JLabel("All songs:");
		lblAllSongs.setBounds(30, 240, 86, 18);
		frmChasesKaraokeApp.getContentPane().add(lblAllSongs);
		
		final JButton btnPause = new JButton("Pause");
		btnPause.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pause();
				if(btnPause.getText().equals("Pause")){
					btnPause.setText("Continue");
				}else{
					btnPause.setText("Pause");
				}
			}
		});
		btnPause.setBounds(206, 200, 81, 27);
		frmChasesKaraokeApp.getContentPane().add(btnPause);
		
		JButton btnDel = new JButton("Del");
		btnDel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int r = table_1.getSelectedRow();
				DefaultTableModel tableModel = (DefaultTableModel) table_1.getModel();
				tableModel.removeRow(r);// r是要删除的行序号
			}
		});
		btnDel.setBounds(291, 200, 70, 27);
		frmChasesKaraokeApp.getContentPane().add(btnDel);
		Vector<String> columns = new Vector<String>(); 
		  columns.add("Title");  
		  columns.add("Singer");  
		  columns.add("Language");  
		  columns.add("Path");
		frmChasesKaraokeApp.setBounds(100, 100, 416, 465);
		frmChasesKaraokeApp.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
		
	public static void play(String path) {
		//stop();
		isPlay=true;

		//button_Play.setText("暂停");
		//调用命令行,更多选项请参考mplayer文档
		cmd = new String[] { "mplayer.exe",//mplayer路径
									"-vo","directx",//视频驱动
									"-identify", //输出详情
									"-slave", //slave模式播放
									"-colorkey", "0x000000",//视频窗口的背景色
				"-osdlevel", String.valueOf(3),//osd样式
				//				     "-osd_show_progression",
				//	                 "-osd","0",
				//					"monitoraspect","800:600",
				//					"-fs",
				path //播放文件路径
		};
		try {
			proc = Runtime.getRuntime().exec(cmd);
		} catch (IOException e1) {
			e1.printStackTrace();
			return;
		}
	}//如果取消下面的注释代码，这个括号要删除
//		final InputStream is1 = proc.getErrorStream();
//		final InputStream is2 = proc.getInputStream();
//		final Runnable errorReader = new Runnable() {
//			public void run() {
//				try {
//					final BufferedReader lReader = new BufferedReader(
//							new InputStreamReader(is1));
//					for (String l = lReader.readLine(); l != null; l = lReader
//							.readLine()) {
//									 System.out.println("ERROR "+l);
//					}
//				} catch (Throwable t) {
//					t.printStackTrace();
//				}
//			}
//		};
//		final Runnable standReader = new Runnable() {
//			public void run() {
//				try {
//					final BufferedReader lReader = new BufferedReader(
//							new InputStreamReader(is2));
//					String l = "";
//					while ((l = lReader.readLine()) != null) {
//					}
//				} catch (Throwable t) {
//					t.printStackTrace();
//				}
//			}
//		};
//		t1 = new Thread(errorReader);
//		t1.start();
//		t2 = new Thread(standReader);
//		t2.start();
//		final Thread playThread = new Thread() {
//			public void run() {
//				while (true) {
//					if (isPlay == false ) {
//						play(songs.elementAt(0).getSongUrl());
//						Connection con = null;
//						try {
//							con = dbUtil.getCon();
//							songDao.orderTimeAdd(con, songs.elementAt(0)
//									.getSongId());
//						} catch (Exception e) {
//							e.printStackTrace();
//						} finally {
//							try {
//								dbUtil.closeCon(con);
//							} catch (Exception e) {
//								e.printStackTrace();
//							}
//						}
//						songs.removeElementAt(0);
//						fillTable3();
//					}
//					try {
//						Thread.sleep(1000);
//					} catch (InterruptedException e) {
//						e.printStackTrace();
//					}
//
//				}
//			}
//
//		};
//		playThread.start();
//		Thread waitThread = new Thread() {
//			public void run() {
//				try {
//					proc.waitFor();
//					t1.interrupt();
//					t2.interrupt();
//					playComplete();
//				} catch (InterruptedException e) {
//					e.printStackTrace();
//				}
//
//			}
//		};
//		waitThread.start();
//	}
//
		/*
		 * 切换原唱伴唱
		 */
	public static void balance() {
		if (isLeft) {
			if (proc != null) {
				PrintStream s = new PrintStream(proc.getOutputStream());
				s.print("balance -1 1\n");
				s.flush();
				isLeft = !isLeft;
			}
			//jb_melody.setText("原唱");
		} else {
			if (proc != null) {
				PrintStream s = new PrintStream(proc.getOutputStream());
				s.print("balance 1 1\n");
				s.flush();
				isLeft = !isLeft;
			}
			//jb_melody.setText("伴唱");
		}

	}
//
	public static void pause() {
		if (proc != null) {
			System.out.println(proc.getOutputStream().toString());
			PrintStream s = new PrintStream(proc.getOutputStream());
			s.print("pause\n");
			s.flush();
			isPause = !isPause;
		}
		if (isPause) {
			
		} else {
			
		}
	}
//
//	private void playComplete() {
//		isPlay = false;
//		isPause = false;
//		//button_Play.setText("Play");
//	}
//
	public static void stop() {
		if (proc != null) {
			proc.destroy();
		}
		//playOffset=0;
		isPause = false;
		//button_Play.setText("播放");
		//progressBar.setValue(0);
	}
	
	public static void setVolume(int vol) {
		if (proc != null) {
			if (!isPause) {
				PrintStream s = new PrintStream(proc.getOutputStream());
				s.print("volume " + vol + " 1\n");
				s.flush();
			}
		}
	}
}

	
