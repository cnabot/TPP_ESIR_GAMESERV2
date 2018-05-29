package com.mycompany.myapp.web.rest.hex;

import com.mycompany.myapp.web.rest.hex.Main.Fill;

class Case {
	private int row;
	private int col;
	private Fill filledBy;
	private boolean mark;
	
	Case(int row, int col) {
		this.row = row;
		this.col = col;
		this.filledBy = Fill.blank;
		mark = false;
	}

	int row() {
		return row;
	}
	
	void unmark(){
	    mark = false;
	}
	void mark(){
	    mark = true;
	}
	boolean marked(){
	    return mark;
	}

	int col() {
		return col;
	}

	void fill(Fill fill) {
		this.filledBy = fill;
	}

	Fill filledBy() {
		return filledBy;
	}

	boolean equals(Case case2){
	    return(this.row == case2.row && this.col == case2.col());
    }
}
