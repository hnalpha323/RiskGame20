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
	TestContinents.class})
public class AllTestsSuite {

}
