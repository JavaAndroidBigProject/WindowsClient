package Client;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import ServerInterface.*;

/**
 * Created by zhaokuo on 2016/2/24.
 */
public class ChoiceTableWindow extends JFrame {
    /**
     * 运行逻辑对象
     */
    PlayLogic logic;
    ServerInterface.TableInfo[] tableInfos;

    //Box bxContainer;
    JScrollPane scrollPane = new JScrollPane();
    JLabel lblTitle = new JLabel("选择座位");
    JLabel lblNew = new JLabel("+");

    final int width = 250;
    final int hight = 450;

    public ChoiceTableWindow(PlayLogic logic){
        this.logic = logic;

        /**
         * 获取桌子消息
         */
        this.tableInfos = logic.getTable();

        Toolkit tool = Toolkit.getDefaultToolkit();
        Dimension dim = tool.getScreenSize();
        int w = (int)dim.getWidth();
        int h = (int)dim.getHeight();
        this.setBounds((w - width) / 2, (h - hight) / 2, width, hight);

        lblTitle.setFont(new Font("微软雅黑",0,20));
        lblTitle.setHorizontalAlignment(SwingConstants.CENTER);

        lblNew.setCursor(new Cursor(Cursor.CROSSHAIR_CURSOR));
        lblNew.setForeground(Color.BLUE);
        lblNew.setFont(new Font("微软雅黑", 0, 30));
        lblNew.setHorizontalAlignment(SwingConstants.CENTER);

        //addTable(tableInfos);

        this.add(lblTitle,BorderLayout.NORTH);
        this.add(scrollPane);
        this.add(lblNew,BorderLayout.SOUTH);
        this.setResizable(false);
        this.setVisible(true);
        //this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                logic.quit();
            }
        });
    }

    public void addTable(ServerInterface.TableInfo[] tableinfos){
        //bxContainer = null;
        Box bxContainer = Box.createVerticalBox();
        for(int i = 0; i < tableinfos.length-1; i++){
            bxContainer.add(new TableInfo(logic,tableinfos[i]));
            bxContainer.add(Box.createVerticalStrut(10));
        }
        bxContainer.add(new TableInfo(logic,tableinfos[tableinfos.length-1]));
        scrollPane.setViewportView(bxContainer);
    }
}
