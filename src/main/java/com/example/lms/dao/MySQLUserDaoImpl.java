package com.example.lms.dao;

import com.example.lms.UserType;
import com.example.lms.model.*;
import com.example.lms.utils.CSVUtils;
import com.example.lms.utils.ConnectionUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class MySQLUserDaoImpl implements UserDao {

    @Override
    public User authenticate(String username, String password) {
        User user = findOne(username);
        if (user != null && password.equals(user.getPassword())) {
            return user;
        }
        return null;
    }

    @Override
    public User findOne(int id) {
        User user = null;
        Connection connection = ConnectionUtils.getConnection();

        try {
            String sql = "SELECT * FROM user WHERE user_id = '" + id + "'";
            PreparedStatement pstd = connection.prepareStatement(sql);
            ResultSet rs = pstd.executeQuery();
            if (rs.next()) {
                Long u_id = rs.getLong(1);
                String name = rs.getString(2);
                String usrname = rs.getString(3);
                String email = rs.getString(4);
                String regNo = rs.getString(5);
                String mobileNo = rs.getString(6);
                String gender = rs.getString(7);
                String password = rs.getString(8);
                String usertype = rs.getString(9);

                PreparedStatement pstd1;
                ResultSet rs1;
                if (usertype.equals(UserType.ADMIN)) {
                    String sql1 = "SELECT a.joiningDate\n" +
                            "FROM user as u\n" +
                            "left join admin as a\n" +
                            "on u.user_id = a.user_id\n" +
                            "WHERE u.user_id = '" + id + "'";
                    pstd1 = connection.prepareStatement(sql1);
                    rs1 = pstd1.executeQuery();
                    if (rs1.next()) {
                        String admin_joiningDate = rs1.getString(1);
                        user = new Admin(u_id, name, usrname, email, regNo, mobileNo, gender, password, usertype, admin_joiningDate);
                    }
                } else if (usertype.equals(UserType.STUDENT)) {
                    String sql1 = "SELECT s.branch, s.batch, s.rollNo\n" +
                            "FROM user as u\n" +
                            "left join student as s\n" +
                            "on u.user_id = s.user_id\n" +
                            "WHERE u.user_id = '" + id + "'";
                    pstd1 = connection.prepareStatement(sql1);
                    rs1 = pstd1.executeQuery();
                    if (rs1.next()) {
                        String branch = rs1.getString(1);
                        String batch = rs1.getString(2);
                        Integer rollNo = rs1.getInt(3);
                        user = new Student(u_id, name, usrname, email, regNo, mobileNo, gender, password, usertype, branch, batch, rollNo);
                    }
                } else if (usertype.equals(UserType.TEACHER)) {
                    String sql1 = "SELECT t.department, t.joiningdate, t.isLibrarian\n" +
                            "FROM user as u\n" +
                            "left join teacher as t\n" +
                            "on u.user_id = t.user_id\n" +
                            "WHERE u.user_id = '" + id + "'";
                    pstd1 = connection.prepareStatement(sql1);
                    rs1 = pstd1.executeQuery();
                    if (rs1.next()) {
                        String dept = rs1.getString(1);
                        String teacher_joiningdate = rs1.getString(2);
                        Boolean isLib = rs1.getBoolean(3);
                        user = new Teacher(u_id, name, usrname, email, regNo, mobileNo, gender, password, usertype, dept, teacher_joiningdate, isLib);
                    }
                }

            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return user;
    }

    @Override
    public User findOne(String username) {
        User user = null;
        Connection connection = ConnectionUtils.getConnection();

        try {
            String sql = "SELECT u.user_id, u.name, u.username, u.email, u.regNo, u.mobileNo, u.gender, u.password, u.usertype, a.joiningDate, s.branch, s.batch, s.rollNo, t.department, t.joiningdate, t.isLibrarian\n" +
                    "FROM user as u\n" +
                    "left join admin as a\n" +
                    "on u.user_id = a.user_id\n" +
                    "left join student as s\n" +
                    "on u.user_id = s.user_id\n" +
                    "left join teacher as t\n" +
                    "on u.user_id = t.user_id\n" +
                    "WHERE u.username = '" + username + "'";

            PreparedStatement pstd = connection.prepareStatement(sql);
            ResultSet rs = pstd.executeQuery();
            if (rs.next()) {
                Long id = rs.getLong(1);
                String name = rs.getString(2);
                String usrname = rs.getString(3);
                String email = rs.getString(4);
                String regNo = rs.getString(5);
                String mobileNo = rs.getString(6);
                String gender = rs.getString(7);
                String password = rs.getString(8);
                String usertype = rs.getString(9);
                String admin_joiningDate = rs.getString(10);
                String branch = rs.getString(11);
                String batch = rs.getString(12);
                Integer rollNo = rs.getInt(13);
                String dept = rs.getString(14);
                String teacher_joiningdate = rs.getString(15);
                Boolean isLib = rs.getBoolean(16);

                if (usertype.equals(UserType.ADMIN)) {
                    user = new Admin(id, name, usrname, email, regNo, mobileNo, gender, password, usertype, admin_joiningDate);
                } else if (usertype.equals(UserType.STUDENT)) {
                    user = new Student(id, name, usrname, email, regNo, mobileNo, gender, password, usertype, branch, batch, rollNo);
                } else if (usertype.equals(UserType.TEACHER)) {
                    user = new Teacher(id, name, usrname, email, regNo, mobileNo, gender, password, usertype, dept, teacher_joiningdate, isLib);
                }

            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return user;
    }

    @Override
    public Boolean isLibrarian(Long user_id_teacher) {
        String sql = "SELECT isLibrarian from teacher where user_id = '" + user_id_teacher + "' ";
        Connection connection = ConnectionUtils.getConnection();
        boolean isLibrarian = false;
        try {
            PreparedStatement pstd = connection.prepareStatement(sql);
            ResultSet rs = pstd.executeQuery();
            if (rs.next()) {
                isLibrarian = rs.getBoolean(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }


        return isLibrarian;
    }

    @Override
    public Collection<User> findAll() {

        List<User> users = new ArrayList<User>();
        User user = null;

        String sql = "SELECT user_id FROM user";

        Connection connection = ConnectionUtils.getConnection();
        try {
            PreparedStatement pstd = connection.prepareStatement(sql);
            ResultSet rs = pstd.executeQuery();

            while (rs.next()) {
                Long user_id = rs.getLong(1);
                String sql1 = "SELECT u.user_id, u.name, u.username, u.email, u.regNo, u.mobileNo, u.gender, u.password, u.usertype, a.joiningDate, s.branch, s.batch, s.rollNo, t.department, t.joiningdate, t.isLibrarian\n" +
                        "FROM user as u\n" +
                        "left join admin as a\n" +
                        "on u.user_id = a.user_id\n" +
                        "left join student as s\n" +
                        "on u.user_id = s.user_id\n" +
                        "left join teacher as t\n" +
                        "on u.user_id = t.user_id\n" +
                        "WHERE u.user_id = '" + user_id + "'";

                PreparedStatement pstd1 = connection.prepareStatement(sql1);
                ResultSet rs1 = pstd1.executeQuery();
                if (rs1.next()) {
                    Long id = rs1.getLong(1);
                    String name = rs1.getString(2);
                    String usrname = rs1.getString(3);
                    String email = rs1.getString(4);
                    String regNo = rs1.getString(5);
                    String mobileNo = rs1.getString(6);
                    String gender = rs1.getString(7);
                    String password = rs1.getString(8);
                    String usertype = rs1.getString(9);
                    String admin_joiningDate = rs1.getString(10);
                    String branch = rs1.getString(11);
                    String batch = rs1.getString(12);
                    Integer rollNo = rs1.getInt(13);
                    String dept = rs1.getString(14);
                    String teacher_joiningdate = rs1.getString(15);
                    Boolean isLib = rs1.getBoolean(16);

                    if (usertype.equals(UserType.ADMIN)) {
                        user = new Admin(id, name, usrname, email, regNo, mobileNo, gender, password, usertype, admin_joiningDate);
                    } else if (usertype.equals(UserType.STUDENT)) {
                        user = new Student(id, name, usrname, email, regNo, mobileNo, gender, password, usertype, branch, batch, rollNo);
                    } else if (usertype.equals(UserType.TEACHER)) {
                        user = new Teacher(id, name, usrname, email, regNo, mobileNo, gender, password, usertype, dept, teacher_joiningdate, isLib);
                    }
                    users.add(user);
                }

            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        users.stream().forEach(System.out::println);

        return users;
    }

    @Override
    public User save(User user) {
        String sql = "INSERT INTO user VALUES(NULL, ?, ?, ? , ? , ? , ? , ? , ?)";
        Connection connection = ConnectionUtils.getConnection();

        try {
            PreparedStatement pstd = connection.prepareStatement(sql);
            pstd.setString(1, user.getName());
            pstd.setString(2, user.getUsername());
            pstd.setString(3, user.getEmail());
            pstd.setString(4, user.getRegNo());
            pstd.setString(5, user.getMobileNo());
            pstd.setString(6, user.getGender());
            pstd.setString(7, user.getPassword());
            pstd.setString(8, user.getUserType());

            int result = pstd.executeUpdate();
            if (result == 1) {
                User u = findOne(user.getUsername());
                user.setId(u.getId());

                if (user.getUserType().equals(UserType.ADMIN)) {
                    String sql1 = "INSERT INTO admin VALUES(?, ?)";
                    PreparedStatement pstd1 = connection.prepareStatement(sql1);

                    Admin admin = (Admin) user;
                    pstd1.setString(1, admin.getJoiningDate());
                    pstd1.setLong(2, u.getId());

                    pstd1.executeUpdate();
                } else if (user.getUserType().equals(UserType.STUDENT)) {
                    String sql2 = "INSERT INTO student VALUES(?, ?, ?, ?)";
                    PreparedStatement pstd2 = connection.prepareStatement(sql2);

                    Student student = (Student) user;
                    pstd2.setString(1, student.getBranch());
                    pstd2.setString(2, student.getBatch());
                    pstd2.setInt(3, student.getRollNo());
                    pstd2.setLong(4, u.getId());

                    pstd2.executeUpdate();
                } else if (user.getUserType().equals(UserType.TEACHER)) {
                    String sql3 = "INSERT INTO teacher VALUES(?, ?, ?, ?)";
                    PreparedStatement pstd3 = connection.prepareStatement(sql3);

                    Teacher teacher = (Teacher) user;
                    pstd3.setString(1, teacher.getDepartment());
                    pstd3.setString(2, teacher.getJoiningDate());
                    pstd3.setBoolean(3, teacher.getLibrarian());
                    pstd3.setLong(4, u.getId());

                    pstd3.executeUpdate();
                }

            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return user;
    }

    @Override
    public User update(User user) {


        String sql = "UPDATE user SET name = ?, username = ?, email = ?, regNo = ?, mobileNo = ?, gender = ?, password = ?, userType = ? WHERE user_id = '" + user.getId() + "'";
        Connection connection = ConnectionUtils.getConnection();
        try {
            PreparedStatement pstd = connection.prepareStatement(sql);
            pstd.setString(1, user.getName());
            pstd.setString(2, user.getUsername());
            pstd.setString(3, user.getEmail());
            pstd.setString(4, user.getRegNo());
            pstd.setString(5, user.getMobileNo());
            pstd.setString(6, user.getGender());
            pstd.setString(7, user.getPassword());
            pstd.setString(8, user.getUserType());
            int res = pstd.executeUpdate();
            if (res == 1) {
                PreparedStatement pstd1;
                if (user.getUserType().equals(UserType.ADMIN)) {
                    String sql1 = "UPDATE admin SET joiningDate= ? WHERE id= ?";
                    pstd1 = connection.prepareStatement(sql1);

                    pstd1.setLong(1, findOne(user.getUsername()).getId());
                    Admin admin = (Admin) user;
                    pstd1.setString(2, admin.getJoiningDate());
                    pstd1.executeUpdate();
                } else if (user.getUserType().equals(UserType.STUDENT)) {
                    String sql1 = "UPDATE admin SET id = ?, branch= ?, batch = ? , rollNo = ?";
                    pstd1 = connection.prepareStatement(sql1);

                    pstd1.setLong(1, findOne(user.getUsername()).getId());
                    Student student = (Student) user;
                    pstd1.setString(2, student.getBranch());
                    pstd1.setString(3, student.getBatch());
                    pstd1.setInt(4, student.getRollNo());
                    pstd1.executeUpdate();
                } else if (user.getUserType().equals(UserType.TEACHER)) {
                    String sql1 = "UPDATE admin SET id = ?, department= ?, joiningdate = ? , isLibrarian = ?";
                    pstd1 = connection.prepareStatement(sql1);

                    pstd1.setLong(1, findOne(user.getUsername()).getId());
                    Teacher teacher = (Teacher) user;
                    pstd1.setString(2, teacher.getDepartment());
                    pstd1.setString(3, teacher.getJoiningDate());
                    pstd1.setBoolean(4, teacher.getLibrarian());
                    pstd1.executeUpdate();
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return user;
    }

    /**
     * TODO: Duplicate entry should be handled
     * @param user
     * @param books
     * @return
     */
    @Override
    public User update(User user, List<Book> books) {
        String sql = "UPDATE user SET name = ?, username = ?, email = ?, regNo = ?, mobileNo = ?, gender = ?, password = ?, userType = ? WHERE user_id = '" + user.getId() + "'";
        Connection connection = ConnectionUtils.getConnection();
        try {
            PreparedStatement pstd = connection.prepareStatement(sql);
            pstd.setString(1, user.getName());
            pstd.setString(2, user.getUsername());
            pstd.setString(3, user.getEmail());
            pstd.setString(4, user.getRegNo());
            pstd.setString(5, user.getMobileNo());
            pstd.setString(6, user.getGender());
            pstd.setString(7, user.getPassword());
            pstd.setString(8, user.getUserType());
            int res = pstd.executeUpdate();
            if (res == 1) {
                PreparedStatement pstd1;
                if (user.getUserType().equals(UserType.ADMIN)) {
                    String sql1 = "UPDATE admin SET joiningDate= ? WHERE id= ?";
                    pstd1 = connection.prepareStatement(sql1);

                    pstd1.setLong(1, findOne(user.getUsername()).getId());
                    Admin admin = (Admin) user;
                    pstd1.setString(2, admin.getJoiningDate());
                    pstd1.executeUpdate();
                } else if (user.getUserType().equals(UserType.STUDENT)) {
                    String sql1 = "UPDATE admin SET id = ?, branch= ?, batch = ? , rollNo = ?";
                    pstd1 = connection.prepareStatement(sql1);

                    pstd1.setLong(1, findOne(user.getUsername()).getId());
                    Student student = (Student) user;
                    pstd1.setString(2, student.getBranch());
                    pstd1.setString(3, student.getBatch());
                    pstd1.setInt(4, student.getRollNo());
                    pstd1.executeUpdate();
                } else if (user.getUserType().equals(UserType.TEACHER)) {
                    String sql1 = "UPDATE admin SET id = ?, department= ?, joiningdate = ? , isLibrarian = ?";
                    pstd1 = connection.prepareStatement(sql1);

                    pstd1.setLong(1, findOne(user.getUsername()).getId());
                    Teacher teacher = (Teacher) user;
                    pstd1.setString(2, teacher.getDepartment());
                    pstd1.setString(3, teacher.getJoiningDate());
                    pstd1.setBoolean(4, teacher.getLibrarian());
                    pstd1.executeUpdate();
                }
            }

            saveUserBook(user, books, connection);

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return user;
    }

    @Override
    public User update(User user, Collection<String> book_name) {
        String sql = "UPDATE user SET name = ?, username = ?, email = ?, regNo = ?, mobileNo = ?, gender = ?, password = ?, userType = ? WHERE user_id = '" + user.getId() + "'";
        Connection connection = ConnectionUtils.getConnection();
        try {
            PreparedStatement pstd = connection.prepareStatement(sql);
            pstd.setString(1, user.getName());
            pstd.setString(2, user.getUsername());
            pstd.setString(3, user.getEmail());
            pstd.setString(4, user.getRegNo());
            pstd.setString(5, user.getMobileNo());
            pstd.setString(6, user.getGender());
            pstd.setString(7, user.getPassword());
            pstd.setString(8, user.getUserType());
            int res = pstd.executeUpdate();
            if (res == 1) {
                PreparedStatement pstd1;
                if (user.getUserType().equals(UserType.ADMIN)) {
                    String sql1 = "UPDATE admin SET joiningDate= ? WHERE user_id= '" + user.getId() + "'";
                    pstd1 = connection.prepareStatement(sql1);

                    pstd1.setLong(1, findOne(user.getUsername()).getId());
                    Admin admin = (Admin) user;
                    pstd1.setString(2, admin.getJoiningDate());
                    pstd1.executeUpdate();
                } else if (user.getUserType().equals(UserType.STUDENT)) {
                    String sql1 = "UPDATE student SET branch= ?, batch = ? , rollNo = ? WHERE user_id= '" + user.getId() + "'";
                    pstd1 = connection.prepareStatement(sql1);

                    Student student = (Student) user;
                    pstd1.setString(1, student.getBranch());
                    pstd1.setString(2, student.getBatch());
                    pstd1.setInt(3, student.getRollNo());
                    pstd1.executeUpdate();
                } else if (user.getUserType().equals(UserType.TEACHER)) {
                    String sql1 = "UPDATE teacher SET department= ?, joiningdate = ? , isLibrarian = ? WHERE user_id= '" + user.getId() + "'";
                    pstd1 = connection.prepareStatement(sql1);

                    Teacher teacher = (Teacher) user;
                    pstd1.setString(1, teacher.getDepartment());
                    pstd1.setString(2, teacher.getJoiningDate());
                    pstd1.setBoolean(3, teacher.getLibrarian());
                    pstd1.executeUpdate();
                }
            }

            saveUserBook(user, book_name, connection);

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return user;
    }

    @Override
    public User delete(int id) {
        return null;
    }

    @Override
    public User delete(String username) {
        return null;
    }


    public void saveUserBook(User user, List<Book> books, Connection connection) {
        List<String> strList = books.stream().map(book -> String.valueOf(book.getId())).collect(Collectors.toList());

        String sql = "INSERT INTO user_book VALUES ("+CSVUtils.getCSV(strList)+")";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }

    public void saveUserBook(User user, Collection<String> book_names, Connection connection) {
        List<String> list = book_names.stream().collect(Collectors.toList());
        List<Long> book_ids = new ArrayList<>();
        //Connection connection = ConnectionUtils.getConnection();
        PreparedStatement pstd = null;
        for (int i = 0; i < list.size(); i++) {
            String bk = list.get(i);
            String sql = "SELECT book_id FROM book WHERE name = '" + bk + "' ";
            try {
                pstd = connection.prepareStatement(sql);
                ResultSet rs = pstd.executeQuery();
                if (rs.next()) {
                    book_ids.add(rs.getLong(1));
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }

        StringBuilder sb = new StringBuilder("INSERT INTO user_book VALUES ");

        for (int i = 0; i < book_ids.size(); i++) {
            sb.append("(" + user.getId() + " , " + book_ids.get(i) + "),");
            if (i == book_ids.size() - 1) {
                sb.setLength(sb.length() - 1);
            }
        }
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sb.toString());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Book> findUserBooks(User user) {
        Connection connection = ConnectionUtils.getConnection();
        String sql = "SELECT book_id from user_book WHERE user_id = " + user.getId() + "";
        List<Long> book_ids = new ArrayList<>();
        List<Book> books = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = preparedStatement.executeQuery();

            while(rs.next()){
                book_ids.add(rs.getLong(1));
            }
            books = new MySQLBookDaoImpl().findBooks(book_ids);
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return books;
    };

    public void deleteUserBook(User user, List<Book> books) {
        Connection connection = ConnectionUtils.getConnection();
        //List<Long> list = books.stream().map(b -> b.getId()).collect(Collectors.toList());
        String sql = "SELECT * from user_book WHERE user_id = '" + user.getId() + "'";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet rs = preparedStatement.executeQuery();
            if(rs.next()){
                    List<String> list1 = books.stream().map(book -> String.valueOf(book.getId()) ).collect(Collectors.toList());
                    String sql1 = "DELETE FROM user_book WHERE user_id = '" + user.getId() +"' AND book_id IN("+CSVUtils.getCSV(list1)+")";
                    preparedStatement = connection.prepareStatement(sql1);
                    preparedStatement.executeUpdate();

            }else{
                System.out.println("The user has no book issued currently under his/her name");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }

}
