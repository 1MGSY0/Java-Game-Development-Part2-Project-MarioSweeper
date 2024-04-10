package mariosweeper;

import java.awt.event.*;

import gameplay.GameBoardPanel;

public class KeyHandler implements KeyListener{
	
	MainWindow mw;
	GameBoardPanel gbp;
	
	public boolean upPressed, downPressed, leftPressed, rightPressed;
	public boolean enterPressed, pPressed, spacePressed, hPressed;
	public boolean showSystemInfo = false;
	public String keyAction;
	
	public KeyHandler(MainWindow mw, GameBoardPanel gbp) {
		this.mw = mw;
		this.gbp = gbp;
	}

	@Override
	public void keyPressed(KeyEvent e) {
		keyAction = "Pressed";
		if (mw.gameState == mw.titleState) {
			titleKeyHandler(e,keyAction);
		}
		
		if (mw.gameState == mw.playState) {
			playKeyHandler(e,keyAction);
		}
		
		if (mw.gameState == mw.pauseState) {
			pauseKeyHandler(e,keyAction);
		}	
		
		if (mw.gameState == mw.helpState) {
			helpKeyHandler(e,keyAction);
		}
		
		if (mw.gameState == mw.settingState ) {
			settingKeyHandler(e,keyAction);
		}
		
		if(mw.gameState == mw.resultState) {
			resultKeyHandler(e,keyAction);
		}
			
	}

	@Override
	public void keyReleased(KeyEvent e) {
		
		keyAction="Released";
		
		if(mw.gameState == mw.titleState) {
			titleKeyHandler(e,keyAction);
		}
		
		if (mw.gameState == mw.playState) {
			playKeyHandler(e,keyAction);
		}
		

	}
	
	public void titleKeyHandler(KeyEvent e, String keyAction) {
		
		if(keyAction=="Pressed") {
			switch(e.getKeyCode()) {
			case KeyEvent.VK_H:
				hPressed = true;
				break;
			case KeyEvent.VK_W, KeyEvent.VK_UP:
				upPressed = true;
				if (mw.ui.titleMenuCmd > 0) {
					mw.ui.titleMenuCmd--;
				}
				break;
			case KeyEvent.VK_S, KeyEvent.VK_DOWN:
				downPressed = true;
				if (mw.ui.titleMenuCmd < 4) {
					mw.ui.titleMenuCmd++;
				}
				break;
			case KeyEvent.VK_A, KeyEvent.VK_LEFT:
				leftPressed = true;
				if (mw.ui.titleMenuCmd == 0 && mw.ui.lvlCmdNum > 0) {
					mw.ui.lvlCmdNum--;
				}else if (mw.ui.titleMenuCmd == 0 && mw.ui.lvlCmdNum <= 0) {
					mw.ui.lvlCmdNum = 3;
				}
				if (mw.ui.titleMenuCmd > 2 && mw.ui.titleMenuCmd <= 4) {
					mw.ui.titleMenuCmd--;
				}
				break;
			case KeyEvent.VK_D, KeyEvent.VK_RIGHT:
				rightPressed = true;
				if (mw.ui.titleMenuCmd == 0 && mw.ui.lvlCmdNum < 3) {
					mw.ui.lvlCmdNum++;
				}else if (mw.ui.titleMenuCmd == 0 && mw.ui.lvlCmdNum >= 3) {
					mw.ui.lvlCmdNum = 0;
				}
				if (mw.ui.titleMenuCmd >= 2 && mw.ui.titleMenuCmd < 4) {
					mw.ui.titleMenuCmd++;
				}
				break;
			case KeyEvent.VK_T:
				if (showSystemInfo == true) {
					showSystemInfo = false;
				}else if (showSystemInfo == false){
					showSystemInfo = true;
				}
				break;
			case KeyEvent.VK_SPACE:
				spacePressed = true;
				if(mw.ui.titleMenuCmd == 1) {
					mw.stopMusic();
					mw.playSelectedLvlMusic(mw.ui.getSelectedLevel(mw.ui.lvlCmdNum));
					mw.gameState = mw.playState;
					gbp.newGame();
					
				}
				if(mw.ui.titleMenuCmd == 2) {
					mw.preState = mw.titleState;
					mw.gameState = mw.helpState;
				}
				if(mw.ui.titleMenuCmd == 3) {
					mw.preState = mw.titleState;
					mw.gameState = mw.settingState;
				}
				if(mw.ui.titleMenuCmd == 4) {
					System.exit(0);
				}
				break;
			
			}
		}
		
		if (keyAction=="Released") {
			if (mw.gameState == mw.titleState) {
				switch(e.getKeyCode()) {
				case KeyEvent.VK_W, KeyEvent.VK_UP:
					upPressed = false;
					break;
				case KeyEvent.VK_S, KeyEvent.VK_DOWN:
					downPressed = false;
					break;
				case KeyEvent.VK_A, KeyEvent.VK_LEFT:
					leftPressed = false;
					break;
				case KeyEvent.VK_D, KeyEvent.VK_RIGHT:
					rightPressed = false;
					break;
				case KeyEvent.VK_H:
					hPressed = false;
					break;
				case KeyEvent.VK_SPACE:
					spacePressed = false;
					break;
				}
			}
		}
	}
	
