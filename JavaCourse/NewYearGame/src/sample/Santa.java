package sample;

public class Santa{
    private int x, y;
    private Main.Grid grid;
    private boolean santaBusy = false;
    private boolean presentDroped = false;

    public Santa(Main.Grid grid) {
        x = y = 5;
        this.grid = grid;
    }

    public void dropPresent(){
        if(!searchForChild())
            return;
        Main.timerRun(3000);
        long timer = System.currentTimeMillis();
        while(System.currentTimeMillis() - timer < 3000){
           if(santaBusy){
               Main.timerOn = false;
               return;
           }
        }
        presentDroped = true;
    }

    public void tryToGo(int dx, int dy){
        santaBusy=true;
        int newX = x+dx;
        int newY = y+dy;
        if(newX < 0)
            newX = Main.X_TILES-1;
        if(newY < 0)
            newY = Main.Y_TILES-1;
        if(newX == Main.X_TILES)
            newX = 0;
        if(newY == Main.Y_TILES)
            newY = 0;
        if(grid.getStateAt(newX, newY).equals("")) {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                santaBusy=false;
                System.out.println("Santa sleep interrupted");
            }
        }
        if(!searchForChild())
            presentDroped=false;
        if (grid.checkIfStateEmptyAndSet(x, y, newX, newY, "S", presentDroped)) {
            if(presentDroped)
                presentDroped=false;
            x = newX;
            y = newY;
        }
        santaBusy=false;
    }
    public boolean getSantaBusy() {
        return santaBusy;
    }

    private boolean searchForChild() {
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
                if (grid.getStateAt(observedX, observedY).equals("Z") || grid.getStateAt(observedX, observedY).equals("Y")){
                    return true;
                }
            }
        return false;
    }

}
