import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class CaveExplorer
{
	public char[][] cave_matrix = new char[20][20];
	public String  to_string_cave;
	public boolean[][] cave_matrix_visited = new boolean[20][20];
	public String path_to_exit;
	public int fi;
	public int fj;

	CaveExplorer()
	{
		char[][] cave_matrix_1=
		{{'R','R','R','R','R','R'},
		{'R','.','.','S','R','R'},
		{'R','.','R','R','R','R'},
		{'R','.','M','R','R','R'},
		{'R','R','R','R','R','R'}};

		fi=3;
		fj=2;

		boolean[][] cave_matrix_visited1 = new boolean[20][20];
		for(int i=0;i<cave_matrix_1.length;i++)
		{
			for(int j=0;j<cave_matrix_1[0].length;j++)
			{
				cave_matrix_visited1[i][j]=false;
			}

		}
		cave_matrix_visited=cave_matrix_visited1;
		cave_matrix=cave_matrix_1;
	}

	public String toString()
	{
		String  to_string_cave1="";
		for(int i=0;i<cave_matrix.length;i++)
		{
			for(int j=0;j<cave_matrix[0].length;j++)
			{
				to_string_cave1+=cave_matrix[i][j];
			}
			to_string_cave1+="\n";
		}
		return to_string_cave1;
	}

	public boolean  valid(char[][] matrix,String moves)
	{
		int start_pos_x=0;
		int start_pos_y=0;
		boolean[][] cave_matrix_visited1 = new boolean[matrix.length][matrix[0].length];

		for(int i=0;i<matrix.length;i++)
		{
			boolean flag=false;
			for(int j=0;j<matrix[0].length;j++)
			{
				if(matrix[i][j]=='S')
				{
					start_pos_x=i;
					start_pos_y=j;
					cave_matrix_visited1[i][j]=false;
				}
			}
		}
		for(int i=0;i<moves.length();i++)
		{
			if (moves.charAt(i)=='L')start_pos_y--;
			if (moves.charAt(i)=='R')start_pos_y++;
			if (moves.charAt(i)=='U')start_pos_x--;
			if (moves.charAt(i)=='D')start_pos_x++;
			if(!((start_pos_y<matrix[0].length && start_pos_y>=0) && (start_pos_x<matrix.length && start_pos_x>=0)) || matrix[start_pos_x][start_pos_y]=='R' || cave_matrix_visited1[start_pos_x][start_pos_y]  )return false;
			if(i!=moves.length()-1 && start_pos_x==fi &&start_pos_y==fj)return false;
			cave_matrix_visited1[start_pos_x][start_pos_y]=true;
		}
		if (start_pos_x==fi && start_pos_y==fj)return true;
		if (cave_matrix_visited[start_pos_x][start_pos_y]) return false;
		cave_matrix_visited[start_pos_x][start_pos_y]=true;
		return true;
	}

	public boolean findend(char[][] matrix,String moves)
	{
		int start_pos_x=0;
		int start_pos_y=0;
		for(int i=0;i<matrix.length;i++)
		{
			boolean flag=false;
			for(int j=0;j<matrix[0].length;j++)
			{
				if(matrix[i][j]=='S')
				{
					start_pos_x=i;
					start_pos_y=j;
					flag=true;
				}
			}
			if(flag)break;
		}
		for(int i=0;i<moves.length();i++)
		{
			if (moves.charAt(i)=='L')start_pos_y--;
			if (moves.charAt(i)=='R')start_pos_y++;
			if (moves.charAt(i)=='U')start_pos_x--;
			if (moves.charAt(i)=='D')start_pos_x++;
		}
		if(matrix[start_pos_x][start_pos_y]=='M')
		{
			return true;
		}
		return false;
	}

	public boolean solve()
	{
		CaveExplorer obj1 = new CaveExplorer();

		Queue<String> q= new LinkedList<>();
		q.add("");
		String add=new String();
		char[] pos={'L', 'R', 'U', 'D'};
		String put="";
		while (!obj1.findend(obj1.cave_matrix,add))
		{
			if(q.isEmpty())
			{
				path_to_exit="";
				return false;
			}
			add=q.remove();

			for(int i=0;i<pos.length;i++)
			{
				put=add+pos[i];

				if(obj1.valid(obj1.cave_matrix,put)){
					q.add(put);
				}
			}
		}

		if (add.charAt(0)=='L')	path_to_exit=""+'W';
		if (add.charAt(0)=='R')path_to_exit=""+'E';
		if (add.charAt(0)=='U')path_to_exit=""+'N';
		if (add.charAt(0)=='D')path_to_exit=""+'S';
		for(int i=1;i<add.length();i++)
		{
			if (add.charAt(i)=='L')path_to_exit+='W';
			if (add.charAt(i)=='R')path_to_exit+='E';
			if (add.charAt(i)=='U')path_to_exit+='N';
			if (add.charAt(i)=='D')path_to_exit+='S';
		}
		return true;
	}

	public String getPath()
	{
		return path_to_exit;
	}

	 void  getPathv2(char[][] matrix,String moves )
	{
		int start_pos_x=0;
		int start_pos_y=0;
		char[][] cave_helper =   new char[matrix.length][matrix[0].length];
		for(int i=0;i<cave_helper.length;i++)
		{
			for(int j=0;j<cave_helper[0].length;j++)
			{
				cave_helper[i][j]=matrix[i][j];
			}
		}
		for(int i=0;i<matrix.length;i++)
		{
			boolean flag=false;
			for(int j=0;j<matrix[0].length;j++)
			{
				if(matrix[i][j]=='S')
				{
					start_pos_x=i;
					start_pos_y=j;
					flag=true;
				}
			}
			if(flag)break;
		}
		for(int i=0;i<moves.length()-1;i++)
		{
			if (moves.charAt(i)=='L')start_pos_y--;
			if (moves.charAt(i)=='R')start_pos_y++;
			if (moves.charAt(i)=='U')start_pos_x--;
			if (moves.charAt(i)=='D')start_pos_x++;
			cave_helper[start_pos_x][start_pos_y]='X';
		}
		String to_string_cave1="";
		for(int i=0;i<cave_helper.length;i++)
		{
			for(int j=0;j<cave_helper[0].length;j++)
			{
				to_string_cave1+=cave_helper[i][j];
			}
			to_string_cave1+="\n";
		}
		System.out.println(to_string_cave1);
	}

	CaveExplorer (String fname) throws Exception
	{
		Scanner in = new Scanner(new File(fname));

		int rows = in.nextInt();
		int cols = in.nextInt();
		String s = in.nextLine();

		char[][] cave_matrix1 = new char[rows][cols];
		for(int i=0;i<rows;i++)
		{
			String xxx=in.nextLine();
			for(int j=0;j<cols;j++)
			{
				cave_matrix1[i][j]=xxx.charAt(j);
				if(cave_matrix1[i][j]=='M')
				{
					fi=i;
					fj=j;
				}
			}
		}

		boolean[][] cave_matrix_visited1 = new boolean[rows][cols];
		for(int i=0;i<cave_matrix1.length;i++)
		{

			for(int j=0;j<cave_matrix1[0].length;j++)
			{
				cave_matrix_visited1[i][j]=false;
			}
		}
		cave_matrix_visited=cave_matrix_visited1;
		cave_matrix=cave_matrix1;
  	}

  	public boolean solve1(String fname)
 	{
		try
		{
			CaveExplorer obj1 = new CaveExplorer(fname);
			Queue<String> q= new LinkedList<>();
			q.add("");
			String add=new String();
			char[] pos={'L', 'R', 'U', 'D'};
			String put="";
			int resetingi=0;
			int resetingj=0;
			for(int i=0;i<cave_matrix.length;i++)
			{
				for(int j=0;j<cave_matrix[0].length;j++)
				{
					if(cave_matrix[i][j]=='M')
					{
						resetingi=i;
						resetingj=j;
					}
				}
			}
			int number=0;
			while (!q.isEmpty())
			{
				add=q.remove();
				if(obj1.findend(obj1.cave_matrix,add))
				{
					number++;
					System.out.println("Found1: "+add);
					getPathv2(obj1.cave_matrix,add);
				}
				cave_matrix_visited[resetingi][resetingj]=false;
				for(int i=0;i<pos.length;i++)
				{
					put=add+pos[i];
					if(obj1.valid(obj1.cave_matrix,put))
					{
						q.add(put);
					}
				}
			}

		if (number==0)return false;
		if (add.charAt(0)=='L')	path_to_exit=""+'W';
		if (add.charAt(0)=='R')path_to_exit=""+'E';
		if (add.charAt(0)=='U')path_to_exit=""+'N';
		if (add.charAt(0)=='D')path_to_exit=""+'S';
		for(int i=1;i<add.length();i++)
		{
			if (add.charAt(i)=='L')path_to_exit+='W';
			if (add.charAt(i)=='R')path_to_exit+='E';
			if (add.charAt(i)=='U')path_to_exit+='N';
			if (add.charAt(i)=='D')path_to_exit+='S';
		}
		return true;
	}
	catch (FileNotFoundException e )
	{
		System.out.println("Can't find file " + fname);
	}
	catch (Exception e)
	{
		System.out.println("Other error: " + e);
	}
	return false;
}
	public static void main(String[] args)
	{
		System.out.println("Starting CaveExplorer");
		String fileName = "testdat1.txt";
		try
		{
			CaveExplorer ce1 = new CaveExplorer(fileName);
			System.out.println(ce1.toString());
			if(ce1.solve1(fileName) )
			{

			}
			else
			{
				System.out.println("Didnt Found anything! ");
			}
		}
		catch (FileNotFoundException e )
		{
			System.out.println("Can't find file " + fileName);
		}
		catch (Exception e)
		{
			System.out.println("Other error: " + e);
		}

		System.out.println("==================================================================");
		String fileName2 = "testdat2.txt";
		try
		{
			CaveExplorer ce2 = new CaveExplorer(fileName2);
			System.out.println(ce2.toString());
			if(ce2.solve1(fileName2) )
			{

			}
			else
			{
				System.out.println("Didnt Found anything! ");
			}
		}
		catch (FileNotFoundException e )
		{
			System.out.println("Can't find file " + fileName2);
		}
		catch (Exception e)
		{
			System.out.println("Other error: " + e);
		}

		System.out.println("==================================================================");
		String fileName3 = "testdat3.txt";
		try
		{
			CaveExplorer ce3 = new CaveExplorer(fileName3);
			System.out.println(ce3.toString());
			if(ce3.solve1(fileName3) )
			{

			}
			else
			{
			System.out.println("Didnt Found anything! ");
			}
		}
		catch (FileNotFoundException e )
		{
			System.out.println("Can't find file " + fileName3);
		}
		catch (Exception e)
		{
			System.out.println("Other error: " + e);
		}

		System.out.println("==================================================================");
		String fileName4 = "testdat4.txt";
		try
		{
			CaveExplorer ce4 = new CaveExplorer(fileName4);
			System.out.println(ce4.toString());
			if(ce4.solve1(fileName4) )
			{

			}
			else
			{
			System.out.println("Didnt Found anything! ");
			}
		}
		catch (FileNotFoundException e )
		{
			System.out.println("Can't find file " + fileName4);
		}
		catch (Exception e)
		{
			System.out.println("Other error: " + e);
		}
		System.out.println("Finished CaveExplorer");
	}
}
