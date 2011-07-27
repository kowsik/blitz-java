package io.blitz.curl;

import java.net.URL;
import org.junit.BeforeClass;
import org.junit.runners.Suite;
import org.junit.runner.RunWith;
import java.net.URLStreamHandlerFactory;
import io.blitz.mock.MockURLStreamHandler;

import static org.mockito.Mockito.*;
/**
 *
 * @author ghermeto
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({
    io.blitz.curl.SprintTest.class, 
    io.blitz.curl.ClientTest.class, 
    io.blitz.curl.RushTest.class
})
public class CurlTestSuite {

    private static URLStreamHandlerFactory factory;
    private static MockURLStreamHandler handler;

    @BeforeClass
    public static void mockSetup() {
        handler = getHandler();
        factory = getFactory();
        try{ URL.setURLStreamHandlerFactory(factory); } catch(Error e) {}
    }

    public static MockURLStreamHandler getHandler() {
        if(handler == null) {
            handler = new MockURLStreamHandler();
        }
        return handler;
    }
    
    public static URLStreamHandlerFactory getFactory() {
        if(factory == null) {
            factory = mock(URLStreamHandlerFactory.class);
            when(factory.createURLStreamHandler("http")).thenReturn(handler);
        }
        return factory;
    }
}
