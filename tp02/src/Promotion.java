import java.util.ArrayList;

public class Promotion {
    private ArrayList<Student> studentList;

    public Promotion(){
        studentList = new ArrayList<Student>();
    }

    public int newId(){
        int studentCount = studentList.size();
        if (studentCount == 0){
            return 0;
        }
        else{
            int maxId = studentList.get(0).getId();
            for (int i=1; i<studentCount; i++){
                int newId = studentList.get(i).getId();
                if (maxId<newId){
                    maxId = newId;
                }
            }
            return maxId+1;
        }
    }

    public int add(String firstName, String lastName){
        int id = newId();
        studentList.add(new Student(id, firstName, lastName));
        return id;
    }

    public void printToConsole(){
        
        for (Student s : studentList){
            System.out.println(s);
        }
    }

}

