package rolodex;

import java.util.ArrayList;

//I pledge my honor that I have abided by the Stevens Honor System.
//Colleen Que

public class Rolodex {
	private Entry cursor;
	private final Entry[] index;



	private String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	// Constructor



	Rolodex() {
		// TODO

		this.index = new Entry [26];
		for(int i =0; i<26; i++) {


			char a = alphabet.charAt(i);
			Separator x = new Separator(null,null, a);
			index [i] = (Entry) x;
		}



		Separator x = new Separator(index[25],  index[1], 'A');
		index[0] = (Entry) x;


		Separator y = new Separator(index[24],  index[0], 'Z');
		index[25] = (Entry) y;

		for (int i=1; i < 25; i++) {


			Entry xx = index[i];
			xx.prev = index[i-1];
			xx.next = index[i+1];

		}
	}


	//determines whether there is a card for name
	public Boolean contains(String name) {
		// TODO
		char a = name.charAt(0);



		Entry current = index [(int)a-65];
		current=current.next;

		while (!current.isSeparator() ) {

			if (current.getName() == name) {
				return true;}
			current= current.next;
		}
		return false;

	}

	//returns the number of cards (separaters do not count)
	public int size() {
		// TODO
		int count = 0;

		Entry current = index[0];
		current=current.next;

		while (current!= index[0]) {

			if (!current.isSeparator())
				count ++;


			current = current.next;
		}

		return count;


	}

	public ArrayList<String> lookup(String name) {
		// TODO

		ArrayList <String> output = new ArrayList<String>();


		if (!contains(name))
			throw new IllegalArgumentException ("lookup: name not found"); 


		else {

			Entry current = index[0];


			while(current.next!= index[0]) {

				if (current.getName()==name) {

					Card x = (Card)current;
					String cell = x.getCell();
					output.add(cell);
				}

				current=current.next;
			}


		}

		return output;


	}




	public void addCard(String name, String cell) {
		// TODO



		if (contains(name)) {
			if (lookup(name).contains(cell)) {

				throw new IllegalArgumentException("addCard: duplicate entry");				
			}

		}


		char a = name.charAt(0);
		Entry current = index [(int)a-65];



		Card add = new Card(null, null, name,  cell);


		if (current.next.isSeparator()) {


			//nothing is in the separator
			add.prev=current;
			add.next = current.next;
			current.next.prev=add;
			current.next = add;

			return;
		}



		else {
			current=current.next;
			//something is in the separator
			while (!current.isSeparator()) {

				if (name.compareTo(current.getName())==0) {

					//same name
					add.prev=current;
					add.next = current.next;
					current.next.prev=add;
					current.next = add;

					return;


				}
				else if (name.compareTo(current.getName())<0) {

					//add before current

					add.prev = current.prev;
					add.next = current;
					current.prev.next=add;
					current.prev = add;

					return;
				}

				current = current.next;

			}

			//add before current because current is separator
			add.prev = current.prev;
			add.next = current;
			current.prev.next=add;
			current.prev = add;

			return;


		}


	}

	public void removeCard(String name, String cell) {
		// TODO




		if (!contains(name)) {


			throw new IllegalArgumentException("removeCard: name does not exist");				


		}

		if (!lookup(name).contains(cell)) {
			throw new IllegalArgumentException("removeCard: : cell for that name does not exist");				


		}



		char a = name.charAt(0);

		Entry current = index [(int)a-65];

		//assuming there is a name

		while (!current.next.isSeparator()) {

			Card current2 = (Card)current.next;

			if (current2.getName()==name && current2.getCell()==cell) {


				current2.prev.next = current2.next;
				current2.next.prev=  current2.prev;
				return;
			}

			current= current.next;
		}




	}



	public void removeAllCards(String name) {
		// TODO


		if (!contains(name)) {


			throw new IllegalArgumentException("removeCard: name does not exist");				

		}



		char a = name.charAt(0);

		Entry current = index [(int)a-65];
		current=current.next;
		//assuming there is a name

		while (!current.isSeparator()) {



			if (current.getName()==name ) {


				current.prev.next = current.next;
				current.next.prev=  current.prev;

			}

			current= current.next;
		}



	}




	public String toString() {
		Entry current = index[0];

		StringBuilder b = new StringBuilder();
		while (current.next!=index[0]) {
			b.append(current.toString()+"\n");
			current=current.next;
		}
		b.append(current.toString()+"\n");		
		return b.toString();
	}






	// Cursor operations

	public void initializeCursor() {
		// TODO

		cursor = index[0];


	}

	public void nextSeparator() {
		// TODO

		while(!cursor.next.isSeparator()) {
			cursor=cursor.next;
		}
		cursor = cursor.next;



	}

	public void nextEntry() {
		// TODO


		cursor = cursor.next;

	}

	public String currentEntryToString() {
		// TODO


		return cursor.toString();
	}


	public static void main(String[] args) {

		Rolodex r = new Rolodex();


		System.out.println(r);
		System.out.println(r.size());

		r.addCard("Chloe", "123");
		r.addCard("Chad", "23");
		r.addCard("Cris", "3");
		r.addCard("Cris", "4");
		r.addCard("Cris", "5");
		//		r.addCard("Cris", "4");
		r.addCard("Maddie", "23");
		
		System.out.println(r.lookup("Cris"));
		
		

		System.out.println(r);

		System.out.println(r.contains("Albert"));

		r.removeAllCards("Cris");

		
		System.out.println(r);
		System.out.println(r.size());

		r.removeAllCards("Chad");
		r.removeAllCards("Chloe");

		r.addCard("Chloe", "123");
		r.addCard("Chad", "23");
		r.addCard("Cris", "3");
		r.addCard("Cris", "4");

		
		System.out.println(r);
		System.out.println(r.size());


	}

}
