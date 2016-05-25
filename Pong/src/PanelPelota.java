import java.awt.*;
import java.awt.event.KeyEvent;
import javax.swing.*;
public class PanelPelota extends JPanel implements Runnable {

	private static final long serialVersionUID = 1L;
	// Positions on X and Y for the ball, player 1 and player 2
	private int BallX = 300, BallY = 150, P1X=10, P1Y=100, P2X=1175, P2Y=100;
	Thread Thread;
	int Right = 5; // to the right
	int Left = -5; //to the left
	int Up = 20; // upward
	int Down = -20; // down
	int Width, Height; // Width and height of the ball
	// Scores
	int contPlay1=0, contPlay2=0;
	boolean player1FlagArr,player1FlagAba, player2FlagArr, player2FlagAba;
	boolean Game, gameOver;
	public PanelPelota(){
		Game=true;
		Thread=new Thread(this);
		Thread.start();
	
	}

	// Draw ball and ships
	public void paintComponent(Graphics gc){
		setOpaque(false);
		super.paintComponent(gc);

		// Draw ball
		gc.setColor(Color.white);
		gc.fillOval(BallX, BallY, 8,8);

		// Draw ships
		gc.fillRect(P1X, P1Y, 10, 75);
		gc.fillRect(P2X, P2Y, 10, 75);

		//Draw scores
		gc.drawString("Player 1: "+contPlay1, 25, 10);
		gc.drawString("Player 2: "+contPlay2, 150, 10);

		if(gameOver)
			gc.drawString("Game Over", 100, 125);
	}

	// Positions on X and Y for the ball
	public void DrawBall (int nx, int ny)
	{
		BallX= nx;
		BallY= ny;
		this.Width=this.getWidth();
		this.Height=this.getHeight();
		repaint();
	}

	// Here we receive from the game container class the key pressed
	public void keyPressed(KeyEvent evt)
	{
		switch(evt.getKeyCode())
		{
		// Move ship 1
		case KeyEvent.VK_W :
			player1FlagArr = true;
			break;
		case KeyEvent.VK_S :
			player1FlagAba = true;
			break;

			// Move ship 2
		case KeyEvent.VK_UP:
			player2FlagArr=true;
			break;
		case KeyEvent.VK_DOWN:
			player2FlagAba=true;
			break;
		}
	}

	// Here we receive from the game container class the key released
	public void keyReleased(KeyEvent evt)
	{
		switch(evt.getKeyCode())
		{
		// Move 1
		case KeyEvent.VK_W :
			player1FlagArr = false;
			break;
		case KeyEvent.VK_S :
			player1FlagAba = false;
			break;

			// Move 2
		case KeyEvent.VK_UP:
			player2FlagArr=false;
			break;
		case KeyEvent.VK_DOWN:
			player2FlagAba=false;
			break;
		}
	}

	// Move player 1
	public void moverPlayer1()
	{
		if (player1FlagArr == true && P1Y >= 0)
			P1Y += Down;
		if (player1FlagAba == true && P1Y <= (this.getHeight()-25))
			P1Y += Up;
		Player1(P1X, P1Y);
	}

	// Move player 2
	public void moverPlayer2()
	{
		if (player2FlagArr == true && P2Y >= 0)
			P2Y += Down;
		if (player2FlagAba == true && P2Y <= (this.getHeight()-25))
			P2Y += Up;
		Player2(P2X, P2Y);
	}

	// Position on Y for the player 1
	public void Player1(int x, int y){
		this.P1X=x;
		this.P1Y=y;
		repaint();
	}
	// Position on Y for the player 2
	public void Player2(int x, int y){
		this.P2X=x;
		this.P2Y=y;
		repaint();
	}

	public void run() {
		// TODO Auto-generated method stub
		boolean izqDer=false;
		boolean arrAba=false;

		while(true){

			if(Game){

				// The ball move from left to right
				if (izqDer)
				{
					// Right
					BallX += Right;
					if (BallX >= (Width - 8))
						izqDer= false;
				}
				else
				{
					//Left
					BallX += Left;
					if ( BallX <= 0)
						izqDer = true;
				}


				// The ball moves from up to down
				if (arrAba)
				{
					// Up
					BallY += Up;
					if (BallY >= (Height - 8))
						arrAba= false;

				}
				else
				{
					// Down
					BallY += Down;
					if ( BallY <= 0)
						arrAba = true;
				}
				DrawBall(BallX, BallY);

				// Delay
				try
				{
					Thread.sleep(50);
				}
				catch(InterruptedException ex)
				{

				}

				// Move player 1
				moverPlayer1();

				// Move player 2
				moverPlayer2();

				// The score of the player 1 increase
				if (BallX >= (Width - 8))
					contPlay1++;

				// The score of the player 2 increase
				if ( BallX == 0)
					contPlay2++;

				// When the score reach to the value, the game will end
				if(contPlay1==5 || contPlay2==5){
					JOptionPane.showMessageDialog(null, "Game over!");
					Game=false;
					gameOver=true;
				}

				// The ball stroke with the player 1
				if(BallX==P1X+10 && BallY>=P1Y && BallY<=(P1Y+30))
					izqDer=true;

				// The ball stroke with the player 2
				if(BallX==(P2X-5) && BallY>=P2Y && BallY<=(P2Y+30))
					izqDer=false;
			}
		}
	}

}