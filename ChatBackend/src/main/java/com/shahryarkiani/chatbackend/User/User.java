package com.shahryarkiani.chatbackend.User;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;

@Document("users")
public class User implements UserDetails {

    @Id
    private String id;

    @Indexed
    private String username;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    private Set<String> friends;

    private Set<String> incomingRequests;

    private Set<String> outgoingRequests;

    @JsonCreator
    public User(String username, String password) {
        this.id = null;
        this.username = username;
        this.password = password;
        this.friends = new TreeSet<>();
        this.incomingRequests = new TreeSet<>();
        this.outgoingRequests = new TreeSet<>();
    }

    public User(String id, String username, String password, Set<String> friends, Set<String> outgoingRequests, Set<String> incomingRequests) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.friends = friends;
        this.incomingRequests = new TreeSet<>();
        this.outgoingRequests = new TreeSet<>();
    }

    //This
    public User() {
        this.id = null;
        this.username = null;
        this.password = null;
        this.friends = null;
        this.outgoingRequests = null;
        this.incomingRequests = null;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<String> getFriends() {
        return friends;
    }

    public void setFriends(Set<String> friends) {
        this.friends = friends;
    }

    public Set<String> getOutgoingRequests() { return outgoingRequests; }

    public Set<String> getIncomingRequests() { return incomingRequests; }


    public boolean hasFriend(String friendName) {
        return friends.contains(friendName);
    }



    @Override
    public String getPassword() {
        return password;
    }
    @Override
    public String getUsername() {
        return username;
    }
    @JsonIgnore
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return new ArrayList<>();
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id) && Objects.equals(username, user.username) && Objects.equals(password, user.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, password);
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", friends=" + friends +
                ", incomingRequests=" + incomingRequests +
                ", outgoingRequests=" + outgoingRequests +
                '}';
    }
}
