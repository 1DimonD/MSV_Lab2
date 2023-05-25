
import org.testng.Assert;
import org.testng.annotations.BeforeGroups;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import visibilityPolygon.CCWPolygon;
import visibilityPolygon.VisibilityPolygon;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;

public class VisibilityPolygonTest {

    private CCWPolygon polygon;
    private List<Point2D> viewPoints;

    @BeforeGroups(groups = "group2")
    public void setUp() {
        polygon = createPolygon();
        viewPoints = createViewPoints();
    }

    private CCWPolygon createPolygon() {
        List<Point2D> vertices = new ArrayList<>();
        vertices.add(new Point2D.Double(0, 0));
        vertices.add(new Point2D.Double(0, 1));
        vertices.add(new Point2D.Double(1, 1));
        return new CCWPolygon(vertices);
    }

    private List<Point2D> createViewPoints() {
        List<Point2D> viewPoints = new ArrayList<>();
        viewPoints.add(new Point2D.Double(0.5, 0.5));
        viewPoints.add(new Point2D.Double(0.2, 0.2));
        return viewPoints;
    }

    @Test(groups = "group2")
    public void testComputeVisPol_singleViewPoint() {
        List<CCWPolygon> result = VisibilityPolygon.computeVisPol(polygon, viewPoints.subList(0, 1));

        Assert.assertEquals(result.size(), 1);
        //Assert.fail();
    }

    @Test(groups = "group2")
    public void testComputeVisPol_multipleViewPoints() {

        List<CCWPolygon> result = VisibilityPolygon.computeVisPol(polygon, viewPoints);

        Assert.assertEquals(result.size(), viewPoints.size());
    }

}

