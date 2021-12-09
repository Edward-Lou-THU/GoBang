import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ButtLstr implements ActionListener, gbSetting{
    public myGBFrame Frame;
    public JComboBox Box;   //更换游戏模式

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
            // 新游戏，此时重绘棋盘
            for(int i = 0; i < rowIdx; i++)
                for(int j = 0; j < colIdx; j++)
                    Frame.pceStatus[i][j] = 0;
            Frame.repaint();
            Frame.player = 1;  // 1号玩家开始游戏
        }
        else if (e.getActionCommand().equals("Regret"))
        {   //悔棋
            if (Frame.chessPositionArrayList.size() > 1 && Frame.player != 0)
            {   //上一步下棋的不是AI

                //丢弃落子顺序数组中最后一个数，并且保存最后一枚棋子的位置信息
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
                Frame.shwError("游戏结束", "白棋获胜");
            if(Frame.player == 2 && !Frame.actAI)
                Frame.shwError("游戏结束", "黑棋获胜");
            Frame.player = 0;  //锁住
        }
        else if(e.getActionCommand().equals("Rule"))
        {
            JDialog rlFrame = new JDialog();
            rlFrame.setBounds( new Rectangle(200, 200, 400, 400));
            JLabel JL = new JLabel("五子棋游戏规则");
            rlFrame.getContentPane().add(JL);
            Font newFont = new Font("TimesNewRoman", Font.ITALIC, 12);
            JL.setFont(newFont);
            JL.setText("<html>黑白双方依次落子,任一方先在棋盘上形成横向、竖向、斜向的连续的相同颜色的五个(含五个以上)棋子的一方为胜。<br><br>" +
                    " 无禁手规则时黑棋必胜，故而本游戏中设置以下禁手规则：<br>" +
                    " 黑方形成三三禁手时，其汇点不能落子<br>" +
                    " 黑方不能形成两个冲四或活四 <br>" +
                    " 黑方不可以形成长连(即六个以上连子) "); // 这里添加游戏规则
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
