import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ButtLstr implements ActionListener, gbSetting{
    public myGBFrame Frame;
    public JComboBox Box;   //������Ϸģʽ

    public ButtLstr(myGBFrame tmpFrame, JComboBox tmpBox){
        this.Frame = tmpFrame;
        this.Box = tmpBox;
    }

    public ButtLstr(){}

    @Override
    public void actionPerformed(ActionEvent e) {
        int rowIdx = Frame.pceStatus.length;
        int colIdx = Frame.pceStatus[0].length;
        if(e.getActionCommand().equals("New Game"))
        {
            // ����Ϸ����ʱ�ػ�����
            for(int i = 0; i < rowIdx; i++)
                for(int j = 0; j < colIdx; j++)
                    Frame.pceStatus[i][j] = 0;
            Frame.repaint();
            Frame.player = 1;  // 1����ҿ�ʼ��Ϸ
        }
        else if (e.getActionCommand().equals("Regret"))
        {   //����
            if (Frame.chessPositionArrayList.size() > 1 && Frame.player != 0)
            {   //��һ������Ĳ���AI

                //��������˳�����������һ���������ұ������һö���ӵ�λ����Ϣ
                pceCord lstPiece = new pceCord();
                lstPiece = Frame.chessPositionArrayList.remove(Frame.chessPositionArrayList.size() - 1);

                Frame.pceStatus[lstPiece.X][lstPiece.Y] = 0;

                if (Frame.player == 1)
                    Frame.player = 2;
                else
                    Frame.player = 1;

                Frame.repaint();
            }
            else
            {
                Frame.shwError("Error!", "You cannot regret!");
            }
        }
        else if(e.getActionCommand().equals("Surrender"))
        {
            if(Frame.player == 1 && !Frame.actAI)
                Frame.shwError("��Ϸ����", "�����ʤ");
            if(Frame.player == 2 && !Frame.actAI)
                Frame.shwError("��Ϸ����", "�����ʤ");
            Frame.player = 0;  //��ס
        }
        else if(e.getActionCommand().equals("Rule"))
        {
            JDialog rlFrame = new JDialog();
            rlFrame.setBounds( new Rectangle(200, 200, 400, 400));
            JLabel JL = new JLabel("��������Ϸ����");
            rlFrame.getContentPane().add(JL);
            Font newFont = new Font("TimesNewRoman", Font.ITALIC, 12);
            JL.setFont(newFont);
            JL.setText("<html>�ڰ�˫����������,��һ�������������γɺ�������б�����������ͬ��ɫ�����(���������)���ӵ�һ��Ϊʤ��<br><br>" +
                    " �޽��ֹ���ʱ�����ʤ���ʶ�����Ϸ���������½��ֹ���<br>" +
                    " �ڷ��γ���������ʱ�����㲻������<br>" +
                    " �ڷ������γ��������Ļ���� <br>" +
                    " �ڷ��������γɳ���(��������������) "); // ���������Ϸ����
            JL.setVerticalAlignment(JLabel.NORTH);
            JL.setHorizontalAlignment(JLabel.CENTER);
            rlFrame.setModalityType(Dialog.ModalityType.APPLICATION_MODAL);
            rlFrame.setVisible(true);
        }
        else if(Box.getSelectedItem().equals("PVP"))
        {
            Frame.actAI = false;
            Frame.player = 0;
        }
        else if(Box.getSelectedItem().equals("PVE"))
        {
            Frame.actAI = true;
            Frame.player = 0;
        }
    }
}
