package Client;

import ServerInterface.PlayerInfo;
import javafx.scene.image.*;

import javax.swing.*;
import java.awt.*;
import java.awt.Image;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import java.awt.image.ImageFilter;
import java.awt.image.ImageObserver;

/**
 * Created by zhaokuo on 2016/2/22.
 */
public class InformationPanel extends JPanel {
    //对手
    JLabel lblmatchId = new JLabel("对方:");
    JLabel lblmatchScore = new JLabel("");

    Point lctMatchColor = new Point(40,90);
    Point lctMatchRun = new Point(40,120);

    //自己
    JLabel lblmyId = new JLabel("宝宝:");
    JLabel lblmyScore = new JLabel("是");
    //JLabel lblmyRun = new JLabel(" O ");
    Point lctMyColor = new Point(40,350);
    Point lctMyRun = new Point(40,380) ;

    Box bxContainer = Box.createVerticalBox();
    final int DIAME = 20;

    private boolean isBlack;
    private boolean isMyTurn;
    private PlayerInfo myInfo;
    private PlayerInfo matchInfo;
    private boolean isMeHandup;
    private boolean isMatchHandup;
    /**
     * 更新信息
     * @param isBlack
     * @param isMyTurn
     * @param myInfo
     * @param matchInfo
     */
    public void updateInfo(boolean isBlack,boolean isMyTurn, PlayerInfo myInfo, PlayerInfo matchInfo, boolean isMeHandup, boolean isMatchHandup){
        this.isBlack = isBlack;
        this.isMyTurn = isMyTurn;
        this.myInfo = myInfo;
        this.matchInfo = matchInfo;
        this.isMeHandup = isMeHandup;
        this.isMatchHandup = isMatchHandup;
        repaint();
    }

    public InformationPanel(){
        this.setPreferredSize(new Dimension(100,0));
        init();

    }



    public void init(){
        bxContainer.add(Box.createVerticalStrut(30));
        //对手
        bxContainer.add(lblmatchId);
        bxContainer.add(Box.createVerticalStrut(15));
        bxContainer.add(lblmatchScore);
        bxContainer.add(Box.createVerticalStrut(15));
        //bxContainer.add(lblmatchRun);
        bxContainer.add(Box.createVerticalStrut(200));
        //自己
        bxContainer.add(lblmyId);
        bxContainer.add(Box.createVerticalStrut(15));
        bxContainer.add(lblmyScore);
        bxContainer.add(Box.createVerticalStrut(15));
//        bxContainer.add(lblmyRun);
        bxContainer.add(Box.createVerticalStrut(30));

        System.out.println(lctMatchColor.getX());

        this.add(bxContainer);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        if(matchInfo != null){
            lblmatchId.setText("对方: " + matchInfo.name);
            lblmatchScore.setText(String.valueOf("   "+matchInfo.score));
        }

        if (myInfo != null){
            lblmyId.setText("宝宝: " + myInfo.name);
            lblmyScore.setText(String.valueOf("   "+myInfo.score));
        }

        if (isMeHandup){
            g.drawImage(new ImageIcon("src\\handup.png").getImage(),20,410, null);
        }

        if (isMatchHandup){
            g.drawImage(new ImageIcon("src\\handup.png").getImage(),20,150, null);
        }


        //画棋子颜色
        if(isBlack){
            Globle.painChess(g,Color.white,lctMatchColor.x,lctMatchColor.y+10);
            Globle.painChess(g, Color.black, lctMyColor.x, lctMyColor.y+10);
        }
        else{
            Globle.painChess(g,Color.black,lctMatchColor.x,lctMatchColor.y+10);
            Globle.painChess(g, Color.white, lctMyColor.x, lctMyColor.y+10);
        }

        if (isMyTurn){
            g.setColor(Color.green);
            Ellipse2D e = new Ellipse2D.Float((float)lctMyRun.getX()-10,(float)lctMyRun.getY(),DIAME,DIAME);
            ((Graphics2D) g).fill(e);
        }
        else{
            //lblmatchRun.setBackground(Color.green);
            g.setColor(Color.green);
            Ellipse2D e = new Ellipse2D.Float((float)lctMatchRun.getX()-10,(float)lctMatchRun.getY(),DIAME,DIAME);
            ((Graphics2D) g).fill(e);
        }
    }
}
