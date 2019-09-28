package com.example.heroku.model;

import com.example.heroku.utils.Consts;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "user", schema = "public")
public class User implements UserDetails {

    public long getId() {
        return id;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;
    private String pass = "";
    private int type = 0;
    private String email = "";
    private String fio = "";
    private String role = "";
    private String city = "";

    public String getStringType(int language) {
        String[] typesRu = {"Не активирован", "Заблокирован", "Активирован", "Администратор"};
        if (language == 0) {
            switch (type) {
                case Consts.USER_DISABLED:
                    return typesRu[0];
                case Consts.USER_BLOCKED:
                    return typesRu[1];
                case Consts.USER_ENABLED:
                    return typesRu[2];
                case Consts.USER_ADMIN:
                    return typesRu[3];
                default:
                    return "TYPE NULL";
            }
        } else
            return "LANGUAGE NULL";
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> grantedAuths = new ArrayList<>();
        grantedAuths.add(new SimpleGrantedAuthority(role));
        return grantedAuths;
    }

    @Override
    public String getPassword() {
        return pass;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    public User(String pass, String email, String fio,String city) {
        this.pass = pass;
        this.email = email;
        this.fio = fio;
        this.city = city;
    }

    @Override
    public boolean isAccountNonLocked() {
        return type != -3;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return type > 0;
    }


    public void setCity(String region, String district, String city, String address, String postcode) {
        this.city = "РФ" + "$" + region + "$" + district + "$" + city + "$" + address + "$" + postcode;
    }

    public String[] getListOfAddress() {
        if (city != null && !city.equals(""))
            return city.split("\\$");
        else
            return new String[]{"", "", "", "", "", ""};
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFio() {
        return fio;
    }

    public void setFio(String fio) {
        this.fio = fio;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
