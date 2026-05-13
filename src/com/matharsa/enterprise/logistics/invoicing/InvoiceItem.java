package project09.business.invoicing;

public class InvoiceItem {
    String description;
    int qty;
    double unitPrice;

    public InvoiceItem(String description, int qty, double unitPrice) {
        this.description = description;
        this.qty = qty;
        this.unitPrice = unitPrice;
    }

    public double getNetPrice() {
        return qty * unitPrice;
    }
}
