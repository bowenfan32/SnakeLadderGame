package view;

import java.awt.Graphics;

public abstract class Draw
{
   protected static int XMARGIN = 40;
   protected static int YMARGIN = 40;
   protected static double factor = 0.4;

   public static int getX(int pos)
   {
      pos--;
      if ( (pos/10) % 2 == 0 )
	  return XMARGIN + 20 + pos%10 * 80;
      else
	  return  XMARGIN  + 740 - pos%10 * 80;
   }
   public static int getY(int pos)		// returns the x coordinate given the board position
   {
      pos--;
      return YMARGIN - 60 + 800 - pos/10 * 80; // returns the y coordinate given the board position
   }

   public abstract void draw(Graphics g);
}