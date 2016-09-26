[["java:package:com.xxx.demo"]]
module demo{
struct Order {
long orderId;
string phone;
string orderNum;
int orderDate;
int ticketType;
double amount;
int orderStatus;
};
  interface MyService{
  Order hello(Order in);
  }  ;
};