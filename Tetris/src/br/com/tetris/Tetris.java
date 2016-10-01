package br.com.tetris;

import android.app.Activity;
import android.os.Bundle;

public class Tetris extends Activity {
	
	private TetrisView tetrisView;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        tetrisView = (TetrisView)findViewById(R.id.tetrisView);
        tetrisView.engine.play();
    }
}