package com.dsBjpa.entity;

import javax.persistence.*;

@Entity 
@Table(name="parent")
public class Parent {
	
	@Column(name="id")
    private Integer id;
	
    @Column(name="name")
    private String name;
    
   
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

    protected Parent() { }


    @Override
    public String toString() {
        return String.format(
                "Parent[id=%d, name='%s']",
                id, name);
    }
}

