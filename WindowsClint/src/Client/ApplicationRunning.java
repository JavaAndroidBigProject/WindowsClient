package Client; /**
 * Created by zhaokuo on 2016/01/23.
 */

import javax.swing.*;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class ApplicationRunning {
    public static  void main(String []args){
        /**
         * 游戏逻辑对象, 在程序入口处初始化
         */
        PlayLogic playLogic;
        try {
            playLogic = new PlayLogic(InetAddress.getByName("yidea.xyz"),4000);
//            playLogic = new PlayLogic(InetAddress.getByName("10.95.73.26"),4000);
        } catch (UnknownHostException e) {
            JOptionPane.showMessageDialog(null,"网络连接失败");
        }
    }
}
