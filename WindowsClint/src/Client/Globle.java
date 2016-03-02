package Client;

import java.awt.*;
import java.awt.geom.Ellipse2D;

/**
 * Created by zhaokuo on 2016/2/27.
 */
public class Globle {
    private static  int DIAMETER = 30;

    /**
     * 画棋子
     * @param g 绘图句柄
     * @param c 颜色
     * @param x x坐标
     * @param y y坐标
     */
    public static void painChess(Graphics g, Color c, int x, int y){
        RadialGradientPaint paint;
        if (c.equals(Color.black)){
            /*
            1. 黑棋子: 先创建一个从黑到白的辐射, 辐射中心在圆心右上方
             */
            paint = new RadialGradientPaint(
                    x+DIAMETER/4,
                    y-DIAMETER/4,
                    20,
                    new float[]{0f,1f},
                    new Color[]{Color.WHITE,Color.BLACK});
        }

        else if (c.equals(Color.white)){
             paint = new RadialGradientPaint(
                     x+DIAMETER/4,
                     y-DIAMETER/4,
                    50,
                    new float[]{0f,1f},
                    new Color[]{Color.WHITE,Color.BLACK});
        }
        else return;
        ((Graphics2D) g).setPaint(paint);
        /*
        2. 消除锯齿
         */
        ((Graphics2D) g).setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON );
        ((Graphics2D) g).setRenderingHint(RenderingHints.KEY_ALPHA_INTERPOLATION,RenderingHints.VALUE_ALPHA_INTERPOLATION_DEFAULT);

        /*
        3. 填充圆
         */
        Ellipse2D e = new Ellipse2D.Float(
                x - DIAMETER/2,
                y - DIAMETER/2,
                DIAMETER-2,
                DIAMETER-2
        );

        ((Graphics2D) g).fill(e);
    }
}
