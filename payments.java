import java.util.ArrayList;
import java.util.List;

public class Payments {

    public enum Status {
        PENDING, SUCCESS, FAILED
    }

    public static class Payment {
        private String id;
        private double amount;
        private String currency;
        private Status status;

        public Payment(String id, double amount, String currency) {
            this.id = id;
            this.amount = amount;
            this.currency = currency;
            this.status = Status.PENDING;
        }

        public String getId() {
            return id;
        }

        public double getAmount() {
            return amount;
        }

        public String getCurrency() {
            return currency;
        }

        public Status getStatus() {
            return status;
        }

        public void setStatus(Status status) {
            this.status = status;
        }

        @Override
        public String toString() {
            return String.format("Payment[id=%s, amount=%.2f %s, status=%s]",
                    id, amount, currency, status);
        }
    }

    private final List<Payment> payments = new ArrayList<>();

    public Payment createPayment(String id, double amount, String currency) {
        Payment payment = new Payment(id, amount, currency);
        payments.add(payment);
        return payment;
    }

    public boolean processPayment(String id) {
        for (Payment p : payments) {
            if (p.getId().equals(id)) {
                if (p.getAmount() <= 0) {
                    p.setStatus(Status.FAILED);
                    return false;
                }
                p.setStatus(Status.SUCCESS);
                return true;
            }
        }
        throw new IllegalArgumentException("Payment not found: " + id);
    }

    public List<Payment> getAllPayments() {
        return payments;
    }

    public static void main(String[] args) {
        Payments payments = new Payments();

        payments.createPayment("PAY001", 250.00, "USD");
        payments.createPayment("PAY002", 75.50, "EUR");
        payments.createPayment("PAY003", -10.00, "USD"); // will fail

        payments.processPayment("PAY001");
        payments.processPayment("PAY002");
        payments.processPayment("PAY003");

        for (Payment p : payments.getAllPayments()) {
            System.out.println(p);
        }
    }
}
