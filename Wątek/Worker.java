package demo2;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Worker {

	
	Random random=new Random();
	
	// zmiany:)
	
	private Object lock1=new Object();
	private Object lock2=new Object();
	
	private List <Integer> list1 = new ArrayList<Integer>();
	private List <Integer> list2 = new ArrayList<Integer>();
	
	public  void  stageOne(){
		
		synchronized (list1) {
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				
				e.printStackTrace();
			}
		}
		
		list1.add(random.nextInt(100));
	}
	public  void stageTwo(){
		
		
		synchronized (list2) {
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				
				e.printStackTrace();
			}
		}
		
		list2.add(random.nextInt(100));
	}
	
	public void proces(){
		for(int i=0; i<1000;i++){
			stageOne();
			stageTwo();
		}
	}
	public  void main() {
		System.out.println("Starting.......");
		
		long start=System.currentTimeMillis();
		
		 Thread t1=new Thread(new Runnable() {
			
			@Override
			public void run() {
				proces();
				
			}
		});
		 t1.start();
		 
		 try {
			t1.join();
			
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
		 Thread t2=new Thread(new Runnable() {
				
				@Override
				public void run() {
					proces();
					
				}
			});
		 t2.start();
		 try {
			t2.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
			 
		
		long end=System.currentTimeMillis();
		
		System.out.println("Time taken " +(start- end));
		
		System.out.println("List1 " + list1.size() + " List2 " + list2.size());

	}

}
