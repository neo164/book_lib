package org.predybaylo;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class DateValidatorTest {

    private static DateValidator validator;

    @BeforeClass
    public static void setUpClass() throws Exception {
        validator = new DateValidator() {
            @Override
            public boolean validate(String date) {
                return false;
            }
        };

    }

    @DataProvider
    public Object[][] validDateProvider() {
        return new Object[][] {
                new Object[] {"1.1.2010"}, new Object[] {"01.01.2010"},
                new Object[] {"31.1.2010"}, new Object[] {"31.01.2010"},
                new Object[] {"29.02.2008"}, new Object[] {"29.02.2008"},
                new Object[] {"28.2.2009"}, new Object[] {"28.02.2009"},
                new Object[] {"31.3.2010"}, new Object[] {"31.03.2010"},
                new Object[] {"30.4.2010"}, new Object[] {"30.4.2010"},
                new Object[] {"31.5.2010"}, new Object[] {"31.05.2010"},
                new Object[] {"30.6.2010"}, new Object[] {"30.06.2010"},
                new Object[] {"31.7.2010"}, new Object[] {"31.07.2010"},
                new Object[] {"31.8.2010"}, new Object[] {"31.08.2010"},
                new Object[] {"30.9.2010"}, new Object[] {"30.09.2010"},
                new Object[] {"31.10.2010"}, new Object[] {"30.10.2010"},
                new Object[] {"30.11.2010"}, new Object[] {"29.11.2010"},
                new Object[] {"29.2.2000"}, new Object[] {"31.12.2010"},
        };
    }

    @DataProvider
    public Object[][] invalidDateProvider() {
        return new Object[][] {
                new Object[] {"32.1.2010"}, new Object[] {"32.01.2010"},
                new Object[] {"01.13.2010"}, new Object[] {"31.01.1860"},
                new Object[] {"29.2.2007"}, new Object[] {"29.02.2007"},
                new Object[] {"30.2.2008"}, new Object[] {"31.02.2008"},
                new Object[] {"28.a.2010"}, new Object[] {"a.01.2010"},
                new Object[] {"333.1.2010"}, new Object[] {"31.01.201a"},
                new Object[] {"31.4.2010"}, new Object[] {"31.04.2010"},
                new Object[] {"31.6.2010"}, new Object[] {"31.06.2010"},
                new Object[] {"31.9.2010"}, new Object[] {"31.09.2010"},
                new Object[] {"31.11.2010"}
        };
    }

    @Test(dataProvider="validDateProvider")
    public void validDateTest(String date) {
        boolean valid = validator.validate(date);
        System.out.println("Date \"" + date + "\" is valid: " + valid);
        Assert.assertTrue(valid);
    }

    @Test(dataProvider="invalidDateProvider", dependsOnMethods="validDateTest")
    public void invalidDateTest(String date) {
        boolean valid = validator.validate(date);
        System.out.println("Date \"" + date + "\" is valid: " + valid);
        Assert.assertFalse(valid);
    }
}