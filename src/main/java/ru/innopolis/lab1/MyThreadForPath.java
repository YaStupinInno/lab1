package ru.innopolis.lab1;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import static ru.innopolis.lab1.Debug.*;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Set;

/**
 * Created by ADMIN on 07.11.2016.
 * Класс потока работает с источником который ему передали
 * наследник MyThread
 */
public class MyThreadForPath extends MyThread {
    private final static Logger logger = LoggerFactory.getLogger(MyThread.class); //логер
    public MyThreadForPath(Set<String> globalSet) {
        super( globalSet);
    }

    /**
     * Принимает источник, создает Thread
     * @param sours
     */
    @Override
    void startRun(String sours) {
        try  {
            nextText = new FileInputStream(sours);
            super.start();
            if (isDebugEnabled)
            logger.info("start thread --- " + sours);
        } catch (FileNotFoundException e) {
            logger.error("проверте источник - " + sours);
        }
    }
}