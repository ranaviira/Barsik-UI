package com.example.ui.service;

import com.example.ui.entity.Contract;
import com.example.ui.repository.ContractRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class ContractService {

    private final ContractRepository contractRepository;

    @Autowired
    public ContractService(ContractRepository contractRepository) {
        this.contractRepository = contractRepository;
    }

    /**
     * Метод берет все данные из базы данных и обновляет checkBox,
     * true если дата последнего обновления договора меньше текущей даты на 60 дней
     * @return List<Contract>
     */
    public List<Contract> getAllContracts() {
        List<Contract> contractList = contractRepository.findAll();
        for (Contract list : contractList) {
            LocalDate localDateUpdate = list.getUpdate();
            LocalDate localDateNow = LocalDate.now().minusDays(60);
            list.setCheckBox(localDateUpdate.isAfter(localDateNow));
        }
        return contractList;
    }
}
