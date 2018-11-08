package jUnitTest;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import model.ArmiesCalculationTest;
import model.GameDriverTest;
import model.MapInterfaceTest;
import model.MapValidatorTest;
import model.PlayersExceptionTest;
import model.PlayersexceptionOf7PlayersTest;
import model.TerrorteriesArmyTest;
import model.strategy.TestAggressiveStrategy;
import model.strategy.TestDefensiveStrategy;
import model.strategy.TestNormalStrategy;

@RunWith(Suite.class)
@SuiteClasses({ 
	MapValidatorTest.class, 
	PlayersExceptionTest.class,
	PlayersexceptionOf7PlayersTest.class,
	MapInterfaceTest.class,
	GameDriverTest.class,
	TerrorteriesArmyTest.class,
	ArmiesCalculationTest.class,
	TestCardExchange.class,
	TestContinents.class,
	TestAggressiveStrategy.class,
	TestNormalStrategy.class,
	TestDefensiveStrategy.class
	})
public class AllTestsSuite {

}
