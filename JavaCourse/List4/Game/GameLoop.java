package Game;

public class GameLoop {
    private GameEngine ge;
    private GameInterface gi;
    public GameLoop(){
        ge = new GameEngine();
        gi = new GameInterface();
    }
    public void startGame() throws NoNumbersContainsException, WrongLengthPermutationException, NotPermutationException{
        boolean gameOn = true;
        gi.askForName();
        long startGame = System.currentTimeMillis();
        while (gameOn) {
            ge.newRound();
            gi.newRoundInfo(ge);
            long start = System.currentTimeMillis();
            while (true) {
                try {
                    String usersMess = gi.askForComm();
                    if (usersMess.equals(""))
                        continue;
                    if (usersMess.equals("exit")) {
                        gameOn = false;
                        break;
                    }
                    if (!usersMess.matches("[0-9]+"))
                        throw new NoNumbersContainsException(gi.getUsersName());
                    if (!GameEngine.isPermOfNatsToN(usersMess))
                        throw new NotPermutationException(gi.getUsersName());
                    String message = ge.messageOfPermChecking(usersMess);
                    System.out.println(message);
                    if (message == "Success")
                        break;
                    gi.attemptsLeftInfo(ge);
                    assert ge.getAttempts() > 0 : gi.getUsersName() + ", you Lose!";
                }catch(Exception exc){
                    System.out.println(exc);
                }
            }
            long finish = System.currentTimeMillis();
            gi.timeInfo("round",(finish - start)/1000.0);
        }
        long endGame = System.currentTimeMillis();
        gi.timeInfo("game",(endGame - startGame)/1000.0);
    }
}
