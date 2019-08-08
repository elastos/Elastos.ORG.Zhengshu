package org.elastos.dto;

import javax.persistence.*;

@Entity
@Table(name="user",indexes = {@Index(name = "user_did_index", columnList = "did"),
        @Index(name = "user_phone_index", columnList = "phone")})
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name="phone", nullable = false, unique = true)
    private String phone;
    @Column(name="did", unique = true)
    private String did;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getDid() {
        return did;
    }

    public void setDid(String did) {
        this.did = did;
    }
}
