package Map;

import Main.GamePanel;

import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Random;

public class TileManager {
    private GamePanel gp;
    private Tile tiles;
    private int tileNum[][];

    public TileManager(GamePanel gp){
        tiles = new Tile();
        this.gp = gp;
        tileNum = new int[gp.getWorldWidth()][gp.getWorldHeight()];
        loadMap("/Map/Maps/map1.txt");
    }

    Random rand = new Random();

    public void loadMap(String s){

        try {
            InputStream is = getClass().getResourceAsStream(s);
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            int row = 0;
            int col = 0;
            while (col < gp.getMaxWorldX() && row < gp.getMaxWorldY()){
                String line = br.readLine();
                while (col < gp.getMaxWorldX()){
                    String nums[] = line.split(" ");
                    int num = Integer.parseInt(nums[col]);
                    tileNum[col][row] = num;
                    col++;
                }
                if (col == gp.getMaxWorldX()) {
                    col = 0;
                    row++;
                }
            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void draw(Graphics2D g2){
        int row = 0;
        int col = 0;

        while (col < gp.getMaxWorldX() && row < gp.getMaxWorldY()){

            int tileN = tileNum[col][row];
            int worldX = col * gp.getTileSize();
            int worldY = row * gp.getTileSize();
            int screenX = worldX - gp.getPlayer().getX() + gp.getPlayer().getScreenX();
            int screenY = worldY - gp.getPlayer().getY() + gp.getPlayer().getScreenY();

            if(worldX + gp.getTileSize() > gp.getPlayer().getX() - gp.getPlayer().getScreenX() &&
                    worldX - gp.getTileSize() < gp.getPlayer().getX() + gp.getPlayer().getScreenX() &&
                    worldY + gp.getTileSize() > gp.getPlayer().getY() - gp.getPlayer().getScreenY() &&
                    worldY - gp.getTileSize() < gp.getPlayer().getY() + gp.getPlayer().getScreenY()){
                g2.drawImage(tiles.getImg(tileN), screenX, screenY, gp.getTileSize(), gp.getTileSize(), null);
            }
            col++;

            if (col == gp.getMaxWorldX()){
                col=0;
                row++;

            }
        }
    }
}
