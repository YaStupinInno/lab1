package ru.innopolis.lab1;

import org.junit.Assert;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Created by ADMIN on 13.11.2016.
 */
public class KeeperListTest {

    @Test
    public void setList() throws Exception {
        KeeperList a = new KeeperList();
        a.setList(new String[]{"./hlam/1.txt", "./hlam/2.txt"});
        Assert.assertArrayEquals(a.getList(), new String[]{"./hlam/1.txt", "./hlam/2.txt"});
    }

    @Test
    public void itsEmpti() throws Exception {
        KeeperList a = new KeeperList();
        boolean tes = a.itsEmpti(new String[]{"./hlam/1.txt", "./hlam/2.txt"});
        Assert.assertTrue(tes);
    }

}