public class Student {

    private final int id;
    private String firstName;
    private String lastName;

    @Override
    public String toString(){
        return firstName + " " + lastName + " (" + id + ")";
    }

    public final String getFirstName(){
        return firstName;
    }

    public final String getLastName(){
        return lastName;
    }

    public final int getId(){
        return id;
    }

    public final void setFirstName(String firstName){
        this.firstName = firstName;
    }

    public final void setLastName(String lastName){
        this.lastName = lastName;
    }

    public Student(int id, String firstName, String lastName){
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
    }
}
