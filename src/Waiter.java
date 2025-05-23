public class Waiter{
    String UserName;
    String Password;
    String Name;
    String Phone;
    int EmployeeID;
    String Email;
    String Department;
    int DepartmentID;
    int HireDate;
    String Manager;
    String ManagerID;
    int Salary;

    //default constructor
    public Waiter(String UserName, String Password, String Name, String Phone, String Email, int HireDate){
        this.UserName = UserName;
        this.Password = Password;
        this.Name = Name;
        this.Phone = Phone;
        this.Email = Email;
        this.HireDate = HireDate;
    }

    //methods for retrieving values
    public String getUserName(){
        return UserName;
    }
    public String getPassword(){
        return Password;
    }
    public String getName(){
        return Name;
    }
    public String getPhone(){
        return Phone;
    }
    public String getEmail(){
        return Email;
    }
    public int getHireDate(){
        return HireDate;
    }
    public String getDepartment(){
        return Department;
    }
    public int getDepartmentID(){
        return DepartmentID;
    }
    public int getEmployeeID(){
        return EmployeeID;
    }
    public String getManager(){
        return Manager;
    }
    public String getManagerID(){
        return ManagerID;
    }
    public int getSalary(){
        return Salary;
    }

    //Check functions for login
    public boolean checkUsername(String UserName){
        if(UserName == this.getUserName()){
            return true;
        }
        else{
            return false;
        }
    }
    public boolean checkPassword(String Password){
        if(Password == this.getPassword()){
            return true;
        }
        else{
            return false;
        }
    }
}