package uz.pdp.springbootprojectprocesses.entity.Les9;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import uz.pdp.springbootprojectprocesses.entity.enums.SystemRoleName;
import uz.pdp.springbootprojectprocesses.entity.les10_1.Attachment;
import uz.pdp.springbootprojectprocesses.entity.template.AbsUUIDEntity;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity(name = "users")
public class User  extends AbsUUIDEntity implements UserDetails {


    private String  fullName;

    @Column(nullable = false, unique = true)
    private String  email;

    private String  password;

    private String  color;

    private String  initialLetter;

    @OneToOne(fetch = FetchType.LAZY)
    private Attachment avatar;

    private String   emailCode;

    private Timestamp lastActiveTime;

/*

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Role  role;
*/


    @Enumerated(EnumType.STRING)
    private SystemRoleName systemRoleName;


    private boolean  enabled;
    private boolean  accountNonExpired = true;
    private boolean  accountNonLocked= true;
    private boolean  credentialsNonExpired= true;


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singletonList(new SimpleGrantedAuthority(this.systemRoleName.name()));
    }


    @Override
    public String getUsername() {
        return this.email;
    }


    @Override
    public boolean isAccountNonExpired() {
        return this.accountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return this.accountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return this.credentialsNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return this.enabled;
    }

    public User(String fullName, String email, String password, SystemRoleName systemRoleName) {
        this.fullName = fullName;
        this.email = email;
        this.password = password;
        this.systemRoleName = systemRoleName;
    }

}
