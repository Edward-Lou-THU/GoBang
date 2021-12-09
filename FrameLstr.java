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
        // 需要给AI赋值一些策略权重
        if(a == null || b == null)
            return 0;

        // 此时代表101和202
        else if( a >= 22 && a <= 25 && b >= 22 && b <= 25)
            return 60;

        // 此时代表1011和2022
        else if((a >= 22 && a <= 25 && b >= 76 && b <= 80) ||
                (a >= 76 && a <= 80 && b >= 22 && b <= 25))
            return 800;

        // 此时代表10111和20222
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
        // 考察棋子将落在哪个点上,需要将X和Y做一修正
        int rectX = 20 + (X / 40) * 40;
        int rectY = 20 + (Y / 40) * 40;
//        System.out.println("rectX = " + rectX);
//        System.out.println("rectY = " + rectY);
        // 再反演获得其在数组中的位置
        int ArrayX = (rectX - 20) / 40;
        int ArrayY = (rectY - 20) / 40;
//        System.out.println("ArrayX = " + ArrayX);
//        System.out.println("ArrayY = " + ArrayY);

        Graphics G = Frame.getGraphics();

        if (Frame.pceStatus[ArrayX][ArrayY] != 0)
        {
            Frame.shwError("木得办法", "这里不能下棋吼");
        }
        else
        {
            //如果是PVP模式
            if (Frame.actAI == false)
            {
                if (Frame.player == 1) {
                    // 落子以前判定黑棋是否下在禁手区

                    // step 1: 判定33禁手
                    if (ArrayX > 0 && ArrayY > 0 && ArrayX < 14 && ArrayY < 14 &&
                            Frame.pceStatus[ArrayX - 1][ArrayY - 1] == 1 &&
                            Frame.pceStatus[ArrayX - 1][ArrayY + 1] == 1 &&
                            Frame.pceStatus[ArrayX + 1][ArrayY + 1] == 1 &&
                            Frame.pceStatus[ArrayX + 1][ArrayY - 1] == 1) {
                        System.out.println("三三禁手");
                        Frame.shwError("禁手", "黑方三三禁手");
                        return;
                    }   // 叉型禁手
                    if (ArrayX > 1 && ArrayY > 1 && Frame.pceStatus[ArrayX - 2][ArrayY] == 1 &&
                            Frame.pceStatus[ArrayX - 1][ArrayY] == 1 &&
                            Frame.pceStatus[ArrayX][ArrayY - 2] == 1 &&
                            Frame.pceStatus[ArrayX][ArrayY - 1] == 1) {
                        System.out.println("直角型禁手");
                        Frame.shwError("禁手", "黑方禁手");
                        return;
                    }   // 直角禁手1
                    if (ArrayX < 13 && ArrayY < 13 && Frame.pceStatus[ArrayX + 2][ArrayY] == 1 &&
                            Frame.pceStatus[ArrayX + 1][ArrayY] == 1 &&
                            Frame.pceStatus[ArrayX][ArrayY + 2] == 1 &&
                            Frame.pceStatus[ArrayX][ArrayY + 1] == 1) {
                        System.out.println("直角型禁手");
                        Frame.shwError("禁手", "黑方禁手");
                        return;
                    }  // 直角禁手2
                    if (ArrayX > 1 && ArrayY < 13 && Frame.pceStatus[ArrayX - 2][ArrayY] == 1 &&
                            Frame.pceStatus[ArrayX - 1][ArrayY] == 1 &&
                            Frame.pceStatus[ArrayX][ArrayY + 2] == 1 &&
                            Frame.pceStatus[ArrayX][ArrayY + 1] == 1) {
                        System.out.println("直角型禁手");
                        Frame.shwError("禁手", "黑方禁手");
                        return;
                    }  // 直角禁手3
                    if (ArrayX < 13 && ArrayY > 1 && Frame.pceStatus[ArrayX + 1][ArrayY] == 1 &&
                            Frame.pceStatus[ArrayX + 2][ArrayY] == 1 &&
                            Frame.pceStatus[ArrayX][ArrayY - 2] == 1 &&
                            Frame.pceStatus[ArrayX][ArrayY - 1] == 1) {
                        System.out.println("直角型禁手");
                        Frame.shwError("禁手", "黑方禁手");
                        return;
                    }  // 直角禁手4

                    // step 2: 判定44禁手，44禁手的判定逻辑要复杂一些
                    if (ArrayX > 2 && ArrayY > 2 && Frame.pceStatus[ArrayX - 3][ArrayY] == 1 &&
                            Frame.pceStatus[ArrayX - 2][ArrayY] == 1 &&
                            Frame.pceStatus[ArrayX - 1][ArrayY] == 1 &&
                            Frame.pceStatus[ArrayX][ArrayY - 3] == 1 &&
                            Frame.pceStatus[ArrayX][ArrayY - 2] == 1 &&
                            Frame.pceStatus[ArrayX][ArrayY - 1] == 1) {
                        System.out.println("直角型禁手");
                        Frame.shwError("禁手", "黑方四四禁手");
                        return;
                    }  // 直角禁手1
                    else if (ArrayX > 2 && ArrayY < 12 && Frame.pceStatus[ArrayX - 3][ArrayY] == 1 &&
                            Frame.pceStatus[ArrayX - 2][ArrayY] == 1 &&
                            Frame.pceStatus[ArrayX - 1][ArrayY] == 1 &&
                            Frame.pceStatus[ArrayX][ArrayY + 3] == 1 &&
                            Frame.pceStatus[ArrayX][ArrayY + 2] == 1 &&
                            Frame.pceStatus[ArrayX][ArrayY + 1] == 1) {
                        System.out.println("直角型禁手");
                        Frame.shwError("禁手", "黑方四四禁手");
                        return;
                    }  // 直角禁手2
                    else if(ArrayX < 12 && ArrayY < 12 && Frame.pceStatus[ArrayX + 3][ArrayY] == 1 &&
                            Frame.pceStatus[ArrayX + 2][ArrayY] == 1 &&
                            Frame.pceStatus[ArrayX + 1][ArrayY] == 1 &&
                            Frame.pceStatus[ArrayX][ArrayY + 3] == 1 &&
                            Frame.pceStatus[ArrayX][ArrayY + 2] == 1 &&
                            Frame.pceStatus[ArrayX][ArrayY + 1] == 1) {
                        System.out.println("直角型禁手");
                        Frame.shwError("禁手", "黑方四四禁手");
                        return;
                    }  // 直角禁手3
                    else if (ArrayX < 12 && ArrayY > 2 &&
                            Frame.pceStatus[ArrayX + 3][ArrayY] == 1 &&
                            Frame.pceStatus[ArrayX + 2][ArrayY] == 1 &&
                            Frame.pceStatus[ArrayX + 1][ArrayY] == 1 &&
                            Frame.pceStatus[ArrayX][ArrayY - 3] == 1 &&
                            Frame.pceStatus[ArrayX][ArrayY - 2] == 1 &&
                            Frame.pceStatus[ArrayX][ArrayY - 1] == 1) {
                        System.out.println("直角型禁手");
                        Frame.shwError("禁手", "黑方四四禁手");
                        return;
                    }  // 直角禁手4
                    else if (ArrayX > 0 && ArrayY > 0 && ArrayX < 13 && ArrayY < 13 &&
                            Frame.pceStatus[ArrayX - 1][ArrayY] == 1 &&
                            Frame.pceStatus[ArrayX + 1][ArrayY] == 1 &&
                            Frame.pceStatus[ArrayX + 2][ArrayY] == 1 &&
                            Frame.pceStatus[ArrayX][ArrayY - 1] == 1 &&
                            Frame.pceStatus[ArrayX][ArrayY + 1] == 1 &&
                            Frame.pceStatus[ArrayX][ArrayY + 2] == 1) {
                        System.out.println("直角型禁手");
                        Frame.shwError("禁手", "黑方四四禁手");
                        return;
                    }  // 叉型禁手1
                    else if (ArrayX < 14 && ArrayY < 14 && ArrayX > 1 && ArrayY > 1 &&
                            Frame.pceStatus[ArrayX - 2][ArrayY] == 1 &&
                            Frame.pceStatus[ArrayX - 1][ArrayY] == 1 &&
                            Frame.pceStatus[ArrayX + 1][ArrayY] == 1 &&
                            Frame.pceStatus[ArrayX][ArrayY - 2] == 1 &&
                            Frame.pceStatus[ArrayX][ArrayY - 1] == 1 &&
                            Frame.pceStatus[ArrayX][ArrayY + 1] == 1) {
                        System.out.println("直角型禁手");
                        Frame.shwError("禁手", "黑方四四禁手");
                        return;
                    }  // 叉型禁手2
                    else if (ArrayX > 0 && ArrayY < 14 && ArrayX < 13 && ArrayY > 1 &&
                            Frame.pceStatus[ArrayX - 1][ArrayY] == 1 &&
                            Frame.pceStatus[ArrayX + 1][ArrayY] == 1 &&
                            Frame.pceStatus[ArrayX + 2][ArrayY] == 1 &&
                            Frame.pceStatus[ArrayX][ArrayY + 1] == 1 &&
                            Frame.pceStatus[ArrayX][ArrayY - 1] == 1 &&
                            Frame.pceStatus[ArrayX][ArrayY - 2] == 1) {
                        System.out.println("直角型禁手");
                        Frame.shwError("禁手", "黑方四四禁手");
                        return;
                    }  // 叉型禁手3
                    else if (ArrayX < 14 && ArrayY > 0 && ArrayX > 1 && ArrayY < 13 &&
                            Frame.pceStatus[ArrayX + 1][ArrayY] == 1 &&
                            Frame.pceStatus[ArrayX - 1][ArrayY] == 1 &&
                            Frame.pceStatus[ArrayX - 2][ArrayY] == 1 &&
                            Frame.pceStatus[ArrayX][ArrayY - 1] == 1 &&
                            Frame.pceStatus[ArrayX][ArrayY + 1] == 1 &&
                            Frame.pceStatus[ArrayX][ArrayY + 2] == 1) {
                        System.out.println("直角型禁手");
                        Frame.shwError("禁手", "黑方四四禁手");
                        return;
                    }  // 叉型禁手4
                    // step 3: 判定长连禁手
                    // 3.1 横向长连禁手
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
                            System.out.println("长连禁手");
                            Frame.shwError("禁手", "黑方长连禁手");
                            return;
                        }
                    }
                    // 3.2 纵向长连禁手
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
                            System.out.println("长连禁手");
                            Frame.shwError("禁手", "黑方长连禁手");
                            return;
                        }
                    }

