package model.services;

public class PaypalService implements OnlinePaymentService {

    public Double paymentFee(Double amount){
        return amount*2/100; 
    }
    public Double interest(Double amount, Integer months){
        return amount*months/100;
    }
}
