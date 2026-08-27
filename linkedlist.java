import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

// Node class to make objects
class Node {
    Course course;
    Node next;
    Node prev;
    int val;
    //The Constructor
    Node (Course course){
        this.course=course;
        next = null;
        prev = null;
    }
}

// to make the linked list
public class linkedlist {
    // the main variables
private Node head;
private Node tail;
private int size;

//The Constructor

    linkedlist(){
        head= null;
        tail = null;
        size = 0 ;
    }

// add at first
// this for student's choice panel
// we have over load two classes named addCourse, but with different parameters
    // this one is used to add in the main panel to ensure it will not be empty when start
    public void addCourse (Course course  ){
        Node newnode = new Node(course);
        //first course to be added
        if (head == null){
            head = newnode;
            tail= newnode;
        }
        else {
            // any other course to be added
            newnode.next= head;
            head.prev = newnode;
            head=newnode;
        }

        size++;
    }
    // this is used any time after starting the program
    // it helps in refreshing the code because it calls StudentChoice function, because it has the enough parameters to do that
    public void addCourse (Course course, JPanel panel  ,JPanel mainPanel, Course[] allCourses, linkedlist studentSelection){
        Node newnode = new Node(course);
        //first course to be added
        if (head == null){
            head = newnode;
            tail= newnode;
        }
        else {
            newnode.next= head;
            head.prev = newnode;
            head=newnode;
        }
        // repaint the interface
        panel.revalidate();
        panel.repaint();
        size++;
        //This
        StudentChoice(panel , mainPanel , allCourses , studentSelection );

    }

// delete at position
// this for student's choice panel
    public void deleteCourse(int Id , JPanel panel , JPanel mainPanel, Course[] allCourses  , linkedlist studentSelection){

        if(size !=0){ // to ensure there is something to delete
            Node newnode = head;
            // to find which course wanted to delete
            for (int i =0 ; i < size ; i++){
                if (newnode.course.getId() == Id){

                    //if the course was at first
                    if (newnode == head) {
                        head = head.next;
                        if (head != null) {
                            head.prev = null;
                        }
                        //if there was only one course
                        else {
                            tail = null;
                        }
                    }
                    //if the course was at the end
                    else if (newnode == tail){
                        tail= newnode.prev;
                        tail.next = null;
                    }
                    //if the course was at the middle
                    else {

                        Node prenode = newnode.prev;
                        Node nextnode = newnode.next;
                        // connect the prenode and next
                        prenode.next = nextnode;
                        nextnode.prev = prenode;
                        // cut the connection between the node and prenode and next node
                        newnode.prev = null;
                        newnode.next = null;
                    }

                    panel.revalidate();
                    panel.repaint();
                   size--;
                    StudentChoice(panel , mainPanel , allCourses ,studentSelection );

                   break;
                }
                else {
                    newnode = newnode.next;
                }
            }
        }
    }

    public Course findCourseInArray(int id, Course[] allCourses) {
        for (Course c : allCourses) {
            if (c.getId() == id) return c;
        }
        return null;
    }
    // to find if we can sign the course in
    public boolean isAvailable(Course course, Course[] allCourses) {
        //if the course does not have getPrerequisites it is available
        if (course.getPrerequisites() == null) return true;

        for (int i = 0; i < course.getPrerequisites().length; ++i) {
            // to find the course that unlock this course
            // first we tack the Prerequisite from the array
            int preReqId = course.getPrerequisites()[i];

            // here you find the preReq course
            Course preReq = findCourseInArray(preReqId, allCourses);
            // check if it is passed
            if (preReq == null || !preReq.isIspass()) {
                return false;
            }
        }
        return true;
    }

