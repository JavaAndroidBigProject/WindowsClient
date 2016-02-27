package Client;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by zhaokuo on 2016/01/18.
 */
public class RegisterWindow extends JFrame {

    /**
     * 程序运行逻辑对象
     */
    PlayLogic logic;

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

    final int width = 300;
    final int high = 300;
    public RegisterWindow(PlayLogic logic){

        this.logic  = logic;
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

        bxAll.add(Box.createVerticalStrut(50));
        bxAll.add(bxUser);
        bxAll.add(Box.createVerticalStrut(15));
        bxAll.add(bxPsw);
        bxAll.add(Box.createVerticalStrut(15));
        bxAll.add(bxEmail);
        bxAll.add(Box.createVerticalStrut(15));
        bxAll.add(bxBtn);
        bxAll.add(Box.createVerticalStrut(50));
        this.add(bxAll);

        Toolkit tool = Toolkit.getDefaultToolkit();
        Dimension dim = tool.getScreenSize();
        int w = (int)dim.getWidth();
        int h = (int)dim.getHeight();
        this.setBounds((w-width)/2,(h-high)/2,width,high);

        buttonListen();

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

    /**
     * 按钮添加事件监听
     */
    public void buttonListen(){
        btnCancle.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                logic.hideRegisterWindow();
                logic.showLoginWindow();
            }
        });

        btnOK.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(txtEmail.getText().equals("") || txtUser.getText().equals("") || txtPsw.getText().equals(""))
                    JOptionPane.showMessageDialog(btnOK, "所有信息都是必要的, 请重新输入!");
                else if(!validEmail(txtEmail.getText()))
                    JOptionPane.showMessageDialog(btnOK, "请输入正确的邮箱地址");
                else{
                    logic.register(txtUser.getText(), txtPsw.getText());
                }
            }
        });
    }

    private boolean validEmail(String email){
        String reg = "\\w{3,}@\\w+(.\\w+){1,2}";
        Pattern pattern = Pattern.compile(reg);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
}
