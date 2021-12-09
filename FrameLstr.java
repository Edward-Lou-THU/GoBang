import java.awt.event.*;
import java.awt.Graphics;
import java.time.temporal.ValueRange;

public class FrameLstr  implements MouseListener, gbSetting{
    myGBFrame Frame;

    public void setGraphics(myGBFrame tmpFrame)
    {
        this.Frame = tmpFrame;
    }

    public Integer unionWeight(Integer a, Integer b)
    {
        // ��Ҫ��AI��ֵһЩ����Ȩ��
        if(a == null || b == null)
            return 0;

        // ��ʱ����101��202
        else if( a >= 22 && a <= 25 && b >= 22 && b <= 25)
            return 60;

        // ��ʱ����1011��2022
        else if((a >= 22 && a <= 25 && b >= 76 && b <= 80) ||
                (a >= 76 && a <= 80 && b >= 22 && b <= 25))
            return 800;

        // ��ʱ����10111��20222
        else if((a >= 10 && a <= 25 && b >= 1050 && b <= 1100) ||
                (a >= 1050 && a <= 1100 && b >= 10 && b <= 25) ||
                (a >= 76 && a <= 80 && b>= 76 && b <= 80))
            return 3000;

        else if((a >= 22 && a <= 25 && b >= 140 && b <= 150) ||
                (a >= 140 && a <= 150 && b >= 22 && b <= 25))
            return 3000;

        else if ((a >= 76 && a <= 80 && b >= 1050 && b <= 1100) ||
                 (a >= 1050 && a <= 1100 && b >= 76 && b <= 80))
            return 3000;
        else
            return 0;
    }

