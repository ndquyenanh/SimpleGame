package game.example.demo.myapplication;

import android.graphics.Canvas;
import android.graphics.Paint;

/**
 * Created by sev_user on 29-Nov-14.
 */
public class Bar {

    int x;
    int y;

    int vx;

    Paint paint;
    int width;
    int height;

    public Bar(int x, int y, int width, int height, Paint paint) {
        this.height = height;
        this.width = width;
        this.paint = paint;
        this.y = y;
        this.x = x;

        vx = 10;
    }

    public void draw(Canvas canvas) {
        canvas.drawRect(x, y, x + 250, height, paint);
    }

    public void moveBar(int horizontal) {
        x = horizontal;
        width = x + 250;
    }

    public void moveBar1(int WIDTH) {
        x += vx;

        width = x + 250;

        if (x <= 0 || x + 250 >= WIDTH) {
            vx = -vx;
        }
    }
}
