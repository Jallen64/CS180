public class MyQueue {

    private int count;
    private Node head;

    public MyQueue(){

    }

    public void add(Object o){
        Node n = new Node(o);

        if(head==null) {
            head=n;
            count++;
        }
        else{

            Node temp = head;

            while(temp.getNext() != null) {
                temp=temp.getNext();
            }

            n.setPrev(temp);
            temp.setNext(n);

            count++;
        }
    }

    public Node remove(){

        if(count==0){
            return null;
        }

        if(count==1){
            Node temp=head;
            head=null;
            count--;
            return temp;
        }

        Node returnValue = head;

        head=head.getNext();
        head.setPrev(null);
        count--;

        return returnValue;
    }

    public Node peek(){

        return head;
    }

    public boolean isEmpty(){

        if(count==0){
            return true;
        }
        else{
            return false;
        }

    }

    public int size(){

        return count;
    }


}