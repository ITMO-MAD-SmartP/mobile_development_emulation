package ru.itmo.mobile_development.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder

@Data

public class UsersEntity {


    private Integer id;

    private String name;

}
