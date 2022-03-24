package Map;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Tile {
    private BufferedImage[] img = new BufferedImage[9];

    Tile(){
        try {
            this.img[0] = ImageIO.read(getClass().getClassLoader().getResource("Map/MapTiles/bland_grass.png"));
            this.img[1] = ImageIO.read(getClass().getClassLoader().getResource("Map/MapTiles/grass.png"));
            this.img[2] = ImageIO.read(getClass().getClassLoader().getResource("Map/MapTiles/wet_grass_top.png"));
            this.img[3] = ImageIO.read(getClass().getClassLoader().getResource("Map/MapTiles/wet_grass_left.png"));
            this.img[4] = ImageIO.read(getClass().getClassLoader().getResource("Map/MapTiles/wet_grass_right.png"));
            this.img[5] = ImageIO.read(getClass().getClassLoader().getResource("Map/MapTiles/wet_grass_bottom.png"));
            this.img[6] = ImageIO.read(getClass().getClassLoader().getResource("Map/MapTiles/water.png"));
            this.img[7] = ImageIO.read(getClass().getClassLoader().getResource("Map/MapTiles/lilyflower.png"));
            this.img[8] = ImageIO.read(getClass().getClassLoader().getResource("Map/MapTiles/lilypad.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public BufferedImage getImg(int num) {
        return img[num];
    }

    public void setImg(BufferedImage img, int num) {
        this.img[num] = img;
    }
}
