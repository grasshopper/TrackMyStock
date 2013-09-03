/**
 * 
 */
package com.coolisland.trackmystock.database;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.sql.SQLException;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.coolisland.trackmystocks.database.StockBO;
import com.coolisland.trackmystocks.database.StockDao;


/**
 * @author Silvio
 * 
 */
public class TestTickersDao {

	private static final int EXPECTED_TICKERS_TO_TRACK = 120;
	private static final int EXPECTED_TICKERS_OWNED = 37;

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	/**
	 * Test method for
	 * {@link com.coolisland.StockDao.TickersDao#getStockTickers()}.
	 */
	@Test
	public void testGetStockTickers() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for
	 * {@link com.coolisland.StockDao.TickersDao#getAllNonIndexStockTickers()}.
	 */
	@Test
	public void testGetAllNonIndexStockTickers() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for
	 * {@link com.coolisland.StockDao.TickersDao#getIndexStockTickers()}.
	 */
	@Test
	public void testGetIndexStockTickers() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for
	 * {@link com.coolisland.StockDao.TickersDao#getStockTicker(java.lang.String)}
	 * .
	 */
	@Test
	public void testGetStockTicker() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for
	 * {@link com.coolisland.StockDao.TickersDao#getStockTickersToTrack()}.
	 */
	@Test
	public void testGetStockTickersToTrack() {
		StockDao tickers = null;

		try {
			tickers = new StockDao();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		assertNotNull("Unable to create TickersDao", tickers);

		List<StockBO> tickersToTrack = null;
		try {
			tickersToTrack = tickers.getStockTickersToTrack();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		assertNotNull("No tickers found", tickersToTrack);
		assertTrue("We should have found at least " + EXPECTED_TICKERS_TO_TRACK + " tickers to track",
				tickersToTrack.size() >= EXPECTED_TICKERS_TO_TRACK);

		for (StockBO ticker : tickersToTrack) {
			assertTrue(ticker.getTrack());
		}
	}

	/**
	 * Test method for
	 * {@link com.coolisland.StockDao.TickersDao#getStockTickersToTrack()}.
	 */
	@Test
	public void testGetStockTickersOwned() {
		StockDao tickers = null;

		try {
			tickers = new StockDao();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		assertNotNull("Unable to create TickersDao", tickers);

		List<StockBO> tickersToTrack = null;
		try {
			tickersToTrack = tickers.getStockTickersToTrack();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		assertNotNull("No tickers found", tickersToTrack);

		int tickersOwnedFound = 0;
		for (StockBO ticker : tickersToTrack) {
			if (ticker.getOwn()) {
				tickersOwnedFound++;
			}
		}

		assertEquals("We should have found " + EXPECTED_TICKERS_OWNED + " tickers owned", EXPECTED_TICKERS_OWNED,
				tickersOwnedFound);
	}

}
