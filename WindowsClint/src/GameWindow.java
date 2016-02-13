import javax.swing.*;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.*;

/**
 * Created by zhaokuo on 2016/01/19.
 */
public class GameWindow extends JFrame{

    public GameWindow(){
        super("游戏室");
        this.setVisible(true);
        Toolkit tool = Toolkit.getDefaultToolkit();
        Dimension dim = tool.getScreenSize();
        int w = (int)dim.getWidth();
        int h = (int)dim.getHeight();
        int width = 500;
        int hight = 500;
        this.setBounds((w-width)/2, (h-hight)/2, width, hight);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        initWidget();
        initLayout();
    }

    Box bxLeft = Box.createVerticalBox();
    Box bxCenter = Box.createVerticalBox();
    Box bxRight = Box.createVerticalBox();
    Box bxAll = Box.createHorizontalBox();
    //对手
    JLabel lblmatchId = new JLabel("ID");
    JLabel lblmatchColor = new JLabel("...");
    JButton btnmatchHuiqi = new JButton("接受悔棋");
    JLabel lblmatchRun = new JLabel("...");

    //棋盘
    JLabel lbltitle = new JLabel("五子棋OnLine");
    JLabel lblchessBoard = new JLabel("...");
    JTextField txtchat = new JTextField();

    //自己
    JLabel lblmyId = new JLabel("ID");
    JLabel lblmyColor = new JLabel("...");
    JButton btnmyHuiqi = new JButton("请求悔棋");
    JLabel lblmyRun = new JLabel("...");

    /**
     * 对控件进行初始化
     */
    private void initWidget(){
        lblchessBoard.setIcon(new ImageIcon("F:\\JAVA\\java2015\\FinalProject\\WindowsClient\\WindowsClint\\src\\grid.png"));
    }

    /**
     * 布局初始化
     */
    public void initLayout(){
        bxLeft.add(lblmatchId);
        bxLeft.add(lblmatchColor);
        bxLeft.add(btnmatchHuiqi);
        bxLeft.add(lblmatchRun);

        bxCenter.add(lbltitle);
        bxCenter.add(lblchessBoard);
        bxCenter.add(txtchat);

        bxRight.add(lblmyId);
        bxRight.add(lblmyColor);
        bxRight.add(btnmyHuiqi);
        bxRight.add(lblmyRun);

        bxAll.add(bxLeft);
        bxAll.add(bxCenter);
        bxAll.add(bxRight);

        this.add(bxAll);

    }
}
