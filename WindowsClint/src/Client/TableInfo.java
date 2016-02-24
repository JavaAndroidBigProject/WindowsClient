package Client;

import ServerInterface.PlayerInfo;
import com.sun.prism.shader.DrawEllipse_Color_Loader;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Ellipse2D;

/**
 * Created by zhaokuo on 2016/2/24.
 */
public class TableInfo extends JPanel {
    /**
     * 运行逻辑对象
     */
    PlayLogic logic;

    PlayerInfo whitePlayer;
    PlayerInfo blackPlayer;
    int tableNum;
    JButton btnEnter = new JButton("进入");
    final int DIAME = 40;

    public TableInfo(PlayLogic logic,ServerInterface.TableInfo tableInfo) {
        this.logic = logic;
        this.tableNum = tableInfo.id;
        this.whitePlayer = tableInfo.player1;
        this.blackPlayer = tableInfo.player2;

        this.setPreferredSize(new Dimension(205,80));
        this.setBackground(Color.lightGray);
        this.setLayout(null);
        btnEnter.setFont(new Font("微软雅黑", 0, 16));
        btnEnter.setLocation(125,15);
        btnEnter.setSize(80,50);
        btnEnter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                logic.enterTable(tableNum);
            }
        });
        this.add(btnEnter);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        if(whitePlayer.score != -1){
            g.setColor(Color.WHITE);
            Ellipse2D e = new Ellipse2D.Float(5,5,DIAME,DIAME);
            ((Graphics2D) g).fill(e);

            g.setColor(Color.BLUE);
            g.drawString(whitePlayer.name,5,60);
            g.drawString("积分:"+String.valueOf(whitePlayer.score),5,75);
        }else {
            g.setColor(Color.BLUE);
            g.drawString("座位空",5,60);
        }
        if(blackPlayer.score != -1){
            g.setColor(Color.black);
            Ellipse2D e = new Ellipse2D.Float(65,5,DIAME,DIAME);
            ((Graphics2D) g).fill(e);
            g.setColor(Color.BLUE);

            g.drawString(blackPlayer.name,65,60);
            g.drawString("积分:"+String.valueOf(blackPlayer.score),65,75);
        }
        else {
            g.setColor(Color.BLUE);
            g.drawString("座位空",65,60);
        }

        g.setColor(Color.red);
        g.drawRect(5,5,DIAME,DIAME);
        g.drawRect(65,5,DIAME,DIAME);

    }
}
