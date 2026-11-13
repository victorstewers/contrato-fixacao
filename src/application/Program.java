package application;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Scanner;

import model.entities.Contract;
import model.entities.Installment;
import model.services.ContractService;
import model.services.PaypalService;

public class Program {
    public static void main(String[] args) throws ParseException {
        Locale.setDefault(Locale.US);
        Scanner sc = new Scanner(System.in);
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        System.out.println("Entre os dados do contrato");
        System.out.print("Numero: ");
        int number = sc.nextInt();
        sc.nextLine();
        System.out.print("Data (dd/MM/yyyy): ");
        Date date = sdf.parse(sc.nextLine());
        System.out.print("Valor do contrato: ");
        double totalValue = sc.nextDouble();
        System.out.print("Entre com o numero de parcelas: ");
        int installmentQuantity = sc.nextInt();

        System.out.println("Parcelas:");
        Contract contract = new Contract(number, date, totalValue);

        ContractService contractService = new ContractService(new PaypalService());
        contractService.processContract(contract,installmentQuantity);

        for(Installment installment : contract.getInstallments()){
            System.out.println(installment.toString());
        }
        sc.close();
    }
}
