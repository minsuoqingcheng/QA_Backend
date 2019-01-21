package cn.nju.edu.se.entity;

import javax.persistence.*;

/**
 * 用户关注用户
 */
@Entity
@Table(name = "user_map")
public class UserMap {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn
    private User user;

    @ManyToOne
    @JoinColumn
    private User focusedUser;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public User getFocusedUser() {
        return focusedUser;
    }

    public void setFocusedUser(User focusedUser) {
        this.focusedUser = focusedUser;
    }

}
