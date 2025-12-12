package com.not_a_team.university;

import java.util.ArrayList;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.servlet.http.HttpSession;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String password;
    private Role role;
    private ArrayList<String> sessions;

    public User(String name, String password, Role role) {
        this.name = name;
        this.password = password;
        this.role = role;
        this.sessions = new ArrayList<String>();
    }
    public User(String name, String password, Role role, String sessionId) {
        this.name = name;
        this.password = password;
        this.role = role;
        this.sessions = new ArrayList<String>();
        
        this.addSesseion(sessionId);
    }
    public User(String name, String password, Role role, HttpSession session) {
        this.name = name;
        this.password = password;
        this.role = role;
        this.sessions = new ArrayList<String>();
        
        this.addSesseion(session.getId());
    }

    // -- Session management
    // Adding a new session
    public void addSesseion(String sessionId) {
        if (!this.sessions.contains(sessionId))
            this.sessions.add(sessionId);
    }
    public void addSesseion(HttpSession session) {
        addSesseion(session.getId());
    }
    // Look for a session
    public boolean findSession(String sessionId) {
        return this.sessions.contains(sessionId);
    }
    public boolean findSession(HttpSession session) {
        return this.findSession(session.getId());
    }
    // Logout from a session
    public void removeSession(String sessionId) throws NoSessionFound {
        if (this.sessions.contains(sessionId))
            this.sessions.remove(sessionId);
        else
            throw new NoSessionFound(sessionId);
    }
    public void removeSession(HttpSession session) throws NoSessionFound {
        removeSession(session.getId());
    }
    
    // -- Setters & getters
    // Id
    public Long getId() {
        return this.id;
    }
    public void setId(Long newId) {
        this.id = newId;
    }
    // name
    public String getusername() {
        return this.name;
    }
    public void setusername(String newusername) {
        this.name = newusername;
    }
    // Password
    public String getPassword() {
        return this.password;
    }
    public void setPassword(String newPassword) {
        this.password = newPassword;
    }
    // Role
    public Role getRole() {
        return this.role;
    }
    public void setRole(Role role) {
        this.role = role;
    }

    // Exceptions
    public class NoSessionFound extends Exception {
        public NoSessionFound(String sessionId) {
            super("Session with id <" + sessionId + "> could not be found");
        }
    }
}
