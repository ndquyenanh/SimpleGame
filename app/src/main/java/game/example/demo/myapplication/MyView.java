package game.example.demo.myapplication;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

/**
 * Created by sev_user on 29-Nov-14.
 */
public class MyView extends SurfaceView implements SurfaceHolder.Callback {

    GameThread gameThread;

    Paint clearPaint;

    Ball ball1;
    Ball ball2;

    Bar mBar;
    Paint bar1Paint;

    Bar comBar;
    Paint comBarPaint;

    Bar comBar2;
    Paint comBar2Paint;

    Paint textPaint;
    Context mContext;

    Bitmap mBackgroundBitmap;

    /**
     * Simple constructor to use when creating a view from code.
     *
     * @param context The Context the view is running in, through which it can
     *                access the current theme, resources, etc.
     */
    public MyView(Context context) {
        super(context);
        initVal();

        mContext = context;
    }

    /**
     * Constructor that is called when inflating a view from XML. This is called
     * when a view is being constructed from an XML file, supplying attributes
     * that were specified in the XML file. This version uses a default style of
     * 0, so the only attribute values applied are those in the Context's Theme
     * and the given AttributeSet.
     * <p/>
     * <p/>
     * The method onFinishInflate() will be called after all children have been
     * added.
     *
     * @param context The Context the view is running in, through which it can
     *                access the current theme, resources, etc.
     * @param attrs   The attributes of the XML tag that is inflating the view.
     * @see #View(android.content.Context, android.util.AttributeSet, int)
     */
    public MyView(Context context, AttributeSet attrs) {
        super(context, attrs);

        getHolder().addCallback(this);
        initVal();

        mContext = context;
    }

    Drawable mDrawable;

    /**
     * Set the background to a given resource. The resource should refer to
     * a Drawable object or 0 to remove the background.
     *
     * @param resid The identifier of the resource.
     * @attr ref android.R.styleable#View_background
     */
    @Override
    public void setBackgroundResource(int resid) {
        super.setBackgroundResource(resid);
    }

    @Override
    public void setBackground(Drawable background) {
        super.setBackground(background);

        mDrawable = background;
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);

