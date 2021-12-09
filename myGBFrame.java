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
	public int[][] pceStatus = new int[column][row];   //0����û���ӣ�1������壬2�������
	public ArrayList<pceCord> chessPositionArrayList = new ArrayList<pceCord>();
	public short player = 0;  //0�����ɲ���, 1����1����ң� 2����2�����
	public boolean actAI = false;  //T����PVE��F����PVP, Ĭ�ϲ�����AI

	public static HashMap<String, Integer> wghtMap = new HashMap<String, Integer>();
	public int[][] wghtArray = new int[column][row];  //��PVEģʽ�б����������Ȩֵ
	// static���δ���̬�����
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
		JFrame mainFrame = new JFrame();  //������
		mainFrame.setTitle("GoBang Game");
		mainFrame.setSize(765, 635);
		mainFrame.setLocationRelativeTo(null); //Ĭ�ϳ�������Ļ����
		mainFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

		mainFrame.setLayout(new BorderLayout());   //������-�ҽ��棬����QQ������һ�µ�

		Dimension boardDim = new Dimension(550, 0);  //����
		Dimension panelDim = new Dimension(150, 0);  //�������
		Dimension buttonDim = new Dimension(140, 40);  //��ť

		setPreferredSize(boardDim);  //����÷�ʹ��boardDim��ĸ߶ȿ����洰�ڴ�С�仯
		setBackground(Color.LIGHT_GRAY);  //��������ɫ��Ĭ��������ǿ�������
		mainFrame.add(this, "Center"); //���ڿ������

		JPanel gbPanel = new JPanel();
		gbPanel.setPreferredSize(panelDim);
		gbPanel.setBackground(Color.LIGHT_GRAY);
		mainFrame.add(gbPanel, "East");

		gbPanel.setLayout(new FlowLayout());  //��ť������ʽ���ֹ���ȽϺ�

		String[] buttName = {"New Game", "Regret", "Surrender"};
		JButton[] buttArray = new JButton[3];
		int supIndex = buttArray.length;
		for (int i = 0; i < supIndex; i++) {
			buttArray[i] = new JButton(buttName[i]);
			buttArray[i].setPreferredSize(buttonDim);
			gbPanel.add(buttArray[i]);
		}

		//����˵���ť
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
		this.addMouseListener(frameLstr);  //���������Ϣ����

		mainFrame.setVisible(true);
		while(true)
		{
			playMusic();
		}
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);  //����JFrame����Ҫ��дpaint������û��ʲô��˵��

		Image brdIcon = new ImageIcon("E:\\�廪��ѧ_����_�γ�ѧϰ\\2021-2022�＾ѧ��\\Ӳ��\\Java\\����ҵ\\MyGoBang_A2\\chessboard.jpg").getImage();
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
	{// �������ֲ���
		try {
			int i = 0;
			while(i < 100) {
				AudioInputStream ais = AudioSystem.getAudioInputStream(new File("E:/�廪��ѧ_����_�γ�ѧϰ/2021-2022�＾ѧ��/Ӳ��/Java/����ҵ/MyGoBang_A2/BGM.wav"));    //����·��
				AudioFormat aif = ais.getFormat();
				final SourceDataLine sdl;
				DataLine.Info info = new DataLine.Info(SourceDataLine.class, aif);
				sdl = (SourceDataLine) AudioSystem.getLine(info);
				sdl.open(aif);
				sdl.start();
				FloatControl fc = (FloatControl) sdl.getControl(FloatControl.Type.MASTER_GAIN);
				// value��������������������0-2.0
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