package ch.kerbtier.amarillo.tests;

import ch.kerbtier.amarillo.Call;
import ch.kerbtier.amarillo.Router;
import ch.kerbtier.amarillo.Verb;
import ch.kerbtier.amarillo.tests.actions.ClassPattern;
import ch.kerbtier.amarillo.tests.actions.RuleAc;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class RuleTests {
  
  private Router routing;
  
  @BeforeMethod
  public void setUp() {
    routing = new Router();
    
    routing.register(RuleAc.class);
  }
  
  
  @Test
  public void rootGetTest() throws NoSuchMethodException, SecurityException {
    Call call = routing.find("a/b/3", Verb.GET);
    System.out.println(call.getMethod());
//    assertEquals(call.getMethod(), ClassPattern.class.getMethod("rootGet"));
    Integer l1 = (Integer)call.execute();
    System.out.println(l1);
  }


  @Test
  public void rootGetTest3() throws NoSuchMethodException, SecurityException {
    Call call = routing.find("a/bb/3", Verb.GET);
    System.out.println(call.getMethod());
//    assertEquals(call.getMethod(), ClassPattern.class.getMethod("rootGet"));
    Integer l1 = (Integer)call.execute();
    System.out.println(l1);
  }
}
