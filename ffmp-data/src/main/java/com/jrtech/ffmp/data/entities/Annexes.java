package com.jrtech.ffmp.data.entities;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.util.Date;

/**
 * 附件
 * Created by suelmer on 2016/7/16.
 */
@Entity
@Table(name = "Annexes")
public class Annexes extends AbstractDocumentObject{
    //合同
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "contract_id", nullable = true)
    private Contract contract;

    public Contract getContract() {
        return contract;
    }

    public void setContract(Contract contract) {
        this.contract = contract;
    }

}
