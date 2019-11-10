package Game;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class GameInterface {
    private String usersName;
    public GameInterface(){
        usersName="";
    }
    public void timeInfo(String type, double time){
        System.out.println("Time of "+type+" is "+time+" sec");
    }
    public void newRoundInfo(GameEngine ge){
        System.out.println(usersName+" ROUND "+ge.getRound());
        System.out.println("Input "+ge.getDifficulty()+"-long permutation in one line, like '123'");
        System.out.println("Input 'exit' to finish game");
    }
    public void attemptsLeftInfo(GameEngine ge){
        System.out.println(usersName+", "+ge.getAttempts()+" attempts left");
    }
    public String askForComm(){
        BufferedReader br = new BufferedReader(
                new InputStreamReader(System.in));
        String usersComm="";
        try {
            usersComm = br.readLine();
        }catch (IOException exc){
            System.out.println("Input error");
        }
        return usersComm;
    }
    public String askForName(){
        while(usersName.equals("")){
            System.out.print("Input your name: ");
            BufferedReader br = new BufferedReader(
                    new InputStreamReader(System.in));
            try {
                usersName = br.readLine();
            } catch (IOException exc) {
                System.out.println("Input error");
            }
        }
        return usersName;
    }
    public String getUsersName() {
        return usersName;
    }
}
