import javax.sound.sampled.*;
import javax.swing.*;
import java.applet.Applet;
import java.applet.AudioClip;
import java.io.*;
import java.awt.*;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import sun.audio.*;
public class myGBFrame extends JPanel implements gbSetting {
	public Graphics myGraphics;
	public int[][] pceStatus = new int[column][row];   //0代表没下子，1代表黑棋，2代表白棋
	public ArrayList<pceCord> chessPositionArrayList = new ArrayList<pceCord>();
	public short player = 0;  //0代表不可操作, 1代表1号玩家， 2代表2号玩家
	public boolean actAI = false;  //T代表PVE，F代表PVP, 默认不激活AI

	public static HashMap<String, Integer> wghtMap = new HashMap<String, Integer>();
	public int[][] wghtArray = new int[column][row];  //在PVE模式中保留各个点的权值
	// static修饰代表静态代码块
	JButton RuleButton = new JButton();

	static {
		wghtMap.put("01", 25);
		wghtMap.put("02", 22);
		wghtMap.put("001", 17);
		wghtMap.put("002", 12);
		wghtMap.put("0001", 17);
		wghtMap.put("0002", 12);

		wghtMap.put("0102", 25);
		wghtMap.put("0201", 22);
		wghtMap.put("0012", 15);
		wghtMap.put("0021", 10);
		wghtMap.put("01002", 25);
		wghtMap.put("02001", 22);
		wghtMap.put("00102", 17);
		wghtMap.put("00201", 12);
		wghtMap.put("00012", 15);
		wghtMap.put("00021", 10);

		wghtMap.put("01000", 25);
		wghtMap.put("02000", 22);
		wghtMap.put("00100", 19);
		wghtMap.put("00200", 14);
		wghtMap.put("00010", 17);
		wghtMap.put("00020", 12);
		wghtMap.put("00001", 15);
		wghtMap.put("00002", 10);

		wghtMap.put("0101", 65);
		wghtMap.put("0202", 60);
		wghtMap.put("0110", 80);
		wghtMap.put("0220", 76);
		wghtMap.put("011", 80);
		wghtMap.put("022", 76);
		wghtMap.put("0011", 65);
		wghtMap.put("0022", 60);

		wghtMap.put("01012", 65);
		wghtMap.put("02021", 60);
		wghtMap.put("0110", 80);
		wghtMap.put("0220", 76);
		wghtMap.put("0011", 65);
		wghtMap.put("0022", 60);

		wghtMap.put("01100", 80);
		wghtMap.put("02200", 76);
		wghtMap.put("01010", 75);
		wghtMap.put("02020", 70);
		wghtMap.put("00110", 75);
		wghtMap.put("00220", 70);
		wghtMap.put("00011", 75);
		wghtMap.put("00022", 70);

		wghtMap.put("0111", 150);
		wghtMap.put("0222", 140);
		wghtMap.put("01112", 150);
		wghtMap.put("02221", 140);

		wghtMap.put("01110", 1100);
		wghtMap.put("02220", 1050);
		wghtMap.put("01101", 1000);
		wghtMap.put("02202", 800);
		wghtMap.put("01011", 1000);
		wghtMap.put("02022", 800);

		wghtMap.put("01111", 3000);
		wghtMap.put("02222", 3500);
	}


	public static void main(String[] args) {
		myGBFrame gbFrame = new myGBFrame();
		gbFrame.initUI();
	}

