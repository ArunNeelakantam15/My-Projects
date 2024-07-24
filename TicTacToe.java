import java.util.Random;
import java.util.Scanner;
class Game{
	static char[][] Board;
	public Game(){
		Board= new char[3][3];
		initialboard();
	}
	
	void initialboard() {
		for(int i=0;i<Board.length;i++) {
			for(int j=0;j<Board[i].length;j++) {
				Board[i][j] = ' ';
			}
		}
	}
	
	static void displayboard() {
		System.out.println("-------------");
		for(int i=0;i<Board.length;i++) {
			System.out.print("| ");
			for(int j=0;j<Board[i].length;j++) {
				System.out.print(Board[i][j] + " | ");
			}
			System.out.println();
			System.out.println("-------------");
		}
	}
	
	static void Marking(int row, int col, char mark) {
		if((row>=0 && row <3) && ( col>=0 && col<3)){
			Board[row][col]= mark;
		}
		else {
			System.out.println("Invalid Position");
		}
	}
	
	static boolean columnWin() {
		for(int j=0;j<3;j++) {
			if(Board[0][j]!=' ' && Board[0][j] == Board[1][j] && Board[1][j] == Board[2][j] ) {
				return true;
			}
		}
		return false;
	}
	
	static boolean rowWin() {
		for(int i=0;i<3;i++) {
			if(Board[i][0]!=' ' && Board[i][0] == Board[i][1] && Board[i][1] == Board[i][2] ) {
				return true;
			}
		}
		return false;
	}
	
	static boolean DiagonalWin() {
		if(Board[0][0]!=' ' && Board[0][0] == Board[1][1] && Board[1][1] == Board[2][2]  ||
				Board[0][2]!=' ' && Board[0][2] == Board[1][1] && Board[1][1] == Board[2][0] ) {
			return true;
		}
		else {
			return false;
		}
	}
	
	static boolean isEmpty() {
		for(int i=0;i<3;i++) {
			for(int j=0;j<3;j++) {
				if(Board[i][j]==' ') {
					return true;
				}
			}
		}
		return false;
	}
	
}


abstract class Player{
	String name;
	char mark;
	abstract void makeMove();
	boolean isValidMove(int row, int col) {
		if (row>=0 && row <3 && col>=0 && col <3) {
			if(Game.Board[row][col]== ' ') {
				return true;
			}
		}
		System.out.println("Oh! '"+ name+"' It's an Invalid Move");
		return false;
	}
	
}


class HumanPlayer extends Player{
	HumanPlayer(String name, char mark){
		this.name = name;
		this.mark = mark;
	}
	void makeMove(){
		Scanner scan=new Scanner(System.in);
		int row;
		int col;
		do {
			System.out.println("My Dear '" + name + "' Please enter the Row and Column");
			row=scan.nextInt();
			col=scan.nextInt();
		}while(!isValidMove(row,col));	
		Game.Marking(row,col,mark);
	}
	
}


class AIPlayer extends Player{
	AIPlayer(String name, char mark){
		this.name = name;
		this.mark = mark;
	}
	void makeMove(){
		Scanner scan=new Scanner(System.in);
		int row;
		int col;
		do {
			System.out.println("My Dear '" + name + "' Please enter the Row and Column");
			Random r= new Random();
			row = r.nextInt(3);
			col = r.nextInt(3);
		}while(!isValidMove(row,col));	
		Game.Marking(row,col,mark);
	}
}


public class TicTacToe {
	public static void main (String...args) {
		Game g= new Game();
		Scanner scan=new Scanner(System.in);
		System.out.println("Please enter Respective Numerics to Select the Mode");
		System.out.println("    1. Human vs Human ");
		System.out.println("    2. Human vs AI ");
		int mode=scan.nextInt();
		scan.nextLine();
		if(mode==1) {
			System.out.println("Enter Player1 Name: ");
			String p1Name=scan.nextLine();
			System.out.println("Enter Player2 Name: ");
			String p2Name=scan.nextLine();
			HumanPlayer p1= new HumanPlayer(p1Name,'X');
			HumanPlayer p2= new HumanPlayer(p2Name,'O');
			HumanPlayer cp;
			cp=p1;
			while(true) {
				cp.makeMove();
				Game.displayboard();
				if(Game.columnWin()||Game.rowWin()||Game.DiagonalWin()) {
					System.out.println(cp.name + " Has Won");
					break;
				}
				else if(!Game.isEmpty()) {
					System.out.println("Game is a Draw");
					break;
				}
				else {
					if(cp==p1) {
						cp=p2;
					}
					else {
						cp=p1;
					}
				}
			}
		}
		else if(mode==2) {
			System.out.print("Enter Player's Name: ");
			String p1Name=scan.nextLine();
			HumanPlayer p1= new HumanPlayer(p1Name,'X');
			AIPlayer p2= new AIPlayer("AI",'O');
			Player cp;
			cp=p1;
			while(true) {
				cp.makeMove();
				Game.displayboard();
				if(Game.columnWin()||Game.rowWin()||Game.DiagonalWin()) {
					System.out.println(cp.name + " Has Won");
					break;
				}
				else if(!Game.isEmpty()) {
					System.out.println("Game is a Draw");
					break;
				}
				else {
					if(cp==p1) {
						cp=p2;
					}
					else {
						cp=p1;
					}
				}
			}
		}
		else {
			System.out.println("Invalid Mode Selection");
		}
	}
}
