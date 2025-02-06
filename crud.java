package crudapp;
import java.util.*;
class Items{
	static int tempid=0;
	public int id;
	public String pname;
	public float price;
	public int quantity;
	
	Items(){
		id=0;
		pname=null;
		price=0.0f;
		quantity=0;
				
	}
	Items(String pname,float price,int quantity){
		tempid++;
		this.id=tempid;
		this.pname=pname;
		this.price=price;
		this.quantity=quantity;
		
	}
}

public class crud {
	Scanner sc=new Scanner(System.in);
	
	void Display(Items[] item) {
		System.out.println("---------------------------------------------------------------\n\t\t\tMENU\n---------------------------------------------------------------\n");
		System.out.println("Id\tName\t\tPrice\n"); 
		for(int i=0;i<item.length;i++) {
			System.out.printf("%-5d \t%-15s %-10.2f\n",item[i].id,item[i].pname,item[i].price);
			}System.out.println("---------------------------------------------------------------");		
	}
	
	void OrderSummary(Items[] item) {
		System.out.println("---------------------------------------------------------------\n\t\t\tORDER SUMMARY\n---------------------------------------------------------------\n");
		System.out.println("Id\tName\t\tQuantity\n");
		for(int i=0;i<item.length;i++) {
			if(item[i].quantity!=0)
				System.out.printf("%-5d \t%-15s %-5d\n",item[i].id,item[i].pname,item[i].quantity);
		}
		System.out.println("---------------------------------------------------------------\n");
	}
	
	 void generateOTP() {
		 	Random random=new Random();
			int chance=0;
			System.out.println("Enter OTP to proceed");
			 while(chance<3) {				
				 	int otp = random.nextInt((9999 - 1000) + 1) + 1000;
					System.out.println("OTP: "+otp);
    			 int res=sc.nextInt();
    			 if(res==otp) {
    				 System.out.println("Order confirmed!!");
    				 System.exit(0);
    			 }
    			 else {
    				 chance++;
    				 if(chance==3) {
    	    			 System.out.println("OOPS!!Maximum Attempt reached.");
    	    			 System.exit(0);
    			 }
			 }
	 
	 
	 
	 }
	 }
	
	void Insert(Items[] item) {
		boolean flag=true;		
		while(flag) {
		Display(item);
		System.out.print("Enter the Item ID:  ");
		int id=sc.nextInt();
		System.out.print("\nEnter the Quantity of "+item[id-1].pname+": ");
		item[id-1].quantity+=sc.nextInt();
		OrderSummary(item);
		System.out.print("Do you wish to add more items?(Y/N) ");
		flag=sc.next().equals("Y");
		}
	}
	
	
	void Delete(Items[] item) {
		boolean flag=true;
		while(flag) {
		OrderSummary(item);
		System.out.print("Enter the Item ID:  ");
		int id=sc.nextInt();
		item[id-1].quantity=0;
		System.out.println(item[id-1].pname+" deleted Successfully!!");
		OrderSummary(item);
		System.out.print("Do you wish to delete more items?(Y/N) ");
		flag = sc.next().equals("Y");
		}
		
	}
	
	void Update(Items[] item) {
		int id;
		boolean flag=true;
		while(flag) {
		OrderSummary(item);
		
		
			System.out.println("1.Add\n2.Subtract\n3.Return to Main Page\n");
			switch(sc.nextInt()) {
			case 1:
				System.out.print("Enter the Item ID:  ");
				id=sc.nextInt();
				System.out.print("\nEnter the Quantity of "+item[id-1].pname+": ");
				item[id-1].quantity+=sc.nextInt();
				OrderSummary(item);
				System.out.println(item[id-1].pname+" updated Successfully!!");
				break;
				
			case 2:
				System.out.print("Enter the Item ID:  ");
				id=sc.nextInt();
				System.out.print("\nEnter the Quantity of "+item[id-1].pname+": ");
				item[id-1].quantity-=sc.nextInt();
				OrderSummary(item);
				System.out.println(item[id-1].pname+" updated Successfully!!");
				break;
				
			case 3:
				return;
			
			}
			
		
		OrderSummary(item);
		System.out.print("Do you wish to Update more items?(Y/N) ");
		flag = sc.next().equals("Y");
		}
	}
	
	void CheckOut(Items[] item) {
		float rate=0.0f,total=0.0f;
		boolean flag=true;
		System.out.println("---------------------------------------------------------------\n\t\t\tCHECKOUT\n---------------------------------------------------------------\n");
		System.out.println("Id\tName\t\tQuantity\tPrice\n");
		for(int i=0;i<item.length;i++) {
			if(item[i].quantity!=0) {
				rate=item[i].price*item[i].quantity;
				System.out.printf("%-5d \t%-15s %-5d\t\t%-10.2f\n",item[i].id,item[i].pname,item[i].quantity,rate);
				total+=rate;
				}
		}
		System.out.println("---------------------------------------------------------------\n\t\tTOTAL: Rs."+total+"\n---------------------------------------------------------------\n");
		System.out.print("Proceed to Payment?(Y/N) ");
		flag = sc.next().equals("Y");
		if(flag==true) {
			generateOTP();
		}
		else {
			return;
		}
	}
	
	public static void main(String[] args) {
	crud c=new crud();
	Scanner sc=new Scanner(System.in);
	Items[] item={
		new Items("Burger",50.0f,0),
		new Items("Icecream",100.0f,0),
		new Items("Noodles",150.0f,0),
		new Items("Pizza",200.0f,0),
		new Items("Sandwich",250.0f,0)
	};
	
	int choice=1;
	while(choice!=5) {
		System.out.println("---------------------------------------------------------------\n\t\t\tFOOD ORDER\n---------------------------------------------------------------\n");
		 System.out.println("1->Insert\n2->Delete\n3->Update\n4->CheckOut\n5->Exit\n---------------------------------------------------------------\n");
		 choice=sc.nextInt();
		 switch(choice) {
		 case 1:
			 c.Insert(item);
			 break;
		 case 2:
			 c.Delete(item);
			 break;
		 case 3:
			 c.Update(item);
			 break;
		 case 4:
			 c.CheckOut(item);
			 break;
		 case 5:
			 break;
		 }		
	}
	
}
}
