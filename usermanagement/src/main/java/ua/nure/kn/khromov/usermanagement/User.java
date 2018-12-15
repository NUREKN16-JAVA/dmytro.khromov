package ua.nure.kn.khromov.usermanagement;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

public class User implements Serializable {

    private static final long serialVersionUID = 5193357156492322710L;
    private Long id;
    private String firstName;
    private String lastName;
    private Date dateOfBirth;

    public User(){}

    public User(String firstName, String lastName, Date dateOfBirth) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
    }

    public User(Long id, String firstName, String lastName, Date dateOfBirth){
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    /**
     * Returns User's full name.
     * Format: "lastName, firstName"
     * For example: "khromov, Dmitriy"
     *
     * @return String concatenation of last and first names.
     */
    public String getFullName() {
        return (new StringBuilder(getLastName()))
                .append(", ")
                .append(getFirstName())
                .toString();
    }

    /**
     * Returns User's age based on User's birth date and current system date.
     *
     * @return int User's age.
     */
    public int getAge() {
        Calendar calendar = Calendar.getInstance();
        int currentYear = calendar.get(Calendar.YEAR);
        int currentMonth = calendar.get(Calendar.MONTH);
        int currentDayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);

        calendar.setTime(getDateOfBirth());
        int yearOfBirth = calendar.get(Calendar.YEAR);
        int monthOfBirth = calendar.get(Calendar.MONTH);
        int dayOfBirth = calendar.get(Calendar.DAY_OF_MONTH);

        int currentAge = currentYear - yearOfBirth;
        if (monthOfBirth >= currentMonth && dayOfBirth >= currentDayOfMonth) {
            return currentAge - 1;
        }

        return currentAge;
    }

    public boolean equals(Object object){
        if(object == null){
            return false;
        }
        if(this == object){
            return true;
        }
        if(this.getId() == null && ((User)object).getId() == null){
            return true;
        }

        return this.getId().equals(((User)object).getId());
    }

    public int hashCode(){
        if(this.getId() == null){
            return 0;
        }
        return this.getId().hashCode();
    }
}
