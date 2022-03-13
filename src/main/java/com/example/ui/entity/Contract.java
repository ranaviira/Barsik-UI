package com.example.ui.entity;

import lombok.Data;

import javax.persistence.Id;
import java.time.LocalDate;

@Data
public class Contract {

    @Id
    private Long id;
    private LocalDate startDate;
    private String ContractNumber;
    private LocalDate updateDate;
    private Boolean checkBox;
}
