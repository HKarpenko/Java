package Game;

public class NotPermutationException extends WrongInputException {
    private String message="command should be a permutation or 'exit'";
    private String usersName="";
    public NotPermutationException(String name){
        usersName=name;
    }
    public NotPermutationException(String name, String usersMessage){
        usersName=name;
        message=usersMessage;
    }
    public NotPermutationException(){
        super();
    }
    @Override
    public String toString(){
        if(!usersName.equals(""))
            return usersName+", "+message;
        return message;
    }
}
