package Client;

import ServerInterface.PlayerInfo;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;

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
    /**
     * 更新信息
     * @param isBlack
     * @param isMyTurn
     * @param myInfo
     * @param matchInfo
     */
    public void updateInfo(boolean isBlack,boolean isMyTurn, PlayerInfo myInfo, PlayerInfo matchInfo){
        this.isBlack = isBlack;
        this.isMyTurn = isMyTurn;
        this.myInfo = myInfo;
        this.matchInfo = matchInfo;
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
            lblmatchScore.setText(String.valueOf(matchInfo.score));
        }

        if (myInfo != null){
            lblmyId.setText("宝宝: " + myInfo.name);
            lblmyScore.setText(String.valueOf(matchInfo.score));
        }


        //画棋子颜色
        if(isBlack){
            //lblmatchScore.setBackground(Color.white);
            g.setColor(Color.white);
            Ellipse2D e = new Ellipse2D.Float((float)lctMatchColor.getX(),(float)lctMatchColor.getY(),DIAME,DIAME);
            ((Graphics2D) g).fill(e);
            //lblmyColor.setBackground(Color.black);
            g.setColor(Color.black);
            e = new Ellipse2D.Float((float)lctMyColor.getX(),(float)lctMyColor.getY(),DIAME,DIAME);
            ((Graphics2D) g).fill(e);
        }
        else{
            //lblmatchScore.setBackground(Color.white);
            g.setColor(Color.black);
            Ellipse2D e = new Ellipse2D.Float((float)lctMatchColor.getX(),(float)lctMatchColor.getY(),DIAME,DIAME);
            ((Graphics2D) g).fill(e);
            //lblmyColor.setBackground(Color.black);
            g.setColor(Color.white);
            e = new Ellipse2D.Float((float)lctMyColor.getX(),(float)lctMyColor.getY(),DIAME,DIAME);
            ((Graphics2D) g).fill(e);
        }
        if (isMyTurn){
            //lblmyRun.setBackground(Color.green);
            g.setColor(Color.green);
            Ellipse2D e = new Ellipse2D.Float((float)lctMyRun.getX(),(float)lctMyRun.getY(),DIAME,DIAME);
            ((Graphics2D) g).fill(e);
        }
        else{
            //lblmatchRun.setBackground(Color.green);
            g.setColor(Color.green);
            Ellipse2D e = new Ellipse2D.Float((float)lctMatchRun.getX(),(float)lctMatchRun.getY(),DIAME,DIAME);
            ((Graphics2D) g).fill(e);
        }
    }
}
