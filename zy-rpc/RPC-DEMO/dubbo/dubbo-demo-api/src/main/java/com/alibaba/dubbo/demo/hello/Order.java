

package com.alibaba.dubbo.demo.hello;

public class Order implements java.lang.Cloneable, java.io.Serializable
{
    public long orderId;

    public String phone;

    public String orderNum;

    public int orderDate;

    public int ticketType;

    public double amount;

    public int orderStatus;

    public Order()
    {
        phone = "";
        orderNum = "";
    }

    public Order(long orderId, String phone, String orderNum, int orderDate, int ticketType, double amount, int orderStatus)
    {
        this.orderId = orderId;
        this.phone = phone;
        this.orderNum = orderNum;
        this.orderDate = orderDate;
        this.ticketType = ticketType;
        this.amount = amount;
        this.orderStatus = orderStatus;
    }




    public static final long serialVersionUID = -634673034L;
}
