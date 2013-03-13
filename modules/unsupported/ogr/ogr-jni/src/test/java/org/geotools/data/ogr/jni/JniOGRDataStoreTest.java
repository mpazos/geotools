package org.geotools.data.ogr.jni;

import java.io.File;

import org.geotools.data.ogr.OGRDataStore;
import org.geotools.data.ogr.OGRDataStoreTest;
import org.geotools.data.simple.SimpleFeatureCollection;
import org.geotools.feature.FeatureCollection;
import org.junit.Test;

public class JniOGRDataStoreTest extends OGRDataStoreTest {

    public JniOGRDataStoreTest() {
        super(JniOGRDataStoreFactory.class);

    }

    // FIXME HACK MAURO 
    @Test
    public void testAttributesWritingKML() throws Exception {
        if(!ogrSupports("KML")) {
            System.out.println("Skipping KML writing test as OGR was not built to support it");
            
        }
        SimpleFeatureCollection features = createFeatureCollection();
        File tmpFile = getTempFile("test-kml", ".kml");
        tmpFile.delete();
        OGRDataStore s = new OGRDataStore(tmpFile.getAbsolutePath(), "KML", null, ogr);
        s.createSchema(features, true, null);
        
//        assertEquals(1, s.getTypeNames().length);
//        assertEquals(features.size(), s.getFeatureSource("junk").getFeatures().size());
    }
    
    // FIXME HACK MAURO 
    @Test
    public void testAttributesWritingShapefile() throws Exception {
    	
        SimpleFeatureCollection features = createFeatureCollection();
        File tmpFile = getTempFile("test-shp", ".shp");
        tmpFile.delete();
        
        OGRDataStore s = new OGRDataStore(tmpFile.getAbsolutePath(), "ESRI shapefile", null, ogr);
        s.createSchema(features, true, null);
        
        assertEquals(1, s.getTypeNames().length);
        assertEquals(features.size(), s.getFeatureSource("junk").getFeatures().size());
    }
    


}
