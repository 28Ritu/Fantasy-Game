import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Random;
import java.util.StringTokenizer;

class InReader {
    static BufferedReader reader;
    static StringTokenizer tokenizer;
    
    /** call this method to initialize reader for InputStream */
    static void init(InputStream input) {
        reader = new BufferedReader(
                     new InputStreamReader(input) );
        tokenizer = new StringTokenizer("");
    }

    /** get next word */
    static String next() throws IOException {
        while ( ! tokenizer.hasMoreTokens() ) {
            //TODO add check for eof if necessary
            tokenizer = new StringTokenizer(
                   reader.readLine() );
        }
        return tokenizer.nextToken();
    }

    static int nextInt() throws IOException {
        return Integer.parseInt( next() );
    }
    
    static double nextDouble() throws IOException {
        return Double.parseDouble( next() );
    }
    
    static float nextFloat() throws IOException {
    	return Float.parseFloat( next() );
    }
}
class Creature
{
	protected String name;
	int power, health, cost, asset;
	public Creature()
	{
		name="";
		power=health=cost=asset=0;
	}
	public String getname()
	{
		return name;
	}
	public void setname(String nm)
	{
		name=nm;
	}
}
class Humans extends Creature
{
	public Humans(int c, int a, int p, int h)
	{
		cost=c;
		asset=a;
		power=p;
		health=h;
	}
}
class Dragons extends Creature
{
	public Dragons()
	{
		cost=asset=power=health=0;
	}
	public int damagedone()
	{
		Random r=new Random();
		float chance=r.nextFloat();
		System.out.print(chance);
		if (chance>=0.15)
			return 25;
		else
			return 0;  
	}
}
class Fire_Dragons extends Dragons
{
	public Fire_Dragons(int c, int a, int p, int h)
	{
		cost=c;
		asset=a;
		power=p;
		health=h;
	}
}
class Ice_Dragons extends Dragons
{
	public Ice_Dragons(int c, int a, int p, int h)
	{
		cost=c;
		asset=a;
		power=p;
		health=h;
	}
	public boolean damage()
	{
		Random r=new Random();
		float chance=r.nextFloat();
		System.out.print(chance);
		if (chance>=0.05)
			return true;
		else
			return false;
	}
}
class Daemons extends Creature
{
	public Daemons(int c, int a, int p, int h)
	{
		cost=c;
		asset=a;
		power=p;
		health=h;
	}
	public boolean damagedone()
	{
		Random r=new Random();
		float chance=r.nextFloat();
		System.out.print(chance);
		if (chance>=0.5)
			return true;
		else
			return false;
	}
}
class Wolves extends Creature
{
	public Wolves(int c, int a, int p, int h)
	{
		cost=c;
		asset=a;
		power=p;
		health=h;
	}
}
class TeamGood 
{
	int Money_Good, no;
	Humans[] hm=new Humans[20];
	Fire_Dragons[] fdr=new Fire_Dragons[20];
	Wolves[] wv=new Wolves[20];
	public TeamGood(int m)
	{
		Money_Good=m;
		no=0;
	}
}
class TeamBad
{
	int Money_Bad, no;
	Ice_Dragons[] idr=new Ice_Dragons[20];
	Daemons[] dm=new Daemons[20];
	public TeamBad(int m)
	{
		Money_Bad=m;
		no=0;
	}
}
public class Game {
	TeamGood team1;
	TeamBad team2;
	
	Creature player1;
	Creature player2;

