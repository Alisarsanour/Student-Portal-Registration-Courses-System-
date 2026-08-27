class Nod {
    Course course;
    Nod R;
    Nod L;

     Nod (Course course){
        this.course=course;
    }
}

public class BST {
    private Nod root;

    BST (){
        root = null;
    }

// add grades for the BST

    public void addGrade (Course allCourses []){
        Nod newc;

        // To go on all courses
        for (Course c : allCourses){
            Nod newNode = new Nod(c);
            if (c.getGrade() > 0){
                if (root == null){
                    root = newNode;
                }
                else{
                     newc = root;
                    Nod parent = null;

                    // To determine where the course will be in the tree
                    while (newc!= null  ){
                        parent = newc;
                        if (c.getGrade() > newc.course.getGrade()){
                             newc = newc.R;
                        }
                        else {
                            newc = newc.L;
                        }
                    }
                    //to conect the course in the Tree
                    if (c.getGrade() > parent.course.getGrade())
                        parent.R =newNode;
                    else
                        parent.L =newNode;

                }
            }

        }
    }
// search
    // the flag is did we find the course or not
 public boolean flag = false;
    public String searchG (int val) {
        Nod newc = root;
        String all = "";

        while (newc != null) {
            // if we find the course
            if (newc.course.getGrade() == val) {
                flag = true;
                all+= newc.course.getId() + " " + newc.course.getName() + " hours: " + newc.course.getHours() + "\n \n";
                newc = newc.L;
            } else if (newc.course.getGrade() < val) {
                newc = newc.R;
            }
            else if (newc.course.getGrade() > val) {
                newc = newc.L;
            }
        }

        return all;
    }
    // find the highest grade go the right
public Course highestg () {
    if (root != null) {
        Nod newc = root;
        while (newc.R != null) {
            newc = newc.R;
        }
        return newc.course;
        // if there is no courses we will return null
    } return null;
}
    // find the lowest grade go the left
    public Course lowestg (){
        if (root != null){
        Nod newc = root;
        while (newc.L !=null){
            newc= newc.L;
        }
        return newc.course;
        // if there is no courses we will return null
    }return null;
    }
}
