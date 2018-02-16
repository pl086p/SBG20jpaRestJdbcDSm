package com.noDBjpa;

public class Greeting {

    private final long id;
    private final String content;
    private final String email;

    public Greeting(long id, String content, String email) {
        this.id = id;
        this.content = content;
        this.email = email;
    }

    public long getId() {
        return id;
    }

    public String getContent() {
        return content;
    }
    
    public String getEmail() {
        return email;
    }    
}
