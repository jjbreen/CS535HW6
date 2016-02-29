import java.util.Random;


public class Girl extends Kid implements Runnable
{
	public static int girlsAcquired = 0;
	public static int girlsReleased = 0;
	public static boolean girls = false;
	public static Object glock = new Object();
	
	@Override
	public void run()
	{
		while(true)
		{
			try
			{
				enterFemale();
				System.out.println("Girl Entering Bathroom!");
				//bathroom.add(this);
				gotoBathroom();
				//bathroom.remove(this);
				System.out.println("Girl Leaving Bathroom!");
				leaveFemale();
				Thread.sleep(rand.nextInt(200));
			}
			catch (InterruptedException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public void enterFemale() throws InterruptedException
	{
		synchronized(glock)
		{
			while(Boy.boys)
			{
				glock.wait();
			}
			if (girlsAcquired == girlsReleased)
			{
				girls = true;
			}
		}
		
		synchronized(lock)
		{
			while (Boy.boysAcquired != Boy.boysReleased)
			{
				lock.wait();
			}
			girlsAcquired++;
		}
	}
	
	public void leaveFemale()
	{
		synchronized(Boy.block)
		{
			girls = false;
			Boy.block.notifyAll();
		}
		synchronized(lock)
		{
			girlsReleased++;
			if (girlsAcquired == girlsReleased)
			{
				lock.notifyAll();
			}
		}
	}
	
	public void gotoBathroom()
	{
//		for (int x = 0; x < bathroom.size(); x++)
//		{
//			if (!(bathroom.get(x) instanceof Girl))
//			{
//				System.out.println(bathroom.get(x).toString());
//				throw new IllegalArgumentException("Girls and Boys Can't Be in Bathroom at Same Time");
//			}
//		}
		try
		{
			Thread.sleep(5);
		}
		catch (InterruptedException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public String toString()
	{
		return "I'm a Girl!!";
	}
}
