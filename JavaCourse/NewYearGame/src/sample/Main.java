package sample;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.scene.media.Media;

import java.awt.*;
import java.io.File;

public class Main extends Application {
    static Dimension sSize = Toolkit.getDefaultToolkit ().getScreenSize ();
    private static final Child[] children = new Child[5];
    static final int TILE_SIZE = 50;
    static final int W = sSize.width;
    static final int H = sSize.height-50;

    static private Image Y_emoji = new Image("Y_emoji.png");
    static private Image Z_emoji = new Image("Z_emoji.png");
    static private Image O_emoji = new Image("O_emoji.png");
    static private Image C_emoji = new Image("C_emoji.png");
    static private Image S_emoji = new Image("S_emoji.png");
    static private Image H_emoji = new Image("H_emoji.png");
    static private Image P_emoji = new Image("P_emoji.png");

    static int childrenHappy = 0;

    static boolean timerOn = false;
    static private long timerTime = 0;
    private MediaPlayer mediaPlayer1 = new MediaPlayer( new Media(new File("JingleBells.mp3").toURI().toString()));
    private MediaPlayer mediaPlayer2 = new MediaPlayer( new Media(new File("HoHoHo.mp3").toURI().toString()));
    private MediaPlayer mediaPlayer3 = new MediaPlayer( new Media(new File("GameOver.mp3").toURI().toString()));

    static final int X_TILES = W / TILE_SIZE;
    static final int Y_TILES = H / TILE_SIZE;

    private Grid grid = new Grid();

    static boolean gameOver = false;

    class Grid{
        Tile[][] grid;

        Grid(){
            grid = new Tile[X_TILES][Y_TILES];
        }
        void setTileInGrid(int x, int y, Tile tile) {
            grid[x][y] = tile;
        }
        synchronized boolean checkIfStateEmptyAndSet(int oldX, int oldY, int x, int y, String state, boolean dropPresent) {
            if(grid[x][y].getState().equals("") || grid[x][y].getState().equals("P")) {
                grid[x][y].setState(state);
                if(dropPresent)
                    grid[oldX][oldY].setState("P");
                else
                grid[oldX][oldY].setState("");
                return true;
            }
            return false;
        }
        synchronized void setForceState(int x, int y, String state) {
            grid[x][y].setState(state);
        }
        String getStateAt(int x, int y){
            return grid[x][y].getState();
        }
    }

    private void createContent() {
        int childrenLeft = children.length;
        int iteration = 1;
        fillArr: while(childrenLeft>0) {
            for (int x = 0; x < X_TILES; x++)
                for (int y = 0; y < Y_TILES; y++){
                    if (childrenLeft == 0 && iteration>1)
                        break fillArr;
                    Tile tile;
                    if (x == 5 && y == 5)
                        tile = new Tile(x, y, "S");
                    else if (!(x >= 4 && x <= 6 && y >= 4 && y <= 6) && Math.random() <= 0.07 && childrenLeft > 0) {
                        tile = new Tile(x, y, "C");
                        childrenLeft--;
                    } else
                        tile = new Tile(x, y, "");
                    grid.setTileInGrid(x, y, tile);
                }
        iteration++;
        }
    }

    class Tile {
        private int x, y;
        private String state = "";

        public Tile(int x, int y, String state) {
            this.x = x;
            this.y = y;
            this.state = state;
        }


        public String getState(){
            return state;
        }

        synchronized public void setState(String state){
            switch (state) {
                case (""):
                    this.state = "";
                    break;
                case ("S"):
                    this.state = "S";
                    break;
                case ("C"):
                    this.state = "C";
                    break;
                case ("Z"):
                    this.state = "Z";
                    break;
                case ("Y"):
                    this.state = "Y";
                    break;
                case ("O"):
                    this.state = "O";
                    break;
                case ("P"):
                    this.state = "P";
                    break;
                case ("H"):
                    this.state = "H";
                    break;
            }
        }
    }

