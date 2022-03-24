package Main;

import Entity.Player;
import Map.TileManager;

import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class GamePanel extends JPanel implements Runnable{
    private final int width = 1200;
    private final int height = 800;
    private final int tileSize = 16 * 5;
    private final int maxTilesX = width/tileSize;
    private final int maxTilesY = height/tileSize;

    private final int maxWorldX = calcSizeX("/Map/Maps/map1.txt");
    private final int maxWorldY = calcSizeY("/Map/Maps/map1.txt");
    private final int worldWidth = tileSize * maxWorldX;
    private final int worldHeight = tileSize * maxWorldY;

    private int maxFPS = 60;
    public int fps;

    private Thread gameThread;

    private KeyHandlerClass keyH = new KeyHandlerClass();
    GamePanel(){
        this.setPreferredSize(new Dimension(width, height));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);
    }

    public void startGameThread(){
        gameThread = new Thread(this);
        gameThread.start();
    }

    public int calcSizeX(String s){
        int size = 0;
        try {
            InputStream is = getClass().getResourceAsStream(s);
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            String line = br.readLine();
            String nums[] = line.split(" ");
            size = nums.length;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return size;
    }

    public int calcSizeY(String s){
        int size = 0;
        try {
            InputStream is = getClass().getResourceAsStream(s);
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            while (br.readLine() != null) size++;
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return size;
    }

    @Override
    public void run() {

        double drawInterval = 1000000000/maxFPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;
        long timer = 0;
        int drawCount = 0;

        while (gameThread != null){

            currentTime = System.nanoTime();
            delta += (currentTime - lastTime) / drawInterval;
            timer += (currentTime - lastTime);
            lastTime = currentTime;
            if (delta>=1){
                update();
                repaint();
                delta--;
                drawCount++;
            }
            if (timer >= 1000000000){
                fps = drawCount;
                drawCount = 0;
                timer = 0;
            }
        }
    }

    TileManager tm = new TileManager(this);
    Player player = new Player(this, keyH);

    public void update(){
        player.update();
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        tm.draw(g2);
        player.draw(g2);
        g2.dispose();
    }

    public int getMaxFPS() {
        return maxFPS;
    }
    public void setMaxFPS(int maxFPS) {
        this.maxFPS = maxFPS;
    }

    public void log(){
        System.out.println("Fps: " + fps);
    }

    public int getTileSize() {
        return tileSize;
    }
    public int getMaxTilesX() {
        return maxTilesX;
    }
    public int getMaxTilesY() {
        return maxTilesY;
    }
    @Override
    public int getWidth() {
        return width;
    }
    @Override
    public int getHeight() {
        return height;
    }
    public int getMaxWorldX() {
        return maxWorldX;
    }
    public int getMaxWorldY() {
        return maxWorldY;
    }
    public int getWorldWidth() {
        return worldWidth;
    }
    public int getWorldHeight() {
        return worldHeight;
    }
    public Player getPlayer() {
        return player;
    }
}
