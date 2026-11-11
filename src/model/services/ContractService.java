package model.services;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import model.entities.Contract;
import model.entities.Installment;

public class ContractService {

    private OnlinePaymentService onlinePaymentService;

    public ContractService(OnlinePaymentService onlinePaymentService){
        this.onlinePaymentService = onlinePaymentService;
    }

    public void processContract(Contract contract, Integer months){
        double valueContract = contract.getTotalValue();
        double installmentInitialValue = valueContract / months;
        List<Installment> installments = new ArrayList<>();

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        for(int i = 1; i<=months;i++){
            double installmentValueInterest = onlinePaymentService.interest((installmentInitialValue),i);
            double installmentPaymentFee = onlinePaymentService.paymentFee(installmentInitialValue + installmentValueInterest );
            double finalValueInstallment =  installmentInitialValue + installmentPaymentFee + installmentValueInterest;

            Date dueDate = new Date(contract.getDate().getTime());

            dueDate.setMonth(dueDate.getMonth()+i);

            installments.add(new Installment(dueDate, finalValueInstallment));

        }
        contract.setInstallments(installments);
    }

}