	public void playKeyHandler(KeyEvent e, String keyAction) {
		
		if (keyAction=="Pressed") {
			switch(e.getKeyCode()) {
			case KeyEvent.VK_W, KeyEvent.VK_UP:
				upPressed = true;
				break;
			case KeyEvent.VK_S, KeyEvent.VK_DOWN:
				downPressed = true;
				break;
			case KeyEvent.VK_A, KeyEvent.VK_LEFT:
				leftPressed = true;
				break;
			case KeyEvent.VK_D, KeyEvent.VK_RIGHT:
				rightPressed = true;
				break;
			case KeyEvent.VK_H:
				hPressed = true;
				break;
			case KeyEvent.VK_T:
				if (showSystemInfo == true) {
					showSystemInfo = false;
				}else if (showSystemInfo == false){
					showSystemInfo = true;
				}
				break;
			case KeyEvent.VK_P:
				mw.gameState = mw.pauseState;
				break;
			}
		}
		if(keyAction=="Released") {
			switch(e.getKeyCode()) {
			case KeyEvent.VK_W, KeyEvent.VK_UP:
				upPressed = false;
				break;
			case KeyEvent.VK_S, KeyEvent.VK_DOWN:
				downPressed = false;
				break;
			case KeyEvent.VK_A, KeyEvent.VK_LEFT:
				leftPressed = false;
				break;
			case KeyEvent.VK_D, KeyEvent.VK_RIGHT:
				rightPressed = false;
				break;
			case KeyEvent.VK_H:
				hPressed = false;
				break;
			}
		}
	}
	
	public void pauseKeyHandler(KeyEvent e, String keyAction) {
		
		if(keyAction == "Pressed") {
			switch(e.getKeyCode()) {
			case KeyEvent.VK_W, KeyEvent.VK_UP:
				if (mw.ui.pauseMenuCmd > 0) {
					mw.ui.pauseMenuCmd--;
				}
				break;
			case KeyEvent.VK_S, KeyEvent.VK_DOWN:
				if (mw.ui.pauseMenuCmd < 4) {
					mw.ui.pauseMenuCmd++;
				}
				break;
			case KeyEvent.VK_A, KeyEvent.VK_LEFT:
				if (mw.ui.pauseMenuCmd == 0 && mw.ui.lvlCmdNum > 0) {
					mw.ui.lvlCmdNum--;
				}else if (mw.ui.pauseMenuCmd == 0 && mw.ui.lvlCmdNum <= 0) {
					mw.ui.lvlCmdNum = 3;
				}
				if (mw.ui.pauseMenuCmd > 2 && mw.ui.pauseMenuCmd <= 4) {
					mw.ui.pauseMenuCmd--;
				}
				break;
			case KeyEvent.VK_D, KeyEvent.VK_RIGHT:
				if (mw.ui.pauseMenuCmd == 0 && mw.ui.lvlCmdNum < 3) {
					mw.ui.lvlCmdNum++;
				}else if (mw.ui.pauseMenuCmd == 0 && mw.ui.lvlCmdNum >= 3) {
					mw.ui.lvlCmdNum = 0;
				}
				if (mw.ui.pauseMenuCmd >= 2 && mw.ui.pauseMenuCmd < 4) {
					mw.ui.pauseMenuCmd++;
				}
				break;
			case KeyEvent.VK_ESCAPE:
				mw.gameState = mw.playState;
				break;
			case KeyEvent.VK_SPACE:
				if(mw.ui.pauseMenuCmd == 0) {
					mw.gameState = mw.playState;
				}
				if(mw.ui.pauseMenuCmd == 1) {
					mw.gameState = mw.playState;
					gbp.newGame();
				}
				if(mw.ui.pauseMenuCmd == 2) {
					mw.stopMusic();
					mw.gameState = mw.titleState;
					mw.playMusic(0);
				}
				if(mw.ui.pauseMenuCmd == 3) {
					mw.preState = mw.pauseState;
					mw.gameState = mw.helpState;
				}
				if(mw.ui.pauseMenuCmd == 4) {
					mw.preState = mw.pauseState;
					mw.gameState = mw.settingState;	
				}
				break;
			}
		}
		if(keyAction == "Released") {
			//NOTHING
		}	
	}
	
	public void helpKeyHandler(KeyEvent e, String keyAction) {
		if(keyAction == "Pressed") {
			switch(e.getKeyCode()) {
			case KeyEvent.VK_ESCAPE:
				mw.gameState = mw.preState;
				break;
			}
		}
		if(keyAction == "Released") {
			//NOTHING
		}
	}
	
	public void settingKeyHandler(KeyEvent e, String keyAction) {
		if(keyAction == "Pressed") {
			switch(e.getKeyCode()) {
			case KeyEvent.VK_ESCAPE:
				mw.gameState = mw.preState;
				break;
			}
		}
		if(keyAction == "Released") {
			//NOTHING
		}
	}
	
	public void resultKeyHandler(KeyEvent e, String keyAction) {
		if(keyAction == "Pressed") {
			switch(e.getKeyCode()) {
			case KeyEvent.VK_W, KeyEvent.VK_UP:
				if (mw.ui.resultMenuCmd == 1) {
					mw.ui.resultMenuCmd = 0;
				}
				break;
			case KeyEvent.VK_S, KeyEvent.VK_DOWN:
				if (mw.ui.resultMenuCmd == 0) {
					mw.ui.resultMenuCmd = 1;
				}
				break;
			case KeyEvent.VK_SPACE:
				if(mw.ui.resultMenuCmd == 0) {
					mw.gameState = mw.playState;
					gbp.newGame();
				}
				if(mw.ui.resultMenuCmd == 1) {
					mw.stopMusic();
					mw.gameState = mw.titleState;
					mw.playMusic(0);
				}
				break;
			}
		}
		if(keyAction == "Released") {
			//NOTHING
		}
	}
	
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
}
