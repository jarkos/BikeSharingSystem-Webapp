package com.jarkos.bss.persistance.entity;

import com.jarkos.bss.persistance.enums.Role;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Set;

@Entity
public class User extends Persistent implements UserDetails {

    @Size(min = 6, max = 100)
    @Column(nullable = false, length = 100, unique = true)
    private String email;

    @Size(min = 5, max = 50)
    @Pattern(regexp = "^[a-z0-9]*$", message = "Tylko male litery sa dozwolone")
    @Column(nullable = false, length = 50, unique = true)
    private String username;

    @Size(min = 6, max = 50)
    @Column(nullable = false, length = 50)
    private String password;

    @Column(nullable = false)
    private boolean enabled;

    @Column(nullable = false)
    private boolean locked;

    @NotEmpty
    @ElementCollection(targetClass = Role.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"))
    @Column(name = "role", nullable = false)
    private Set<Role> roles;

    @Column(name="account_balance", nullable = false, columnDefinition = "int default 0")
    private Integer accountBalance;

    @OneToOne
    @JoinColumn(name = "bike_id")
    private Bike borrowedBike;

    @OneToOne
    @JoinColumn(name = "bike_booked_id")
    private Bike bookedBike;

    @Column(nullable = false)
    private String fullName;

    @Column(nullable = false)
    private String address;

    @Column
    private boolean verified;

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public boolean isLocked() {
        return locked;
    }

    public void setLocked(boolean locked) {
        this.locked = locked;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    //   Spring Security methods
    @Override
    public Set<? extends GrantedAuthority> getAuthorities() {
        return getRoles();
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonLocked() {
        return !isLocked();
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true; //Not implemented
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true; //Not implemented
    }

    public Integer getAccountBalance() {
        return accountBalance;
    }

    public void setAccountBalance(Integer accountBalance) {
        this.accountBalance = accountBalance;
    }

    public Bike getBorrowedBike() {
        return borrowedBike;
    }

    public void setBorrowedBike(Bike borrowedBike) {
        this.borrowedBike = borrowedBike;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public boolean isVerified() {
        return verified;
    }

    public void setVerified(boolean verified) {
        this.verified = verified;
    }

    public Bike getBookedBike() {
        return bookedBike;
    }

    public void setBookedBike(Bike bookedBike) {
        this.bookedBike = bookedBike;
    }

}
