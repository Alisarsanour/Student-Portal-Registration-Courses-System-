import javax.swing.*;
import javax.swing.JFrame;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class Main {

    public static void main(String[] args) {




        //Courses
        //Math subjects
        Course math3 = new Course( "Math3", 300 , 3 , new int[]{ 200} , null);
        Course math2 = new Course( "Math2", 200 , 3 , new int[]{100} , new Course[]{math3});
        Course math1 = new Course( "Math1", 100 , 3 , null , new Course[]{math2 });



        //Coding Subjects
        Course dataS = new Course( "Data Structure and Algorithm", 600 , 3 ,new int[]{500} , null);
        Course programing = new Course( "Programing", 500 , 3 ,new int[]{400} ,new Course[]{dataS});
        Course foc = new Course( "FOC", 400 , 400 ,null ,new Course[]{programing  });


        //Arabic
        Course arabic = new Course( "Arabic Language", 700 , 1 ,null , null);

        Course allCourses [] = {math1,math2,math3,foc , programing , dataS , arabic};









//The welcoming frame
        JFrame welcomeP = new JFrame("Welcome");
        welcomeP.setSize(700,400);
        welcomeP.setLayout(null);
        //The Text
        Font titelfont = new Font("Arial", Font.BOLD , 20);
        Font normalfont = new Font("Arial", Font.TRUETYPE_FONT , 20);
        JLabel wLabel = new JLabel("Welcome to HTU portal!");
        wLabel.setBounds(230,20, 350,40);
        wLabel.setFont(titelfont);
        welcomeP.add(wLabel);
        // The Lets go label
        JLabel label1 = new JLabel("Lets begin..!");
        label1.setBounds(100,70, 150,20);
        label1.setFont(normalfont);
        welcomeP.add(label1);
        // Text end


        //Student Button
        JButton Studenbut = new JButton("Student");
        Studenbut.setBounds(250, 250, 100, 50);
        welcomeP.add(Studenbut);



        welcomeP.setVisible(true);

        //Student listener
        Studenbut.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                StuP stP = new StuP(welcomeP);
                stP.setVisible(true);
                welcomeP.setVisible(false);
            }
        });





    }
}
