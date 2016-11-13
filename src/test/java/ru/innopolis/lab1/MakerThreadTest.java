package ru.innopolis.lab1;

import org.junit.Assert;
import org.junit.Test;

import java.util.Set;

import static org.junit.Assert.*;

/**
 * Created by ADMIN on 13.11.2016.
 */
public class MakerThreadTest {

    @Test
    public void issuccessful() throws Exception {
        MakerThread tes = new MakerThread(new String[]{""});
        MyThread.predicat= true;
        Assert.assertTrue(tes.issuccessful());
    }

}