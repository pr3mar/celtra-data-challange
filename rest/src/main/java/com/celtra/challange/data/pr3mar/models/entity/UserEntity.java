package com.celtra.challange.data.pr3mar.models.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

import static javax.persistence.GenerationType.IDENTITY;

@Entity(name = "User")
@Table(name = "Users")
@NamedQueries({
        @NamedQuery(
                name = "User.findAll",
                query = "SELECT c FROM User c"
        ),
        @NamedQuery(
                name = "User.countAll",
                query = "SELECT count(c) FROM User c"
        ),
        @NamedQuery(
                name = "User.getIds",
                query = "SELECT c.id FROM User c ORDER BY c.dateStored DESC "
        ),
        @NamedQuery(
                name = "User.getLastId",
                query = "SELECT c.id FROM User c ORDER BY c.dateStored DESC "
        )
})
public class UserEntity implements Serializable {

    private long id;
    private String email;
    private Date dateStored = new Date();

    public UserEntity() {
    }

    public UserEntity(String email) {
        this.email = email;
    }

    public UserEntity(long id, String email) {
        this.id = id;
        this.email = email;
    }

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "ID", columnDefinition = "bigint", unique = true, updatable = false, nullable = false)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Column(name = "Email", columnDefinition = "nvarchar(150)", updatable = false, nullable = false)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Column(name = "DateStored", columnDefinition = "datetime", updatable = false, nullable = false)
    public Date getDateStored() {
        return dateStored;
    }

    public void setDateStored(Date dateStored) {
        this.dateStored = dateStored;
    }

    @Override
    public String toString() {
        return "UserEntity{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", dateStored=" + dateStored +
                '}';
    }
}
