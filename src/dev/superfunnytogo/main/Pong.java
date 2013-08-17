package dev.superfunnytogo.main;

import java.awt.Frame;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.media.opengl.*;
import javax.media.opengl.awt.GLCanvas;

import com.jogamp.opengl.util.*;

public class Pong implements GLEventListener,KeyListener {

	boolean iPressed;
	boolean kPressed;
	boolean wPressed;
	boolean sPressed;
	
	double bluePaddlePos = 0;
	double redPaddlePos = 0;
	char key;
	
    public static void main(String[] args) {
        
    	GLProfile glp = GLProfile.getDefault();
        GLCapabilities caps = new GLCapabilities(glp);
        GLCanvas canvas = new GLCanvas(caps);

        Frame frame = new Frame("Pong by Spencer Fishman");
        frame.setSize(1000,500);
        frame.add(canvas);
        frame.setVisible(true);
        
        FPSAnimator animator = new FPSAnimator(canvas,60);
        animator.start();
        
        frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        
        canvas.addGLEventListener(new Pong());
    }

	public void display(GLAutoDrawable drawable) {
		update();
	    render(drawable);
	}
	
	private void update() {
		if (iPressed) {
			bluePaddlePos = bluePaddlePos + 0.05;
		}
	}
	
	private void render(GLAutoDrawable drawable) {
		GL2 GL = drawable.getGL().getGL2();
		GL.glClear(javax.media.opengl.GL.GL_COLOR_BUFFER_BIT);
		
		GL.glColor3d(0.8, 0.8, 0.8);
		
		GL.glBegin(GL2GL3.GL_QUADS);
        
		GL.glVertex2d(0.01,1.0);
       	GL.glVertex2d(-0.01,1.0);
       	GL.glVertex2d(-0.01,-1.0);
       	GL.glVertex2d(0.01,-1.0);
        
       	GL.glEnd();
        
       	GL.glColor3d(0.0, 0.0, 1.0);
        //Blue Line
       	GL.glBegin(GL2GL3.GL_QUADS);
        
       	GL.glVertex2d(1.0,1.0);
       	GL.glVertex2d(0.99,1.0);
       	GL.glVertex2d(0.99,-1.0);
       	GL.glVertex2d(1.0,-1.0);
        
       	GL.glEnd();
        
       	GL.glColor3d(1.0, 0.0, 0.0);
        //Red Line
       	GL.glBegin(GL2GL3.GL_QUADS);
        
       	GL.glVertex2d(-1.0,1.0);
       	GL.glVertex2d(-0.99,1.0);
       	GL.glVertex2d(-0.99,-1.0);
       	GL.glVertex2d(-1.0,-1.0);
        
       	GL.glEnd();
        
       	GL.glColor3d(0.8, 0.8, 0.8);
        //Top Line
       	GL.glBegin(GL2GL3.GL_QUADS);
        
       	GL.glVertex2d(1.0,1.0);
       	GL.glVertex2d(1.0,0.98);
       	GL.glVertex2d(-1.0,0.98);
       	GL.glVertex2d(-1.0,1.0);
        
       	GL.glEnd();
       	
        //Bot Line
       	GL.glBegin(GL2GL3.GL_QUADS);
        
       	GL.glVertex2d(1.0,-1.0);
        GL.glVertex2d(1.0,-0.98);
        GL.glVertex2d(-1.0,-0.98);
        GL.glVertex2d(-1.0,-1.0);
        
        GL.glEnd();
        
        GL.glColor3d(0.0, 0.0, 1.0);
        //Blue Paddle
        GL.glBegin(GL2GL3.GL_QUADS);
        
        GL.glVertex2d(0.92,bluePaddlePos + 0.25);
        GL.glVertex2d(0.88,bluePaddlePos + 0.25);
        GL.glVertex2d(0.88,bluePaddlePos - 0.25);
        GL.glVertex2d(0.92,bluePaddlePos - 0.25);
        
        GL.glEnd();
        
        GL.glColor3d(1.0, 0.0, 0.0);
        //Red Paddle
        GL.glBegin(GL2GL3.GL_QUADS);
        
        GL.glVertex2d(-0.92,0 + 0.25);
        GL.glVertex2d(-0.88,0 + 0.25);
        GL.glVertex2d(-0.88,0 - 0.25);
        GL.glVertex2d(-0.92,0 - 0.25);
        
        GL.glEnd();
        
        GL.glColor3d(0.0,1.0,0.0);
        
        GL.glBegin(javax.media.opengl.GL.GL_TRIANGLE_FAN);
        //glVertex2f(x1 + sind(angle#) * radius#, y1 + cosd(angle#) * radius#)
        
        GL.glVertex2d(0.0,0.0);
        for (int angle = 0;angle < 360;angle++) {
        	GL.glVertex2d(0.0 + Math.sin(angle) * 0.04, 0.0 + Math.cos(angle) * 0.08);
        }
        GL.glEnd();

	}

	public void dispose(GLAutoDrawable arg0) {
		
	}

	public void init(GLAutoDrawable drawable) {
		drawable.getGL().setSwapInterval(1);
	}

	public void reshape(GLAutoDrawable arg0,int arg1,int arg2,int arg3,int arg4) {
		
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		switch (key) {
			case 105:
				iPressed = true;
				break;
			case 107:
				kPressed = true;
				break;
			case 119:
				wPressed = true;
				break;
			case 115:
				sPressed = true;
				break;
			default:
				break;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();
		switch (key) {
		case 105:
			iPressed = false;
			break;
		case 107:
			kPressed = false;
			break;
		case 119:
			wPressed = false;
			break;
		case 115:
			sPressed = false;
			break;
		default:
			break;
	}
		
	}

	@Override
	public void keyTyped(KeyEvent e) {}
}

