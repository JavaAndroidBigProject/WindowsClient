package Client; /**
 * Created by zhaokuo on 2016/2/24.
 */
import ServerInterface.*;
import ServerInterface.TableInfo;

import javax.swing.*;
import javax.swing.text.html.Option;
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
    public ServerInterface.TableInfo[] tableInfos;


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

    /**
     * 登录时网络连接失败
     * @param reason 失败的原因
     */
    @Override
    public void onConnectionFail(String reason) {
        JOptionPane.showMessageDialog(loginWindow,"网络连接失败!请检查网络后再登录\n错误原因: "+reason);

    }

    /**
     * 游戏进行中网络断开
     * @param reason    错误原因
     */
    @Override
    public void onLostConnection(String reason) {
        JOptionPane.showMessageDialog(loginWindow,"网络断开, 退出游戏, 请重新连接\n错误原因: " + reason);
        if (registerWindow!=null)
            registerWindow.dispose();
        if (choiceTableWindow!=null)
            choiceTableWindow.dispose();
        if (gameWindow!=null)
            gameWindow.dispose();
        loginWindow.setVisible(true);
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
        //System.out.println(ifLogined + "  " + score + " " + reason);
        if(!ifLogined){
            JOptionPane.showMessageDialog(loginWindow,"登录失败: "+reason);
        }
        else {
           // this.getTables();

        }
    }

    /**
     * 收到桌子信息
     * @param tableInfos 桌子信息数组
     */
    @Override
    public void onRespondGetTables(ServerInterface.TableInfo[] tableInfos) {
        this.tableInfos = tableInfos;
        hideLoginWindow();
        showChoiceTableWindow();
    }

    public ServerInterface.TableInfo[] getTable(){
        //getTables();
        return this.tableInfos;
    }


    /**
     * 接收到是否进入游戏桌信息
     * @param tableId
     * 游戏桌编号
     * @param ifEntered
     * 是否进入游戏桌
     * @param reason
     */
    @Override
    public void onRespondEnterTable(int tableId, boolean ifEntered, String reason) {
        if (ifEntered){
            hideChoiceTableWindow();
            showGameWindow(tableId);
        }
        else {
            JOptionPane.showMessageDialog(choiceTableWindow,"进入游戏桌失败: "+reason);
        }

    }

    /**
     * @param opponentInfo
     * 对手信息
     * @param ifMyHandUp
     * 自己是否举手
     * @param ifOpponentHandUp
     * 对手是否举手
     * @param isPlaying
     * 游戏是否进行中
     * @param board
     * 棋盘的逻辑数组，1表黑棋，2表白旗，0表空
     * @param isBlack
     * 自己是否执黑子
     * @param isMyTurn
     */
    @Override
    public void onTableChange(PlayerInfo myInfo,PlayerInfo opponentInfo, boolean ifMyHandUp, boolean ifOpponentHandUp, boolean isPlaying, int[][] board, boolean isBlack, boolean isMyTurn) {
        gameWindow.resetInfo(myInfo,opponentInfo,ifMyHandUp,ifOpponentHandUp,isPlaying,board,isBlack,isMyTurn);
        gameWindow.repaint();
    }

    /**
     * 游戏结果
     * @param isDraw
     * 是否是平局
     * @param ifWin
     * 是否是自己赢
     * @param ifGiveUp
     * 是否是某一方认输
     */
    @Override
    public void onGameOver(boolean isDraw, boolean ifWin, boolean ifGiveUp) {

        StringBuffer str = new StringBuffer();
        str.append("游戏结束 : ");

        if (isDraw)
            str.append("平局.");
        else if (ifWin){
            if (ifGiveUp)
                str.append("对方认输, ");
            str.append("我赢了!!!");
        }
        else {
            str.append("我输了...");
        }

        JOptionPane.showMessageDialog(gameWindow,str);
        if (JOptionPane.showConfirmDialog(gameWindow,"再来一局!","提示", JOptionPane.OK_CANCEL_OPTION,JOptionPane.INFORMATION_MESSAGE) == JOptionPane.OK_OPTION){
            // 如果再来一句, 不做处理, 静候OnTableChange 消息
        }
        else{
            this.quitTable();
            gameWindow.dispose();
            showChoiceTableWindow();
        }

    }

    /**
     * 当收到请求悔棋响应<br>
     * 服务器返回 ON_RESPOND_RETRACT#ifAgree
     * @param ifAgree
     * 对手是否同意悔棋，若同意，会随后收到onBoardChange
     */
    @Override
    public void onRespondRetract(boolean ifAgree) {
        String str = "同意";
        if (!ifAgree)
            str = "不同意";
        gameWindow.txtChatHis.append("[系统消息]: 对方["+str+"]悔棋请求!");
    }

    /**
     * 当收到对手请求悔棋<br>
     * 服务器返回 ON_OPPONENT_RETRACT
     */
    @Override
    public void onOpponentRetract() {
        if (JOptionPane.showConfirmDialog(gameWindow,"是否同意悔棋","悔棋",JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION){
            this.respondRetract(true);
        }
        else {
            this.respondRetract(false);
        }
    }

    @Override
    public void onReceiveMessage(String message) {
        gameWindow.txtChatHis.append("对方: " + message + "\n");
    }

    @Override
    public void onRespondQuitTable() {
        JOptionPane.showMessageDialog(gameWindow,"你已经退出游戏");
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

    /**
     * 显示登录窗口
     */
    public void showLoginWindow(){
        if (loginWindow == null)
            loginWindow = new LoginWindow(this);
        else
            loginWindow.setVisible(true);
    }

    /**
     * 隐藏登录窗口
     */
    public void hideLoginWindow(){
        if (loginWindow != null)
            loginWindow.setVisible(false);
    }

    /**
     * 显示游戏窗口
     */
    public void showGameWindow(int tableNum){
        if (gameWindow != null){
            gameWindow.dispose();
        }
        gameWindow = new GameWindow(this);
    }

    /**
     * 隐藏游戏窗口
     */
    public void hideGameWindow(){
        if (gameWindow != null)
            gameWindow.dispose();
    }

    /**
     * 显示选桌窗口
     */
    public void showChoiceTableWindow(){
        if (choiceTableWindow != null){
            choiceTableWindow.setVisible(true);
        }
        else
            choiceTableWindow = new ChoiceTableWindow(this);
        choiceTableWindow.addTable(tableInfos);
    }

    /**
     * 隐藏选桌窗口
     */
    public void hideChoiceTableWindow(){
        choiceTableWindow.dispose();
        choiceTableWindow = null;
    }
}
