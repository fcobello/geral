package br.com.tetris;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;

public class Pintura
{
	private Bitmap bmpVazio;
	private Blocos blocos = new Blocos();
	private Engine eng;
	private Paint background = new Paint();
	private View view;
	private int blockSize, xBloco, yBloco;

	public Pintura(View view, Engine eng)
	{
		this.eng = eng;
		this.view = view;
		bmpVazio = BitmapFactory.decodeResource(view.getResources(), blocos.VAZIO);
	}

	public void init(Canvas canvas)
	{
		blockSize = 13;
		background.setColor(Color.BLUE);
		canvas.save();
		int gradeAltura = eng.grade.getAltura();
		int gradeLargura = eng.grade.getLargura();
		for (int i = 0; i < gradeLargura; i++)
		{
			for (int j = 0; j < gradeAltura; j++)
			{
				xBloco = (i * blockSize + blockSize + 1);
				yBloco = (j * blockSize + blockSize + 1);
				canvas.drawBitmap(Bitmap.createBitmap(bmpVazio, 0, 0, blockSize, blockSize), xBloco, yBloco, background);
			}
		}
		canvas.restore();
	}

	public void pintaTela(Canvas canvas)
	{
		
		pintaGridPrincipal(canvas);
		pintaProxPeca(canvas);
//		pintaInfo(g);
	}

	public void pintaGridPrincipal(Canvas canvas)
	{

		int gradeAltura = eng.grade.getAltura();
		int gradeLargura = eng.grade.getLargura();

		for (int i = 0; i < gradeLargura; i++)
		{
			for (int j = 0; j < gradeAltura; j++)
			{
				Bloco bloco = eng.grade.getBloco(i, j);
				xBloco = (i * blockSize + blockSize + 1);
				yBloco = (j * blockSize + blockSize + 1);

				if (bloco != null && bloco.existe)
				{

					if (bloco.cor == Color.RED)
					{
						canvas.drawBitmap(Bitmap.createBitmap(BitmapFactory.decodeResource(view.getResources(), blocos.VERMELHO), 0, 0, blockSize, blockSize), xBloco, yBloco, background);
					}
					else if (bloco.cor == Color.BLUE)
					{
						canvas.drawBitmap(Bitmap.createBitmap(BitmapFactory.decodeResource(view.getResources(), blocos.AZUL), 0, 0, blockSize, blockSize), xBloco, yBloco, background);
					}
					else if (bloco.cor == Color.GREEN)
					{
						canvas.drawBitmap(Bitmap.createBitmap(BitmapFactory.decodeResource(view.getResources(), blocos.VERDE), 0, 0, blockSize, blockSize), xBloco, yBloco, background);
					}
					else if (bloco.cor == Color.YELLOW)
					{
						canvas.drawBitmap(Bitmap.createBitmap(BitmapFactory.decodeResource(view.getResources(), blocos.AMARELO), 0, 0, blockSize, blockSize), xBloco, yBloco, background);
					}
					else if (bloco.cor == Color.MAGENTA)
					{
						canvas.drawBitmap(Bitmap.createBitmap(BitmapFactory.decodeResource(view.getResources(), blocos.LARANJA), 0, 0, blockSize, blockSize), xBloco, yBloco, background);
					}
					else if (bloco.cor == Color.CYAN)
					{
						canvas.drawBitmap(Bitmap.createBitmap(BitmapFactory.decodeResource(view.getResources(), blocos.ROSA), 0, 0, blockSize, blockSize), xBloco, yBloco, background);
					}
					else if (bloco.cor == Color.BLACK)
					{
						canvas.drawBitmap(Bitmap.createBitmap(BitmapFactory.decodeResource(view.getResources(), blocos.PRETO), 0, 0, blockSize, blockSize), xBloco, yBloco, background);
					}
					else if (bloco.cor == Color.WHITE)
					{
						canvas.drawBitmap(Bitmap.createBitmap(BitmapFactory.decodeResource(view.getResources(), blocos.BRANCO), 0, 0, blockSize, blockSize), xBloco, yBloco, background);
					}
					else
					{
						canvas.drawBitmap(Bitmap.createBitmap(BitmapFactory.decodeResource(view.getResources(), blocos.AZUL), 0, 0, blockSize, blockSize), xBloco, yBloco, background);
					}
				}
			}
		}

	}

