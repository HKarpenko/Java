package Game;

public class NoNumbersContainsException extends WrongInputException{
    private String message="command should have only numbers or 'exit'";
    private String usersName="";
    public NoNumbersContainsException(String name){
        usersName=name;
    }
    public NoNumbersContainsException(String name, String usersMessage){
        usersName=name;
        message=usersMessage;
    }
    public NoNumbersContainsException(){
        super();
    }
    @Override
    public String toString(){
        if(!usersName.equals(""))
            return usersName+", "+message;
        return message;
    }
}