        if (mDrawable != null) {
            mDrawable.setBounds(left, top, right, bottom);
        }
    }

    private void initVal() {
        Paint paint1 = new Paint();
        paint1.setColor(Color.RED);

        Paint paint2 = new Paint();
        paint2.setColor(Color.BLACK);

        ball1 = new Ball(100, 100, 10, 10, 30, paint1);
        ball2 = new Ball(50, 1500, 10, -15, 40, paint2);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        switch (event.getAction()) {

            case MotionEvent.ACTION_DOWN | MotionEvent.ACTION_MOVE:

                //mBar.x = (int) event.getX();
                //mBar.width = 250 + mBar.x;

                mBar.moveBar((int) event.getX());

                if (mBar.width > getWidth()) {
                    mBar.width = getWidth();
                }

                Log.v("mbar==>", "mbar width = " + mBar.width);
                Log.v("X==>", "mbar X = " + mBar.width);
                break;
        }
        return true;
    }

    /**
     * Implement this method to handle hover events.
     * <p>
     * This method is called whenever a pointer is hovering into, over, or out of the
     * bounds of a view and the view is not currently being touched.
     * Hover events are represented as pointer events with action
     * {@link android.view.MotionEvent#ACTION_HOVER_ENTER}, {@link android.view.MotionEvent#ACTION_HOVER_MOVE},
     * or {@link android.view.MotionEvent#ACTION_HOVER_EXIT}.
     * </p>
     * <ul>
     * <li>The view receives a hover event with action {@link android.view.MotionEvent#ACTION_HOVER_ENTER}
     * when the pointer enters the bounds of the view.</li>
     * <li>The view receives a hover event with action {@link android.view.MotionEvent#ACTION_HOVER_MOVE}
     * when the pointer has already entered the bounds of the view and has moved.</li>
     * <li>The view receives a hover event with action {@link android.view.MotionEvent#ACTION_HOVER_EXIT}
     * when the pointer has exited the bounds of the view or when the pointer is
     * about to go down due to a button click, tap, or similar user action that
     * causes the view to be touched.</li>
     * </ul>
     * <p>
     * The view should implement this method to return true to indicate that it is
     * handling the hover event, such as by changing its drawable state.
     * </p><p>
     * The default implementation calls {@link #setHovered} to update the hovered state
     * of the view when a hover enter or hover exit event is received, if the view
     * is enabled and is clickable.  The default implementation also sends hover
     * accessibility events.
     * </p>
     *
     * @param event The motion event that describes the hover.
     * @return True if the view handled the hover event.
     * @see #isHovered
     * @see #setHovered
     * @see #onHoverChanged
     */
    @Override
    public boolean onHoverEvent(MotionEvent event) {

        mBar.moveBar((int) event.getX());
        return true;
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        clearPaint = new Paint();
        clearPaint.setColor(Color.WHITE);

        bar1Paint = new Paint();
        bar1Paint.setColor(Color.YELLOW);
        mBar = new Bar(100, 0, 360, 65, bar1Paint);

        comBarPaint = new Paint();
        comBarPaint.setColor(Color.CYAN);
        comBar = new Bar(10, getHeight() - 65, 270, getHeight(), comBarPaint);

        comBar2Paint = new Paint();
        comBar2Paint.setColor(Color.MAGENTA);
        comBar2 = new Bar(getWidth() - 500, getHeight() - 65, getWidth() - 250, getHeight(), comBar2Paint);

        textPaint = new Paint();
        Typeface typeface = Typeface.createFromAsset(mContext.getAssets(), "BroadcastTitling.ttf");
        textPaint.setTypeface(typeface);
        textPaint.setAntiAlias(true);
        textPaint.setColor(Color.parseColor("#00ccff"));
        textPaint.setTextSize(100);

        mCountThread.start();
        mBackgroundBitmap = BitmapFactory.decodeResource(mContext.getResources(), R.drawable.bg);
    }

    void drawScore(Canvas canvas) {

        canvas.drawText("Time: " + mCountThread.getCount(), getWidth() / 2 - 150, getHeight() / 2 - 50, textPaint);
    }

    public void pause() throws InterruptedException {
        gameThread.setRunning(false);

        gameThread.join();
    }

    public void reRun() {

        gameThread = new GameThread(getHolder());

        gameThread.setRunning(true);
        gameThread.start();

    }

    private void clearScreen(Canvas canvas) {
        //canvas.drawBitmap(mBackgroundBitmap,getWidth(),getHeight(),clearPaint);
        canvas.drawRect(0, 0, getWidth(), getHeight(), clearPaint);
    }

    /**
     * This is called immediately after the surface is first created.
     * Implementations of this should start up whatever rendering code
     * they desire.  Note that only one thread can ever draw into
     * a {@link Surface}, so you should not draw into the Surface here
     * if your normal rendering will be in another thread.
     *
     * @param holder The SurfaceHolder whose surface is being created.
     */
    @Override
    public void surfaceCreated(SurfaceHolder holder) {

        gameThread = new GameThread(holder);
        gameThread.setRunning(true);
        gameThread.start();
    }

    /**
     * This is called immediately after any structural changes (format or
     * size) have been made to the surface.  You should at this point update
     * the imagery in the surface.  This method is always called at least
     * once, after {@link #surfaceCreated}.
     *
     * @param holder The SurfaceHolder whose surface has changed.
     * @param format The new PixelFormat of the surface.
     * @param width  The new width of the surface.
     * @param height The new height of the surface.
     */
    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    /**
     * This is called immediately before a surface is being destroyed. After
     * returning from this call, you should no longer try to access this
     * surface.  If you have a rendering thread that directly accesses
     * the surface, you must ensure that thread is no longer touching the
     * Surface before returning from this function.
     *
     * @param holder The SurfaceHolder whose surface is being destroyed.
     */
    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        if (gameThread.isAlive()) {

            try {

                gameThread.setRunning(false);
                gameThread.join();
                gameThread = null;

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void checkCollision() {

        if (ball1.x + ball1.radius >= ball2.x - ball2.radius &&
                ball1.x - ball1.radius <= ball2.x + ball2.radius &&
                ball1.y + ball1.radius >= ball2.y - ball2.radius &&
                ball1.y - ball1.radius <= ball2.y + ball2.radius) {

            ball1.vx *= -1;
            ball1.vy *= -1;

            ball2.vx *= -1;
            ball2.vy *= -1;
        }

        checkBar_BallCollisionManual(ball1);
        checkBar_BallCollisionManual(ball2);

        //checkBar_Ball_Auto(ball1);
        check_Bar_Ball_Auto(ball1, comBar);
        check_Bar_Ball_Auto(ball2, comBar);

        check_Bar_Ball_Auto(ball1, comBar2);
        check_Bar_Ball_Auto(ball2, comBar2);

        check_Bar_Bar_collision();
    }

    int tmp = 0;
    boolean b = true;

//    void checkBar_Ball_Auto(Ball ball) {
//
//        Log.v("in here", "Ball = " + ball.x);
//
//        int tmp1 = ball.x - ball.radius;
//        boolean b1 = tmp1 <= comBar.width;
//
//        int tmp2 = ball.x + ball.radius;
//        boolean b2 = tmp2 >= comBar.x;
//
//        int tmp3 = ball.y + ball.radius;
//        boolean b3 = tmp3 >= comBar.y;
//
//        if (b1 && b2 && b3) {
//
//            ball.vx *= -1;
//            ball.vy *= -1;
//            tmp++;
//
//            if (tmp % 2 == 0) {
//                comBar.paint.setColor(Color.parseColor("#00ffcc"));
//            } else {
//                comBar.paint.setColor(Color.parseColor("#00dd00"));
//            }
//
//            b = !b;
//        }
//    }

    void check_Bar_Ball_Auto(Ball ball, Bar bar) {

        if (ball.x - ball.radius <= bar.width
                && ball.x + ball.radius >= bar.x
                && ball.y + ball.radius >= bar.y) {

            ball.vx *= -1;
            ball.vy *= -1;
        }
    }

    void checkBar_BallCollisionManual(Ball ball) {

        if (ball.x + ball.radius <= mBar.width &&
                ball.x - ball.radius >= mBar.x &&
                ball.y - ball.radius <= mBar.height) {

            ball.vx *= -1;
            ball.vy *= -1;
        }
    }

    void check_Bar_Bar_collision() {
        if (comBar.x + 250 >= comBar2.x) {
            comBar2.vx *= -1;
            comBar.vx *= -1;
        }
    }

    class GameThread extends Thread {

        Canvas mCanvas;
        SurfaceHolder surfaceHolder;
        boolean isRunning;

        public GameThread(SurfaceHolder surfaceHolder) {
            this.surfaceHolder = surfaceHolder;
        }

        public void setRunning(boolean isRunning) {
            this.isRunning = isRunning;
        }

        /**
         * Starts executing the active part of the class' code. This method is
         * called when a thread is started that has been created with a class which
         * implements {@code Runnable}.
         */
        @Override
        public void run() {

            while (isRunning) {
                try {
                    mCanvas = surfaceHolder.lockCanvas(null);

                    synchronized (surfaceHolder) {

                        clearScreen(mCanvas);

                        ball1.draw(mCanvas);
                        ball2.draw(mCanvas);

                        if (mDrawable != null) {
                            mDrawable.draw(mCanvas);
                        }

//                        if (b) {
//                            comBar.moveBar(ball1.x);
//                        } else {
//                            comBar.moveBar(ball2.x);
//                        }

                        drawScore(mCanvas);
                        ball1.moveBall(getWidth(), getHeight());
                        ball2.moveBall(getWidth(), getHeight());
                        checkCollision();

                        mBar.draw(mCanvas);
                        comBar.draw(mCanvas);
                        comBar.moveBar1(getWidth());

                        comBar2.draw(mCanvas);
                        comBar2.moveBar1(getWidth());

                    }

                } catch (Exception ex) {
                    ex.printStackTrace();

                } finally {
                    if (mCanvas != null) {
                        surfaceHolder.unlockCanvasAndPost(mCanvas);
                    }
                }
            }
        }
    }

    CountThread mCountThread = new CountThread();
}
