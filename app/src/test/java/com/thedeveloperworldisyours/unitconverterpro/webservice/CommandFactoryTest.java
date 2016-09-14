package com.thedeveloperworldisyours.unitconverterpro.webservice;

import org.junit.Before;
import org.junit.Test;

import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertTrue;
import static org.mockito.Mockito.mock;

/**
 * Created by javierg on 19/08/16.
 */
public class CommandFactoryTest {

    private String mURL;
    private ResponseHandler mHandler;
    private CommandFactory mCommandFactory;

    @Before
    public void setUp() {
        mURL = "Some";

        mHandler = mock(ResponseHandler.class);
        mCommandFactory = new CommandFactory();
    }

    @Test
    public void commandFactoryTest(){

        Command command = mCommandFactory.createGetCommand(mURL,mHandler);
        assertNotNull(command);
        assertTrue(command.getClass().equals(GetCommand.class));

    }
}
