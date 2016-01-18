import javax.swing.*;
import java.awt.*;

/**
 * Created by zhaokuo on 2016/01/17.
 */
public class LoginWindow extends JFrame {
    //控件列表
    JLabel lblUser = new JLabel("用户名: ");
    JLabel lblPsw = new JLabel("密    码: ");
    JLabel lblSigin = new JLabel("新用户?点击注册");
    JLabel lblFindPsw = new JLabel("密码找回");
    JTextField txtfUser = new JTextField(25);
    JPasswordField txtfPsw = new JPasswordField(25);
    JButton btnLogin = new JButton("登录");
    JButton btnExit = new JButton("退出");
    Box bxUser = Box.createHorizontalBox();
    Box bxPsw = Box.createHorizontalBox();
    Box bxBtn = Box.createHorizontalBox();
    Box bxAll = Box.createVerticalBox();

    /**
     * 构造函数,画出图形.
     */
    public LoginWindow(){
        //初始化项目
        super("登录");
        this.setVisible(true);
        Toolkit tool = Toolkit.getDefaultToolkit();
        Dimension dim = tool.getScreenSize();
        int w = (int)dim.getWidth();
        int h = (int)dim.getHeight();
        this.setBounds(w/2-250,h/2-150,300,250);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //设置属性

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
        bxAll.add(lblFindPsw);
        bxAll.add(Box.createVerticalStrut(30));
        bxAll.add(bxBtn);
        bxAll.add(Box.createVerticalStrut(30));

        this.add(bxAll);
        lblSigin.setHorizontalAlignment(SwingConstants.RIGHT);
        this.add(lblSigin,BorderLayout.SOUTH);
    }

    public static void main(String[] args){
        new SigninWindow();
      //  new LoginWindow();
    }
}
