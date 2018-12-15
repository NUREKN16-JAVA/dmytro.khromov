package ua.nure.kn.khromov.usermanagement.db;

import com.mockobjects.dynamic.Mock;

public class MockDaoFactory extends DaoFactory {
    private Mock mockUserDao;

    /**
     * Creates mockUserDao.
     * */
    public MockDaoFactory() {
        mockUserDao = new Mock(UserDao.class);
    }

    /**
     * Gets mockUserDao.
     *
     * @return mockUserDao.
     * */
    public Mock getMockUserDao() {
        return mockUserDao;
    }

    /**
     * Gets userDao.
     *
     * @return userDao.
     * */
    @Override
    public UserDao getUserDao() {
        return (UserDao) mockUserDao.proxy();
    }
}
