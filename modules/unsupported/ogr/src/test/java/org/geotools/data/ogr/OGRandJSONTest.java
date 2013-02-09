package org.geotools.data.ogr;

import java.io.StringWriter;

import org.geotools.data.simple.SimpleFeatureCollection;
import org.geotools.data.simple.SimpleFeatureSource;
import org.geotools.geojson.feature.FeatureJSON;
import org.junit.Test;

public class OGRandJSONTest {
	
	@Test
    public void testJSONTranformation() throws Exception {
    	
        //FeatureCollection features = loadFeatures(STATE_POP, Query.ALL);
    	final String fileName = "/home/mauro/devel-box/projects/geotools/mpazos-geotools/modules/unsupported/ogr/src/test/resources/org/geotools/data/ogr/test-data/points-4326.shp";
        OGRDataStore store = new OGRDataStore(fileName, "ESRI shapefile", null);
		
        SimpleFeatureSource source = store.getFeatureSource(store.getTypeNames()[0]);

		FeatureJSON fjson = new FeatureJSON();
		fjson.setEncodeFeatureCollectionBounds(true);
		fjson.setEncodeFeatureCollectionCRS(true);

    	SimpleFeatureCollection featureCollection = source.getFeatures();
		StringWriter writer = new StringWriter();
		fjson.writeFeatureCollection(featureCollection, writer);

		System.out.println("GEOJSON: " + writer.toString());
    }
}