    public void start(Stage primaryStage) {
        try {
            VBox root = new VBox();
            Canvas c = new Canvas(W, H);
            GraphicsContext gc = c.getGraphicsContext2D();
            root.getChildren().add(c);
            Santa santa = new Santa(grid);

            new AnimationTimer() {
                long lastTick = 0;

                public void handle(long now) {
                    if (lastTick == 0) {
                        lastTick = now;
                        tick(gc);
                        return;
                    }

                    if (now - lastTick > 10000000) {
                        lastTick = now;
                        tick(gc);
                    }
                }

            }.start();

            Scene scene = new Scene(root, W, H);

            scene.addEventFilter(KeyEvent.KEY_PRESSED, key -> {
                if(!santa.getSantaBusy()) {
                    if (key.getCode() == KeyCode.W) {
                        new Thread(() -> santa.tryToGo(0, -1)).start();
                    }
                    if (key.getCode() == KeyCode.A) {
                        new Thread(() -> santa.tryToGo(-1, 0)).start();
                    }
                    if (key.getCode() == KeyCode.S) {
                        new Thread(() -> santa.tryToGo(0, 1)).start();
                    }
                    if (key.getCode() == KeyCode.D) {
                        new Thread(() -> santa.tryToGo(1, 0)).start();
                    }
                    if (key.getCode() == KeyCode.P) {
                        new Thread(santa::dropPresent).start();
                    }
                    if (key.getCode() == KeyCode.R) {
                        restart(primaryStage);
                    }
                }
            });

            createContent();
            int childrenToAdd = 0;
            for (int x = 0; x < X_TILES; x++)
                for (int y = 0; y < Y_TILES; y++)
                    if(grid.getStateAt(x, y).equals("C")){
                        children[childrenToAdd] = new Child(x, y, grid);
                        childrenToAdd++;
                    }

            primaryStage.setScene(scene);
            primaryStage.setTitle("NEW YEAR GAME");
            primaryStage.show();
            primaryStage.setOnCloseRequest(event -> {
                gameOver=true;
            });
            mediaPlayer1.play();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void tick(GraphicsContext gc) {
        if (gameOver) {
            mediaPlayer1.pause();
            if(childrenHappy == children.length){
                mediaPlayer2.play();
                gc.setFill(Color.YELLOW);
                gc.setFont(new Font("", 50));
                gc.fillText("YOU WIN", W/3, H/2);
                return;
            }
            mediaPlayer3.play();
            gc.setFill(Color.RED);
            gc.setFont(new Font("", 50));
            gc.fillText("GAME OVER", W/3, H/2);
            return;
        }

        gc.setFill(Color.GREEN);
        gc.fillRect(0, 0, W, H);

        for (int x = 0; x < X_TILES; x++)
            for (int y = 0; y < Y_TILES; y++){
                gc.setFill(Color.LIGHTGREEN);
                gc.fillRect(x * TILE_SIZE, y * TILE_SIZE, TILE_SIZE-1, TILE_SIZE-1);
                gc.setFill(Color.GREEN);
                gc.fillRect(x * TILE_SIZE, y * TILE_SIZE, TILE_SIZE-2, TILE_SIZE-2);
                switch (grid.getStateAt(x,y)) {
                    case ("Y"):
                        gc.drawImage(Y_emoji, x * TILE_SIZE+2, y * TILE_SIZE+2);
                        break;
                    case ("Z"):
                        gc.drawImage(Z_emoji, x * TILE_SIZE+2, y * TILE_SIZE+2);
                        break;
                    case ("O"):
                        gc.drawImage(O_emoji, x * TILE_SIZE+2, y * TILE_SIZE+2);
                        break;
                    case ("S"):
                        gc.drawImage(S_emoji, x * TILE_SIZE+2, y * TILE_SIZE+2);
                        break;
                    case ("C"):
                        gc.drawImage(C_emoji, x * TILE_SIZE+2, y * TILE_SIZE+2);
                        break;
                    case ("H"):
                        gc.drawImage(H_emoji, x * TILE_SIZE+2, y * TILE_SIZE+2);
                        break;
                    case ("P"):
                        gc.drawImage(P_emoji, x * TILE_SIZE+2, y * TILE_SIZE+2);
                        break;
                }
        }
        gc.setFill(Color.WHITE);
        gc.setFont(new Font("", 30));
        gc.fillText("Score: " + childrenHappy, 10, 30);
        if(timerOn){
            if(System.currentTimeMillis()>timerTime)
                timerOn=false;
            else{
                gc.setFill(Color.WHITE);
                gc.setFont(new Font("", 30));
                gc.fillText("Present will drop in: " + (timerTime - System.currentTimeMillis())/1000, 600, 30);
            }
        }
        if(childrenHappy == children.length){
            gameOver=true;
        }
    }
    static void timerRun(int millSec){
        timerOn = true;
        timerTime=System.currentTimeMillis()+millSec;
    }

    void restart(Stage stage){
        for(Child c : children)
            c.setActive(false);
        childrenHappy = 0;

        timerOn = false;
        timerTime = 0;
        grid = new Grid();
        gameOver = false;
        this.start(stage);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
