package com.example.ui.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name="contracts")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Contract {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "start")
    private LocalDate start;

    @Column(name = "number")
    private String number;

    @Column(name = "update")
    private LocalDate update;

    @Column(name = "check_box")
    private Boolean checkBox;
}
