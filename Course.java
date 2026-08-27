public class Course {
    private int Id ;
    private int grade;
    private boolean ispass;
    private String name;
    private int hours;
    private int[] prerequisites;
    private Course[] unlock;
    private boolean isTaken;

    public Course (String name ,int Id,int hours, int[] prerequisites , Course[] unlock ){
        this.name =name;
        this.Id=Id;
        this.hours=hours;
        grade=-1;
        ispass = false;
        isTaken=false;
        this.prerequisites = prerequisites;
        this.unlock=unlock;
    }


public int getId(){
        return Id;
}
 public void setId(int Id){
        this.Id=Id;
 }


    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
        if(grade >= 50)
            ispass=true;
    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHours() {
        return hours;
    }

    public void setHours(int hours) {
        this.hours = hours;
    }

    public int [] getPrerequisites(){
        return prerequisites;
    }

    public void setPrerequisites (int [] prerequisites){
        this.prerequisites=prerequisites;
    }

    public boolean isIspass() {
        return ispass;
    }

    public void setIspass(boolean ispass) {
        this.ispass = ispass;
    }

    public boolean getisTaken() {
        return isTaken;
    }

    public void setisTaken(boolean taken) {
        isTaken = taken;
    }

    public Course[] getUnlock() {
        return unlock;
    }

    public void setUnlock(Course[] unlock) {
        this.unlock = unlock;
    }
}

