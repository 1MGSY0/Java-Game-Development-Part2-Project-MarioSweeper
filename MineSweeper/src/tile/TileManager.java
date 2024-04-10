package tile;

import java.awt.Graphics2D;
import java.io.*;

import javax.imageio.ImageIO;

import mariosweeper.MainWindow;
import mariosweeper.UtilityTool;

public class TileManager {
	
	MainWindow mw;
	Tile[] tile;
	int mapTileNum[][];
	
	public TileManager(MainWindow mw) {
		
		this.mw = mw;
		tile = new Tile[3];
		mapTileNum = new int[MainWindow.maxScreenCol][MainWindow.maxScreenRow];
		
		getTileImage();
		loadMap("/maps/map1.txt");
	}
	
	public void getTileImage() {

			setup(0, "Field");
			setup(1, "Floor");
			setup(2, "Wall");
	}
	
	public void setup(int index, String imageName) {
		
		UtilityTool uTool = new UtilityTool();
		
		try {
			tile[index] = new Tile();
			tile[index].image = ImageIO.read(getClass().getResourceAsStream("/blocks/"+imageName+".png"));
			tile[index].image = uTool.scaleImage(tile[index].image, MainWindow.tileSize, MainWindow.tileSize);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void loadMap(String filePath){
		try {
			
			InputStream is = getClass().getResourceAsStream(filePath);
			BufferedReader br = new BufferedReader(new InputStreamReader(is));
			
			int col = 0, row = 0;
			
			while(col < MainWindow.maxScreenCol && row < MainWindow.maxScreenRow) {
				
					String line = br.readLine();
				
				while(col < MainWindow.maxScreenCol) {
					
					String numbers[] = line.split("");
					int num = Integer.parseInt(numbers[col]);
					mapTileNum[col][row] = num;
					col++;
				}
				
				if(col == MainWindow.maxScreenCol) {
					col = 0;
					row++;
				}
			}
			br.close();
		} catch (IOException e) {
				e.printStackTrace();
		}
			
	}
	public void draw(Graphics2D g2) {
		
		int col = 0, row = 0;
		int x = 0, y = 0;
		
		while(col < MainWindow.maxScreenCol && row < MainWindow.maxScreenRow) {
			
			int tileNum = mapTileNum[col][row];
			
			g2.drawImage(tile[tileNum].image, x, y, null);
			col++;
			x += MainWindow.tileSize;
			
			if(col == MainWindow.maxScreenCol) {
				col = 0;
				x = 0;
				row++;
				y += MainWindow.tileSize;
			}
		}
		
	}
	
}
