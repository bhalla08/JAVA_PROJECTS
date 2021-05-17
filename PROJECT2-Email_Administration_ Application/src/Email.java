import java.util.Locale;
import java.util.Scanner;

public class Email {
    private String firstname;
    private String lastname;
    private String email;
    private String password;
    private String department;
    private int defaultpasswordLength = 10;
    private int mailboxcapacity = 500;
    private String alternateEmail;
    private String companySuffix = "nerdherd.com";

    //constructor to recieve the first and last name
    public Email(String firstname,String lastname){
        this.firstname=firstname;
        this.lastname=lastname;


        //call a method to ask for department--return the department
        this.department = setDepartment();


        //call a method that returns a random password.
        this.password = randomPassword(defaultpasswordLength);
        System.out.println("The password is: " +this.password);

        //combine elements to generate email
        email=firstname.toLowerCase() + "." + lastname.toLowerCase() + "@" + department + companySuffix;


    }
    //ask for the department
    private String setDepartment(){
        System.out.println("New worker: " +firstname+ ". Department Codes\n1 for Sales \n2 for Developement\n3 for Accounting \n0 for none\n" +
                "Enter Department code: ");
        Scanner in=new Scanner(System.in);
        int depChoice = in.nextInt();
        if(depChoice == 1)
        {
            return "Sales";
        }

        else if(depChoice == 2){
            return "Developement";
        }

        else if(depChoice == 3){
            return "Accounting";
        }
        else
            return "";
    }

    //Generate a random password
    private String randomPassword(int length){
        String passwordSet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789!@#$%";
        char[] password = new char[length];

        for (int i=0; i<length;i++){
            int rand = (int) (Math.random() * passwordSet.length());
            password[i]=passwordSet.charAt(rand);
        }
        return new String(password);

    }

    //set the mailbox capacity
        public void setMailboxcapacity(int capacity){
        this.mailboxcapacity = capacity;
        }
    //set the alternate email
        public void setAlternateEmail(String altEmail){
        this.alternateEmail = altEmail;
        }

    //change the password
        public void changePassword(String password){
        this.password = password;
        }

        public int getMailboxcapacity(){
        return mailboxcapacity;
    }

    public String getAlternateEmail(){
        return alternateEmail;
    }
    public String getPassword()
    {
        return password;
    }

    public String showInfo(){
        return "DISPLAY NAME: " + firstname + " " + lastname + "\nCOMPANY EMAIL: " +email+ "\nMAILBOX CAPACITY: "+mailboxcapacity+"mb";
    }
}

