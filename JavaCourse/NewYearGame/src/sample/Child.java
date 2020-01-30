package sample;

import java.util.Random;

public class Child implements Runnable {
    int x, y;
    private Main.Grid grid;
    Thread t;
    private Random rand;
    private int endurance;
    private boolean presentFound;
    private boolean active=true;

    Child(int cordX, int cordY, Main.Grid grid) {
        x = cordX;
        y = cordY;
        this.grid = grid;
        rand = new Random(System.currentTimeMillis());
        endurance = rand.nextInt(7000)+3000;
        presentFound = false;
        t = new Thread(this);
        t.start();
    }

    @Override
    public void run() {
        long timer = System.currentTimeMillis();
        while (!Main.gameOver && !presentFound && active) {
            if(System.currentTimeMillis() - timer > endurance){
                int sleepTime = rand.nextInt(7000)+3000;
                try {
                    grid.setForceState(x, y, "Z");
                    Thread.sleep(sleepTime);
                    grid.setForceState(x, y, "Y");
                    Thread.sleep(rand.nextInt(500)+700);
                    grid.setForceState(x, y, "C");
                    Thread.sleep(50);
                }catch (InterruptedException e){
                    System.out.println(e.getMessage());
                }
                timer = System.currentTimeMillis();
                endurance = sleepTime;
            }
            if(searchForPresent())
                continue;
            if (!searchForSanta()) {
                chooseDirect(rand.nextInt(), rand.nextInt());
                try {
                    Thread.sleep(50);
                    grid.setForceState(x, y, "Y");
                    Thread.sleep(rand.nextInt(2000)+500);
                    grid.setForceState(x, y, "C");
                    Thread.sleep(50);
                }catch (InterruptedException e){
                    System.out.println(e.getMessage());
                }
                endurance+=1000;
            }
        }
    }

    private boolean searchForPresent() {
        int[][] positions = {{-1,0},{1,0},{0,-1},{0,1}};
        for (int[] position : positions){
                int observedX = x + position[0];
                int observedY = y + position[1];
                if (observedX < 0)
                    observedX = Main.X_TILES - observedX;
                if (observedY < 0)
                    observedY = Main.Y_TILES - observedY;
                if (observedX >= Main.X_TILES)
                    observedX -= Main.X_TILES;
                if (observedY >= Main.Y_TILES)
                    observedY -= Main.Y_TILES;
                if (grid.getStateAt(observedX, observedY).equals("P"))
                    if (grid.checkIfStateEmptyAndSet(x, y, observedX, observedY, "H", false)) {
                        x = observedX;
                        y = observedY;
                        presentFound = true;
                        Main.childrenHappy++;
                        return true;
                    }
            }
        return false;
    }
    private boolean searchForSanta() {
        for (int i = -4; i <= 4; i++)
            for (int j = -4; j <= 4; j++) {
                int observedX = x + i;
                int observedY = y + j;
                if (observedX < 0)
                    observedX = Main.X_TILES - observedX;
                if (observedY < 0)
                    observedY = Main.Y_TILES - observedY;
                if (observedX >= Main.X_TILES)
                    observedX -= Main.X_TILES;
                if (observedY >= Main.Y_TILES)
                    observedY -= Main.Y_TILES;
                if (grid.getStateAt(observedX, observedY).equals("S")) {
                    if (Math.abs(observedX - x) == 1 && Math.abs(observedY - y) == 0 ||
                            Math.abs(observedX - x) == 0 && Math.abs(observedY - y) == 1) {
                        grid.setForceState(x, y, "O");
                        try {
                            Thread.sleep(100);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        Main.gameOver = true;
                        return true;
                    }
                    chooseDirect(observedX, observedY);
                    return true;
                }
            }
        return false;
    }

    private void chooseDirect(int destinationX, int destinationY) {
        if (Math.abs(destinationX - x) >= Math.abs(destinationY - y))
            tryToGo(destinationX - x > 0 ? 1 : -1, 0);
        else if (Math.abs(destinationX - x) == Math.abs(destinationY - y)) {
            if (Math.random() <= 0.5)
                tryToGo(0, destinationY - y > 0 ? 1 : -1);
            else
                tryToGo(destinationX - x > 0 ? 1 : -1, 0);
        } else
            tryToGo(0, destinationY - y > 0 ? 1 : -1);
    }

    public void tryToGo(int dx, int dy) {
        int newX = x + dx;
        int newY = y + dy;
        if (newX < 0)
            newX = Main.X_TILES - 1;
        if (newY < 0)
            newY = Main.Y_TILES - 1;
        if (newX >= Main.X_TILES)
            newX = 0;
        if (newY >= Main.Y_TILES)
            newY = 0;
        if (grid.getStateAt(newX, newY).equals("")) {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                System.out.println("Child sleep interrupted");
            }
        }
        if (grid.checkIfStateEmptyAndSet(x, y, newX, newY, "C",false)) {
            x = newX;
            y = newY;
        }
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
