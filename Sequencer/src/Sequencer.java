import java.util.Scanner;

/**
 * Created by jaall on 2/12/2017.
 */


public class Sequencer {

    public static void main(String[] args){

        Scanner s = new Scanner(System.in);
        String dna = "";


        System.out.println("Input lowercase DNA fragments one line at a time. End with a blank line.");

        while(s.hasNextLine()==true){ //while there is a new variable being added

            String line = s.nextLine();//takes in the users input
            line = line.trim().toLowerCase();
            for(int i = 0; i<line.length(); i++){
                if (!((line.charAt(i) == ('a')) ||(line.charAt(i) == ('t'))||(line.charAt(i) == ('c'))||(line.charAt(i) == ('g')))){//checks for no atcg
                    System.out.print("DNA is invalid");
                    return;
                }
                else{
                    if(i == line.length()-1){
                        line = line.trim().toLowerCase();//makes it lowercase and trimmed
                    }

                }
            }//changes dna to a lower case string

            if (line.equals("")) { //reads if the line is blank and breaks if it is, else continue to merging
                break; //will return to the conditional statement and say "Hey not more new lines so stop"
            }
            else{ //this is where the merge code is

                if(dna.equals("")){//sets dna in as line if it is empty
                    dna=line;
                }
                else{//merges two different strands together
                    for(int i =0; i<dna.length(); i++){//counts through dna string's legnth
                        if(line.startsWith((dna.substring(i)))){//see if lines starts with anything that begins in dna string
                            dna = dna.substring(0,i) + line;//if so, combines the substrings of dna and line together
                            System.out.println("Input DNA: " + dna);
                            break;
                        }
                    }
                }
            }
        }
        System.out.println("Input DNA: "+dna);//prints the final dna strand

        //==========================================================Part 2=======================================================
      String gene="";
      int startCodon = dna.indexOf("atg");
     // System.out.println(startCodon);


        if(dna == ""){System.out.println("DNA does not contain a gene start codon"); return;}//

        if(dna.contains("atg")){ //checks if it contains atg
            for (int j = 0; j<dna.length()-2; j++){
                if((dna.substring(j,j+3).equals("tag")) || (dna.substring(j,j+3).equals("taa")) || (dna.substring(j,j+3).equals("tga"))){
                    if((j-startCodon)%3 == 0){//sees if it is 3 multiples
                        System.out.println("Start codon position: " + startCodon );
                        System.out.println("End codon position: " + j);
                        System.out.println("Gene: " + dna.substring(startCodon,j));
                        System.out.println("");
                        gene = (dna.substring(startCodon,j));
                        break;
                    }
                    else{
                        if(j==dna.length()-3){
                            System.out.println("DNA does not contain a gene end codon");
                            return;

                        }
                    }
                }
                else{
                    if( j ==dna.length()-3){
                        System.out.println("DNA does not contain a gene end codon");//doesnt contain any 3 conditions
                        return;

                    }



                }
            }
        }
        else
        {
            System.out.println("DNA does not contain a gene start codon"); //fails start codon
            return;
        }

        /*String gene = "";
        if(dna == ""){System.out.println("DNA does not contain a gene start codon"); return;}

        for(int i = 0; i<dna.length()-2;i++){//goes through dna
            if (!gene.equals("")){break;}
            if (dna.substring(i,i+3).equals("atg")){//finds out where the proteins start

                for(int j = dna.length()-3;j>=3;j-- ){//iterates ending position
                    if((dna.substring(j,j+3).equals("tag")) || (dna.substring(j,j+3).equals("taa")) || (dna.substring(j,j+3).equals("tga")) ){//finds out if ending letters match
                        if((j-i)%3 == 0){//sees if it is 3 multiples
                            System.out.println("Start codon position: " + i );
                            System.out.println("End codon position: " + j);
                            System.out.println("Gene: " + dna.substring(i,j));
                            System.out.println("");
                            gene = (dna.substring(i,j));
                            break;
                        }
                        else{
                            if(j == 4){
                                System.out.println("DNA does not contain a gene end codon");//breaks if not mulitples of 3
                                return;
                            }
                        }
                    }
                    else{
                        if(j==3){
                            System.out.println("DNA does not contain a gene end codon");//breaks if the 3 conditions dont exists
                            return;
                        }
                    }
                }
            }
            else if (i==dna.length()-3){//tests for failed start codon
                System.out.println("DNA does not contain a gene start codon");
                return;
            }
        }
*/
        //===========================================================Part 3============================================================
        String eyes = "";
        String hairColor = "";
        String canRollTongue= "";

        if(dna.length()>=30){//checks length
            System.out.println("Analysis Results");
            System.out.println("");



            switch (gene.charAt(20)) {//determines eye color
                case 'a':
                    eyes = "blue";
                    break;
                case 't':
                    eyes = "green";
                    break;
                case 'c':
                    eyes = "brown";
                    break;
                case 'g':
                    eyes = "brown";
                    break;

            }
            System.out.println("Eye color: "+eyes);

            switch (gene.charAt(18)) {//determines haircolor
                case 'a':
                    hairColor = "black";
                    break;
                case 't':
                    hairColor = "blond";
                    break;
                case 'c':
                    hairColor = "brown";
                    break;
                case 'g':
                    hairColor = "red";
                    break;

            }
            System.out.println("Hair Color: "+hairColor);

            switch (gene.charAt(8)) {//determines rolling tongue
                case 'a':
                    canRollTongue = "yes";
                    break;
                case 't':
                    canRollTongue = "no";
                    break;
                case 'c':
                    canRollTongue = "no";
                    break;
                case 'g':
                    canRollTongue = "no";
                    break;

            }
            System.out.println("Can roll tongue? "+canRollTongue);



        }
        else{
            System.out.println("The gene is not long enough to continue.");
            return;
        }
    }
}




