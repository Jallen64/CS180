
import java.util.ArrayList;
import java.util.List;

/**
 * Created by jaall on 4/9/2017.
 */
public class SearchThread implements Runnable{
    public int start;
    public int finish;
    public String[] terms;


    public SearchThread(int start, int finish, String[] terms){
        this.start=start;
        this.finish=finish;
        this.terms=terms;
    }

    @Override
    public void run() {

        for(String s:terms){   // Iterate over all terms

            if(findTerm(s)!=null){

                Word w=findTerm(s);// Attempt to lookup term on indices (start,end) of wordList


                for(Integer id:w.getList()){ // If word found, go through its posting list
                    Result r = new Result(Search.pageList.get(id).getURL(),id);//create result object to manipulate result

                    // If URL ID exists, update score otherwise add a new Result
                    if(Search.resultSet.contains(r)){ //  if resultSet contains id
                        Search.resultSet.get(Search.resultSet.indexOf(r)).updateScore(r.score); //update Result score for Result with id
                    }
                    else{
                        Search.resultSet.add(r); //add new Result to result list
                    }
                }
            }
        }
    }

    public Word findTerm(String term){
        for(Word w:Search.wordList.subList(start,finish)){
            if(w.getWord().equals(term)){
                return w;
            }
        }
        return null;
    }


}
