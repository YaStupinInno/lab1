package ru.innopolis.lab1;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by ADMIN on 13.11.2016.
 */
public class WorkerWithTextTest {
    @Test
    public void itsRus() throws Exception {
        WorkerWithText tes = new WorkerWithText();
        Assert.assertFalse(tes.itsRus("sdf"));
    }

    @Test
    public void stringClner() throws Exception {
        WorkerWithText tes = new WorkerWithText();
        String q  = tes.stringClner("ф+.?+-.,");
        Assert.assertEquals(q , "ф");
    }

}