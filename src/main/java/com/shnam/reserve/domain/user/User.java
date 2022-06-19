package com.shnam.reserve.domain.user;

import com.shnam.reserve.domain.BaseTimeEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter @Setter
@Entity
@Table(name = "USERS")
public class User extends BaseTimeEntity {

    @Id @GeneratedValue
    private Long id;
}
