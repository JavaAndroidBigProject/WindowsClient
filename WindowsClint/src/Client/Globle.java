package Client;

import java.awt.*;
import java.awt.geom.Ellipse2D;

/**
 * Created by zhaokuo on 2016/2/27.
 */
public class Globle {
    private static  int DIAMETER = 30;
    public static void painChess(Graphics g, Color c, int x, int y){
        RadialGradientPaint paint;
        if (c.equals(Color.black)){
            paint = new RadialGradientPaint(
                    x-DIAMETER/2+25,
                    y-DIAMETER/2+10,
                    20,
                    new float[]{0f,1f},
                    new Color[]{Color.WHITE,Color.BLACK});
        }
        else if (c.equals(Color.white)){
             paint = new RadialGradientPaint(
                    x-DIAMETER/2+25,
                    y-DIAMETER/2+10,
                    70,
                    new float[]{0f,1f},
                    new Color[]{Color.WHITE,Color.BLACK});
        }
        else return;
        ((Graphics2D) g).setPaint(paint);
        //warning 可能有错
        ((Graphics2D) g).setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON );
        ((Graphics2D) g).setRenderingHint(RenderingHints.KEY_ALPHA_INTERPOLATION,RenderingHints.VALUE_ALPHA_INTERPOLATION_DEFAULT);

        Ellipse2D e = new Ellipse2D.Float(
                x - DIAMETER/2,
                y - DIAMETER/2,
                DIAMETER-2,
                DIAMETER-2
        );

        ((Graphics2D) g).fill(e);
    }
}
