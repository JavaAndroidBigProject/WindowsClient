package Client;

import javax.swing.*;
import java.awt.*;
import ServerInterface.*;

/**
 * Created by zhaokuo on 2016/2/24.
 */
public class ChoiceTableWindow extends JFrame {
    /**
     * 运行逻辑对象
     */
    PlayLogic logic;

    Box bxContainer = Box.createVerticalBox();
    JScrollPane scrollPane = new JScrollPane();
    JLabel lblTitle = new JLabel("选择座位");
    JLabel lblNew = new JLabel("+");

    final int width = 250;
    final int hight = 400;

    public ChoiceTableWindow(PlayLogic logic, ServerInterface.TableInfo[] tableinfos){
        this.logic = logic;

        Toolkit tool = Toolkit.getDefaultToolkit();
        Dimension dim = tool.getScreenSize();
        int w = (int)dim.getWidth();
        int h = (int)dim.getHeight();
        this.setBounds((w - width) / 2, (h - hight) / 2, width, hight);

        lblTitle.setFont(new Font("微软雅黑",0,20));
        lblTitle.setHorizontalAlignment(SwingConstants.CENTER);

        lblNew.setCursor(new Cursor(Cursor.HAND_CURSOR));
        lblNew.setForeground(Color.BLUE);
        lblNew.setFont(new Font("微软雅黑", 0, 30));
        lblNew.setHorizontalAlignment(SwingConstants.CENTER);

        addTable(tableinfos);
        this.add(lblTitle,BorderLayout.NORTH);
        this.add(scrollPane);
        this.add(lblNew,BorderLayout.SOUTH);
        //this.setResizable(false);
        this.setVisible(true);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    public void addTable(ServerInterface.TableInfo[] tableinfos){

        for(int i = 0; i < tableinfos.length; i++){
            bxContainer.add(new TableInfo(logic,tableinfos[i]));
            bxContainer.add(Box.createVerticalStrut(10));
        }
        scrollPane.setViewportView(bxContainer);
    }
}
