package com.example.java20.week4;

import org.hibernate.jpa.HibernatePersistenceProvider;
import org.postgresql.ds.PGSimpleDataSource;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceUnitUtil;
import javax.sql.DataSource;
import java.util.Properties;

/**
 *  Hibernate : session : save() / saveOrUpdate() ..
 *  Jpa       : entity manager: persist() / merge() ..
 *  Class Configuration
 *      1. data source
 *      2. entity classes
 *
 *          @Entity
 *          @Table("")
 *          class XX {
 *              @Id
 *              GeneratedType ,  Identity
 *              private String id;
 *              @Column("stu_name")
 *              private String name;
 *          }
 *      3. create relations
 *
 *          class A {
 *              @OneToMany(mappedBy = "a123", fetchType.LAZY)
 *              private List<B> bList = new ArrayList<>();
 *          }
 *
 *          class B {
 *              @ManyToOne
 *              @JoinColumn(foreign key, fetchType.LAZY)
 *              private A a123;
 *          }
 *     4. session / entity manager
 *     5. transaction
 *
 *      List<Student> stuList = em.createQuery(Select..);
 *     *      *      *      *      *      *      *      *      *      *      *      *      *
 *  1. why orm / hibernate
 *  ..
 *  2. diff lazy loading and eager loading
 *  ..
 *  3. what is N + 1 problem
 *      student 1 - m student_teacher lazy loading
 *      List<Student> list = em.createQuery(..);  1
 *      for(Student stu: list) {
 *          stu.getStudentTeacher();    N
 *      }
 *      return list;
 *  4. how to solve N + 1 problem
 *      fetch join -> convert lazy loading query to eager loading query
 *      entity graph
 *  5. status / stage of instance in ORM
 *      transient : new instance()
 *      proxied / attached : proxy instance
 *      un-proxied / detached :
 *  6. lazy initialization exception
 *  7. data source
 *
 *
 *  coding question
 *  1. write entity classes on white board
 *  2. write sql query
 *  3. write spring data jpa
 *
 *
 *
 *  TODO
 *      1. create package under the jdbc homework
 *      2. use ORM Config to connect to your database
 *
 *
 *
 *
 */
public class ORMConfig {

    private DataSource getDataSource() {
        final PGSimpleDataSource dataSource = new PGSimpleDataSource();
//        dataSource.setDatabaseName("OrmDemo");
        dataSource.setUser("postgres");
        dataSource.setPassword("password");
        dataSource.setUrl("jdbc:postgresql://localhost:5432/university");
        return dataSource;
    }

    private Properties getProperties() {
        final Properties properties = new Properties();
        properties.put("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");
        properties.put("hibernate.connection.driver_class", "org.postgresql.Driver");
        properties.put("hibernate.show_sql", "true");
        return properties;
    }

    private EntityManagerFactory entityManagerFactory(DataSource dataSource, Properties hibernateProperties) {
        final LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(dataSource);
        em.setPackagesToScan("com/example/java20/week4/");
        em.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
        em.setJpaProperties(hibernateProperties);
        em.setPersistenceUnitName("demo-unit");
        em.setPersistenceProviderClass(HibernatePersistenceProvider.class);
        em.afterPropertiesSet();
        return em.getObject();
    }

    public static void main(String[] args) throws Exception{
        ORMConfig ormConfig = new ORMConfig();
        DataSource dataSource = ormConfig.getDataSource();
        Properties properties = ormConfig.getProperties();
        EntityManagerFactory entityManagerFactory = ormConfig.entityManagerFactory(dataSource, properties);
        EntityManager em = entityManagerFactory.createEntityManager();
        PersistenceUnitUtil unitUtil = entityManagerFactory.getPersistenceUnitUtil();
        //..
        Student stu = em.find(Student.class, "1");
    }
}