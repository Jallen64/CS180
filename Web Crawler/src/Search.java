import java.util.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Search extends Object  {
    static List<Page>	pageList;
    private String	pageListFile;
    static List<Result>	resultSet;
    static List<Word>	wordList;
    private String	wordListFile;
    public Search(String wordListFile, String pageListFile){
        this.wordListFile=wordListFile;
        this.pageListFile=pageListFile;
        //Says only 1 thread can access the resultSet at a time
        resultSet =  Collections.synchronizedList(new ArrayList<Result>());
        //Collections.sort(pageList);
        setUp(this.wordListFile, this.pageListFile);
    }
    public List<Result> executeQuery(String query) {

        //edge cases
        if(query == null || query.equals("")) {
            return null;
        }

        //edge case v.2
        nullCheck();

        //setUp the method



        String[] terms = query.toLowerCase().split(" "); // Split the query String into an array of terms
        int section = (wordList.size()/5); //creates a section number for your
        int begin = 0;//sets the begining point for your section
        int end = begin + section;//sets the end destination for your section

        Thread[] threads = new Thread[5];//creates your 5 thread bois that'll help you search through your wordList
        for (int i = 0; i < threads.length; i++) {//gotta access your thread bois somehow
            threads[i] = new Thread(new SearchThread(begin, end, terms));//equip your threads with a begining point, end point, and some sweet,sweet, terms
            begin = end;//after a thread uses it's begin, it passes it over the the next thread and starts where the last one ended, it's new end point is defined above

            //if wordlist not divisible by 5, this saves you from going over the arraylist length and getting el screwedo
            if (begin + section < wordList.size()) {
                end = begin + section;
            }
            else {
                end = wordList.size();
            }
        }
        for (Thread t : threads) { //starts threads
            t.start();
        }
        for (Thread t : threads) { //joins threads
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        sort(); //sorts threads
        System.out.println(resultSet.size());
        return resultSet; //returns the bank

    }


    public void nullCheck(){
        if(wordList==null||pageList==null){
            //setUp(wordListFile,pageListFile);
            setUp(this.wordListFile, this.pageListFile);
        }
    }
    public static void sort(){
        if(resultSet != null) {
            Collections.sort(resultSet);
        }
    }
    public void setUp(String wordListFile, String pageListFile){
        FileUtils fileUtils=new FileUtils();
        pageList=fileUtils.getPageList(pageListFile);
        wordList=fileUtils.getWordList(wordListFile);

        //resultSet=Collections.synchronizedList(new ArrayList<Result>());
        //Collections.sort(pageList);
    }
}
