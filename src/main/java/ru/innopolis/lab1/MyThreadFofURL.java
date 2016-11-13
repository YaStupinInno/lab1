package ru.innopolis.lab1;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import static ru.innopolis.lab1.Debug.*;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;
import java.util.Set;

/**
 * Created by ADMIN on 07.11.2016.
 * Класс потока работает с источником который ему передали
 * наследник MyThread
 */
public class MyThreadFofURL extends MyThread{
    private final static Logger logger = LoggerFactory.getLogger(MyThread.class); //логер
    public MyThreadFofURL( Set<String> globalSet) {
        super( globalSet);
    }
    /**
     * Принимает источник, создает Thread
     * @param sours
     */
    @Override
    void startRun(String sours) {
        URL url = null;
        try {
            url = new URL(sours);
            nextText = url.openStream();
            super.start();
            if (isDebugEnabled)
                logger.info("start thread - " + sours);
        } catch (MalformedURLException e) {
            logger.error("1проверте источник - " + sours);
        }catch (IOException e) {
            logger.error("2проверте источник - " + sours);
        }
    }
}
