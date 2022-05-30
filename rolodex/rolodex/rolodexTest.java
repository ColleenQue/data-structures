package rolodex;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class rolodexTest {

	@Test
	void test1() {
		Rolodex r = new Rolodex();
		assertEquals(r.contains("Barbie"),false);
		
		assertEquals(r.size(),0);
		r.addCard("Barbie", "123");
		assertEquals(r.size(),1);
		r.addCard("Bob", "123");
		assertEquals(r.size(),2);
		assertEquals(r.contains("Barbie"),true);
	}

	
	@Test
	void test2() {
		Rolodex r = new Rolodex();
		r.initializeCursor();
		assertEquals(r.currentEntryToString(),"Separator A");
		
		r.nextEntry();
		assertEquals(r.currentEntryToString(),"Separator B");
		
		r.addCard("Barbie", "123");
		r.nextEntry();
		assertEquals(r.currentEntryToString(),"Name: Barbie, Cell: 123");
		
	}
}