	public void pintaProxPeca(Canvas canvas)
	{
		if (eng.getProxPeca() == null)
			return;
		int x = 190, y = 10, xBloco, yBloco;
		canvas.drawBitmap(Bitmap.createBitmap(bmpVazio, 0, 0, 4 * blockSize, 4 * blockSize), x, y, background);
		if (eng.isGameOver())
		{
			pintaFim();
		}
		for (int i = 0; i < eng.getProxPeca().getLargura(); i++)
		{
			for (int j = 0; j < eng.getProxPeca().getAltura(); j++)
			{
				Bloco bloco = eng.getProxPeca().getBloco(i, j);
				if (bloco != null && bloco.existe)
				{

					int xf = (4 * blockSize) / 2 - (eng.getProxPeca().getLargura() * blockSize) / 2;
					int yf = (4 * blockSize) / 2 - (eng.getProxPeca().getAltura() * blockSize) / 2;
					;
					xBloco = (i * blockSize + 1) + x + xf;
					yBloco = (j * blockSize + 1) + y + yf;

					if (bloco.cor == Color.RED)
					{
						canvas.drawBitmap(Bitmap.createBitmap(BitmapFactory.decodeResource(view.getResources(), blocos.VERMELHO), 0, 0, blockSize, blockSize), xBloco, yBloco, background);
					}
					else if (bloco.cor == Color.BLUE)
					{
						canvas.drawBitmap(Bitmap.createBitmap(BitmapFactory.decodeResource(view.getResources(), blocos.AZUL), 0, 0, blockSize, blockSize), xBloco, yBloco, background);
					}
					else if (bloco.cor == Color.GREEN)
					{
						canvas.drawBitmap(Bitmap.createBitmap(BitmapFactory.decodeResource(view.getResources(), blocos.VERDE), 0, 0, blockSize, blockSize), xBloco, yBloco, background);
					}
					else if (bloco.cor == Color.YELLOW)
					{
						canvas.drawBitmap(Bitmap.createBitmap(BitmapFactory.decodeResource(view.getResources(), blocos.AMARELO), 0, 0, blockSize, blockSize), xBloco, yBloco, background);
					}
					else if (bloco.cor == Color.MAGENTA)
					{
						canvas.drawBitmap(Bitmap.createBitmap(BitmapFactory.decodeResource(view.getResources(), blocos.LARANJA), 0, 0, blockSize, blockSize), xBloco, yBloco, background);
					}
					else if (bloco.cor == Color.CYAN)
					{
						canvas.drawBitmap(Bitmap.createBitmap(BitmapFactory.decodeResource(view.getResources(), blocos.ROSA), 0, 0, blockSize, blockSize), xBloco, yBloco, background);
					}
					else if (bloco.cor == Color.BLACK)
					{
						canvas.drawBitmap(Bitmap.createBitmap(BitmapFactory.decodeResource(view.getResources(), blocos.PRETO), 0, 0, blockSize, blockSize), xBloco, yBloco, background);
					}
					else if (bloco.cor == Color.WHITE)
					{
						canvas.drawBitmap(Bitmap.createBitmap(BitmapFactory.decodeResource(view.getResources(), blocos.BRANCO), 0, 0, blockSize, blockSize), xBloco, yBloco, background);
					}
					else
					{
						canvas.drawBitmap(Bitmap.createBitmap(BitmapFactory.decodeResource(view.getResources(), blocos.AZUL), 0, 0, blockSize, blockSize), xBloco, yBloco, background);
					}
				}
			}
		}

	}

	public void pintaInfo(Canvas g)
	{

		//	Graphics2D gd = (Graphics2D) g;

		//	g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		//	g.setFont(new Font("Arial", Font.BOLD, 12));
		//	gd.setColor(Color.WHITE);
		g.drawText("Score: " + eng.getPontos(), 230, 120, background);
		g.drawText("Lines: " + eng.getLinhas(), 230, 140, background);
	}

	public void pintaFim()
	{
		Bloco b = new Bloco();
		b.idPeca = 1000000;
		b.cor = Color.BLACK;
		b.existe = true;
		for (int i = 0; i < eng.grade.getAltura(); i++)
		{
			for (int j = 0; j < eng.grade.getLargura(); j++)
			{
				if (eng.grade.getBloco(j, i) == null || eng.grade.getBloco(j, i).idPeca != 1000000)
				{
					eng.grade.setBloco(b, j, i);
					return;
				}
			}
		}
	}

}
