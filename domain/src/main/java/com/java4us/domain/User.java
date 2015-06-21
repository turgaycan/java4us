package com.java4us.domain;

// Generated Jan 14, 2014 10:36:40 PM by Hibernate Tools 3.6.0

import com.java4us.domain.common.enums.BaseStatus;
import com.java4us.domain.common.enums.UserType;
import com.java4us.domain.core.BaseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;

@Entity
@Table(name = "user", schema = "java4us")
@SequenceGenerator(name = "user_id_seq", sequenceName = "user_id_seq", allocationSize = 1)
public class User extends BaseEntity implements UserDetails, Serializable {

	private static final long serialVersionUID = 8403804704291870939L;

	@Id
	@Column(name = "id", nullable = false, unique = true)
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "user_id_seq")
	private Long id;
	@Column(name = "email", nullable = false, unique = true)
	private String email;
	@Column(name = "password", nullable = false)
	private String password;
	@Enumerated(EnumType.STRING)
	@Column(name = "status", nullable = false)
	private BaseStatus status;
	@Column(name = "createdate", nullable = false)
	@Temporal(javax.persistence.TemporalType.TIMESTAMP)
	private Date createDate;
	@Column(name = "login")
	private String login;
	@OneToMany(mappedBy = "userId", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private Set<UserRoles> userRoles;

	public User() {
	}

	public User(String email) {
		this.email = email;
	}

	public User(Long id, String email, String login, String password,
			BaseStatus status) {
		super();
		this.id = id;
		this.email = email;
		this.login = login;
		this.password = password;
		this.status = status;
	}

	public User(String email, String login, String password, Date createDate, BaseStatus status) {
		this.email = email;
		this.login = login;
		this.password = password;
		this.createDate = createDate;
		this.status = status;
	}

	@Override
	public Long getId() {
		return this.id;
	}

	@Override
	public void setId(Long id) {
		this.id = id;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public BaseStatus getStatus() {
		return status;
	}

	public void setStatus(BaseStatus status) {
		this.status = status;
	}

	public Set<UserRoles> getUserRoles() {
		return userRoles;
	}

	public void setUserRoles(Set<UserRoles> userRoles) {
		this.userRoles = userRoles;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public Boolean hasRole(String role) {
		if (role != null) {
			for (UserRoles currentRole : userRoles) {
				if (currentRole.equals(role)) {
					return true;
				}
			}
		}
		return false;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (id != null ? id.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are
		// not set
		if (!(object instanceof User)) {
			return false;
		}
		User other = (User) object;
		return (this.id != null || other.id == null)
				&& (this.id == null || this.id.equals(other.id));
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		List<GrantedAuthority> authorities = new ArrayList<>();
		for (UserType role : UserType.values()) {
			authorities.add(new SimpleGrantedAuthority(role.name()));
		}
		return authorities;
	}

	@Override
	public String getUsername() {
		return email;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	public List<GrantedAuthority> buildUserAuthority() {
		Set<GrantedAuthority> setAuths = new HashSet<>();
		getUserRoles().stream().forEach(
				(userRole) -> {
					setAuths.add(new SimpleGrantedAuthority(userRole.getRole()
							.toString()));
				});
		List<GrantedAuthority> authList = new ArrayList<>(setAuths);
		return authList;
	}

	public void addRole(UserRoles role) {
		this.userRoles.add(role);
		role.setUserId(this);
	}

}
