package com.investigatingsoftware.app;

public class Repo {
    public String name;
    public int id;
    public String full_name;

    Owner owner;
    class Owner {
        String login;
        int id;
        String url;
        Owner() {}
    }

    Repo() {}
}
