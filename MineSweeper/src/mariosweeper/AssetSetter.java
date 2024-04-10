package mariosweeper;

import object.OBJ_Coin;

public class AssetSetter {
	
	MainWindow mw;

	public AssetSetter(MainWindow mw) {
		this.mw = mw;
	}
	
	public void setObject() {
		
		mw.obj[0] = new OBJ_Coin();
		mw.obj[0].x = 500;
		mw.obj[0].y = 85;
		
	}

}
