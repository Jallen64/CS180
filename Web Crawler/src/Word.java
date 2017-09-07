import java.io.InterruptedIOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Word implements Serializable{

   private String word;
   private List<Integer> postings=new ArrayList<Integer>();
   final static long serialVersionUID=-3696191086353573895L;


    public Word(String word, int urlID) {
        this.word=word;
        this.postings.add(urlID);



    }

    public void addURLID(int urlID){


        postings.add(urlID);

    }



    @Override
    public boolean equals(Object obj){
        Word b = (Word) obj;

        if(this.getWord().equals( b.getWord())){
            return true;
        }
        else{
            return false;
        }


    }

    public List<Integer> getList(){

        return postings;

    }

    public String getWord(){
        return word;

    }

    public static void main(String[] args){
        Word w= new Word("Memes",4);


    }
}