/**
 * Created by zhaokuo on 2016/2/13.
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * 棋盘类, 负责棋盘部分以及棋子的重绘
 */
public class chessBoard extends JPanel implements KeyListener,MouseListener {

    public static final int MARGIN = 30;//边距
    public static final int GRID_SPAN = 30;//行距
    public static final int ROWS = 15; //行数
    public static final int COLS = 15; //列数
    public static final int DIAMETER = 30;//棋子直径
    public int [][] chessMatrix;

    Image img; //背景木板

    public  chessBoard(){
        chessMatrix = new int[15][15];
        init();
    }

    /**
     * 带参数构造函数, 参数为棋子信息的二维数组
     * @param chessMatrix 棋子矩阵
     */
    public chessBoard(int [][]chessMatrix){
        this.chessMatrix = chessMatrix;
        init();
    }

    /**
     * 初始化函数
     */
    private void init(){

    }

    /**
     * 画棋盘的函数
     * @param g
     */
    public  void paintCrid(Graphics g){
        //画棋盘
        super.paintComponent(g);
        int imgWidth = img.getWidth(this);
        int imgHeight = img.getHeight(this);//获取背景图尺寸

        int fWidth = getWidth();
        int fHeight = getHeight();//获取Panel尺寸

        int x = (fWidth-imgWidth)/2;
        int y = (fHeight = imgHeight);//将背景居中放置
        g.drawImage(img, x, y, null);

        //画横线, 横坐标(边距 --> 边距+列数*列距), 纵坐标(边距+i*列距)
        for (int i= 0; i<=ROWS ; i++ ) {
            g.drawLine(MARGIN,MARGIN+i*GRID_SPAN,MARGIN+COLS*GRID_SPAN,MARGIN+i*GRID_SPAN);
        }
        //画纵线
        for(int i=0; i<=COLS; i++){
            g.drawLine(MARGIN+i*GRID_SPAN,MARGIN,MARGIN+i*GRID_SPAN,MARGIN+ROWS*GRID_SPAN);
        }

        //画棋子

    }


    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
