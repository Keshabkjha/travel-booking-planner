package com.travelplanner.model;

public class User {
    private int ID;
    private String Name;
    private String Email;
    private String Password;
    private String role; // "user" or "admin"

    public int getId() { return ID; }
    public void setId(int id) { this.ID = id; }

    public String getName() { return Name; }
    public void setName(String name) { this.Name = name; }

    public String getEmail() { return Email; }
    public void setEmail(String email) { this.Email = email; }

    public String getPassword() { return Password; }
    public void setPassword(String password) { this.Password = password; }

    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }
}