	int pr1_code, pr2_code;
	public void Player1(String pr1, int nh, int nf, int nw)
	{
		boolean found=false;
		for (int i=0; i<nh; i++)
		{
			if (pr1.equalsIgnoreCase(team1.hm[i].name)==true)
			{
				player1=team1.hm[i];
				pr1_code=1;
				found=true;
				break;
			}
		}
		if (found==false)
		{
			for (int i=0; i<nf; i++)
			{
				if (pr1.equalsIgnoreCase(team1.fdr[i].name)==true)
				{
					player1=team1.fdr[i];
					pr1_code=2;
					found=true;
					break;
				}
			}
		}
		if (found==false)
		{
			for (int i=0; i<nw; i++)
			{
				if (pr1.equalsIgnoreCase(team1.wv[i].name)==true)
				{
					player1=team1.wv[i];
					pr1_code=3;
					found=true;
					break;
				}
			}
		}
	}
	public void Player2(String pr2, int nd, int ni)
	{
		boolean found=false;
		for (int i=0; i<nd; i++)
		{
			if (pr2.equalsIgnoreCase(team2.dm[i].name)==true)
			{
				player2=team2.dm[i];
				pr2_code=1;
				found=true;
				break;
			}
		}
		if (found==false)
		{
			for (int i=0; i<ni; i++)
			{
				if (pr2.equalsIgnoreCase(team2.idr[i].name)==true)
				{
					player2=team2.idr[i];
					pr2_code=2;
					found=true;
					break;
				}
			}
		}
	}
	public void GameBegins()
	{
		Random r=new Random();
		int chance=r.nextInt();
		int pr1_h=r.nextInt(player2.power-0)+0;
		int pr2_h=r.nextInt(player1.power-0)+0;
		System.out.print(pr1_h+" "+pr2_h);
		if (pr1_code==2)
			pr2_h+=team1.fdr[0].damagedone();
		if (pr2_code==1)
			if (team2.dm[0].damagedone()==true)
				pr1_h+=pr1_h;
		else if (pr2_code==2)
			if (team2.idr[0].damage()==true)
				pr1_h+=team2.idr[0].damagedone()+(r.nextInt(player2.power-0)+0);
		
		player1.health-=pr1_h;
		player2.health-=pr2_h;
	}
	public static void main(String []args) throws IOException
	{
		InReader.init(System.in);
		Game game=new Game();
		System.out.println("Team Good's total money");
		int money1=InReader.nextInt();
		game.team1=new TeamGood(money1);
		System.out.println("Team Bad's total money");
		int money2=InReader.nextInt();
		game.team2=new TeamBad(money2);
		
		int[] human=new int[4];
		System.out.println("Enter cost,​ asset​, power​ ​and​ ​health​ ​for​ ​Human​ ​(space-separated)​-");
		for (int i=0; i<4; i++)
			human[i]=InReader.nextInt();
		Humans h=new Humans(human[0], human[1], human[2], human[3]);
		
		int[] fdragon=new int[4];
		System.out.println("Enter cost,​ asset​, power​ ​and​ ​health​ ​for​ ​Fire Dragon ​(space-separated)​-");
		for (int i=0; i<4; i++)
			fdragon[i]=InReader.nextInt();
		Fire_Dragons fd=new Fire_Dragons(fdragon[0], fdragon[1], fdragon[2], fdragon[3]);
		
		int[] idragon=new int[4];
		System.out.println("Enter cost,​ asset​, power​ ​and​ ​health​ ​for​ ​Ice Dragon ​(space-separated)​-");
		for (int i=0; i<4; i++)
			idragon[i]=InReader.nextInt();
		Ice_Dragons id=new Ice_Dragons(idragon[0], idragon[1], idragon[2], idragon[3]);
		
		int[] daemon=new int[4];
		System.out.println("Enter cost,​ asset​, power​ ​and​ ​health​ ​for​ Daemon ​(space-separated)​-");
		for (int i=0; i<4; i++)
			daemon[i]=InReader.nextInt();
		Daemons dae=new Daemons(daemon[0], daemon[1], daemon[2], daemon[3]);
		
		int[] wolf=new int[4];
		System.out.println("Enter cost,​ asset​, power​ ​and​ ​health​ ​for​ Wolf ​(space-separated)​-");
		for (int i=0; i<4; i++)
			wolf[i]=InReader.nextInt();
		Wolves wf=new Wolves(wolf[0], wolf[1], wolf[2], wolf[3]);
		
		int choice1=0, choice2=0, prev_choice=0;
		boolean flag=((money1>=h.cost) || (money1>=fd.cost) || (money1>=wf.cost));
		boolean flag2=((money2>=dae.cost) || (money2>=id.cost));
		boolean player1_lose=true, player2_lose=true;
		boolean raise_flag=false, tie=false;
		
		int nh=0, nf=0, nw=0, ni=0, nd=0, round=1;
		String s="", pl1="", pl2="";
		do
		{
			while(choice1!=4 && flag==true)
			{
				System.out.println("Select Creatures For Team Good:");
				if (prev_choice!=1)
					System.out.println("1. Human");
				if (prev_choice!=2)
					System.out.println("2. Fire Dragon");
				if (prev_choice!=3)
					System.out.println("3. Wolf");
				System.out.println("4. Exit Selection");
				choice1=InReader.nextInt();
				switch(choice1)
				{
				case 1: if (money1>=h.cost)
				        {
					       money1-=h.cost;
		                   System.out.println("Enter Name Of The Human -");
		                   s=InReader.next();
		                   game.team1.hm[nh]=new Humans(human[0], human[1], human[2], human[3]);
		                   game.team1.hm[nh].setname(s);
		                   nh+=1;
		                   game.team1.no+=1;
				        }
			 	        else
			 	        {
			 	        	System.out.println("Please Select Again!!!");
			           	    prev_choice=choice1;
			 	        }
				        break;
				case 2: if (money1>=fd.cost)
				        {
					       money1-=fd.cost;
		                   System.out.println("Enter Name Of The Fire-Dragon -");
		                   s=InReader.next();
		                   game.team1.fdr[nf]=new Fire_Dragons(fdragon[0], fdragon[1], fdragon[2], fdragon[3]);
		                   game.team1.fdr[nf].setname(s);
		                   nf+=1;
		                   game.team1.no+=1;
				        }
				        else
	 	                {
				    	   System.out.println("Please Select Again!!!");
			          	   prev_choice=choice1;
	 	                }
				        break;
				case 3: if (money1>=wf.cost)
				        {    
				           money1-=wf.cost;
		                   System.out.println("Enter Name Of The Wolf -");
		                   s=InReader.next();
		                   game.team1.wv[nw]=new Wolves(wolf[0], wolf[1], wolf[2], wolf[3]);
		                   game.team1.wv[nw].setname(s);
		                   nw+=1;
		                   game.team1.no+=1;
				        }
	 	                else
	 	                {
	 	            	   System.out.println("Please Select Again!!!");
	 	             	   prev_choice=choice1;
	 	                }
				        break;
				case 4: break;
				}
			   flag=((money1>=h.cost) || (money1>=fd.cost) || (money1>=wf.cost));
			   game.team1.Money_Good=money1;
			}
						
			while(choice2!=3 && flag2==true)
			{
				System.out.println("Select Creatures For Team Bad:");
				if (prev_choice!=1)
					System.out.println("1. Daemon");
				if (prev_choice!=2)
					System.out.println("2. Ice Dragon");
				System.out.println("3. Exit Selection");
				choice2=InReader.nextInt();
				switch(choice2)
				{
				case 1: if (money2>=dae.cost)
				        {
					       money2-=dae.cost;
		                   System.out.println("Enter Name Of The Daemon -");
		                   s=InReader.next();
		                   game.team2.dm[nd]=new Daemons(daemon[0], daemon[1], daemon[2], daemon[3]);
		                   game.team2.dm[nd].setname(s);
		                   nd+=1;
		                   game.team2.no+=1;
				        }
			 	        else
			 	        {
			 	        	System.out.println("Please Select Again!!!");
							prev_choice=choice2;
			 	        }
				        break;
				case 2: if (money2>=id.cost)
				        {
					       money2-=id.cost;
		                   System.out.println("Enter Name Of The Ice-Dragon -");
		                   s=InReader.next();
		                   game.team2.idr[ni]=new Ice_Dragons(idragon[0], idragon[1], idragon[2], idragon[3]);
		                   game.team2.idr[ni].setname(s);
		                   ni+=1;
		                   game.team2.no+=1;
				        }
				        else
	 	                {
				    	   System.out.println("Please Select Again!!!");
						   prev_choice=choice2;
	 	                }
				        break;
				case 3: break;
				}				    	 
				flag2=((money2>=dae.cost) || (money2>=id.cost));
				game.team2.Money_Bad=money2;
			}
			
			if (choice1==4 && choice2==3 && game.team1.no==0 && game.team2.no==0) 
			{
				break;
			}
			else
			{
			if (round==1)
				System.out.println("The War Begins -");
			System.out.println("Round-"+round+":");
			if (player1_lose==true && tie==false)
			{
			  System.out.println("Enter Creature from Good's Team to fight in the war -");
			  pl1=InReader.next();
			  game.Player1(pl1, nh, nf, nw);
			}
			if (player2_lose==true && tie==false)
			{
			   System.out.println("Enter Creature from Bad's Team to fight in the war -");
			   pl2=InReader.next();
			   game.Player2(pl2, nd, ni);
			}
			if (round!=1 && tie==true)
				game.GameBegins();
			game.GameBegins();
			if (game.player1.health>0 && game.player2.health>0)
			{
				System.out.println("Tie!!! Round "+round+" over."+" "+game.player1.health+" "+" "+game.player2.health);
				round+=1;
				tie=true;
			}
			if (game.player1.health<=0 && game.player2.health>0)
			{
				System.out.println(pl1+" loses in round "+round+" "+game.player1.health);
				game.team2.Money_Bad+=game.player2.asset;
				money2=game.team2.Money_Bad;
				System.out.println("Money Of Bad's Team is "+money2);
				System.out.println("Money Of Good's Team is "+money1);
				round+=1;
				player2_lose=false;
				player1_lose=true;
				tie=false;
				game.team1.no-=1;
				raise_flag=true;
			}
			else if (game.player1.health>0 && game.player2.health<=0)
			{
				System.out.println(pl2+" loses in round "+round+" "+game.player2.health);
				game.team1.Money_Good+=game.player1.asset;
				money1=game.team1.Money_Good;
				System.out.println("Money Of Bad's Team is "+money2);
				System.out.println("Money Of Good's Team is "+money1);
				round+=1;
				player1_lose=false;
				player2_lose=true;
				tie=false;
				game.team2.no-=1;
				raise_flag=true;
			}
			else if (game.player1.health<=0 && game.player2.health<=0)
			{
				game.team1.no-=1;
				game.team2.no-=1;
				System.out.println("Tie!!! Round "+round+" over."+" "+game.player1.health+" "+game.player2.health);
				round+=1;
				tie=true;
				raise_flag=false;
			}
			flag=((money1>=h.cost) || (money1>=fd.cost) || (money1>=wf.cost));
		    flag2=((money2>=dae.cost) || (money2>=id.cost));
		    choice1=choice2=0;
			}
		}while((flag==true || game.team1.no>0) && (flag2==true || game.team2.no>0));
		if (flag==false && game.team1.no<=0 && (choice1!=4 || choice2!=3))
		{
			if (raise_flag==false)
			{
				System.out.println("Round-"+round+":");
				System.out.println(pl1+" loses in round "+round+" "+game.player1.health);
				game.team2.Money_Bad+=game.player2.asset;
				money2=game.team2.Money_Bad;
				money1=game.team1.Money_Good;
				System.out.println("Money Of Bad's Team is "+money2);
				System.out.println("Money Of Good's Team is "+money1);
			}
				System.out.println();
				System.out.println("Team Bad wins the war. The money the team has is "+money2);
			
		}
		else if (flag2==false && game.team2.no<=0 && (choice1!=4 || choice2!=3))
		{
			if (raise_flag==false)
			{
				System.out.println("Round-"+round+":");
				System.out.println(pl2+" loses in round "+round+" "+game.player2.health);
				game.team1.Money_Good+=game.player1.asset;
				money2=game.team2.Money_Bad;
				money1=game.team1.Money_Good;
				System.out.println("Money Of Bad's Team is "+money2);
				System.out.println("Money Of Good's Team is "+money1);
			}
				System.out.println();
				System.out.println("Team Good wins the war. The money the team has is "+money1);
			
		}
	}
}