//                    // 3.3 y = x长连禁手
                    cnt = 0;
                    int deltaInf = Math.min(Math.min(ArrayX, ArrayY), 4);
                    int sttX = ArrayX - deltaInf;
                    int sttY = ArrayY - deltaInf;  //先找下界
                    int deltaSup = Math.min(Math.min(14 - ArrayX, 14 - ArrayY), 4);
                    for(int i = 0; i <= deltaSup + deltaInf; i++)
                    {
                        if(Frame.pceStatus[sttX + i][sttY + i] == 1 && i != deltaInf)
                            cnt++;
                        else if(Frame.pceStatus[sttX + i][sttY + i] != 1 && i != deltaInf)
                            cnt = 0;
                        if(cnt == 5)
                        {
                            System.out.println("长连禁手");
                            Frame.shwError("禁手", "黑方长连禁手");
                            return;
                        }
                    }
//
//                    // 3.4 y = -x长连禁手
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
                            System.out.println("长连禁手");
                            Frame.shwError("禁手", "黑方长连禁手");
                            return;
                        }
                    }

                    // 这四行代表下了一枚棋子
                    G.drawImage(blkPiece, rectX - size / 2, rectY - size / 2, size, size, null);
                    Frame.pceStatus[ArrayX][ArrayY] = 1;
                    Frame.chessPositionArrayList.add(new pceCord(ArrayX, ArrayY));
                    Frame.player = 2;

                    // 判断一列是否出现了五枚棋子
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
                            System.out.println("黑棋获胜");
                            Frame.shwError("下完喽", "黑方胜利");
                            Frame.player = 0;
                            return;
                        }
                    }


                    //判断一行是否出现了五枚棋子
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
                            System.out.println("黑棋获胜");
                            Frame.shwError("下完喽", "黑方胜利");
                            Frame.player = 0;
                            return;
                        }
                    }

                    //斜线是否出现五枚棋子
                    int cntPlayer1_3 = 0;
                    for (int i = -4; i <= 4; i++) {
                        if ((ArrayX + i >= 0) && (ArrayY + i >= 0) &&
                                (ArrayX + i <= 14) && (ArrayY + i <= 14)) {
                            if (Frame.pceStatus[ArrayX + i][ArrayY + i] == 1)
                                cntPlayer1_3++;
                            else
                                cntPlayer1_3 = 0;
                            if (cntPlayer1_3 == 5) {
                                System.out.println("黑棋获胜");
                                Frame.shwError("下完喽", "黑方胜利");
                                Frame.player = 0;
                                return;
                            }
                        }
                    }


                    //另一条斜线是否出现五枚棋子
                    int cntPlayer1_4 = 0;
                    for (int i = -4; i <= 4; i++) {
                        if ((ArrayX + i >= 0) && (ArrayY - i >= 0) &&
                                (ArrayX + i <= 14) && (ArrayY - i <= 14)) {
                            if (Frame.pceStatus[ArrayX + i][ArrayY - i] == 1)
                                cntPlayer1_4++;
                            else
                                cntPlayer1_4 = 0;
                            if (cntPlayer1_4 == 5) {
                                System.out.println("黑棋获胜");
                                Frame.shwError("下完喽", "黑方胜利");
                                Frame.player = 0;
                                return;
                            }
                        }
                    }
                }
                else if (Frame.player == 2)//判定2号玩家胜利
                {
                    {
                        G.drawImage(whtPiece, rectX - size / 2, rectY - size / 2, size, size, null);
                        Frame.pceStatus[ArrayX][ArrayY] = 2;
                        Frame.chessPositionArrayList.add(new pceCord(ArrayX, ArrayY));
                        Frame.player = 1;

                        // 判断一列是否出现了五枚棋子
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
                                System.out.println("白棋获胜");
                                Frame.shwError("下完喽", "白方胜利");
                                Frame.player = 0;
                                return;
                            }
                        }


                        //判断一行是否出现了五枚棋子
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
                                System.out.println("白棋获胜");
                                Frame.shwError("下完喽", "白方胜利");
                                Frame.player = 0;
                                return;
                            }
                        }

                        //斜线是否出现五枚棋子
                        int cntPlayer2_3 = 0;
                        for (int i = -4; i <= 4; i++) {
                            if ((ArrayX + i >= 0) && (ArrayY + i >= 0) &&
                                    (ArrayX + i <= 14) && (ArrayY + i <= 14)) {
                                if (Frame.pceStatus[ArrayX + i][ArrayY + i] == 1)
                                    cntPlayer2_3++;
                                else
                                    cntPlayer2_3 = 0;
                                if (cntPlayer2_3 == 5) {
                                    System.out.println("黑棋获胜");
                                    Frame.shwError("下完喽", "黑方胜利");
                                    Frame.player = 0;
                                    return;
                                }
                            }
                        }


                        //另一条斜线是否出现五枚棋子
                        int cntPlayer1_4 = 0;
                        for (int i = -4; i <= 4; i++) {
                            if ((ArrayX + i >= 0) && (ArrayY - i >= 0) &&
                                    (ArrayX + i <= 14) && (ArrayY - i <= 14)) {
                                if (Frame.pceStatus[ArrayX + i][ArrayY - i] == 1)
                                    cntPlayer1_4++;
                                else
                                    cntPlayer1_4 = 0;
                                if (cntPlayer1_4 == 5) {
                                    System.out.println("白棋获胜");
                                    Frame.shwError("下完喽", "白方胜利");
                                    Frame.player = 0;
                                    return;
                                }
                            }
                        }
                    }

                } }
            else  //如果是PVE模式
            {
                if (Frame.player == 1)
                {
                        G.drawImage(blkPiece, rectX - size / 2, rectY - size / 2, size, size, null);
                        Frame.pceStatus[ArrayX][ArrayY] = 1;
                        Frame.chessPositionArrayList.add(new pceCord(ArrayX, ArrayY));
                        Frame.player = 2;  //AI默认执白子

                        int cnt = 0;
                        int blkminIdx = (ArrayX - 4 < 0) ? 0 : ArrayX - 4;
                        int blkmaxIdx = (ArrayX + 4 > 14) ? 14 : ArrayX + 4;
                        for (int i = blkminIdx; i <= blkmaxIdx; i++) {
                            if (Frame.pceStatus[i][ArrayY] == 1)
                                cnt++;
                            else
                                cnt = 0;
                            if (cnt == 5) {
                                System.out.println("黑方赢喽");
                                Frame.shwError("下完喽!", "恭喜你干掉了AI!");
                                Frame.player = 0;  //锁住
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
                                System.out.println("黑方赢喽");
                                Frame.shwError("下完喽!", "恭喜你干掉了AI!");
                                Frame.player = 0;  //锁住
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
                                    System.out.println("黑方赢喽");
                                    Frame.shwError("下完喽!", "恭喜你干掉了AI!");
                                    Frame.player = 0;  //锁住
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
                                    System.out.println("黑方赢喽");
                                    Frame.shwError("下完喽!", "恭喜你干掉了AI!");
                                    Frame.player = 0;  //锁住
                                    return;
                                }
                            }
                        }

                        //AI落子
                        //首先算出棋盘上所有可以下的子的权重
                        for (int i = 0; i < Frame.pceStatus.length; i++) {
                            for (int j = 0; j < Frame.pceStatus[i].length; j++) {
                                if (Frame.pceStatus[i][j] == 0) {
                                    // step 1: 往左边延拓寻找权值
                                    String cnntType = "0";  //因为我们的起点的"0"，故而初值设为"0"
                                    int minY = Math.max(j - 4, 0);  //这里复杂度高了
                                    for (int crdY = j - 1; crdY >= minY; crdY--) {
                                        cnntType += Frame.pceStatus[i][crdY];
                                    }
                                    Integer lftWghtValue = Frame.wghtMap.get(cnntType);
                                    if (lftWghtValue != null)
                                        Frame.wghtArray[i][j] += lftWghtValue;

                                    // step 2: 向右延拓寻找权值
                                    cnntType = "0";
                                    int maxY = Math.min(j + 4, 14);
                                    for (int crdY = j + 1; crdY <= maxY; crdY++) {
                                        cnntType += Frame.pceStatus[i][crdY];
                                    }
                                    Integer rgtWghtValue = Frame.wghtMap.get(cnntType);
                                    System.out.println(rgtWghtValue);
                                    if (rgtWghtValue != null) {
                                        System.out.println("进行了判断");
                                        Frame.wghtArray[i][j] += rgtWghtValue;
                                    }

                                    //通过行延拓结果增加行权值，这很好理解，因为五子棋中左右两边的棋子布局当然是相关的
                                    Frame.wghtArray[i][j] += unionWeight(lftWghtValue, rgtWghtValue);

                                    // step 3: 向上延伸
                                    cnntType = "0";
                                    int minX = Math.max(0, i - 4);
                                    for (int crdX = i - 1; crdX >= minX; crdX--)
                                        cnntType += Frame.pceStatus[crdX][j];
                                    Integer upWghtValue = Frame.wghtMap.get(cnntType);
                                    if (upWghtValue != null)
                                        Frame.wghtArray[i][j] += upWghtValue;

                                    // step 4: 向下延伸
                                    cnntType = "0";
                                    int maxX = Math.min(14, i + 4);
                                    for (int crdX = i; crdX <= maxX; crdX++) {
                                        cnntType += Frame.pceStatus[crdX][j];
                                    }
                                    Integer dwWghtValue = Frame.wghtMap.get(cnntType);
                                    if (dwWghtValue != null)
                                        Frame.wghtArray[i][j] += dwWghtValue;

                                    Frame.wghtArray[i][j] += unionWeight(upWghtValue, dwWghtValue);

                                    // step 5: 左上延伸
                                    cnntType = "0";
                                    for (int Idx = -1; Idx >= -4; Idx--) {
                                        if ((Idx + i >= 0) && (Idx + i <= 14) && (Idx + j >= 0) && (Idx + j <= 14))
                                            cnntType += Frame.pceStatus[i + Idx][j + Idx];
                                    }
                                    Integer lft_upWghtValue = Frame.wghtMap.get(cnntType);
                                    if (lft_upWghtValue != null)
                                        Frame.wghtArray[i][j] += lft_upWghtValue;


                                    // step 6: 右下延伸
                                    cnntType = "0";
                                    for (int Idx = 1; Idx <= 4; Idx++) {
                                        if ((Idx + i >= 0) && (Idx + i <= 14) && (Idx + j >= 0) && (Idx + j <= 14))
                                            cnntType += Frame.pceStatus[i + Idx][j + Idx];
                                    }
                                    Integer rgt_downWghtValue = Frame.wghtMap.get(cnntType);
                                    if (rgt_downWghtValue != null)
                                        Frame.wghtArray[i][j] += rgt_downWghtValue;

                                    //左上-右下相干判据
                                    Frame.wghtArray[i][j] += unionWeight(lft_upWghtValue, rgt_downWghtValue);


                                    // step 7: 左下延伸 i+ j-
                                    cnntType = "0";
                                    for (int Idx = -1; Idx >= -4; Idx--) {
                                        if ((Idx + i >= 0) && (Idx + i <= 14) && (j - Idx >= 0) && (j - Idx <= 14))
                                            cnntType += Frame.pceStatus[i + Idx][j - Idx];
                                    }
                                    Integer lft_dwWghtValue = Frame.wghtMap.get(cnntType);
                                    if (lft_dwWghtValue != null)
                                        Frame.wghtArray[i][j] += lft_dwWghtValue;

                                    // step 8: 右上延伸, i- j+
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

                                    //至此我们已经完成了棋盘上可以下的子的全部权重分配
                                }

                            }
                        }

                        //找出最大权值的点
                        int judgeX = 0, judgeY = 0;
                        int maxWght = 0;
                        for (int i = 0; i < row; i++) {
                            for (int j = 0; j < column; j++) {
                                if (maxWght < Frame.wghtArray[i][j]) {
                                    maxWght = Frame.wghtArray[i][j];
                                    judgeX = i;
                                    judgeY = j;   //一个简单的找最大值的函数
                                }
                            }
                        }

                        // AI落子
                        int cntX = 40 * judgeX + 20;
                        int cntY = 40 * judgeY + 20;
                        G.drawImage(whtPiece, cntX - size / 2, cntY - size / 2, size, size, null);
                        Frame.chessPositionArrayList.add(new pceCord(judgeX, judgeY));
                        Frame.pceStatus[judgeX][judgeY] = 2;
                        Frame.player = 1;


                        //找出落子的点后，将权重阵归零
                        for (int i = 0; i < row; i++) {
                            for (int j = 0; j < column; j++) {
                                Frame.wghtArray[i][j] = 0;
                            }
                        }

                        // 开始判断白子胜负
                        //逻辑和之前是完全一样的
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
                                System.out.println("白棋获胜");
                                Frame.shwError("哦吼完蛋", "你被AI打败了");
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
                                System.out.println("白棋获胜");
                                Frame.shwError("哦吼完蛋", "你被AI打败了");
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
                                    System.out.println("白棋获胜");
                                    Frame.shwError("哦吼完蛋", "你被AI打败了");
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
                                    System.out.println("白棋获胜");
                                    Frame.shwError("哦吼完蛋", "你被AI打败了");
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
