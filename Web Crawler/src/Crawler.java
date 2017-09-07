import org.jsoup.select.Elements;
import org.jsoup.nodes.Document;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

//import javax.swing.text.Document;
//import javax.swing.text.Element;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by jaall on 4/2/2017.
 */
public class Crawler implements Serializable{

    public static int currentID;
    public static String domain;
    public static int limit;
    public static List<Page> parsed;
    public static Parser parser;
    public MyQueue toParse;
    public static int totalURLs;
    public static List<String> visited;
    public static List<Word> words;


    public Crawler(String seed, String domain, int limit){
        currentID=0;
        totalURLs=0;
        toParse= new MyQueue();
        parser= new Parser();
        this.domain=domain;
        this.limit=limit;
        this.words = new ArrayList<>();
        this.visited =new ArrayList<>();
        this.parsed = new ArrayList<>();
        addToQueue(seed);
        totalURLs--;

    }

    public void crawl() {

        while(!toParse.isEmpty() && currentID<limit){


            String pageUrl= toParse.remove().getData().toString();
            try {
                Document doc = parser.getDocument(pageUrl);
                if (doc == null){
                    System.out.println("AAAAAAAAAAAH");

                }
                if(parse(doc,currentID)==true){
                    Page p = new Page(pageUrl,currentID);
                    System.out.println(currentID);
                    addPageToList(p);
                    currentID++;
                }
            } catch (ParseException e) {

            }
            //visited.add(pageUrl);
        }
    }

    public boolean parse(Document doc, int id){
        try {
            parseLinks(doc);
            parseText(doc,id);
        } catch (ParseException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public void parseLinks(Document doc) throws ParseException{
        //gets the links on a page and stores it to Elements
        Elements links=parser.getLinks(doc);


        for(Element link : links) {
            if (isValidURL(link.attr("abs:href")) && isInDomain(link.attr("abs:href"))) {
                addToQueue(link.attr("abs:href"));
            }
        }
    }

    public void parseText(Document doc,int id)  {
        String rawSource = null;
        try {
            rawSource = parser.getBody(doc);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        if(rawSource==null){
            return;
        }
        String[] spiltSource = rawSource.split(" ");


        for(String s: spiltSource){
            for(Word w: words ){
                if(w.getWord().equals(s)){
                    w.addURLID(id);
                }
            }
            addWordToList(s,id);
        }
    }

    public void addWordToList(String word, int id){
        if(word==null || word.equals("")){
            return;
        }

        Word w=new Word(word.toLowerCase(),id);

        if(words.contains(w)){
            w.addURLID(id);
            return;
        }
        words.add(w);
    }

    public void addToQueue(String url){ //adds the url to queue to get parsed

        if(url != null && !url.equals("")){
            visited.add(url);
            toParse.add(url);
            totalURLs++;
        }
    }

    public void addPageToList(Page p){//adds a page to the parsed page list
        parsed.add(p);
    }

    public boolean isInDomain(String url){ //checks if the URL is in the domain


        if(url.contains(this.domain)){
            return true;
        }
        else{
            return false;
        }

    }

    public boolean isValidURL(String url){  //checks if the url is valid


        if(url.startsWith("https://") || url.startsWith("http://")){
            return true;
        }
        else{
            return false;
        }
    }

}

