package Client;

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

    boolean whiteExists = false;
    boolean blackExists = false;
    int tableNum;
    JButton btnEnter = new JButton("进入");

    public TableInfo(int tableNum,PlayLogic logic,boolean whiteExit, boolean blackExit) {
        this.logic = logic;
        this.whiteExists = whiteExit;
        this.blackExists = blackExit;
        this.tableNum = tableNum;
        this.setPreferredSize(new Dimension(205,60));
        this.setBackground(Color.cyan);
        this.setLayout(null);
        btnEnter.setFont(new Font("微软雅黑", 0, 16));
        btnEnter.setLocation(125,5);
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
        if(whiteExists){
            g.setColor(Color.WHITE);
            Ellipse2D e = new Ellipse2D.Float(5,5,50,50);
            ((Graphics2D) g).fill(e);
        }
        if(blackExists){
            g.setColor(Color.black);
            Ellipse2D e = new Ellipse2D.Float(65,5,50,50);
            ((Graphics2D) g).fill(e);
        }
        g.setColor(Color.red);
        g.drawRect(5,5,50,50);
        g.drawRect(65,5,50,50);
    }
}
