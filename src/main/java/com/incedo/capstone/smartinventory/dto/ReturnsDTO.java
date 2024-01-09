package com.incedo.capstone.smartinventory.dto;

public class ReturnsDTO {

    private long InvoiceNo;
    private String ItemName;
    private Double quantity;
    private String DateOfDelivery;
    private String DateOfReturn;
    private long ReceiptNo;
    private String ReceivedBy;
    private String BillValue;
    private String BillCheckedBy;

    public long getInvoiceNo() {
        return InvoiceNo;
    }

    public void setInvoiceNo(long invoiceNo) {
        InvoiceNo = invoiceNo;
    }

    public String getItemName() {
        return ItemName;
    }

    public void setItemName(String itemName) {
        ItemName = itemName;
    }

    public Double getQuantity() {
        return quantity;
    }

    public void setQuantity(Double quantity) {
        this.quantity = quantity;
    }

    public String getDateOfDelivery() {
        return DateOfDelivery;
    }

    public void setDateOfDelivery(String dateOfDelivery) {
        DateOfDelivery = dateOfDelivery;
    }

    public String getDateOfReturn() {
        return DateOfReturn;
    }

    public void setDateOfReturn(String dateOfReturn) {
        DateOfReturn = dateOfReturn;
    }

    public long getReceiptNo() {
        return ReceiptNo;
    }

    public void setReceiptNo(long receiptNo) {
        ReceiptNo = receiptNo;
    }

    public String getReceivedBy() {
        return ReceivedBy;
    }

    public void setReceivedBy(String receivedBy) {
        ReceivedBy = receivedBy;
    }

    public String getBillValue() {
        return BillValue;
    }

    public void setBillValue(String billValue) {
        BillValue = billValue;
    }

    public String getBillCheckedBy() {
        return BillCheckedBy;
    }

    public void setBillCheckedBy(String billCheckedBy) {
        BillCheckedBy = billCheckedBy;
    }
}
