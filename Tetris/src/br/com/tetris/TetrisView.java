package br.com.tetris;
import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.View;

public class TetrisView extends View
{
	public Engine engine = new Engine();
	private Pintura p = new Pintura(this, engine);
	
    public TetrisView(Context context, AttributeSet attribute)
    {
    	super(context, attribute);
    }
    
    @Override
    public synchronized void onDraw(Canvas canvas) 
    {
    	try
		{
			Thread.sleep(10);
			canvas.scale(0.7f, 0.68f);
	    	p.init(canvas);
	    	p.pintaTela(canvas);
	    	invalidate();
		}
		catch (InterruptedException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
}
