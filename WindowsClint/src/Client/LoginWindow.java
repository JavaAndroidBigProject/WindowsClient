package Client;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Created by zhaokuo on 2016/01/17.
 */


public class LoginWindow extends JFrame {
    /**
     * 运行逻辑对象
     */
    PlayLogic logic;
    RegisterWindow registerWindow = null;

    //控件列表
    JLabel lblUser = new JLabel("用户名: ");
    JLabel lblPsw = new JLabel("密    码: ");
    JLabel lblSigin = new JLabel("新用户?点击注册");
    //JLabel lblFindPsw = new JLabel("密码找回");
    JTextField txtfUser = new JTextField(25);
    JPasswordField txtfPsw = new JPasswordField(25);
    JButton btnLogin = new JButton("登录");
    JButton btnExit = new JButton("退出");
    Box bxUser = Box.createHorizontalBox();
    Box bxPsw = Box.createHorizontalBox();
    Box bxBtn = Box.createHorizontalBox();
    Box bxAll = Box.createVerticalBox();

    final int width = 300;
    final int hight = 250;

    /**
     * 构造函数,画出图形.
     */
    public LoginWindow(PlayLogic logic){
        //初始化项目
        super("登录");
        this.logic = logic;
        Toolkit tool = Toolkit.getDefaultToolkit();
        Dimension dim = tool.getScreenSize();
        int w = (int)dim.getWidth();
        int h = (int)dim.getHeight();
        this.setBounds((w-width)/2,(h-hight)/2,width,hight);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //设置属性
        btnExit.setFont(new Font("宋体",1,20));
        btnLogin.setFont(new Font("宋体", 1, 20));
        lblSigin.setForeground(Color.BLUE);
        lblSigin.setFont(new Font("微软雅黑",0,18));

        //设置布局

        this.setResizable(false);
        bxUser.add(Box.createHorizontalStrut(50));
        bxUser.add(lblUser);
        bxUser.add(txtfUser);
        bxUser.add(Box.createHorizontalStrut(50));

        bxPsw.add(Box.createHorizontalStrut(50));
        bxPsw.add(lblPsw);
        bxPsw.add(txtfPsw);
        bxPsw.add(Box.createHorizontalStrut(50));

        bxBtn.add(btnLogin);
        bxBtn.add(Box.createHorizontalStrut(15));
        bxBtn.add(btnExit);


        bxAll.add(Box.createVerticalStrut(50));
        bxAll.add(bxUser);
        bxAll.add(Box.createVerticalStrut(10));
        bxAll.add(bxPsw);
        //bxAll.add(lblFindPsw);
        bxAll.add(Box.createVerticalStrut(30));
        bxAll.add(bxBtn);
        bxAll.add(Box.createVerticalStrut(30));

        this.add(bxAll);
        lblSigin.setHorizontalAlignment(SwingConstants.RIGHT);
        this.add(lblSigin,BorderLayout.SOUTH);
        loginEvent();
        this.setVisible(true);
    }

    /**
     * 用户登录
     */
    public void Login(){
        String username = txtfUser.getText();
        String password = String.valueOf(txtfPsw.getPassword());
        if(!username.equals("")&& !password.equals(""))
            logic.login(username,password);
        else
            JOptionPane.showMessageDialog(this,"用户名和密码都不能为空!!");
    }


    /**
     * 事件监听方法
     */
    public void loginEvent(){

        btnExit.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                System.exit(0);
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                setCursor(new Cursor(Cursor.HAND_CURSOR));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
                setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            }
        });


        btnLogin.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                Login();
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                setCursor(new Cursor(Cursor.HAND_CURSOR));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
                setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            }
        });

        lblSigin.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                setCursor(new Cursor(Cursor.HAND_CURSOR));
            }

            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                logic.hideLoginWindow();
                logic.showRegisterWindow();
            }

            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
                setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            }
        });
    }
}
