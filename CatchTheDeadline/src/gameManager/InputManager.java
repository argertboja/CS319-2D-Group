package gameManager;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import gameobjects.*;
import window.GameEngine;

public class InputManager extends KeyAdapter {
	
	Handler handler;
	GameEngine gm;
	
	public InputManager( Handler handler, GameEngine gm ) {
		this.handler = handler;
		this.gm = gm;
	}
	
	public void keyPressed( KeyEvent event) {
		int key = event.getKeyCode();
		
		for( int i = 0; i < handler.objectLinkedList.size(); i++ ) {
			GameObject temp = handler.objectLinkedList.get(i);
			
			if( temp.getType() == ObjectType.Player ){
				if( (key == KeyEvent.VK_SPACE || key == KeyEvent.VK_UP) && !temp.isJumping() ) {
					temp.setVelocityY(-5);
					temp.setJumping(true);
				}
				if( key == KeyEvent.VK_DOWN )
					temp.setVelocityY(5);
				if( key == KeyEvent.VK_RIGHT )
					temp.setVelocityX(5);
				if( key == KeyEvent.VK_LEFT )
					temp.setVelocityX(-5);
			}
		}
		if( key == KeyEvent.VK_ESCAPE ) {
			System.exit(1);
		}
	}
	
	public void keyReleased( KeyEvent event) {
		int key = event.getKeyCode();
		
		for( int i = 0; i < handler.objectLinkedList.size(); i++ ) {
			GameObject temp = handler.objectLinkedList.get(i);
			
			if( temp.getType() == ObjectType.Player ){
				if( key == KeyEvent.VK_DOWN )
					temp.setVelocityY(0);
				if( key == KeyEvent.VK_RIGHT )
					temp.setVelocityX(0);
				if( key == KeyEvent.VK_LEFT )
					temp.setVelocityX(0);
				if (key == KeyEvent.VK_A) {
					if (temp.getFacing() == 1)
						handler.addObject(new Pen(temp.getPosX() +38, temp.getPosY() + 88, ObjectType.Pen, temp.getFacing() * 5));
					else
						handler.addObject(new Pen(temp.getPosX(), temp.getPosY() + 88, ObjectType.Pen, temp.getFacing() * 5));
				}
				if (key == KeyEvent.VK_S && gm.isEraserAct() ) {
					if (temp.getFacing() == 1)
						handler.addObject(new Eraser(temp.getPosX() +38, temp.getPosY() + 88, ObjectType.Eraser, temp.getFacing() * 5));
					else
						handler.addObject(new Eraser(temp.getPosX(), temp.getPosY() + 88, ObjectType.Eraser, temp.getFacing() * 5));
				}	
				if (key == KeyEvent.VK_D && gm.isPsAct() ) {
					if (temp.getFacing() == 1)
						handler.addObject(new PaintSpray(temp.getPosX() +38, temp.getPosY() + 88, ObjectType.PaintSpray, temp.getFacing() * 5));
					else
						handler.addObject(new PaintSpray(temp.getPosX(), temp.getPosY() + 88, ObjectType.PaintSpray, temp.getFacing() * 5));
				}	
			}
		}
		if( key == KeyEvent.VK_ESCAPE ) {
			System.exit(1);
		}
	}	
}