package com.example.java20.week3;

import java.sql.*;


/**
 *   homework1: JDBC => deadline is Monday 10am CDT,  code review meeting Monday 2pm
 *      1. create many to many relations in database
 *         student m - m teacher
 *         student(id, ..)
 *         student_teacher(id(pk), s_id(fk), t_id(fk)) / student_teacher(s_id, t_id)
 *         teacher(id, ..)
 *      2. create CRUD operations by using JDBC
 *          create ,  create new student , teacher, junction table rows
 *          retrieve,  select student + teacher info
 *          update,  update student name
 *          delete,  delete student by id
 *
 *   homework2: ORM => deadline is Tuesday 10am CDT, code review meeting Tuesday 2pm
 *      1. student class, teacher class, junction class
 *      2. use annotations
 *      3. use hibernate to do CRUD operations
 *
 *   homework3: SpringBoot + rest api
 *   homework4: merge homework3 + homework2
 *   homework5: Microservice
 *      a.
 *      b.
 *      ...
 *   --------------------------------------------------------------------
 *   Why ORM / Advantages of ORM / Disadvantages of JDBC
 *      1. connection pool (2 blocking queue)
 *      2. hibernate query language / JPQL + database dialect(syntax mapping)
 *      3. query result + object mapping
 *      4. criteria query (dynamic query builder)
 *      5. 1st level cache / 2nd level cache
 *      6. lazy loading vs eager loading
 *         student class 1 - m student_teacher class m - 1 teacher class
 *         select * from student
 *         native query => only get student data
 *         hql + lazy loading => only get student data
 *                 Student stu = query();
 *                 stu.getStudent_teacher();
 *         hql + eager loading => retrieve both student info + student_teacher info + teacher info
 *     ...
 *
 */

public class JdbcExample {
    // JDBC driver name and database URL
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost:8080/database_name";

    //  Database credentials
    static final String USER = "username";
    static final String PASS = "password";

    public static void main(String[] args) {
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            //STEP 2: Register JDBC driver -> DriverManager
            Class.forName("com.mysql.jdbc.Driver"); //static block

            //STEP 3: Open a connection
            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL,USER,PASS);
            conn.setAutoCommit(false);
            //STEP 4: Execute a query
            System.out.println("Creating statement...");
            String username = "";
            String password = "";
            String sql1 = "SELECT .... first, last, age FROM Employees WHERE username = "
                    + username + " AND password = xx + " + password + " EXCEPT ...";
            String sql2 = "SELECT ... FROM ... WHERE val1 = not null AND val2 = col2 OR val5 = col5.....";
                /*
                 */
            stmt = conn.prepareStatement(sql1);

            ResultSet rs = stmt.executeQuery();

            //STEP 5: Extract data from result set
            //30 columns -> new instance , Student / List<Student> / Set<Student>
            //List<Student> studentList = executeQuery(query);
            while(rs.next()){
                //Retrieve by column name
                int id  = rs.getInt("id");
                int age = rs.getInt("age");
                String first = rs.getString("first");
                String last = rs.getString("last");

                //Display values
                System.out.print("ID: " + id);
                System.out.print(", Age: " + age);
                System.out.print(", First: " + first);
                System.out.println(", Last: " + last);
            }

            conn.commit();
            //STEP 6: Clean-up environment
            rs.close();
            stmt.close();
            conn.close();
        } catch(SQLException se) {
            //Handle errors for JDBC
            try {
                conn.rollback();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            se.printStackTrace();
        }catch(Exception e){
            //Handle errors for Class.forName
            e.printStackTrace();
        }finally{
            //finally block used to close resources
            try{
                if(stmt!=null)
                    stmt.close();
            }catch(SQLException se2){
            }// nothing we can do
            try{
                if(conn!=null)
                    conn.close();
            }catch(SQLException se){
                se.printStackTrace();
            }//end finally try
        }//end try
    }//end main
}