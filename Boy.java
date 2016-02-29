import java.util.ArrayList;
import java.util.Queue;


public class Boy extends Kid implements Runnable
{
	public static int boysAcquired = 0;
	public static int boysReleased = 0;
	public static boolean boys = false;
	public static Object block = new Object();
	
	
	@Override
	public void run()
	{
		while(true)
		{
			try
			{
				enterMale();
				System.out.println("Boy Entering Bathroom!");
				//bathroom.add(this);
				gotoBathroom();
				//bathroom.remove(this);
				System.out.println("Boy Leaving Bathroom!");
				leaveMale();
				Thread.sleep(rand.nextInt(200));
			}
			catch (InterruptedException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public void enterMale() throws InterruptedException
	{
		synchronized(block)
		{
			while(Girl.girls)
			{
				block.wait();
			}
			if (boysAcquired == boysReleased)
			{
				boys = true;
			}
		}
		
		synchronized(lock)
		{
			
			while (Girl.girlsAcquired != Girl.girlsReleased)
			{
				lock.wait();
			}
			boysAcquired++;
		}
	}
	
	public void leaveMale()
	{
		synchronized(Girl.glock)
		{
			boys = false;
			Girl.glock.notifyAll();
		}
		
		synchronized(lock)
		{
			boysReleased++;
			if (boysAcquired == boysReleased)
			{
				lock.notifyAll();
			}
		}
	}
	
	public void gotoBathroom()
	{
//		for (int x = 0; x < bathroom.size(); x++)
//		{
//			if (!(bathroom.get(x) instanceof Boy))
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
		return "I'm a Boy!!";
	}
}
