package Game;

public class WrongLengthPermutationException extends WrongInputException {
    private int length=-1;
    private String message="wrong length of permutation";
    public WrongLengthPermutationException(int _length){
        if(_length<0)
            throw new ArithmeticException("Negative length");
        length=_length;
    }
    public WrongLengthPermutationException(int _length, String usersMessage){
        if(length<0)
            throw new ArithmeticException("Negative length");
        length=_length;
        message=usersMessage;
    }
    public WrongLengthPermutationException(){
        super();
    }
    @Override
    public String toString(){
        if(length!=-1)
            return length+" is a "+message;
        return message;
    }
}
