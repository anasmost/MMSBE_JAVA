package local.anas.back_java.model;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import org.hibernate.validator.constraints.Length;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import local.anas.back_java.validation.AccountGroup;
import local.anas.back_java.validation.TokenGroup;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "USERS")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User implements UserDetails {
        @Id
        @Email(message = "Please provide a valid email address",
                        groups = {AccountGroup.class, TokenGroup.class})
        @NotBlank(groups = {AccountGroup.class, TokenGroup.class})
        private String email;

        @Length(min = 3, message = "Username must be at least 3 characters long",
                        groups = {AccountGroup.class})
        @NotBlank(groups = {AccountGroup.class})
        private String username;
        @Length(min = 3, message = "Firstname must be at least 3 characters long",
                        groups = {AccountGroup.class})
        @NotBlank(groups = {AccountGroup.class})
        private String firstname;

        @Length(min = 8, message = "Password must be at least 8 characters long",
                        groups = {AccountGroup.class, TokenGroup.class})
        @NotBlank(groups = {AccountGroup.class, TokenGroup.class})
        private String password;

        @ManyToMany
        @JoinTable(name = "WISH_LISTS", joinColumns = {@JoinColumn(name = "user_id")},
                        inverseJoinColumns = {@JoinColumn(name = "product_id")})
        private Set<Product> wishlist;

        @ManyToMany
        @JoinTable(name = "CARTS", joinColumns = {@JoinColumn(name = "user_id")},
                        inverseJoinColumns = {@JoinColumn(name = "product_id")})
        private Set<Product> cart;

        @Transient
        private final String authority = "USER";

        @Override
        public Collection<? extends GrantedAuthority> getAuthorities() {
                return List.of(() -> this.authority);
        }

}
