package com.mycompany.web.test;


public class ParamTestWithDataProviderTest {
//
//	private PrimeNumberChecker primeNumberChecker;
//
//	   @BeforeMethod(groups={"dptest"})
//	   public void initialize() {
//	      primeNumberChecker = new PrimeNumberChecker();
//	   }
//	
//	
//	@DataProvider(name = "test1")
//	   public static Object[][] primeNumbers() {
//	      return new Object[][] {{2, true}, {6, false}, {19, true}, {22, false}, {23, true}};
//	   }
//	
//	 // This test will run 4 times since we have 5 parameters defined
//	   @Test(groups={"dptest"})
//	   @Factory(dataProvider = "test1")
//	   public void testPrimeNumberChecker(Integer inputNumber, Boolean expectedResult) {
//	      System.out.println(inputNumber + " " + expectedResult);
//	      Assert.assertEquals(expectedResult,
//	      primeNumberChecker.validate(inputNumber));
//	   }
//	
}

class PrimeNumberChecker {
	   public Boolean validate(final Integer primeNumber) {
	   
	      for (int i = 2; i < (primeNumber / 2); i++) {
	         if (primeNumber % i == 0) {
	            return false;
	         }
	      }
	      return true;
	   }
	}