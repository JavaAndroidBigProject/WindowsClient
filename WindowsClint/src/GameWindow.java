import javax.swing.*;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

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
        int hight = 700;
        this.setBounds((w-width)/2, (h-hight)/2, width, hight);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout(10,10));
        initWidget();
        initLayout();
        initEvent();
        this.setVisible(true);
        chessBoard.requestFocus();
    }

   //用户信息
    InformationPanel pnlInfo;


    JPanel pnlRight = new JPanel();
    Box bxButton = Box.createHorizontalBox();
    Box bxCenter = Box.createVerticalBox();
    Box bxRight = Box.createVerticalBox();



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
        txtchat.setFont(new Font("宋体",1,16));
        btnBack.setFont(new Font("微软雅黑",0,24));
        btnExit.setFont(new Font("微软雅黑",0,24));
        txtChatHis.setEditable(false);
        txtchat.setFocusable(true);
        txtchat.requestFocus();
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
        pnlRight.setLayout(new BorderLayout(10,10));
        pnlRight.setPreferredSize(new Dimension(200,0));
        pnlRight.add(txtchat,BorderLayout.SOUTH);
        pnlRight.add(new JScrollPane(txtChatHis),BorderLayout.CENTER);
        bxRight.add(Box.createVerticalStrut(30));
        bxRight.add(pnlRight);
        bxRight.add(Box.createVerticalStrut(30));



        //按钮
        //bxButton.add(Box.createHorizontalStrut(getWidth()/2-100));
        bxButton.setAlignmentX(CENTER_ALIGNMENT);
        bxButton.add(btnBack);
        bxButton.add(Box.createHorizontalStrut(30));
        bxButton.add(btnExit);
        //bxButton.add(Box.createHorizontalStrut(getWidth()/2-30));

        //中路
        //bxCenter.add(chessBoard);
        //bxCenter.add(Box.createVerticalStrut(15));
        bxCenter.setAlignmentY(CENTER_ALIGNMENT);
        bxCenter.add(bxButton);
        bxCenter.add(Box.createVerticalStrut(30));


        this.add(lbltitle,BorderLayout.NORTH);
        this.add(pnlInfo, BorderLayout.WEST);
        this.add(chessBoard, BorderLayout.CENTER);
        //this.add(pnlRight,BorderLayout.EAST);
        this.add(bxRight,BorderLayout.EAST);
        this.add(bxCenter,BorderLayout.SOUTH);
    }

    public void initEvent(){
        //文本框, 回车事件
        txtchat.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                txtChatHis.append(txtchat.getText()+"\n");
                txtchat.setText("");
            }
        });

        //聊天记录框, 改变鼠标指针
        txtChatHis.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                setCursor(new Cursor(Cursor.TEXT_CURSOR));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
                setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            }
        });

        //悔棋按钮事件, 悔棋
        btnBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        //退出按钮事件
        btnExit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }


}
