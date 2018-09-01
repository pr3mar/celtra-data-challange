package com.celtra.challange.data.pr3mar.models.entity;

import javax.persistence.*;
import java.io.Serializable;

import static javax.persistence.GenerationType.IDENTITY;

@Entity(name = "InteractionType")
@Table(name = "InteractionType")
@NamedQueries({
        @NamedQuery(
                name = "InteractionType.findAll",
                query = "SELECT c FROM InteractionType c"
        ),
        @NamedQuery(
                name = "InteractionType.countAll",
                query = "SELECT count(c) FROM InteractionType c"
        ),
        @NamedQuery(
                name = "InteractionType.getIds",
                query = "SELECT DISTINCT c.id FROM InteractionType c"
        )
})
public class InteractionTypeEntity implements Serializable {

    private int id;
    private String type;

    public InteractionTypeEntity() {
    }

    public InteractionTypeEntity(int id, String type) {
        this.id = id;
        this.type = type;
    }

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "ID", columnDefinition = "bigint", unique = true, updatable = false, nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Column(name = "Type", columnDefinition = "nvarchar(50)", nullable = false)
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
