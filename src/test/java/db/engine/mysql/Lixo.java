package db.engine.mysql;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class Lixo {

	private int x;

	@Before
	public void setUp() {
		x = 2;
	}

	@Test
	public void t001() {
		int y = x + 1;
		System.out.println("Old: " + x + " New: " + y);
	}

	@Test
	public void t002() {
		Lixao lixao = new Lixao(x);
		int y = lixao.getX(3);
		System.out.println("Old: " + x + " New: " + y);
	}

	public class Lixao {
		private int x;

		public Lixao(int x) {
			this.x = x;
		}

		public int getX(int add) {
			return x + add;
		}
	}
}
