package Entity;

import Main.GamePanel;
import Main.KeyHandlerClass;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Player extends Entity implements Damageable{

    private int health;
    private GamePanel gp;
    private KeyHandlerClass keyH;
    private BufferedImage right, left;
    private String direction;

    private final int screenX;
    private final int screenY;

    public void setImgs(){
        try {
            right = ImageIO.read(getClass().getClassLoader().getResource("PlayerAssets/player1.png"));
            left = ImageIO.read(getClass().getClassLoader().getResource("PlayerAssets/player2.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Player(GamePanel gp, KeyHandlerClass keyH){
        screenX = gp.getWidth()/2;
        screenY = gp.getHeight()/2;
        this.gp = gp;
        this.keyH = keyH;
        setDefaultValues();
        setImgs();
    }

    Player(int x, int y, int speed, int health, GamePanel gp, KeyHandlerClass keyH){
        super(x, y, speed);
        screenX = gp.getWidth()/2;
        screenY = gp.getHeight()/2;
        this.health = health;
        this.gp = gp;
        this.keyH = keyH;
        setImgs();
    }

    public void setDefaultValues(){
        this.setX(100);
        this.setY(100);
        this.setSpeed(5);
        setHealth(100);
        direction = "right";
    }

    public void update(){
        if (keyH.up){
            move(0, -1);
        }
        if (keyH.down){
            move(0, 1);
        }
        if (keyH.left){
            move(-1, 0);
            direction = "left";
        }
        if (keyH.right){
            move(1, 0);
            direction = "right";
        }
    }
    public void draw(Graphics2D g2){

        BufferedImage img = null;
        switch (direction){
            case "left":
                img = left;
                break;
            case "right":
                img = right;
                break;
        }

        g2.drawImage(img, screenX-(gp.getTileSize()/2), screenY-gp.getTileSize(), gp.getTileSize(), gp.getTileSize(), null);
    }

    @Override
    public void damage(int amount) {
        health -= amount;
    }

    @Override
    public void setHealth(int health) {
        this.health = health;
    }

    @Override
    public int getHealth() {
        return health;
    }

    public int getScreenX() {
        return screenX;
    }

    public int getScreenY() {
        return screenY;
    }
}
