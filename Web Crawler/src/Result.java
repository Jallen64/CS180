import java.io.Serializable;

public class Result implements Serializable, Comparable<Result>{
    String	url;
    int	urlID;
    public static final long serialVersionUID=-938761094876384658L;
    int score;
    Result(String url, int urlID){
        this.url=url;
        this.urlID=urlID;
        incrementScore();

    }
    public int getScore() {
        return this.score;
    }
    public int getURLID() {
        return this.urlID;
    }
    public String getURL() {
        return this.url;
    }
    public int compareTo(Result candidate) {
        if(candidate==null){
            return -1;
        }


        int a=0;
        if (this.score < candidate.score) {
            a= 1;
        }
        if (this.score == candidate.score) {
            a= 0;
        }
        if (this.score > candidate.score) {
            a= -1;
        }
        return a;
    }
    @Override
    public boolean equals(Object obj){
        boolean a=true;
        if (obj instanceof Result) {
            if(this.url.equals(((Result) obj).url)||this.urlID==((Result) obj).urlID){
                a= true;
            }
            else{
                a=false;
            }

        }
        return a;
    }

    public void incrementScore(){
        this.score++;
    }
    public void updateScore(int score){
        this.score+=score;
    }
}

