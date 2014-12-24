package persistence.entity;

import persistence.helper.UserHelper;

import javax.persistence.*;

/**
 * Created by root on 12/23/14.
 */
@Entity
@Table(name = UserHelper.DATABASE_NAME)
public class User {
    private int id;
    private String email;
    private String password;

    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public User() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Column(name = UserHelper.EMAIL, nullable = false)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Column(name = UserHelper.PASSWORD, nullable = false)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
