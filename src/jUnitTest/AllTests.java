package jUnitTest;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ 
	
	TestCardExchange.class,
	TestContinents.class,
	TestCards.class,
	TestDeck.class
	})
public class AllTests {

}
