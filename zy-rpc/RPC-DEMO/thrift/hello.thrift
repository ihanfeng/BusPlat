namespace java com.th.hello

struct Order {
    1: i64 orderId       
    2: string phone      
    3: string name     
    4: string orderNum  
    5: i32 orderDate   
    6: i32 ticketType 
    7: double amount   
    8: i32 orderStatus   

}


service MobileService {

    Order hello(1:Order order),
   
}
