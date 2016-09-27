package com.jrtech.templates.services;

import com.jrtech.ffmp.data.entities.Contract;
import com.jrtech.ffmp.data.entities.Payment;
import com.jrtech.ffmp.data.repositories.ContractRepository;
import com.jrtech.ffmp.data.repositories.CustomerRepository;
import com.jrtech.templates.vo.ContractVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by suelmer on 2016/7/18.
 */
@Service
public class ContractServiceImpl implements ContractService {
    @Autowired
    private ContractRepository contractRepository;
    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public Contract save(ContractVO contractVO) {
        Contract contract = new Contract();
        contract.setAddress(contractVO.getAddress());
        contract.setAgent(contractVO.getAgent());
        contract.setAmount(contractVO.getAmount());
        contract.setContent(contractVO.getContent());
        contract.setContractType(contractVO.getContractType());
        contract.setCustomer(customerRepository.findOne(contractVO.getCustomerId()));
        contract.setExpiry(contractVO.getExpiry());
        contract.setManager(contractVO.getManager());
        contract.setManagerTel(contractVO.getManagerTel());
        contract.setName(contractVO.getName());
        contract.setTaxNO(contractVO.getTaxNO());
        contract.setCreateTime(new Date());
        List<Payment> payments = new ArrayList<>();
        contractVO.getPaymentVOList().forEach(item -> {
            Payment payment = new Payment(item.getPaymentAmount(),item.isConfirmation(), item.getPaymentDate(), item.getPeriod(), item.getReceipt());
            payments.add(payment);

        });
        contract.setPaymentList(payments);
        return contractRepository.save(contract);
    }

    @Override
    public Contract findOne(String id) {
        return contractRepository.findOne(id);
    }

    @Override
    public Page<Contract> findAll(Specification<Contract> spec, Pageable pageable) {
        return contractRepository.findAll(spec, pageable);
    }
}
