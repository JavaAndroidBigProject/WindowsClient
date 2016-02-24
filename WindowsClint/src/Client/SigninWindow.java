package Client;

import javax.swing.*;
import java.awt.*;

/**
 * Created by zhaokuo on 2016/01/18.
 */
public class SigninWindow extends JFrame {
    JLabel lblEmail =  new JLabel("邮    箱: ");
    JLabel lblUser =  new JLabel("用户名: ");
    JLabel lblPsw =  new JLabel("密    码: ");
    JLabel lblNotiU = new JLabel("*");
    JLabel lblNotiP = new JLabel("*");
    JLabel lblNotiE = new JLabel("*");
    JTextField txtEmail = new JTextField();
    JTextField txtUser = new JTextField();
    JTextField txtPsw = new JTextField();
    JButton btnOK = new JButton("确定");
    JButton btnCancle = new JButton("取消");
    Box bxEmail = Box.createHorizontalBox();
    Box bxUser = Box.createHorizontalBox();
    Box bxPsw = Box.createHorizontalBox();
    Box bxBtn = Box.createHorizontalBox();
    Box bxAll = Box.createVerticalBox();
    public SigninWindow(){

        initWigt();

        bxUser.add(Box.createHorizontalStrut(50));
        bxUser.add(lblUser);
        bxUser.add(txtUser);
        bxUser.add(lblNotiU);
        bxUser.add(Box.createHorizontalStrut(50));
        bxPsw.add(Box.createHorizontalStrut(50));
        bxPsw.add(lblPsw);
        bxPsw.add(txtPsw);
        bxPsw.add(lblNotiP);
        bxPsw.add(Box.createHorizontalStrut(50));
        bxEmail.add(Box.createHorizontalStrut(50));
        bxEmail.add(lblEmail);
        bxEmail.add(txtEmail);
        bxEmail.add(lblNotiE);
        bxEmail.add(Box.createHorizontalStrut(50));
        bxBtn.add(btnOK);
        bxBtn.add(Box.createHorizontalStrut(20));
        bxBtn.add(btnCancle);

        bxAll.add(Box.createVerticalStrut(80));
        bxAll.add(bxUser);
        bxAll.add(Box.createVerticalStrut(15));
        bxAll.add(bxPsw);
        bxAll.add(Box.createVerticalStrut(15));
        bxAll.add(bxEmail);
        bxAll.add(Box.createVerticalStrut(15));
        bxAll.add(bxBtn);
        bxAll.add(Box.createVerticalStrut(30));
        this.add(bxAll);

        Toolkit tool = Toolkit.getDefaultToolkit();
        Dimension dim = tool.getScreenSize();
        int w = (int)dim.getWidth();
        int h = (int)dim.getHeight();
        this.setBounds((w-300)/2,(h-300)/2,300,300);
        this.setVisible(true);
        this.setResizable(false);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    public void initWigt(){
        btnOK.setFont(new Font("宋体",1,20));
        btnCancle.setFont(new Font("宋体", 1, 20));
        lblNotiE.setForeground(Color.RED);
        lblNotiP.setForeground(Color.RED);
        lblNotiU.setForeground(Color.RED);
    }
}
