package jUnitTest;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ 
	MapValidator.class, 
	TestCase1.class,
	TestCase2.class,
	TestCase3.class,
	TestCase4.class,
	TestCase5.class,
	TestCase6.class,
	TestCase7.class,
	TestCase8.class,
	TestCardExchange.class,
	TestContinents.class})
public class AllTests {

}
