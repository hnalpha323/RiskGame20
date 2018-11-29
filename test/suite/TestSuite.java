package suite;

import org.junit.runner.RunWith;
import model.*;
import mapFiles.*;
import controller.*;
import model.strategy.*;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({
	ReadControllerTest.class,
	WriteControllerTest.class,
	ReaderTest.class,
	ValidMapTest.class,
	StrategyTest.class,
	ArmiesUseTest.class,
	AttackTest.class,
	ControlPlayerTest.class,
	CardExchangeTest.class,
	InitialArmiesTest.class,
	InvalidMoveTest.class,
	MaxPlayersTest.class,
	MinPlayerTest.class,
	MinReinforceTest.class,
	SaveMethodTest.class,
	
})
public class TestSuite {
	


}
