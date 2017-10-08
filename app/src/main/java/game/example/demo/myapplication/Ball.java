package game.example.demo.myapplication;

import android.graphics.Canvas;
import android.graphics.Paint;

/**
 * Created by sev_user on 29-Nov-14.
 */
public class Ball {

    int x;
    int y;

    int vx;
    int vy;

    int radius;

    Paint paint;

    public Ball(int x, int y, int vx, int vy, int radius, Paint paint) {
        this.x = x;
        this.y = y;
        this.vx = vx;
        this.vy = vy;
        this.radius = radius;
        this.paint = paint;
    }

    public void moveBall(int WIDTH, int HEIGHT) {

        x += vx;
        y += vy;

        if (x >= WIDTH - radius || x <= radius) {
            vx = -vx;
        }

        if (y >= HEIGHT - radius || y <= radius) {
            vy = -vy;
        }

        // invalidate();
    }

    public void draw(Canvas canvas) {
        canvas.drawCircle(x, y, radius, paint);
    }
}
