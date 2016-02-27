package Client;

import ServerInterface.PlayerInfo;

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

    /**
     * 游戏运行逻辑
     */
    PlayLogic logic;

    PlayerInfo myInfo = null;
    PlayerInfo opponentInfo = null;
    boolean ifMyHandUp = false;
    boolean ifOpponentHandUp = false;
    boolean isPlaying = false;
    int[][] board = null;
    boolean isBlack = false;
    boolean isMyTurn = false;

    /**
     * 棋盘信息
     * @param myInfo                我的信息
     * @param opponentInfo          对方信息
     * @param ifMyHandUp            我是否举手
     * @param ifOpponentHandUp      对方是否举手
     * @param isPlaying             游戏是否正在执行
     * @param board                 15*15的棋盘
     * @param isBlack               我是否为黑色
     * @param isMyTurn              是否轮到我下子
     */

    public void resetInfo(PlayerInfo myInfo,PlayerInfo opponentInfo, boolean ifMyHandUp, boolean ifOpponentHandUp, boolean isPlaying, int[][] board, boolean isBlack, boolean isMyTurn){
        this.myInfo = myInfo;
        this.opponentInfo = opponentInfo;
        this.ifMyHandUp = ifMyHandUp;
        this.ifOpponentHandUp = ifOpponentHandUp;
        this.isPlaying = isPlaying;
        this.board = board;
        this.isBlack = isBlack;
        this.isMyTurn = isMyTurn;
        pnlInfo.updateInfo(isBlack,isMyTurn,myInfo,opponentInfo,ifMyHandUp,ifOpponentHandUp);
        chessBoard.updateBoard(board,isBlack,isPlaying,isMyTurn);
    }

    public GameWindow(PlayLogic logic){
        super("游戏室");
        this.logic = logic;
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
    JButton btnGiveUp = new JButton("认输");
    JButton btnHandUp = new JButton("举手");
    JButton btnBack = new JButton("悔棋");
    JButton btnExit = new JButton("退出");

    /**
     * 对控件进行初始化
     */
    private void initWidget(){
        chessBoard = new ChessBoard(logic);
        Container contentPane = getContentPane();
        contentPane.add(chessBoard);
        chessBoard.setOpaque(true);
        txtchat.setFont(new Font("宋体",1,16));
        btnBack.setFont(new Font("微软雅黑",0,24));
        btnExit.setFont(new Font("微软雅黑",0,24));
        btnGiveUp.setFont(new Font("微软雅黑",0,24));
        btnHandUp.setFont(new Font("微软雅黑",0,24));
        txtChatHis.setEditable(false);
        txtchat.setFocusable(true);
        txtchat.requestFocus();
    }

    /**
     * 布局初始化
     */
    public void initLayout(){

        pnlInfo = new InformationPanel();
        pnlInfo.updateInfo(isBlack,isMyTurn,myInfo,opponentInfo,ifMyHandUp,ifOpponentHandUp);

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
        bxButton.add(btnHandUp);
        bxButton.add(Box.createHorizontalStrut(30));
        bxButton.add(btnGiveUp);
        bxButton.add(Box.createHorizontalStrut(30));
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
                if (txtchat.getText().contains("#")){
                    txtChatHis.append("[提示] 字符串中不能包含 # 符号");
                }
                else{
                    txtChatHis.append(txtchat.getText()+"\n");
                    logic.sengMessage(txtchat.getText());
                    txtchat.setText("");
                }
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

        /**
         * 悔棋按钮事件, 悔棋
         */
        btnBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                txtChatHis.append("发送请求悔棋, 正在等待对方响应....\n");
                logic.retract();
            }
        });

        /**
         * 退出按钮事件
         */
        btnExit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (JOptionPane.showConfirmDialog(btnExit,"是否退出当前桌子","确认",JOptionPane.OK_CANCEL_OPTION) == JOptionPane.OK_OPTION){
                    logic.quitTable();
                    dispose();
                    logic.showChoiceTableWindow();
                }
            }
        });

        /**
         * 举手按钮事件
         */
        btnHandUp.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                txtChatHis.append("[我] 举手....\n");
                logic.handUp();
            }
        });

        /**
         * 放弃, 认输  按钮事件
         */
        btnGiveUp.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                logic.giveUp();
            }
        });
    }


}
