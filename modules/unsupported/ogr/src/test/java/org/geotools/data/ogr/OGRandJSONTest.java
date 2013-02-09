package org.geotools.data.ogr;

import java.io.File;
import java.io.IOException;
import java.io.StringWriter;

import org.geotools.data.simple.SimpleFeatureCollection;
import org.geotools.data.simple.SimpleFeatureSource;
import org.geotools.geojson.feature.FeatureJSON;
import org.junit.Test;

public class OGRandJSONTest {
	
	private String getTestData() throws IOException{
		
		String current = new File( "." ).getCanonicalPath();
		String directory = current + "/src/test/resources/org/geotools/data/ogr/test-data/";
		String fileName = directory + "points-4326.shp";

		return fileName;
	}
	
	@Test
    public void testJSONTranformation() throws Exception {
    	
    	final String fileName = getTestData();
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
