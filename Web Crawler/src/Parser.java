import jdk.nashorn.internal.runtime.ParserException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

/**
 * Created by jaall on 3/31/2017.
 */
public class Parser {

    public Parser(){

    }

    public Document getDocument(String url) throws ParseException{

        /* Prepare a Jsoup Document object to hold the page */
        Document d = null;

        if(url==null)
        {
            throw new ParseException("getDocument() failed. String url is null.");
        }
        if(url.equals(""))
        {
            throw new ParseException("getDocument() failed. String url is empty.");
        }

        /* Attempt a connection, throws IOException so be
         * prepared to handle that.
        */
        try
        {
            /* Connect to URL and get the web page */
            d = Jsoup.connect(url).get();
            if(d==null)
            {
                throw new ParseException("getDocument() failed: Document is null." );
            }

        }
        catch(Exception e) {
            if(e.getMessage().equals("protocol = http host = null"))
            {
                throw new ParseException("getDocument() failed. Connection failed.");
            }
            else {
                throw new ParseException("Invalid url");
            }
        }

        /* Return the Document */
        return d;

    }

    public Elements getLinks(Document doc) throws ParseException{

        /* Null check */
        if(doc == null)
        {
            throw new ParseException("getLinks() failed. Document parameter is null.");
        }

        /* A Document is composed of Elements. In our case, we have a HTML
         * Document that is composed of tag Elements (e.g. <body>, <a href ..>).
         * To get the links on a web page, we need to select just that specific
         * type of tag, which is designated in HTML as <a href = " " >
         *
         * From the documentation, we see that the .select() method returns an
         * Elements object, which is an ArrayList composed of multiple Element objects.
         *
         * https://jsoup.org/apidocs/org/jsoup/select/Elements.html#select-java.lang.String-
         */
        Elements links = doc.select("a[href]");

//        for(Element link:links){
//            System.out.println(link.attr("abs:href"));
//        }

        return links;


        /* Elements in an ArrayList of Element objects, in our case we selected link Elements
         * and now have an ArrayList of all link Element object (e.g. one Element might be http://purdue.edu.
         * Now we can iterate over this list of Element objects.
         */
//        for(Element link : links)
//        {
//            /* Links in HTML are structured as <a href = "link" >. We want to access the contents
//             * of that 'href' piece for all of our link Element objects. Looking at the documentation
//             *  for Element, we can do that like below;
//             *
//             *  https://jsoup.org/apidocs/org/jsoup/nodes/Element.html#attr-java.lang.String-java.lang.String-
//             */
//            System.out.println(link.attr("abs:href"));
//        }

        /* In this project, you will need to do this to get and store the links on each page you visit.
         */
    }

    public String getBody(Document doc) throws ParseException{


        /* Null check */
        if(doc == null)
        {
            throw new ParseException("getBody() failed. Document parameter is null.");
        }

        /* A HTML document has one <body> tag, so we can store its contents using an Element
         * object just as we did for links above by using the Document classes .body() method.
         * the Document classes .body() method;
         */
        Element body = doc.body();

        /* To extract the raw text from inside the <body> .. </body> tags, we can use the Element
         * classes .text() method.
         * For example, given HTML <p>Hello <b>there</b> now! </p>, p.text() returns "Hello there now!"
         *
         * Documentation; https://jsoup.org/apidocs/org/jsoup/nodes/Element.html#text--
         */
        //System.out.println(body.text());
        if(body==null){
            System.out.println("Line 129: Parser");
            return null;
        }
        return body.text().toLowerCase();

        /* For your web crawler, you will need fetch the contents of a web pages body like above, but also
         * come up with a way to count and store words in order to query it later. More information about this
         * can be found in the handout.
         */
    }



}
