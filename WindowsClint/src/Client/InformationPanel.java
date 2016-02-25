package Client;

import ServerInterface.PlayerInfo;

import javax.swing.*;
import javax.xml.stream.Location;
import java.awt.*;
import java.awt.geom.Ellipse2D;

/**
 * Created by zhaokuo on 2016/2/22.
 */
public class InformationPanel extends JPanel {
    //对手
    JLabel lblmatchId = new JLabel("ID:");
    JLabel lblmatchColor = new JLabel("  ");
    JLabel lblmatchRun = new JLabel(" O ");
    Point lctMatchColor;
    Point lctMatchRun;

    //自己
    JLabel lblmyId = new JLabel("ID");
    JLabel lblmyColor = new JLabel("  ");
    JLabel lblmyRun = new JLabel(" O ");
    Point lctMyColor;
    Point lctMyRun;

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
        bxContainer.add(lblmatchColor);
        bxContainer.add(Box.createVerticalStrut(15));
        bxContainer.add(lblmatchRun);
        bxContainer.add(Box.createVerticalStrut(200));
        //自己
        bxContainer.add(lblmyId);
        bxContainer.add(Box.createVerticalStrut(15));
        bxContainer.add(lblmyColor);
        bxContainer.add(Box.createVerticalStrut(15));
        bxContainer.add(lblmyRun);
        bxContainer.add(Box.createVerticalStrut(30));

        this.add(bxContainer);

        lctMatchColor = lblmatchColor.getLocation();
        lctMatchRun = lblmatchRun.getLocation();
        lctMyColor = lblmyColor.getLocation();
        lctMyRun = lblmyRun.getLocation();
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        if(matchInfo != null)
            lblmatchId.setText("对方: " + matchInfo.name);
        if (myInfo != null)
            lblmyId.setText("宝宝: " + myInfo.name);

        //画棋子颜色
        if(isBlack){
            //lblmatchColor.setBackground(Color.white);
            g.setColor(Color.white);
            Ellipse2D e = new Ellipse2D.Float((float)lctMatchColor.getX(),(float)lctMatchColor.getY(),DIAME,DIAME);
            ((Graphics2D) g).fill(e);
            //lblmyColor.setBackground(Color.black);
            g.setColor(Color.black);
            e = new Ellipse2D.Float((float)lctMyColor.getX(),(float)lctMyColor.getY(),DIAME,DIAME);
            ((Graphics2D) g).fill(e);
        }
        else{
            //lblmatchColor.setBackground(Color.white);
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
