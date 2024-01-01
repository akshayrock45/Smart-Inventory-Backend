package com.incedo.capstone.smartinventory.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Outwards {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long OutwardsId;
    private String ItemName;
    private int InvoiceNo;
    private String DateOfSupply;
    private String DateOfDelivery;
    private String DeliveredTo;
    private int quantity;
    private String Purpose;
    private int ReceiptNo;
    private String BillValue;
    private String BillCheckedBy;

    @Override
    public String toString() {
        return "Outwards{" +
                "OutwardsId=" + OutwardsId +
                ", ItemName='" + ItemName + '\'' +
                ", InvoiceNo=" + InvoiceNo +
                ", DateOfSupply='" + DateOfSupply + '\'' +
                ", DateOfDelivery='" + DateOfDelivery + '\'' +
                ", DeliveredTo='" + DeliveredTo + '\'' +
                ", quantity=" + quantity +
                ", Purpose='" + Purpose + '\'' +
                ", ReceiptNo=" + ReceiptNo +
                ", BillValue='" + BillValue + '\'' +
                ", BillCheckedBy='" + BillCheckedBy + '\'' +
                '}';
    }

    public Long getOutwardsId() {
        return OutwardsId;
    }

    public void setOutwardsId(Long outwardsId) {
        OutwardsId = outwardsId;
    }

    public String getItemName() {
        return ItemName;
    }

    public void setItemName(String itemName) {
        ItemName = itemName;
    }

    public int getInvoiceNo() {
        return InvoiceNo;
    }

    public void setInvoiceNo(int invoiceNo) {
        InvoiceNo = invoiceNo;
    }

    public String getDateOfSupply() {
        return DateOfSupply;
    }

    public void setDateOfSupply(String dateOfSupply) {
        DateOfSupply = dateOfSupply;
    }

    public String getDateOfDelivery() {
        return DateOfDelivery;
    }

    public void setDateOfDelivery(String dateOfDelivery) {
        DateOfDelivery = dateOfDelivery;
    }

    public String getDeliveredTo() {
        return DeliveredTo;
    }

    public void setDeliveredTo(String deliveredTo) {
        DeliveredTo = deliveredTo;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getPurpose() {
        return Purpose;
    }

    public void setPurpose(String purpose) {
        Purpose = purpose;
    }

    public int getReceiptNo() {
        return ReceiptNo;
    }

    public void setReceiptNo(int receiptNo) {
        ReceiptNo = receiptNo;
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
