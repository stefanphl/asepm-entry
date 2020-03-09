package com.example.asepmentry.modell;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Table
@Entity
public class Request {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @OneToMany(fetch = FetchType.EAGER,
            mappedBy = "request",
    cascade = CascadeType.ALL)
    private Set<Solution> sol = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Solution> getSol() {
        return sol;
    }

    public void setSol(Set<Solution> sol) {
        this.sol = sol;
    }
}
