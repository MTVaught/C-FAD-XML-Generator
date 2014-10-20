package main;

public class Pair 
{
	private int mX;
	private int mY;
	
	public Pair( int x, int y )
	{
		mX = x;
		mY = y;
	}
	
	public int getX() { return mX; }
	public int getY() { return mY; }
	public void setX( int x ) { mX = x; }
	public void setY( int y ) { mY = y; }
}