    public void StudentChoice (JPanel panel, JPanel mainPanel, Course[] allCourses , linkedlist studentSelection){
        panel.removeAll();
        Node current = head;
        for (int i =0 ; i < size ; i++){
           Course currentCourse = current.course;
            JButton courseBut = new JButton(currentCourse.getId() +" : " +currentCourse.getName() );
            courseBut.setForeground(Color.black);
            panel.add(courseBut);

                // the listener
             courseBut.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked (MouseEvent e) {
                    if(SwingUtilities.isLeftMouseButton(e)){
                        currentCourse.setisTaken(false);
                        // for undo action ent the delete action on the stack
                        UndoAction ac = new UndoAction(false, currentCourse);
                        getUndoStack().push(ac);
                        // now delete the course
                        deleteCourse(currentCourse.getId() , panel ,mainPanel, allCourses , studentSelection);
                }
                    else if (SwingUtilities.isRightMouseButton(e)) {
                        String GradeInput = JOptionPane.showInputDialog(null, "Enter the grade for " + currentCourse.getId() + ": " + currentCourse.getName());


                        if (GradeInput == null || GradeInput.trim().isEmpty()) {

                            return;
                        }

                        try {

                            int grade = Integer.parseInt(GradeInput);


                            if (grade < 0 || grade > 100) {
                                JOptionPane.showMessageDialog(null, "Grade must be between 0 and 100!", "Invalid Input", JOptionPane.WARNING_MESSAGE);
                                return;
                            }


                            currentCourse.setGrade(grade);
                            BST bst= new BST();
                            bst.addGrade(allCourses);

                            // because if he fail he can resign on the subject again
                            if (currentCourse.getGrade() < 50 )
                                currentCourse.setisTaken(false);

                            // to delete the course after having the mark
                            deleteCourse(currentCourse.getId() , panel ,mainPanel, allCourses , studentSelection);
                            // To refresh the Student panel
                            StudentChoice(panel , mainPanel , allCourses , studentSelection );
                            // To refresh the main panel
                            displayAll(mainPanel , allCourses , panel , studentSelection);


                        } catch (NumberFormatException ex) {

                            JOptionPane.showMessageDialog(null, "Error: Please enter a valid number", "Wrong Format", JOptionPane.ERROR_MESSAGE);
                        }
                    }

                    }

            });
            current = current.next;
        }
        panel.revalidate();
        panel.repaint();
        //displayAll();
    }


    // for undo button
    static Stack undoStack = new Stack();
    public void displayAll(JPanel panel, Course[] allCourses , JPanel panel1  , linkedlist studentSelection) {

        panel.removeAll();


        for (Course c : allCourses) {

            JButton courseBut = new JButton(c.getId() + " : " + c.getName());


                 //listener

            courseBut.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked (MouseEvent e) {
                    if(SwingUtilities.isLeftMouseButton(e)& isAvailable(c, allCourses) & !c.getisTaken()){
                        // add a course to student panel
                        studentSelection.addCourse(c, panel1 , panel, allCourses , studentSelection);
                        c.setisTaken(true);
                        // adding on the stack for the undo
                        UndoAction ac = new UndoAction(true, c);
                        undoStack.push(ac);

                }
                    else if (SwingUtilities.isRightMouseButton(e)) {
                        // what this course unlock
                        class GraphTraverser {
                            String names = "";

                            void collect(Course course) {
                                // to void any errors and make sure that this course is existed and unlock something
                                if (course == null || course.getUnlock() == null) {
                                    return;
                                }
                                // to go in each element of the array
                                for (Course next : course.getUnlock()) {
                                    // make sure we did not add it before
                                    if (!names.contains(next.getName())) {
                                        names += next.getName() + ", ";
                                        // here we will call the loop again to find what unlock courses can unlock either
                                        collect(next);
                                    }
                                }
                            }
                        }
                        // we make an object of the class
                        GraphTraverser traverser = new GraphTraverser();
                        // we called the function on it
                        traverser.collect(c);
                        String finalNames = traverser.names;
                        // make sure the finalNames is not null
                         if (finalNames.isEmpty()) {
                            finalNames = "None";
                        }

                        JOptionPane.showMessageDialog(null, "This subject opens: " + finalNames);
                    }
                }
            });


            if (c.isIspass()) {
                // if the course had done the button will be green
                courseBut.setForeground(Color.GREEN);
            }
            else {
                if (isAvailable(c, allCourses))
                    // if we did not pass the course yet, but we can add it will be black
                    courseBut.setForeground(Color.black);
                else
                    // if we can not add the course it will be red
                    courseBut.setForeground(new Color(150, 0, 0));


            }
            panel.add(courseBut);
        }
        panel.revalidate();
        panel.repaint();
    }
    public Stack getUndoStack() {
        return undoStack;
    }
}