    @Override
    public void mouseClicked(java.awt.event.MouseEvent e) {
        int X = e.getX();
        int Y = e.getY();
//        System.out.println("X = " + X);
//        System.out.println("Y = " + Y);
        // �������ӽ������ĸ�����,��Ҫ��X��Y��һ����
        int rectX = 20 + (X / 40) * 40;
        int rectY = 20 + (Y / 40) * 40;
//        System.out.println("rectX = " + rectX);
//        System.out.println("rectY = " + rectY);
        // �ٷ��ݻ�����������е�λ��
        int ArrayX = (rectX - 20) / 40;
        int ArrayY = (rectY - 20) / 40;
//        System.out.println("ArrayX = " + ArrayX);
//        System.out.println("ArrayY = " + ArrayY);

        Graphics G = Frame.getGraphics();

        if (Frame.pceStatus[ArrayX][ArrayY] != 0)
        {
            Frame.shwError("ľ�ð취", "���ﲻ�������");
        }
        else
        {
            //�����PVPģʽ
            if (Frame.actAI == false)
            {
                if (Frame.player == 1) {
                    // ������ǰ�ж������Ƿ����ڽ�����

                    // step 1: �ж�33����
                    if (ArrayX > 0 && ArrayY > 0 && ArrayX < 14 && ArrayY < 14 &&
                            Frame.pceStatus[ArrayX - 1][ArrayY - 1] == 1 &&
                            Frame.pceStatus[ArrayX - 1][ArrayY + 1] == 1 &&
                            Frame.pceStatus[ArrayX + 1][ArrayY + 1] == 1 &&
                            Frame.pceStatus[ArrayX + 1][ArrayY - 1] == 1) {
                        System.out.println("��������");
                        Frame.shwError("����", "�ڷ���������");
                        return;
                    }   // ���ͽ���
                    if (ArrayX > 1 && ArrayY > 1 && Frame.pceStatus[ArrayX - 2][ArrayY] == 1 &&
                            Frame.pceStatus[ArrayX - 1][ArrayY] == 1 &&
                            Frame.pceStatus[ArrayX][ArrayY - 2] == 1 &&
                            Frame.pceStatus[ArrayX][ArrayY - 1] == 1) {
                        System.out.println("ֱ���ͽ���");
                        Frame.shwError("����", "�ڷ�����");
                        return;
                    }   // ֱ�ǽ���1
                    if (ArrayX < 13 && ArrayY < 13 && Frame.pceStatus[ArrayX + 2][ArrayY] == 1 &&
                            Frame.pceStatus[ArrayX + 1][ArrayY] == 1 &&
                            Frame.pceStatus[ArrayX][ArrayY + 2] == 1 &&
                            Frame.pceStatus[ArrayX][ArrayY + 1] == 1) {
                        System.out.println("ֱ���ͽ���");
                        Frame.shwError("����", "�ڷ�����");
                        return;
                    }  // ֱ�ǽ���2
                    if (ArrayX > 1 && ArrayY < 13 && Frame.pceStatus[ArrayX - 2][ArrayY] == 1 &&
                            Frame.pceStatus[ArrayX - 1][ArrayY] == 1 &&
                            Frame.pceStatus[ArrayX][ArrayY + 2] == 1 &&
                            Frame.pceStatus[ArrayX][ArrayY + 1] == 1) {
                        System.out.println("ֱ���ͽ���");
                        Frame.shwError("����", "�ڷ�����");
                        return;
                    }  // ֱ�ǽ���3
                    if (ArrayX < 13 && ArrayY > 1 && Frame.pceStatus[ArrayX + 1][ArrayY] == 1 &&
                            Frame.pceStatus[ArrayX + 2][ArrayY] == 1 &&
                            Frame.pceStatus[ArrayX][ArrayY - 2] == 1 &&
                            Frame.pceStatus[ArrayX][ArrayY - 1] == 1) {
                        System.out.println("ֱ���ͽ���");
                        Frame.shwError("����", "�ڷ�����");
                        return;
                    }  // ֱ�ǽ���4

                    // step 2: �ж�44���֣�44���ֵ��ж��߼�Ҫ����һЩ
                    if (ArrayX > 2 && ArrayY > 2 && Frame.pceStatus[ArrayX - 3][ArrayY] == 1 &&
                            Frame.pceStatus[ArrayX - 2][ArrayY] == 1 &&
                            Frame.pceStatus[ArrayX - 1][ArrayY] == 1 &&
                            Frame.pceStatus[ArrayX][ArrayY - 3] == 1 &&
                            Frame.pceStatus[ArrayX][ArrayY - 2] == 1 &&
                            Frame.pceStatus[ArrayX][ArrayY - 1] == 1) {
                        System.out.println("ֱ���ͽ���");
                        Frame.shwError("����", "�ڷ����Ľ���");
                        return;
                    }  // ֱ�ǽ���1
                    else if (ArrayX > 2 && ArrayY < 12 && Frame.pceStatus[ArrayX - 3][ArrayY] == 1 &&
                            Frame.pceStatus[ArrayX - 2][ArrayY] == 1 &&
                            Frame.pceStatus[ArrayX - 1][ArrayY] == 1 &&
                            Frame.pceStatus[ArrayX][ArrayY + 3] == 1 &&
                            Frame.pceStatus[ArrayX][ArrayY + 2] == 1 &&
                            Frame.pceStatus[ArrayX][ArrayY + 1] == 1) {
                        System.out.println("ֱ���ͽ���");
                        Frame.shwError("����", "�ڷ����Ľ���");
                        return;
                    }  // ֱ�ǽ���2
                    else if(ArrayX < 12 && ArrayY < 12 && Frame.pceStatus[ArrayX + 3][ArrayY] == 1 &&
                            Frame.pceStatus[ArrayX + 2][ArrayY] == 1 &&
                            Frame.pceStatus[ArrayX + 1][ArrayY] == 1 &&
                            Frame.pceStatus[ArrayX][ArrayY + 3] == 1 &&
                            Frame.pceStatus[ArrayX][ArrayY + 2] == 1 &&
                            Frame.pceStatus[ArrayX][ArrayY + 1] == 1) {
                        System.out.println("ֱ���ͽ���");
                        Frame.shwError("����", "�ڷ����Ľ���");
                        return;
                    }  // ֱ�ǽ���3
                    else if (ArrayX < 12 && ArrayY > 2 &&
                            Frame.pceStatus[ArrayX + 3][ArrayY] == 1 &&
                            Frame.pceStatus[ArrayX + 2][ArrayY] == 1 &&
                            Frame.pceStatus[ArrayX + 1][ArrayY] == 1 &&
                            Frame.pceStatus[ArrayX][ArrayY - 3] == 1 &&
                            Frame.pceStatus[ArrayX][ArrayY - 2] == 1 &&
                            Frame.pceStatus[ArrayX][ArrayY - 1] == 1) {
                        System.out.println("ֱ���ͽ���");
                        Frame.shwError("����", "�ڷ����Ľ���");
                        return;
                    }  // ֱ�ǽ���4
                    else if (ArrayX > 0 && ArrayY > 0 && ArrayX < 13 && ArrayY < 13 &&
                            Frame.pceStatus[ArrayX - 1][ArrayY] == 1 &&
                            Frame.pceStatus[ArrayX + 1][ArrayY] == 1 &&
                            Frame.pceStatus[ArrayX + 2][ArrayY] == 1 &&
                            Frame.pceStatus[ArrayX][ArrayY - 1] == 1 &&
                            Frame.pceStatus[ArrayX][ArrayY + 1] == 1 &&
                            Frame.pceStatus[ArrayX][ArrayY + 2] == 1) {
                        System.out.println("ֱ���ͽ���");
                        Frame.shwError("����", "�ڷ����Ľ���");
                        return;
                    }  // ���ͽ���1
                    else if (ArrayX < 14 && ArrayY < 14 && ArrayX > 1 && ArrayY > 1 &&
                            Frame.pceStatus[ArrayX - 2][ArrayY] == 1 &&
                            Frame.pceStatus[ArrayX - 1][ArrayY] == 1 &&
                            Frame.pceStatus[ArrayX + 1][ArrayY] == 1 &&
                            Frame.pceStatus[ArrayX][ArrayY - 2] == 1 &&
                            Frame.pceStatus[ArrayX][ArrayY - 1] == 1 &&
                            Frame.pceStatus[ArrayX][ArrayY + 1] == 1) {
                        System.out.println("ֱ���ͽ���");
                        Frame.shwError("����", "�ڷ����Ľ���");
                        return;
                    }  // ���ͽ���2
                    else if (ArrayX > 0 && ArrayY < 14 && ArrayX < 13 && ArrayY > 1 &&
                            Frame.pceStatus[ArrayX - 1][ArrayY] == 1 &&
                            Frame.pceStatus[ArrayX + 1][ArrayY] == 1 &&
                            Frame.pceStatus[ArrayX + 2][ArrayY] == 1 &&
                            Frame.pceStatus[ArrayX][ArrayY + 1] == 1 &&
                            Frame.pceStatus[ArrayX][ArrayY - 1] == 1 &&
                            Frame.pceStatus[ArrayX][ArrayY - 2] == 1) {
                        System.out.println("ֱ���ͽ���");
                        Frame.shwError("����", "�ڷ����Ľ���");
                        return;
                    }  // ���ͽ���3
                    else if (ArrayX < 14 && ArrayY > 0 && ArrayX > 1 && ArrayY < 13 &&
                            Frame.pceStatus[ArrayX + 1][ArrayY] == 1 &&
                            Frame.pceStatus[ArrayX - 1][ArrayY] == 1 &&
                            Frame.pceStatus[ArrayX - 2][ArrayY] == 1 &&
                            Frame.pceStatus[ArrayX][ArrayY - 1] == 1 &&
                            Frame.pceStatus[ArrayX][ArrayY + 1] == 1 &&
                            Frame.pceStatus[ArrayX][ArrayY + 2] == 1) {
                        System.out.println("ֱ���ͽ���");
                        Frame.shwError("����", "�ڷ����Ľ���");
                        return;
                    }  // ���ͽ���4
                    // step 3: �ж���������
                    // 3.1 ����������
                    int cnt = 0;
                    int infIdx = (ArrayX > 4) ? ArrayX - 5 : 0;
                    int supIdx = (ArrayX < 10) ? ArrayX + 5 : 14;
                    for(int i = 0; i <= supIdx - infIdx; i++)
                    {
//                        System.out.println("infIdx + i = " + infIdx +i);
//                        System.out.println("ArrayY = " + ArrayY);
                        if(Frame.pceStatus[infIdx + i][ArrayY] == 1 && infIdx + i != ArrayX)
                            cnt++;
                        else if(Frame.pceStatus[infIdx + i][ArrayY] != 1 && infIdx + i != ArrayX)
                            cnt = 0;
//                        System.out.println("cnt = " + cnt);
                        if(cnt == 5)
                        {
                            System.out.println("��������");
                            Frame.shwError("����", "�ڷ���������");
                            return;
                        }
                    }
                    // 3.2 ����������
                    cnt = 0;
                    infIdx = (ArrayY > 4) ? ArrayY - 5 : 0;
                    supIdx = (ArrayY < 10) ? ArrayY + 5 : 14;
                    for(int i = 0; i <= supIdx - infIdx; i++)
                    {
//                        System.out.println("infIdx + i = " + infIdx +i);
//                        System.out.println("ArrayX = " + ArrayX);
                        if(Frame.pceStatus[ArrayX][infIdx + i] == 1 && infIdx + i != ArrayY)
                            cnt++;
                        else if(Frame.pceStatus[ArrayX][infIdx + i] != 1 && infIdx + i != ArrayY)
                            cnt = 0;
//                        System.out.println("cnt = " + cnt);
                        if(cnt == 5)
                        {
                            System.out.println("��������");
                            Frame.shwError("����", "�ڷ���������");
                            return;
                        }
                    }

//                    // 3.3 y = x��������
                    cnt = 0;
                    int deltaInf = Math.min(Math.min(ArrayX, ArrayY), 4);
                    int sttX = ArrayX - deltaInf;
                    int sttY = ArrayY - deltaInf;  //�����½�
                    int deltaSup = Math.min(Math.min(14 - ArrayX, 14 - ArrayY), 4);
                    for(int i = 0; i <= deltaSup + deltaInf; i++)
                    {
                        if(Frame.pceStatus[sttX + i][sttY + i] == 1 && i != deltaInf)
                            cnt++;
                        else if(Frame.pceStatus[sttX + i][sttY + i] != 1 && i != deltaInf)
                            cnt = 0;
                        if(cnt == 5)
                        {
                            System.out.println("��������");
                            Frame.shwError("����", "�ڷ���������");
                            return;
                        }
                    }
//
//                    // 3.4 y = -x��������
                    cnt = 0;
                    deltaInf = Math.min(Math.min(14 - ArrayX, ArrayY), 4);
                    sttX = ArrayX + deltaInf;
                    sttY = ArrayY - deltaInf;
                    deltaSup = Math.min(Math.min(ArrayX, 14 - ArrayY), 4);
                    for(int i = 0; i <= deltaSup + deltaInf; i++)
                    {
                        if(Frame.pceStatus[sttX - i][sttY + i] == 1 && i != deltaInf)
                            cnt++;
                        else if(Frame.pceStatus[sttX - i][sttY + i] != 1 && i != deltaInf)
                            cnt = 0;
                        if(cnt == 5)
                        {
                            System.out.println("��������");
                            Frame.shwError("����", "�ڷ���������");
                            return;
                        }
                    }

                    // �����д�������һö����
                    G.drawImage(blkPiece, rectX - size / 2, rectY - size / 2, size, size, null);
                    Frame.pceStatus[ArrayX][ArrayY] = 1;
                    Frame.chessPositionArrayList.add(new pceCord(ArrayX, ArrayY));
                    Frame.player = 2;

                    // �ж�һ���Ƿ��������ö����
                    int minX = ArrayX - 4;
                    int maxX = ArrayX + 4;
                    if (minX < 0)
                        minX = 0;
                    if (maxX > 14)
                        maxX = 14;
                    int cntPlayer1_1 = 0;
                    for (int i = minX; i <= maxX; i++) {
                        if (Frame.pceStatus[i][ArrayY] == 1)
                            cntPlayer1_1++;
                        else
                            cntPlayer1_1 = 0;
                        if (cntPlayer1_1 == 5) {
                            System.out.println("�����ʤ");
                            Frame.shwError("�����", "�ڷ�ʤ��");
                            Frame.player = 0;
                            return;
                        }
                    }


                    //�ж�һ���Ƿ��������ö����
                    int minY = ArrayY - 4;
                    int maxY = ArrayY + 4;
                    minY = (minY < 0) ? 0 : minY;
                    maxY = (maxY > 14) ? 14 : maxY;
                    int cntPlayer1_2 = 0;
                    for (int j = minY; j <= maxY; j++) {
                        if (Frame.pceStatus[ArrayX][j] == 1)
                            cntPlayer1_2++;
                        else
                            cntPlayer1_2 = 0;
                        if (cntPlayer1_2 == 5) {
                            System.out.println("�����ʤ");
                            Frame.shwError("�����", "�ڷ�ʤ��");
                            Frame.player = 0;
                            return;
                        }
                    }

                    //б���Ƿ������ö����
                    int cntPlayer1_3 = 0;
                    for (int i = -4; i <= 4; i++) {
                        if ((ArrayX + i >= 0) && (ArrayY + i >= 0) &&
                                (ArrayX + i <= 14) && (ArrayY + i <= 14)) {
                            if (Frame.pceStatus[ArrayX + i][ArrayY + i] == 1)
                                cntPlayer1_3++;
                            else
                                cntPlayer1_3 = 0;
                            if (cntPlayer1_3 == 5) {
                                System.out.println("�����ʤ");
                                Frame.shwError("�����", "�ڷ�ʤ��");
                                Frame.player = 0;
                                return;
                            }
                        }
                    }


                    //��һ��б���Ƿ������ö����
                    int cntPlayer1_4 = 0;
                    for (int i = -4; i <= 4; i++) {
                        if ((ArrayX + i >= 0) && (ArrayY - i >= 0) &&
                                (ArrayX + i <= 14) && (ArrayY - i <= 14)) {
                            if (Frame.pceStatus[ArrayX + i][ArrayY - i] == 1)
                                cntPlayer1_4++;
                            else
                                cntPlayer1_4 = 0;
                            if (cntPlayer1_4 == 5) {
                                System.out.println("�����ʤ");
                                Frame.shwError("�����", "�ڷ�ʤ��");
                                Frame.player = 0;
                                return;
                            }
                        }
                    }
                }
                else if (Frame.player == 2)//�ж�2�����ʤ��
                {
                    {
                        G.drawImage(whtPiece, rectX - size / 2, rectY - size / 2, size, size, null);
                        Frame.pceStatus[ArrayX][ArrayY] = 2;
                        Frame.chessPositionArrayList.add(new pceCord(ArrayX, ArrayY));
                        Frame.player = 1;

                        // �ж�һ���Ƿ��������ö����
                        int minX = ArrayX - 4, maxX = ArrayX + 4;
                        if (minX < 0)
                            minX = 0;
                        if (maxX > 14)
                            maxX = 14;
                        int cntPlayer2_1 = 0;
                        for (int i = minX; i <= maxX; i++) {
                            if (Frame.pceStatus[i][ArrayY] == 1)
                                cntPlayer2_1++;
                            else
                                cntPlayer2_1 = 0;
                            if (cntPlayer2_1 == 5) {
                                System.out.println("�����ʤ");
                                Frame.shwError("�����", "�׷�ʤ��");
                                Frame.player = 0;
                                return;
                            }
                        }


                        //�ж�һ���Ƿ��������ö����
                        int minY = ArrayY - 4, maxY = ArrayY + 4;
                        minY = (minY < 0) ? 0 : minY;
                        maxY = (maxY > 14) ? 14 : maxY;
                        int cntPlayer2_2 = 0;
                        for (int j = minY; j <= maxY; j++) {
                            if (Frame.pceStatus[ArrayX][j] == 1)
                                cntPlayer2_2++;
                            else
                                cntPlayer2_2 = 0;
                            if (cntPlayer2_2 == 5) {
                                System.out.println("�����ʤ");
                                Frame.shwError("�����", "�׷�ʤ��");
                                Frame.player = 0;
                                return;
                            }
                        }

                        //б���Ƿ������ö����
                        int cntPlayer2_3 = 0;
                        for (int i = -4; i <= 4; i++) {
                            if ((ArrayX + i >= 0) && (ArrayY + i >= 0) &&
                                    (ArrayX + i <= 14) && (ArrayY + i <= 14)) {
                                if (Frame.pceStatus[ArrayX + i][ArrayY + i] == 1)
                                    cntPlayer2_3++;
                                else
                                    cntPlayer2_3 = 0;
                                if (cntPlayer2_3 == 5) {
                                    System.out.println("�����ʤ");
                                    Frame.shwError("�����", "�ڷ�ʤ��");
                                    Frame.player = 0;
                                    return;
                                }
                            }
                        }


                        //��һ��б���Ƿ������ö����
                        int cntPlayer1_4 = 0;
                        for (int i = -4; i <= 4; i++) {
                            if ((ArrayX + i >= 0) && (ArrayY - i >= 0) &&
                                    (ArrayX + i <= 14) && (ArrayY - i <= 14)) {
                                if (Frame.pceStatus[ArrayX + i][ArrayY - i] == 1)
                                    cntPlayer1_4++;
                                else
                                    cntPlayer1_4 = 0;
                                if (cntPlayer1_4 == 5) {
                                    System.out.println("�����ʤ");
                                    Frame.shwError("�����", "�׷�ʤ��");
                                    Frame.player = 0;
                                    return;
                                }
                            }
                        }
                    }

                } }
            else  //�����PVEģʽ
            {
                if (Frame.player == 1)
                {
                        G.drawImage(blkPiece, rectX - size / 2, rectY - size / 2, size, size, null);
                        Frame.pceStatus[ArrayX][ArrayY] = 1;
                        Frame.chessPositionArrayList.add(new pceCord(ArrayX, ArrayY));
                        Frame.player = 2;  //AIĬ��ִ����

                        int cnt = 0;
                        int blkminIdx = (ArrayX - 4 < 0) ? 0 : ArrayX - 4;
                        int blkmaxIdx = (ArrayX + 4 > 14) ? 14 : ArrayX + 4;
                        for (int i = blkminIdx; i <= blkmaxIdx; i++) {
                            if (Frame.pceStatus[i][ArrayY] == 1)
                                cnt++;
                            else
                                cnt = 0;
                            if (cnt == 5) {
                                System.out.println("�ڷ�Ӯ�");
                                Frame.shwError("�����!", "��ϲ��ɵ���AI!");
                                Frame.player = 0;  //��ס
                                return;
                            }
                        }

                        cnt = 0;
                        blkminIdx = (ArrayY - 4 < 0) ? 0 : ArrayY - 4;
                        blkmaxIdx = (ArrayY + 4 > 14) ? 14 : ArrayY + 4;
                        for (int j = blkminIdx; j <= blkmaxIdx; j++) {
                            if (Frame.pceStatus[ArrayX][j] == 1)
                                cnt++;
                            else
                                cnt = 0;
                            if (cnt == 5) {
                                System.out.println("�ڷ�Ӯ�");
                                Frame.shwError("�����!", "��ϲ��ɵ���AI!");
                                Frame.player = 0;  //��ס
                                return;
                            }
                        }

                        cnt = 0;
                        for (int i = -4; i < 4; i++) {
                            if ((ArrayX + i >= 0) && (ArrayY + i >= 0) &&
                                    (ArrayX + i <= 14) && (ArrayY + i <= 14)) {
                                if (Frame.pceStatus[ArrayX + i][ArrayY + i] == 1)
                                    cnt++;
                                else
                                    cnt = 0;
                                if (cnt == 5) {
                                    System.out.println("�ڷ�Ӯ�");
                                    Frame.shwError("�����!", "��ϲ��ɵ���AI!");
                                    Frame.player = 0;  //��ס
                                    return;
                                }
                            }
                        }

                        cnt = 0;
                        for (int i = -4; i < 4; i++) {
                            if ((ArrayX + i >= 0) && (ArrayY - i >= 0) &&
                                    (ArrayX + i <= 14) && (ArrayY - i <= 14)) {
                                if (Frame.pceStatus[ArrayX + i][ArrayY - i] == 1)
                                    cnt++;
                                else
                                    cnt = 0;
                                if (cnt == 5) {
                                    System.out.println("�ڷ�Ӯ�");
                                    Frame.shwError("�����!", "��ϲ��ɵ���AI!");
                                    Frame.player = 0;  //��ס
                                    return;
                                }
                            }
                        }

                        //AI����
                        //����������������п����µ��ӵ�Ȩ��
                        for (int i = 0; i < Frame.pceStatus.length; i++) {
                            for (int j = 0; j < Frame.pceStatus[i].length; j++) {
                                if (Frame.pceStatus[i][j] == 0) {
                                    // step 1: ���������Ѱ��Ȩֵ
                                    String cnntType = "0";  //��Ϊ���ǵ�����"0"���ʶ���ֵ��Ϊ"0"
                                    int minY = Math.max(j - 4, 0);  //���︴�Ӷȸ���
                                    for (int crdY = j - 1; crdY >= minY; crdY--) {
                                        cnntType += Frame.pceStatus[i][crdY];
                                    }
                                    Integer lftWghtValue = Frame.wghtMap.get(cnntType);
                                    if (lftWghtValue != null)
                                        Frame.wghtArray[i][j] += lftWghtValue;

                                    // step 2: ��������Ѱ��Ȩֵ
                                    cnntType = "0";
                                    int maxY = Math.min(j + 4, 14);
                                    for (int crdY = j + 1; crdY <= maxY; crdY++) {
                                        cnntType += Frame.pceStatus[i][crdY];
                                    }
                                    Integer rgtWghtValue = Frame.wghtMap.get(cnntType);
                                    System.out.println(rgtWghtValue);
                                    if (rgtWghtValue != null) {
                                        System.out.println("�������ж�");
                                        Frame.wghtArray[i][j] += rgtWghtValue;
                                    }

                                    //ͨ�������ؽ��������Ȩֵ����ܺ���⣬��Ϊ���������������ߵ����Ӳ��ֵ�Ȼ����ص�
                                    Frame.wghtArray[i][j] += unionWeight(lftWghtValue, rgtWghtValue);

                                    // step 3: ��������
                                    cnntType = "0";
                                    int minX = Math.max(0, i - 4);
                                    for (int crdX = i - 1; crdX >= minX; crdX--)
                                        cnntType += Frame.pceStatus[crdX][j];
                                    Integer upWghtValue = Frame.wghtMap.get(cnntType);
                                    if (upWghtValue != null)
                                        Frame.wghtArray[i][j] += upWghtValue;

                                    // step 4: ��������
                                    cnntType = "0";
                                    int maxX = Math.min(14, i + 4);
                                    for (int crdX = i; crdX <= maxX; crdX++) {
                                        cnntType += Frame.pceStatus[crdX][j];
                                    }
                                    Integer dwWghtValue = Frame.wghtMap.get(cnntType);
                                    if (dwWghtValue != null)
                                        Frame.wghtArray[i][j] += dwWghtValue;

                                    Frame.wghtArray[i][j] += unionWeight(upWghtValue, dwWghtValue);

                                    // step 5: ��������
                                    cnntType = "0";
                                    for (int Idx = -1; Idx >= -4; Idx--) {
                                        if ((Idx + i >= 0) && (Idx + i <= 14) && (Idx + j >= 0) && (Idx + j <= 14))
                                            cnntType += Frame.pceStatus[i + Idx][j + Idx];
                                    }
                                    Integer lft_upWghtValue = Frame.wghtMap.get(cnntType);
                                    if (lft_upWghtValue != null)
                                        Frame.wghtArray[i][j] += lft_upWghtValue;


                                    // step 6: ��������
                                    cnntType = "0";
                                    for (int Idx = 1; Idx <= 4; Idx++) {
                                        if ((Idx + i >= 0) && (Idx + i <= 14) && (Idx + j >= 0) && (Idx + j <= 14))
                                            cnntType += Frame.pceStatus[i + Idx][j + Idx];
                                    }
                                    Integer rgt_downWghtValue = Frame.wghtMap.get(cnntType);
                                    if (rgt_downWghtValue != null)
                                        Frame.wghtArray[i][j] += rgt_downWghtValue;

                                    //����-��������о�
                                    Frame.wghtArray[i][j] += unionWeight(lft_upWghtValue, rgt_downWghtValue);


                                    // step 7: �������� i+ j-
                                    cnntType = "0";
                                    for (int Idx = -1; Idx >= -4; Idx--) {
                                        if ((Idx + i >= 0) && (Idx + i <= 14) && (j - Idx >= 0) && (j - Idx <= 14))
                                            cnntType += Frame.pceStatus[i + Idx][j - Idx];
                                    }
                                    Integer lft_dwWghtValue = Frame.wghtMap.get(cnntType);
                                    if (lft_dwWghtValue != null)
                                        Frame.wghtArray[i][j] += lft_dwWghtValue;

                                    // step 8: ��������, i- j+
                                    cnntType = "0";
                                    for (int Idx = 1; Idx <= 4; Idx++) {
//                                    System.out.println(i + Idx);
//                                    System.out.println(i - Idx);
//                                    System.out.println(j + Idx);
//                                    System.out.println(j - Idx);
                                        if ((i - Idx >= 0) && (i - Idx <= 14) && (j + Idx >= 0) && (j + Idx <= 14))
                                            cnntType += Frame.pceStatus[i - Idx][j + Idx];
                                    }
                                    Integer rgt_upWghtValue = Frame.wghtMap.get(cnntType);
                                    if (rgt_upWghtValue != null)
                                        Frame.wghtArray[i][j] += rgt_upWghtValue;

                                    Frame.wghtArray[i][j] += unionWeight(lft_dwWghtValue, rgt_upWghtValue);

                                    //���������Ѿ�����������Ͽ����µ��ӵ�ȫ��Ȩ�ط���
                                }

                            }
                        }

                        //�ҳ����Ȩֵ�ĵ�
                        int judgeX = 0, judgeY = 0;
                        int maxWght = 0;
                        for (int i = 0; i < row; i++) {
                            for (int j = 0; j < column; j++) {
                                if (maxWght < Frame.wghtArray[i][j]) {
                                    maxWght = Frame.wghtArray[i][j];
                                    judgeX = i;
                                    judgeY = j;   //һ���򵥵������ֵ�ĺ���
                                }
                            }
                        }

                        // AI����
                        int cntX = 40 * judgeX + 20;
                        int cntY = 40 * judgeY + 20;
                        G.drawImage(whtPiece, cntX - size / 2, cntY - size / 2, size, size, null);
                        Frame.chessPositionArrayList.add(new pceCord(judgeX, judgeY));
                        Frame.pceStatus[judgeX][judgeY] = 2;
                        Frame.player = 1;


                        //�ҳ����ӵĵ�󣬽�Ȩ�������
                        for (int i = 0; i < row; i++) {
                            for (int j = 0; j < column; j++) {
                                Frame.wghtArray[i][j] = 0;
                            }
                        }

                        // ��ʼ�жϰ���ʤ��
                        //�߼���֮ǰ����ȫһ����
                        int minX = judgeX - 4, maxX = judgeX + 4;
                        if (minX < 0) minX = 0;
                        if (maxX > 14) maxX = 14;
                        cnt = 0;
                        for (int i = minX; i <= maxX; i++) {
                            if (Frame.pceStatus[i][judgeY] == 2)
                                cnt++;
                            else
                                cnt = 0;
                            if (cnt == 5) {
                                System.out.println("�����ʤ");
                                Frame.shwError("Ŷ���군", "�㱻AI�����");
                                Frame.player = 0;
                                return;
                            }
                        }

                        int minY = judgeY - 4, maxY = judgeY + 4;
                        minY = (minY < 0) ? 0 : minY;
                        maxY = (maxY > 14) ? 14 : maxY;
                        cnt = 0;

                        for (int i = minY; i <= maxY; i++) {
                            if (Frame.pceStatus[judgeX][i] == 2)
                                cnt++;
                            else
                                cnt = 0;
                            if (cnt == 5) {
                                System.out.println("�����ʤ");
                                Frame.shwError("Ŷ���군", "�㱻AI�����");
                                Frame.player = 0;
                                return;
                            }
                        }

                        cnt = 0;
                        for (int i = -4; i <= 4; i++) {
                            if ((judgeX + i >= 0) && (judgeX + i <= 14) &&
                                    (judgeY + i >= 0) && (judgeY + i <= 14)) {
                                if (Frame.pceStatus[judgeX + i][judgeY + i] == 2)
                                    cnt++;
                                else
                                    cnt = 0;
                                if (cnt == 5) {
                                    System.out.println("�����ʤ");
                                    Frame.shwError("Ŷ���군", "�㱻AI�����");
                                    Frame.player = 0;
                                    return;
                                }
                            }
                        }

                        cnt = 0;
                        for (int i = -4; i <= 4; i++) {
                            if ((judgeX + i >= 0) && (judgeX + i <= 14) &&
                                    (judgeY - i >= 0) && (judgeY - i <= 14)) {
                                if (Frame.pceStatus[judgeX + i][judgeY - i] == 2)
                                    cnt++;
                                else
                                    cnt = 0;
                                if (cnt == 5) {
                                    System.out.println("�����ʤ");
                                    Frame.shwError("Ŷ���군", "�㱻AI�����");
                                    Frame.player = 0;
                                    return;
                                }
                            }
                        }

                    }
            }
        }
    }
    @Override
    public void mousePressed(java.awt.event.MouseEvent e) {}

    @Override
    public void mouseReleased(java.awt.event.MouseEvent e) {}

    @Override
    public void mouseEntered(java.awt.event.MouseEvent e) {}

    @Override
    public void mouseExited(java.awt.event.MouseEvent e) {}
}
