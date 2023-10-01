package com.sygal.spring.login.models;

import jakarta.persistence.*;
import lombok.*;

@Data
@Entity
@Table(name = "roles")
public class Role {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @Enumerated(EnumType.STRING)
  @Column(length = 20)
  private ERole name;

  public Role() {}

  public Role(ERole name) {
    this.name = name;
  }

}
