package Game;

public class WrongInputException extends Exception{
    public WrongInputException(){
        super();
    }
    @Override
    public String toString(){
        return "Wrong Input";
    }
}
