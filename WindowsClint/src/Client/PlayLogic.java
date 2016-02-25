package Client; /**
 * Created by zhaokuo on 2016/2/24.
 */
import ServerInterface.*;
import ServerInterface.TableInfo;

import javax.swing.*;
import java.net.InetAddress;



public class PlayLogic extends OriginInterface{

    /**
     * 构造函数
     *
     * @param inetAddress 服务器地址
     * @param port
     */

    LoginWindow loginWindow = null;
    ChoiceTableWindow choiceTableWindow = null;
    RegisterWindow registerWindow = null;
    GameWindow gameWindow = null;


    public PlayLogic(InetAddress inetAddress, int port) {
        super(inetAddress, port);
        showLoginWindow();
    }

    /**
     *
     * @param ifRegistered
     * 是否注册成功
     * @param reason 若是注册失败, 则失败的原因
     */
    @Override
    public void onRespondRegister(boolean ifRegistered, String reason) {
        if (ifRegistered){
            JOptionPane.showMessageDialog(registerWindow,"注册成功");
            showLoginWindow();
            hideRegisterWindow();
        }

    }

    @Override
    public void onConnectionFail(String reason) {

    }

    @Override
    public void onLostConnection(String reason) {

    }

    /**
     *
     * @param ifLogined
     * 是否登陆成功
     * @param score
     * 玩家的分数
     * @param reason
     */
    @Override
    public void onRespondLogin(boolean ifLogined, int score, String reason) {
        System.out.println(ifLogined + "  " + score + " " + reason);
        if(!ifLogined){
            JOptionPane.showMessageDialog(loginWindow,"登录失败"+reason);
        }
        else {
            this.getTables();
        }
    }

    /**
     * 收到桌子信息
     * @param tableInfos 桌子信息数组
     */
    @Override
    public void onRespondGetTables(TableInfo[] tableInfos) {
        loginWindow.setVisible(false);

        if (choiceTableWindow == null)
            choiceTableWindow = new ChoiceTableWindow(this,tableInfos);
        else
            choiceTableWindow.setVisible(true);
    }


    @Override
    public void onRespondEnterTable(int tableId, boolean ifEntered, String reason) {

    }

    @Override
    public void onTableChange(PlayerInfo opponentInfo, boolean ifMyHandUp, boolean ifOpponentHandUp, boolean isPlaying, int[][] board, boolean isBlack, boolean isMyTurn) {

    }

    @Override
    public void onGameOver(boolean isDraw, boolean ifWin, boolean ifGiveUp) {

    }

    @Override
    public void onRespondRetract(boolean ifAgree) {

    }

    @Override
    public void onOpponentRetract() {

    }

    @Override
    public void onReceiveMessage(String message) {

    }

    @Override
    public void onRespondQuitTable(boolean ifAgree) {

    }


    /**
     * 显示注册窗口
     */
    public void showRegisterWindow(){
        if (registerWindow == null)
            registerWindow = new RegisterWindow(this);
        else
            registerWindow.setVisible(true);
    }

    /**
     * 隐藏注册窗口
     */
    public void hideRegisterWindow(){
        if (registerWindow != null)
            registerWindow.setVisible(false);
    }

    public void showLoginWindow(){
        if (loginWindow == null)
            loginWindow = new LoginWindow(this);
        else
            loginWindow.setVisible(true);
    }

    public void hideLoginWindow(){
        if (loginWindow != null)
            loginWindow.setVisible(false);
    }
}
