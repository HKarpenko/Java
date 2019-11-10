/**
 * GameMain służy do prezentacji działania Gry
 * @author Heorhii Karpenko
 * @version 1.0*/
package Game;

public class GameMain {
    public static void main(String[] args) throws WrongInputException {
        GameLoop gl = new GameLoop();
        gl.startGame();
    }
}