	public void initUI() {
		JFrame mainFrame = new JFrame();  //主界面
		mainFrame.setTitle("GoBang Game");
		mainFrame.setSize(765, 635);
		mainFrame.setLocationRelativeTo(null); //默认出现于屏幕中央
		mainFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

		mainFrame.setLayout(new BorderLayout());   //采用左-右界面，这与QQ象棋是一致的

		Dimension boardDim = new Dimension(550, 0);  //棋盘
		Dimension panelDim = new Dimension(150, 0);  //控制面板
		Dimension buttonDim = new Dimension(140, 40);  //按钮

		setPreferredSize(boardDim);  //这个用法使得boardDim板的高度可以随窗口大小变化
		setBackground(Color.LIGHT_GRAY);  //下棋面板底色，默认情况下是看不到的
		mainFrame.add(this, "Center"); //放在框架中央

		JPanel gbPanel = new JPanel();
		gbPanel.setPreferredSize(panelDim);
		gbPanel.setBackground(Color.LIGHT_GRAY);
		mainFrame.add(gbPanel, "East");

		gbPanel.setLayout(new FlowLayout());  //按钮组用流式布局管理比较好

		String[] buttName = {"New Game", "Regret", "Surrender"};
		JButton[] buttArray = new JButton[3];
		int supIndex = buttArray.length;
		for (int i = 0; i < supIndex; i++) {
			buttArray[i] = new JButton(buttName[i]);
			buttArray[i].setPreferredSize(buttonDim);
			gbPanel.add(buttArray[i]);
		}

		//加入菜单按钮
		RuleButton.setPreferredSize(buttonDim);
		RuleButton.setText("Rule");
		gbPanel.add(RuleButton);

		String[] modeName = {"PVP", "PVE"};
		JComboBox modeBut = new JComboBox(modeName);
		gbPanel.add(modeBut);

		ButtLstr BL = new ButtLstr(this, modeBut);
		for (int i = 0; i < supIndex; i++) {
			buttArray[i].addActionListener(BL);
		}
		modeBut.addActionListener(BL);
		RuleButton.addActionListener(BL);

		FrameLstr frameLstr = new FrameLstr();
		frameLstr.setGraphics(this);
		this.addMouseListener(frameLstr);  //增加鼠标消息回馈

		mainFrame.setVisible(true);
		while(true)
		{
			playMusic();
		}
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);  //所有JFrame都需要重写paint函数，没有什么可说的

		Image brdIcon = new ImageIcon("E:\\清华大学_本科_课程学习\\2021-2022秋季学期\\硬课\\Java\\大作业\\MyGoBang_A2\\chessboard.jpg").getImage();
		g.drawImage(brdIcon, 0, 0, row * size, column * size, null);

		for (int i = 0; i < row; i++) {
			for (int j = 0; j < column; j++) {
				if (pceStatus[i][j] == 1) {
					int cntX = size * i + 20;
					int cntY = size * j + 20;
					g.drawImage(blkPiece, cntX - size / 2, cntY - size / 2, size, size, null);
				} else if (pceStatus[i][j] == 2) {
					int cntX = size * i + 20;
					int cntY = size * j + 20;
					g.drawImage(whtPiece, cntX - size / 2, cntY - size / 2, size, size, null);
				}
			}
		}
	}

	public void shwError(String title, String reason) {
		JOptionPane ErrorPane = new JOptionPane();
		ErrorPane.showMessageDialog(null, reason, title, JOptionPane.PLAIN_MESSAGE);
	}

	static void playMusic()
	{// 背景音乐播放
		try {
			int i = 0;
			while(i < 100) {
				AudioInputStream ais = AudioSystem.getAudioInputStream(new File("E:/清华大学_本科_课程学习/2021-2022秋季学期/硬课/Java/大作业/MyGoBang_A2/BGM.wav"));    //绝对路径
				AudioFormat aif = ais.getFormat();
				final SourceDataLine sdl;
				DataLine.Info info = new DataLine.Info(SourceDataLine.class, aif);
				sdl = (SourceDataLine) AudioSystem.getLine(info);
				sdl.open(aif);
				sdl.start();
				FloatControl fc = (FloatControl) sdl.getControl(FloatControl.Type.MASTER_GAIN);
				// value可以用来设置音量，从0-2.0
				double value = 1;
				float dB = (float) (Math.log(value == 0.0 ? 0.0001 : value) / Math.log(10.0) * 20.0);
				fc.setValue(dB);
				int nByte = 0;
				final int SIZE = 1024 * 64;
				byte[] buffer = new byte[SIZE];
				while (nByte != -1) {
					nByte = ais.read(buffer, 0, SIZE);
					sdl.write(buffer, 0, nByte);
				}
				sdl.stop();
				i++;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}