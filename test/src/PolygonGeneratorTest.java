import Utils.PolygonGenerator;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.awt.geom.Point2D;
import java.util.List;

public class PolygonGeneratorTest {

    @Test(dataProvider = "polygonData", groups = "group1", dependsOnMethods = "testInvalidValues")
    public void generatePolygon_validParameters_success(Point2D center, double avgRadius, double minRadius,
                                                        double irregularity, double spikiness, int numVertices) {
        List<Point2D> polygon = PolygonGenerator.generatePolygon(center, avgRadius, minRadius,
                irregularity, spikiness, numVertices);

        Assert.assertNotNull(polygon);
        Assert.assertEquals(polygon.size(), numVertices);
        for (Point2D point : polygon) {
            double distance = point.distance(center);
            Assert.assertTrue(distance >= minRadius, "Point distance is less than minRadius");
        }
    }

    @DataProvider(name = "polygonData")
    public Object[][] providePolygonData() {
        return new Object[][] {
                {new Point2D.Double(0, 0), 100, 50, 0.5, 0.3, 6},
                {new Point2D.Double(10, 20), 50, 30, 0.2, 0.1, 8},
                {new Point2D.Double(-5, 5), 80, 40, 0.8, 0.2, 5}
        };
    }

    @Test(expectedExceptions = IllegalArgumentException.class,
            dataProvider = "wrongPolygonData", groups = {"group1", "group2"})
    public void testInvalidValues(Point2D center, double avgRadius, double minRadius,
                                        double irregularity, double spikiness, int numVertices) {
        //Assert.fail();
        PolygonGenerator.generatePolygon(center, avgRadius, minRadius, irregularity, spikiness, numVertices);
    }

    @DataProvider(name = "wrongPolygonData")
    public Object[][] provideWrongPolygonData() {
        return new Object[][] {
                {new Point2D.Double(0, 0), -100, 50, 0.5, 0.3, 6},
                {new Point2D.Double(10, 20), 50, -30, 0.2, 0.1, 8},
                {new Point2D.Double(-5, 5), 80, 40, -0.8, 0.2, 5},
                {new Point2D.Double(-5, 5), 80, 40, -0.8, 1.2, 5},
                {new Point2D.Double(-5, 5), 80, 40, -0.8, 0.2, -5}
        };
    }
}
