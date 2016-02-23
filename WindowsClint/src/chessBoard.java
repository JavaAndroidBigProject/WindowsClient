/**
 * Created by zhaokuo on 2016/2/13.
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RadialGradientPaint;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.geom.*;

/**
 * 棋盘类, 负责棋盘部分以及棋子的重绘
 */
public class ChessBoard extends JPanel implements KeyListener,MouseListener {

    public static final int MARGIN = 30;//边距
    public static final int GRID_SPAN = 30;//行距
    public static final int ROWS = 15; //行数
    public static final int COLS = 15; //列数
    public static final int DIAMETER = 30;//棋子直径
    public int [][] chessMatrix;
    public int redX=0;
    public int redY=0;

    Image img; //背景木板
    boolean gameOver = false;

    public ChessBoard(){
        chessMatrix = new int[15][15];
        chessMatrix[3][3]=2;
        mosemoveListen();
        init();
    }

    /**
     * 带参数构造函数, 参数为棋子信息的二维数组
     * @param chessMatrix 棋子矩阵
     */
    public ChessBoard(int[][] chessMatrix){
        this.chessMatrix = chessMatrix;
        mosemoveListen();
        init();
    }

    public void mosemoveListen(){
        addMouseListener(this);
        addKeyListener(this);
        addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseDragged(MouseEvent e) {

            }

            @Override
            public void mouseMoved(MouseEvent e) {
                //x1,y1为网格所以, 将位置转换为网格.
                int x1 = (e.getX()-MARGIN+GRID_SPAN/2)/GRID_SPAN;
                int y1 = (e.getY()-MARGIN+GRID_SPAN/2)/GRID_SPAN;

                //判断失效位置, 失效,则鼠标为箭头, 否则为手型
                if (x1 < 0 || x1 > ROWS || y1 < 0 || y1 > COLS || gameOver || findChess(x1, y1)) {
                    setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
                } else {
                    setCursor(new Cursor(Cursor.HAND_CURSOR));
                }
            }
        });
    }

    /**
     * 初始化函数
     */
    private void init(){
        img = Toolkit.getDefaultToolkit().getImage("board.jpg");
    }

    /**
     * 画棋盘的函数
     * @param g
     */
    public  void paintComponent(Graphics g){
        //画棋盘
        super.paintComponent(g);
        int imgWidth = img.getWidth(this);
        int imgHeight = img.getHeight(this);//获取背景图尺寸

        int fWidth = getWidth();
        int fHeight = getHeight();//获取Panel尺寸

        int x = (fWidth-imgWidth)/2;
        int y = (fHeight - imgHeight);//将背景居中放置
        g.drawImage(img, x, y, null);

        //画横线, 横坐标(边距 --> 边距+列数*列距), 纵坐标(边距+i*列距)
        for (int i= 0; i<ROWS ; i++ ) {
            g.drawLine(MARGIN,MARGIN+i*GRID_SPAN,MARGIN+(COLS-1)*GRID_SPAN,MARGIN+i*GRID_SPAN);
        }
        //画纵线
        for(int i=0; i<COLS; i++){
            g.drawLine(MARGIN+i*GRID_SPAN,MARGIN,MARGIN+i*GRID_SPAN,MARGIN+(ROWS-1)*GRID_SPAN);
        }

        //画棋子
        for(int i =0; i<chessMatrix.length; i++){
            for(int j =0; j<chessMatrix.length; j++){
                if(chessMatrix[i][j] != 0){
                    int xPos = (int)i*GRID_SPAN+MARGIN;
                    int yPos = (int)j*GRID_SPAN+MARGIN;
                    if (chessMatrix[i][j] == 1){
                        RadialGradientPaint paint = new RadialGradientPaint(
                                xPos-this.DIAMETER/2+25,
                                yPos-this.DIAMETER/2+10,
                                20,
                                new float[]{0f,1f},
                                new Color[]{Color.WHITE,Color.BLACK});

                        ((Graphics2D) g).setPaint(paint);
                        //warning 可能有错
                        ((Graphics2D) g).setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON );
                        ((Graphics2D) g).setRenderingHint(RenderingHints.KEY_ALPHA_INTERPOLATION,RenderingHints.VALUE_ALPHA_INTERPOLATION_DEFAULT);
                    }
                    else if(chessMatrix[i][j]==2){
                        RadialGradientPaint paint = new RadialGradientPaint(
                                xPos-this.DIAMETER/2+25,
                                yPos-this.DIAMETER/2+10,
                                70,
                                new float[]{0f,1f},
                                new Color[]{Color.WHITE,Color.BLACK});

                        ((Graphics2D) g).setPaint(paint);
                        //warning 可能有错
                        ((Graphics2D) g).setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON );
                        ((Graphics2D) g).setRenderingHint(RenderingHints.KEY_ALPHA_INTERPOLATION,RenderingHints.VALUE_ALPHA_INTERPOLATION_DEFAULT );
                    }

                    Ellipse2D e = new Ellipse2D.Float(
                            xPos - this.DIAMETER/2,
                            yPos - this.DIAMETER/2,
                            this.DIAMETER-2,
                            this.DIAMETER-2
                    );

                    ((Graphics2D) g).fill(e);

                    redRect(redX,redY,g);
                }
            }
        }

    }

    public void redRect(int x, int y,Graphics g){
        g.setColor(Color.red);
        g.drawRect(
                (x* GRID_SPAN + MARGIN) - this.DIAMETER/2,
                (y* GRID_SPAN + MARGIN) - this.DIAMETER/2,
                this.DIAMETER-2,
                this.DIAMETER-2
        );
    }

    public boolean isInGrid(int x, int y){
       return  x >= 0 && x < ROWS && y >= 0 && y < COLS;
    }
    public boolean findChess(int x, int y){
        return isInGrid(x,y) && chessMatrix[x][y] != 0;
    }


    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
       System.out.println("hhhh");
        switch (e.getKeyCode()){
            case KeyEvent.VK_RIGHT:
                redX++;break;
            case KeyEvent.VK_LEFT:
                redX--;break;
            case KeyEvent.VK_DOWN:
                redY++;break;
            case KeyEvent.VK_UP:
                redY--;break;
            case KeyEvent.VK_SPACE:
                chessMatrix[redX][redY]=1;break;
            default:
                redX=5;
                redY=5;
                break;
        }
        System.out.print("键盘: " +redX + " " + redY);
        repaint();
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        int xIndex = (e.getX() - MARGIN+GRID_SPAN/2)/GRID_SPAN;
        int yIndex = (e.getY() - MARGIN+GRID_SPAN/2)/GRID_SPAN;
        System.out.println(xIndex + " " + yIndex);

        //落在棋盘外面不能下
        if(xIndex<0 || xIndex > ROWS || yIndex<0 || yIndex>COLS){
            return;
        }

        //棋子已经存在, 不能下
        if (findChess(xIndex, yIndex)){
            return ;
        }

        //其他位置有效
        chessMatrix[xIndex][yIndex]=1;
        redX = xIndex;
        redY = yIndex;
        repaint();
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
