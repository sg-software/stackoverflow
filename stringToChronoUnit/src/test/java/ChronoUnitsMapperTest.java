import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.time.temporal.ChronoUnit;
import java.util.Map;

class ChronoUnitsMapperTest {


    @Test
    public void testChronoUnitsMapperTestYearsMonthsDays () {
        ChronoUnitsMapper chronoUnitsMapper = new ChronoUnitsMapper();
        Map<ChronoUnit, Integer> chronoUnitIntegerMap = chronoUnitsMapper.parseDuration("1 years, 2 months, 22 days");

        Assert.assertTrue("Unexpected entries in chronoUnits map",chronoUnitIntegerMap.size() == 3);
        Assert.assertEquals(1,chronoUnitIntegerMap.get(ChronoUnit.YEARS).intValue());
        Assert.assertEquals(2,chronoUnitIntegerMap.get(ChronoUnit.MONTHS).intValue());
        Assert.assertEquals(22,chronoUnitIntegerMap.get(ChronoUnit.DAYS).intValue());
    }

    @Test
    public void testChronoUnitsMapperTestMonthsDays () {
        ChronoUnitsMapper chronoUnitsMapper = new ChronoUnitsMapper();
        Map<ChronoUnit, Integer> chronoUnitIntegerMap = chronoUnitsMapper.parseDuration("2 months, 22 days");

        Assert.assertTrue("Unexpected entries in chronoUnits map",chronoUnitIntegerMap.size() == 2);
        Assert.assertEquals(2,chronoUnitIntegerMap.get(ChronoUnit.MONTHS).intValue());
        Assert.assertEquals(22,chronoUnitIntegerMap.get(ChronoUnit.DAYS).intValue());
    }

}