import java.util.List;
import java.io.*;

public class FileUtils extends Object implements Serializable {
    FileUtils(){}
    public List<Page> getPageList(String filePath) {

        //check edge cases
        if(filePath==null || filePath.equals("")){
            return null;
        }

        //declare fos and ois with try catches to return null
        FileInputStream fis;
        try {
            fis = new FileInputStream(new File(filePath));
        } catch (FileNotFoundException e) {
            return null;
        }
        ObjectInputStream ois;
        try {
            ois = new ObjectInputStream(fis);
        } catch (IOException e) {
            return null;
        }

        //declare pageList
        List<Page> pageList;

        //creates a reader that will read the object from the stream
        Object reader = null;
        try {
            reader = ois.readObject();
        } catch (IOException e) {
            return null;
        } catch (ClassNotFoundException e) {
            return null;
        }

        //sets pageList to the reader object and type casts it
        pageList = (List<Page>) reader;

        return pageList;
    }
    public List<Word> getWordList(String filePath) {

        //check edge cases
        if(filePath==null || filePath.equals("")){
            return null;
        }

        //declare fos and ois with try catches to return null
        FileInputStream fis;
        try {
            fis = new FileInputStream(new File(filePath));
        } catch (FileNotFoundException e) {
            return null;
        }
        ObjectInputStream ois;
        try {
            ois = new ObjectInputStream(fis);
        } catch (IOException e) {
            return null;
        }

        //declare pageList
        List<Word> wordList;

        //creates a reader that will read the object from the stream
        Object reader = null;
        try {
            reader = ois.readObject();
        } catch (IOException e) {
            return null;
        } catch (ClassNotFoundException e) {
            return null;
        }

        //sets pageList to the reader object and type casts it
        wordList = (List<Word>) reader;

        return wordList;
    }
    public boolean saveWordTable(List<Word> wordTable, String filePath)  {
        FileOutputStream fos;
        if(filePath==null){
            return false;
        }
        else if(wordTable==null){
            return false;
        }
        else {
            try {
                fos = new FileOutputStream(new File(filePath));
            } catch (FileNotFoundException e) {

                return false;
            }
            ObjectOutputStream ous;
            try {
                ous = new ObjectOutputStream(fos);
            } catch (IOException e) {

                return false;
            }
            try {
                ous.writeObject(wordTable);
            } catch (IOException e) {

            }
            try {
                ous.close();
            } catch (IOException e) {

            }
            try {
                fos.close();
            } catch (IOException e) {

            }
            return true;
        }
    }
    public boolean savePageTable(List<Page> pageTable,String filePath) {
        FileOutputStream fos;
        if(filePath==null){
            return false;
        }
        else if(pageTable==null){
            return false;
        }
        else {
            try {
                fos = new FileOutputStream(new File(filePath));
            } catch (FileNotFoundException e) {

                return false;
            }
            ObjectOutputStream ous;
            try {
                ous = new ObjectOutputStream(fos);
            } catch (IOException e) {

                return false;
            }
            try {
                ous.writeObject(pageTable);
            } catch (IOException e) {

            }
            try {
                ous.close();
            } catch (IOException e) {

            }
            try {
                fos.close();
            } catch (IOException e) {

            }
            return true;
        }
    }
}

