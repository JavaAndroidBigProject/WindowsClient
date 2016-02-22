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
        Toolkit tool = Toolkit.getDefaultToolkit();
        Dimension dim = tool.getScreenSize();
        int w = (int)dim.getWidth();
        int h = (int)dim.getHeight();
        int width = 850;
        int hight = 650;
        this.setBounds((w-width)/2, (h-hight)/2, width, hight);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout(10,10));
        initWidget();
        initLayout();
    }

   //用户信息
    InformationPanel pnlInfo;

    JScrollPane pnlShowText = new JScrollPane();
    JPanel pnlRight = new JPanel();
    Box bxButton = Box.createHorizontalBox();
    Box bxCenter = Box.createVerticalBox();



    //棋盘
    JLabel lbltitle = new JLabel("五子棋OnLine");
    ChessBoard chessBoard;

    //对话
    JTextField txtchat = new JTextField();
    JTextArea txtChatHis = new JTextArea();

    //按钮
    JButton btnBack = new JButton("悔棋");
    JButton btnExit = new JButton("退出");

    /**
     * 对控件进行初始化
     */
    private void initWidget(){
        chessBoard = new ChessBoard();
        Container contentPane = getContentPane();
        contentPane.add(chessBoard);
        chessBoard.setOpaque(true);
        txtChatHis.setFocusable(false);
    }

    /**
     * 布局初始化
     */
    public void initLayout(){

        pnlInfo = new InformationPanel();

        //标题
        lbltitle.setHorizontalAlignment(SwingConstants.CENTER);
        lbltitle.setFont(new Font("微软雅黑",0,32));

        //对话信息
        pnlShowText.createVerticalScrollBar();
        pnlShowText.add(txtChatHis);
        pnlShowText.setViewportView(txtChatHis);
        pnlShowText.setVisible(true);
        pnlRight.setLayout(new BorderLayout(10,10));
        pnlRight.setPreferredSize(new Dimension(200,0));
        pnlRight.add(txtchat,BorderLayout.SOUTH);
        pnlRight.add(pnlShowText,BorderLayout.CENTER);


        //按钮
       // bxButton.add(Box.createHorizontalStrut(getWidth()/2-30));
        bxButton.add(btnBack);
        bxButton.add(Box.createHorizontalStrut(10));
        bxButton.add(btnExit);
        //bxButton.add(Box.createHorizontalStrut(getWidth()/2-30));

        //中路
        //bxCenter.add(chessBoard);
        bxCenter.add(Box.createVerticalStrut(15));
        bxCenter.add(bxButton);
        bxCenter.add(Box.createVerticalStrut(20));


        this.add(lbltitle,BorderLayout.NORTH);
        this.add(pnlInfo, BorderLayout.WEST);
        this.add(chessBoard, BorderLayout.CENTER);
        this.add(pnlRight,BorderLayout.EAST);
        this.add(bxCenter,BorderLayout.SOUTH);


    }
}
