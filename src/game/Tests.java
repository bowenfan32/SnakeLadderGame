package game;

import controller.*;
import game.*;
import model.*;
import view.*;

import static org.junit.Assert.*;

import java.sql.ResultSet;

import org.junit.*;

public class Tests {

	Snake snake;
	HumanPlayer players;
	BoardModel bd;
	Dice dice;
	int i;
	String name;
	MySQLAccess sql = new MySQLAccess();

	// @Test
	// public void test() {
	// fail("Not yet implemented");
	// }

	@Before
	public void setUp() throws Exception {
		snake = new Snake(20, 10);
		players = new HumanPlayer(bd, 0, 1, "Bowen", 0);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testGetHead() {
		snake.getHead();
		assertEquals(20, snake.getHead());
	}

	@Test
	public void testGetTail() {
		snake.getTail();
		assertEquals(10, snake.getTail());
	}

	@Test
	public void testInitialEscapePoint() {
		assertEquals(0, players.getEscapePoints());
	}
	
	@Test
	public void testName() {
		assertEquals("Bowen",players.getName());
	}
	
	
	@Test
	public void testLoginSuccess() {
		ResultSet loginResult;
		try {
			loginResult = sql.login("Bowen", "123");
			if (loginResult.next()){
			assertEquals("123", loginResult.getString(1));
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void testLoginFail() {
		
		ResultSet loginResult;
		try {
			loginResult = sql.login("Bowen", "123");
			if (loginResult.next()){
			assertEquals("1234", loginResult.getString(1));
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test 
	public void testRegister() {
		
		
		
		
	}
	
	


	// Bowen tests:
	
	// Paul tests:
	
	// Billy tests:
	
	// Nathan tests:
	
	
}
