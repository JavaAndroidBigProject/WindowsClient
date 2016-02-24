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


    public PlayLogic(InetAddress inetAddress, int port) {
        super(inetAddress, port);
        loginWindow = new LoginWindow(this);
    }

    @Override
    public void onRespondRegister(boolean ifRegistered, String reason) {

    }

    @Override
    public void onConnectionFail(String reason) {

    }

    @Override
    public void onLostConnection(String reason) {

    }

    @Override
    public void onRespondLogin(boolean ifLogined, int score, String reason) {
        System.out.println(ifLogined + "  " + score + " " + reason);
        if(!ifLogined){
            JOptionPane.showMessageDialog(loginWindow,"登录失败"+reason);
        }
        else {
            new ChoiceTableWindow(this,"111");
        }
    }

    @Override
    public void onRespondGetTables(TableInfo[] tableInfos) {

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
}
