package com.springboot.jwt.model;


import lombok.*;

import javax.persistence.*;

@Entity
@Table(name="role")
@Builder(toBuilder = true)
@Setter
@Getter
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@NoArgsConstructor(access = AccessLevel.PACKAGE)
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="role_type")
    private String roleType;

    @Column(name="description")
    private String description;

}
