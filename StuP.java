import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// This inheritance  made it a standalone screen.
// Note: Inheriting from JFrame (Is-A relationship) turns StuP into a standalone window container,
// allowing direct configuration of properties (size, title)
public class StuP extends JFrame {
    JFrame welcomeP;
    public StuP (JFrame welcomeP) {
        this.welcomeP = welcomeP;
        setTitle("Student Portal");
        setSize(800, 700);
        setLayout(null);



        //Courses
        //Math subjects

        Course math3 = new Course( "Math3", 300 , 3 , new int[]{ 200} , null);
        Course math2 = new Course( "Math2", 200 , 3 , new int[]{100} , new Course[]{math3});
        Course math1 = new Course( "Math1", 100 , 3 , null , new Course[]{math2 });




        //Coding Subjects
        Course dataS = new Course( "Data Structure and Algorithm", 600 , 3 ,new int[]{500} , null);
        Course programing = new Course( "Programing", 500 , 3 ,new int[]{400} ,new Course[]{dataS});
        Course foc = new Course( "FOC", 400 , 4 ,null ,new Course[]{programing });


        //Arabic
        Course arabic = new Course( "Arabic Language", 700 , 1 ,null , null);

        Course allCourses [] = {math1,math2, math3,foc , programing , dataS , arabic};



        // back button
        JButton backButton = new JButton("<- back");
        backButton.setBounds(0 ,0 , 90,20);
        add(backButton);
        // back button listener
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                welcomeP.setVisible(true);
                setVisible(false);

            }
        });
        this.setLayout(null);
        // JLabel "Student choice courses"
        JLabel student_choice_material  = new JLabel("Student choice courses");
        student_choice_material.setBounds(250,130 , 180 , 20 );
        add(student_choice_material);
        


        // The Student's choice panel
         JPanel Spanel = new JPanel();
        Spanel.setBounds(250,150,150,300);
        Spanel.setLayout(new GridLayout(0,1,10,0));
        Spanel.setBackground(Color.WHITE);
        Spanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));

// JLabel "Complete study plan materials"
        JLabel plan_materials = new JLabel("Complete study plan materials");
        plan_materials.setBounds(20,130 , 180 , 20 );
        add(plan_materials);
        // Make the panel or the table that will have all the student courses (The Main panel)
   JPanel mainPanel = new JPanel();
   mainPanel.setBounds(50,150,150,300);
   mainPanel.setLayout(new GridLayout(0,1,10,0));
// I made an object from the linked list
   linkedlist allCoursesList = new linkedlist();
        for (Course c : allCourses) {
            allCoursesList.addCourse(c);
        }

        linkedlist studentSelection = new linkedlist();


        // I used the linked list object (list)
        // calling a function in the linked list class (displayAll)
        allCoursesList.displayAll (mainPanel, allCourses , Spanel ,studentSelection );

   add(mainPanel);
   add(Spanel);


   setVisible(true);
//


        // Undo button

        JButton Undo = new JButton("Undo");
        Undo.setBounds(680, 0 , 90,20);
        add(Undo);
        Undo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                UndoAction lastAction = allCoursesList.getUndoStack().pop();

                if (lastAction != null) {
                    if (lastAction.type == true) {
                        //if the last action was adding we will delete the course (undo)
                        lastAction.course.setisTaken(false);
                        studentSelection.deleteCourse(lastAction.course.getId(), Spanel, mainPanel, allCourses, studentSelection);
                    } else {
                        //if the last action was deleting  we will add the course (undo)
                        studentSelection.addCourse(lastAction.course, Spanel, mainPanel, allCourses, studentSelection);
                        lastAction.course.setisTaken(true);
                    }

                    mainPanel.removeAll();
                    allCoursesList.displayAll(mainPanel, allCourses, Spanel, studentSelection);
                    mainPanel.revalidate();
                    mainPanel.repaint();
                    Spanel.revalidate();
                    Spanel.repaint();
                }
            }
        });



        // finding the grades button
        JButton searchG = new JButton("Search grades");
        searchG.setBounds(300, 0 , 150 , 20);
        add(searchG);
        // The button in the student panel listener
        searchG.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // make frame for searching for the grades
                JFrame Grades = new JFrame("Grade Search");
                Grades.setSize(400 , 500);
                Grades.setLayout(null);
                // add label
                JLabel text1 = new JLabel("Search for the grade you want");
                text1.setBounds(20, 30, 300, 30);
                Grades.add(text1);;

                //add text box for grades
                JTextField wanted_grade = new JTextField();
                wanted_grade.setBounds(20,60,100,30);
                Grades.add(wanted_grade);

                // Text button
                JTextField Results = new JTextField();
                Results.setBounds(10,200,360,230);
                // can not write in the box
                Results.setEditable(false);
                Grades.add(Results);

                // add search button

                JButton searchGbut = new JButton("Search");
                searchGbut.setBounds(100, 100 , 100 , 20);
                Grades.add(searchGbut);
                searchGbut.addActionListener(new ActionListener() {

                    @Override
                    public void actionPerformed(ActionEvent e) {
                        // save the text in the text button on a string variable
                        String Grade = wanted_grade.getText();


                        try {

                            int GradN = Integer.parseInt(Grade);
                            if(GradN < 0 || GradN > 100){
                                JOptionPane.showMessageDialog(null, "the grade should be between 100- 0 ",  "Invalid Input", JOptionPane.WARNING_MESSAGE);
                            return;
                            }
                            // the course we searched for
                            // make a BST object
                            // every time we click serach a new bst will be implemented and all courses will be added to it
                            BST bst = new BST();
                            bst.addGrade(allCourses);
                            String result = bst.searchG(GradN);
                            if (bst.flag && !result.isEmpty()) {
                                Results.setText(result);
                            }
                            else {Results.setText(" No Course founded");}
                        }catch (NumberFormatException exception){
                            JOptionPane.showMessageDialog(null, "Numbers only", "Error", JOptionPane.ERROR_MESSAGE );
                        }



                    }
                });

                // add search button for the highest grade

                JButton highest = new JButton("Highest grade");
                highest.setBounds(10, 170 , 130 , 20);
                Grades.add(highest);
                highest.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        BST bst = new BST();
                        bst.addGrade(allCourses);
                        Course ch = bst.highestg();
                        if (ch != null){
                        Results.setText(ch.getId() +": "+ ch.getName() + " hours :"+ch.getHours());
                    }else {Results.setText(" No Course founded");}

                    }
                });

                // add search button for the lowest grade

                JButton lowest = new JButton("Lowest grade");
                lowest.setBounds(180, 170 , 130 , 20);
                Grades.add(lowest);
                lowest.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        BST bst = new BST();
                        bst.addGrade(allCourses);
                        Course ch = bst.lowestg();
                        if (ch != null){
                        Results.setText(ch.getId() +": "+ ch.getName() + " hours :"+ch.getHours());
                    }else {Results.setText(" No Course founded");}
                    }
                });







                Grades.setVisible(true);
            }
        });




    }



}
