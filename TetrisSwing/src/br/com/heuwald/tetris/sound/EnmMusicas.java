package br.com.heuwald.tetris.sound;

public enum EnmMusicas {
    KATIUSHA("katiusha.midi"), TETRIS_BRADINSKY("tetris_bradinsky.mid"), TETRIS_KARINKA("tetris_karinka.mid"), TROIKA("Troika.mid");

    private String nome;

    private EnmMusicas(String nome) {
	this.nome = nome;
    }

    public String getNome() {
	return nome;
    }
}
