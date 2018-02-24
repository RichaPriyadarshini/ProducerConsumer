
public class ProducerConsumer {

	

	public static class Message1{
		private int message;
		boolean valueSet=false;
		public Message1() {
			
		}

		public synchronized void getMessage() {
			while (!valueSet)
			{
				try
				{
					wait();
				}
				catch(Exception e)
				{
					
				}
			}
			
			System.out.println("Get: "+message);
			valueSet=false;
		}

		public synchronized void setMessage(int message) {
			while(valueSet)
			{
				try
				{
					wait();
				}
				catch(Exception e)
				{
					
				}
			}
			System.out.println("put: "+message);
			this.message = message;
			valueSet=true;
		}
		
		
	}
	
	public static class Producer implements Runnable
	{
		Message1 message;
		
		public Producer(Message1 message)
		{
			this.message=message;
			Thread t=new Thread(this, "Producer");
			t.start();
		}
		
		@Override
		public void run() {
			// TODO Auto-generated method stub
			int i=0;
			while(true)
			{
				message.setMessage(i++);
				try {
					Thread.sleep(1000);	
					}
					catch(Exception e){
						
					}
			}
			
		}
		
	}
	
	public static class Consumer implements Runnable
	{
Message1 message;

public  Consumer(Message1 message)
{
	this.message=message;
	Thread t=new Thread(this, "Consumer");
	t.start();
}
		@Override
		public void run() {
			// TODO Auto-generated method stub
			while(true)
			{
				message.getMessage();
				try {
				Thread.sleep(1000);	
				}
				catch(Exception e){
					
				}
				
			}
		}
		
	}
	
	public static void main(String[] args) {
		Message1 message=new Message1();
		
		new Producer(message);
		new Consumer(message);
		
	}
}
