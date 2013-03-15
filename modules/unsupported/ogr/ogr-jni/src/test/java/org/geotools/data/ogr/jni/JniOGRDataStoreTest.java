package org.geotools.data.ogr.jni;

import java.io.File;
import java.io.IOException;

import org.geotools.data.ogr.OGRDataStore;
import org.geotools.data.ogr.OGRDataStoreTest;
import org.geotools.data.store.ContentFeatureCollection;
import org.junit.Test;

public class JniOGRDataStoreTest extends OGRDataStoreTest {

    public JniOGRDataStoreTest() {
        super(JniOGRDataStoreFactory.class);

    }

    
    @Test
    public void testReadShapefile() throws Exception {
    	
        File f = new File( "/home/mauro/devel-box/projects/geotools/mpazos-geotools/modules/unsupported/ogr/ogr-jni/src/test/resources/org/geotools/data/ogr/jni/test-data/usa.shp");

        testRead( f, "ESRI Shapefile");
    }
    
    @Test
    public void testReadGPX() throws Exception {
    	
        File f = new File("/home/mauro/devel-box/projects/geotools/mpazos-geotools/modules/unsupported/ogr/ogr-jni/src/test/resources/org/geotools/data/ogr/jni/test-data/wp.gpx");

        testRead( f, "GPX");
    }

    @Test
    public void testReadKML() throws Exception {
    	
        File f = new File("/home/mauro/devel-box/projects/geotools/mpazos-geotools/modules/unsupported/ogr/ogr-jni/src/test/resources/org/geotools/data/ogr/jni/test-data/states.kml");

        testRead( f, "KML");
    }

    @Test
    public void testReadGML() throws Exception {
    	
        File f = new File("/home/mauro/devel-box/projects/geotools/mpazos-geotools/modules/unsupported/ogr/ogr-jni/src/test/resources/org/geotools/data/ogr/jni/test-data/regions.gml");

        testRead( f, "GML");
    }
    
    private void testRead(final File f, final String format) throws IOException{
    	
        assertTrue(ogrSupports(format));

        OGRDataStore s = new OGRDataStore(f.getAbsolutePath(), format, null, ogr);
        String type = s.getTypeNames()[0];
        ContentFeatureCollection features = s.getFeatureSource(type).getFeatures();
        
        assertTrue(features.size() > 0);
    }

}
