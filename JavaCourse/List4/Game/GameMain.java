package Game;

public class GameMain {
    public static void main(String[] args){
        GameEngine ge = new GameEngine();
        GameInterface gi = new GameInterface();
        boolean gameOn=true;
        gi.askForName();
        while(gameOn){
            ge.newRound();
            gi.newRoundInfo(ge);
            while(true){
                String usersMess=gi.askForComm();
                if(usersMess.equals(""))
                    continue;
                if (usersMess.equals("exit")) {
                    gameOn = false;
                    break;
                }
                if(!usersMess.matches("[0-9]+"))
                    throw new RuntimeException(gi.getUsersName()+", command should have only numbers or 'exit'");
                if(!GameEngine.isPermOfNatsToN(usersMess))
                    throw new RuntimeException(gi.getUsersName()+", command should be a permutations of numbers 1 to "+ge.getDifficulty());
                String message = ge.messageOfPermChecking(usersMess);
                System.out.println(message);
                if(message=="Success")
                    break;
                gi.attemptsLeftInfo(ge);
                assert ge.getAttempts()>0 : gi.getUsersName()+", you Lose!";
            }
        }
    }
}
