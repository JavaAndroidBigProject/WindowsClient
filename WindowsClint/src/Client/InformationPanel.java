package Client;

import javax.swing.*;
import java.awt.*;

/**
 * Created by zhaokuo on 2016/2/22.
 */
public class InformationPanel extends JPanel {
    //对手
    JLabel lblmatchId = new JLabel("ID:");
    JLabel lblmatchColor = new JLabel("角色:");
    JLabel lblmatchRun = new JLabel("状态:");
    //自己
    JLabel lblmyId = new JLabel("ID");
    JLabel lblmyColor = new JLabel("角色:");
    JLabel lblmyRun = new JLabel("状态:");

    Box bxContainer = Box.createVerticalBox();

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
    }

}
