import java.io.Serializable;

public class Page implements Serializable, Comparable<Page>{
   
   String url;
   private int urlID;
   final static long serialVersionUID=-1827677255104766839L;



   public Page(String url, int urlID) {
      this.url=url;
      this.urlID=urlID;


   }

   @Override
   public int compareTo(Page o) {

       if((this.urlID) < (o.urlID)){
           return -1;
       }
       else if ((this.urlID)>(this.urlID)){
           return 1;
       }
       else{
           return 0;
       }

   }


   @Override
   public boolean equals(Object obj){
           Page b = (Page) obj;



       if ((urlID)==(b.urlID)){
           return true;
       }
       else if((url.equals(b.url))){
           return true;
       }
       else{
           return false;
       }


   }

   public String getURL(){
      return url;

   }

   public int getURLID(){
      return urlID;
   }

   public static void main(String[] args){
       Page x = new Page("sadf",3);
       Page y = new Page("sadf",1);

       System.out.println(x.equals(y));

   }

}