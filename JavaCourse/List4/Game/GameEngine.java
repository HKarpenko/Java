package Game;

import java.util.HashSet;
import java.util.PriorityQueue;

public class GameEngine {
    private PriorityQueue<String> history;
    private int round;
    private int difficulty;
    private int attempts;
    private String actualPerm;
    public void newRound(){
        if(actualPerm==null){
            round=1;
            difficulty=3;
            attempts= fact(difficulty);
            actualPerm = genPerm(difficulty);
            history = new PriorityQueue<String>();
            return;
        }
        round++;
        if(difficulty<9)
            difficulty+=round%2;
        attempts= fact(difficulty);
        actualPerm = genPerm(difficulty);
    }
    public String messageOfPermChecking(String usersPerm) throws WrongLengthPermutationException, InterruptedException {
        history.add(usersPerm);
        if(usersPerm.length()!=difficulty)
            throw new WrongLengthPermutationException(usersPerm.length());
        if(actualPerm.equals(usersPerm))
            return "Success";
        attempts--;
        return getHint(actualPerm,usersPerm);
    }
    private static String getHint(String actualPerm,String usersPerm){
        HashSet<String> mists = generateMistakesSet(actualPerm, usersPerm);
        if(mists.isEmpty())
            return null;
        int randIndex = (int)(Math.random()*(mists.size()));
        return (String)mists.toArray()[randIndex];
    }
    private static HashSet<String> generateMistakesSet(String actualPerm,String usersPerm){
        HashSet<String> mists = new HashSet<String>();
        for(int i=0;i<actualPerm.length();i++)
            if(actualPerm.charAt(i)!=usersPerm.charAt(i)){
                char wrongPos = usersPerm.charAt(i);
                if(i < actualPerm.indexOf(wrongPos))
                    mists.add(wrongPos+" should be to the right");
                else
                    mists.add(wrongPos+" should be to the left");
            }
        return mists;
    }
    public int getRound(){
        return round;
    }
    public int getDifficulty(){
        return difficulty;
    }
    public int getAttempts(){
        return attempts;
    }
    private static String genPerm(int n){
        String result="";
        HashSet<Integer> natsToN = new HashSet<Integer>();
        for(int i=1;i<=n;i++)
            natsToN.add(i);
        for(int i=0;i<n;i++){
            int randIndex = (int)(Math.random()*(n-i));
            int num = (int)natsToN.toArray()[randIndex];
            result+=num;
            natsToN.remove(num);
        }
        return result;
    }
    private static int fact(int num){
        int res=1;
        for(int i=2;i<=num;i++)
            res*=i;
        return res;
    }
    public static boolean isPermOfNatsToN(String perm){
        HashSet<Integer> natsToN = new HashSet<Integer>();
        for(int i=1;i<=perm.length();i++)
            natsToN.add(i);
        for(int i=0;i<perm.length();i++)
            if(!natsToN.remove(Integer.parseInt(""+perm.charAt(i))))
                return false;
        return true;
    }
    public void getHistory(){
        System.out.println("Users history:");
        for(String el : history)
            System.out.println(el);
    }
}
