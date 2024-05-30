package ru.itmo.mobile_development.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;


@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder

@Data

public class GadgetEntity {




    private Integer id;


    private String name;


    private Boolean state;


    private Double value;
}
