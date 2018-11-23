package test.java.ua.nure.kn.khromov;

import junit.framework.TestCase;
import main.java.ua.nure.kn.khromov.User;

import java.text.ParseException;
import java.util.Calendar;

public class UserTest extends TestCase {

    private User user;

    private final String FULL_NAME_ETALONE = "Khromov, Dmitriy";
    private final String FIRST_NAME_ETALONE = "Dmitriy";
    private final String LAST_NAME_ETALONE = "Khromov";

    private static final int YEAR_OF_BIRTH = 1998;

    //Birthday has been passed in this year, month = month_of_birth
    private static final int ETALONE_AGE_1 = 19;
    private static final int MONTH_OF_BIRTH_1 = 8;
    private static final int DAY_OF_BIRTH_1 = 11;

    //Birthday hasn't been passed in this year, current month < month_of_birth
    private static final int ETALONE_AGE_2 = 18;
    private static final int MONTH_OF_BIRTH_2 = 12;
    private static final int DAY_OF_BIRTH_2 = 11;

    //Birthday has been passed in this year, in this month
    private static final int ETALONE_AGE_3 = 19;
    private static final int MONTH_OF_BIRTH_3 = 11;
    private static final int DAY_OF_BIRTH_3 = 9;

    //Birthday hasn't been passed in this year, will pass in this month
    private static final int ETALONE_AGE_4 = 18;
    private static final int MONTH_OF_BIRTH_4 = 11;
    private static final int DAY_OF_BIRTH_4 = 11;

    //Birthday was yesterday
    private static final int ETALONE_AGE_5 = 19;
    private static final int MONTH_OF_BIRTH_5 = 11;
    private static final int DAY_OF_BIRTH_5 = 9;

    //Birthday will be tomorrow
    private static final int ETALONE_AGE_6 = 18;
    private static final int MONTH_OF_BIRTH_6 = 11;
    private static final int DAY_OF_BIRTH_6 = 11;

    //Birthday is the 31 of December
    private static final int ETALONE_AGE_7 = 18;
    private static final int MONTH_OF_BIRTH_7 = 12;
    private static final int DAY_OF_BIRTH_7 = 31;

    //Birthday is the 1 of January
    private static final int ETALONE_AGE_8 = 19;
    private static final int MONTH_OF_BIRTH_8 = 1;
    private static final int DAY_OF_BIRTH_8 = 1;

    public UserTest() throws ParseException {
    }

    public void setUp() throws Exception {
        user = new User();
        super.setUp();
    }

    /**
     * Tests User's full name.
     * <p>
     * Expected result: LAST_NAME_ETALONE, FIRST_NAME_ETALONE
     */
    public void testGetFullName() {
        user.setFirstName(FIRST_NAME_ETALONE);
        user.setLastName(LAST_NAME_ETALONE);

        assertEquals(FULL_NAME_ETALONE, user.getFullName());
    }

    /**
     * Tests User's age when Birthday passed in this year.
     * <p>
     * expected result: ETALONE_AGE_1
     */
    public void testGetAge_BirthDayPassedInThisYear_ReturnsAge() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(YEAR_OF_BIRTH, MONTH_OF_BIRTH_1, DAY_OF_BIRTH_1);

        user.setDateOfBirth(calendar.getTime());
        assertEquals(ETALONE_AGE_1, user.getAge());
    }

    /**
     * Tests User's age when Birthday will be in this year.
     * <p>
     * Expected result: ETALONE_AGE_2
     */
    public void testGetAge_BirthDayWillBeInThisYear_ReturnsAgeMinus1() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(YEAR_OF_BIRTH, MONTH_OF_BIRTH_2, DAY_OF_BIRTH_2);

        user.setDateOfBirth(calendar.getTime());
        assertEquals(ETALONE_AGE_2, user.getAge());
    }

    /**
     * Tests User's age when Birthday was in this month.
     * <p>
     * Expected result: ETALONE_AGE_3
     */
    public void testGetAge3_BirthDayWasInThisMonth_ReturnsAge() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(YEAR_OF_BIRTH, MONTH_OF_BIRTH_3, DAY_OF_BIRTH_3);

        user.setDateOfBirth(calendar.getTime());
        assertEquals(ETALONE_AGE_3, user.getAge());
    }

    /**
     * Tests User's age when Birthday will be in this month.
     * <p>
     * Expected result: ETALONE_AGE_4
     */
    public void testGetAge_BirthDayWillBeInThisMonth_ReturnsAgeMinus1() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(YEAR_OF_BIRTH, MONTH_OF_BIRTH_4, DAY_OF_BIRTH_4);

        user.setDateOfBirth(calendar.getTime());
        assertEquals(ETALONE_AGE_4, user.getAge());
    }

    /**
     * Tests User's age when Birthday was yesterday.
     * <p>
     * Expected result: ETALONE_AGE_5
     */
    public void testGetAge_BirthDayWasYesterday_ReturnsAge() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(YEAR_OF_BIRTH, MONTH_OF_BIRTH_5, DAY_OF_BIRTH_5);

        user.setDateOfBirth(calendar.getTime());
        assertEquals(ETALONE_AGE_5, user.getAge());
    }

    /**
     * Tests User's age when Birthday will be tomorrow.
     * <p>
     * Expected result: ETALONE_AGE_6
     */
    public void testGetAge_BirthDayWillBeTomorrow_ReturnsAgeMinus1() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(YEAR_OF_BIRTH, MONTH_OF_BIRTH_6, DAY_OF_BIRTH_6);

        user.setDateOfBirth(calendar.getTime());
        assertEquals(ETALONE_AGE_6, user.getAge());
    }

    /**
     * Tests User's age when Birthday is 31 of December.
     * <p>
     * Expected result: ETALONE_AGE_7
     */
    public void testGetAge_BirthDayIs31OfDecember_ReturnsAgeMinus1() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(YEAR_OF_BIRTH, MONTH_OF_BIRTH_7, DAY_OF_BIRTH_7);

        user.setDateOfBirth(calendar.getTime());
        assertEquals(ETALONE_AGE_7, user.getAge());
    }

    /**
     * Tests User's age when Birthday is the 1 of January.
     * <p>
     * Expected result: ETALONE_AGE_8
     */
    public void testGetAge_BirthDayIs1OfJanuary_ReturnsAge() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(YEAR_OF_BIRTH, MONTH_OF_BIRTH_8, DAY_OF_BIRTH_8);

        user.setDateOfBirth(calendar.getTime());
        assertEquals(ETALONE_AGE_8, user.getAge());
    }
}