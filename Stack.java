class Node1 {
    UndoAction ac;
    Node1 next;

    Node1 (UndoAction ac){
        this.ac = ac;
        next=null;
    }
}
 class UndoAction {

    boolean type;

    Course course;

    public UndoAction(boolean type, Course course) {
        this.type = type;
        this.course = course;
    }
}
 class Linked_list {

     Node1 head;
     int size;
     Node1 tail;

     Linked_list() {
         head = null;
         size = 0;
         tail = null;
     }

     public void insertFirst(UndoAction ac) {
         Node1 new_node = new Node1(ac);
         if (isEmpty()) {
             head = new_node;
             tail = head;
             size++;
         } else {
             new_node.next = head;

             head = new_node;
             size++;
         }
     }

     public void deleteFirst() {
         if (!isEmpty()) {
             if (head == tail) {
                 head = null;
                 tail = null;

             } else {
                 Node1 temp = head;
                 head = temp.next;
                 temp.next = null;


             }
             size--;
         }
     }

     public Boolean isEmpty() {
         if (head == null) {

             return true;
         }
         return false;
     }

     public int size() {
         return size;
     }
 }
     class Stack {
public Linked_list stack = new Linked_list();
        public void push (UndoAction ac){
            stack.insertFirst(ac);
        }

        public UndoAction pop (){
            if (stack.isEmpty()) {
                return null;
            }
            UndoAction topAction = stack.head.ac;
            stack.deleteFirst();
            return topAction;
        }
    }
