package Monster;
import java.lang.reflect.*;
import java.io.*;
import java.util.*;

class Monster{
	String eyeColor;
	String featherColor;
	String magicalAbilities;
	String size;
	String strength;
	String durability;
	String weakness;
	String agressionLevel;
	public static Monster createBabyMonster(Monster parent1, Monster parent2)
	{
		Monster baby = new Monster();
		Field[] fields = Monster.class.getDeclaredFields();
		for(Field f:fields)
		{
			f.setAccessible(true);
			try {
				if(new Random().nextBoolean())
				{
				  f.set(baby, f.get(parent1));
				}
				else
				{
					f.set(baby, f.get(parent2));
				}
			}catch(IllegalAccessException e)
			{
				e.printStackTrace();
			}
		}
		return baby;
		
	}
}

public class MonsterMaker {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		List<Monster> arr = new  ArrayList<Monster>();
		System.out.println("Enter the values of parent");
		arr.add(createMonster());
		System.out.println("Enter value of another parent");
		arr.add(createMonster());
		String ans= "yes";
		while(ans=="yes") {
			arr.add(createMonster());
			System.out.println("Do you want to create more monster, Enter yes or no");
			ans = sc.nextLine();
			}
		int n = arr.size();
		HashSet<Monster> babyMonsters = new HashSet<Monster>();
		for(int i=0;i<n;i++)
		{
		  for(int j=i+1;j<n;j++)
		  {
	
			 Monster bMonster =Monster.createBabyMonster(arr.get(i),arr.get(j));
			 if(babyMonsters.contains(bMonster)==false)
			 {
				 babyMonsters.add(bMonster);
			 }
		  }
		}
		System.out.println("Unique Baby Monster Created Are:");
		for(Monster b:babyMonsters)
		{
			displayMonster(b);
			System.out.println("=======================");
		}
	}
	private static Monster createMonster()
	{
		System.out.println("Enter the Details");
		Monster monster = new Monster();
		Scanner sc = new Scanner(System.in);
		Field [] fields = Monster.class.getDeclaredFields();
		for(Field f:fields)
		{
			f.setAccessible(true);
			try {
				System.out.println(f.getName()+":");
				f.set(monster, sc.nextLine());
			}catch(IllegalAccessException e)
			{
				e.printStackTrace();
			}
		}
		return monster;
	}
	private static void displayMonster(Monster monster)
	{
		Field [] fields = Monster.class.getDeclaredFields();
		for(Field f:fields)
		{
			f.setAccessible(true);
			try {
				System.out.println(f.getName()+ ": "+ f.get(monster));
				
			}catch(IllegalAccessException e)
			{
				e.printStackTrace();
			}
		}
		
	}

}
