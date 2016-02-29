import java.util.ArrayList;


public class ThreadCountRunner
{
	public static void main(String[] args) {
		int numKids = 10;
		ArrayList<Thread> kids = new ArrayList<>();
		
		for (int x = 0; x < numKids; x++)
		{
			Thread boy = new Thread(new Boy());
			Thread girl = new Thread(new Girl());
			kids.add(boy);
			kids.add(girl);
		}
		
		for (int x =0; x < kids.size(); x++)
		{
			kids.get(x).start();
		}
		try
		{
			for (int x = 0; x < kids.size(); x++)
			{
				kids.get(x).join();
			}
		}
		catch (InterruptedException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
