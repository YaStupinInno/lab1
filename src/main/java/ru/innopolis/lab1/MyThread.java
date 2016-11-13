package ru.innopolis.lab1;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import static ru.innopolis.lab1.Debug.*;
import java.io.*;
import java.util.Set;

/**
 * Created by ADMIN on 04.11.2016.
 * Абстрактный класс, реализующий основную логику работы с источником
 * текста, предполагается что унаследованные классы создадут
 * InputStream с либо какого источника, далее получает строку проверяет на наличие латинских символов
 * чистит от знаков препинания и цифр, делит на отдельные слова и
 * пробует добавлять слова в глобальный Set если слово добавилос
 * значит слово уникально иначе дубликат, завершаем работу.
 */
public abstract class MyThread extends Thread implements Closeable {
    private final static Logger logger = LoggerFactory.getLogger(MyThread.class); //логер
    static protected Boolean predicat = true; // общий для все потоков флаг по которому смотрят когда закрываться
    protected InputStream nextText;
    protected Set<String> globalSet;

    public MyThread(Set<String> globalSet) {
        this.globalSet = globalSet;
    }

    /**
     * Метод потока, основная логика
     */
    @Override
    public void run() {
        String temp = null;
        String[] stringArra;
        TextWorkable textWorker = new WorkerWithText();// обьект работающий с текстом

        try (InputStreamReader inputStreamReader = new InputStreamReader(nextText);
             BufferedReader bufferedReader = new BufferedReader(inputStreamReader)) {
            // построчно берем строки, проверяем есть ли еще строки, разбиваем на слова

            while (predicat && (temp = bufferedReader.readLine()) != null
                    && textWorker.itsRus(temp)) {

                temp= textWorker.stringClner( temp);// чистим от лишних символов строку
                stringArra = temp.split(" ");// разбиваем строку на слова
                for (int i = 0; MyThread.predicat && i < stringArra.length ; i++) {
                    if(stringArra[i].isEmpty())
                        continue;
                    addWodr(stringArra[i]);// пробуем доюавить(проверить на уникальность
                }
            }
            if (isDebugEnabled)
                logger.info("End - " + this.getName());
            nextText.close();
        } catch (IOException e) {
            logger.error(" IOException Bad sourses  " + this.getName());
        }
    }
    // МЕТОДЫ ----------------------
    //  добавляем в Set слово проверяем на уникальность
    //  закрываем поток
    //  абстрактный

    /**
     * Метод добавляет в Set слово проверяем на уникальность
     *
     * @param word слово которое нужно добавить в базу
     */
    private void addWodr(String word) {
        synchronized (globalSet) {
            if (MyThread.predicat && !globalSet.add(word)) {
                MyThread.predicat = false;//predicat false, clouse program
                if (isDebugEnabled)
                    logger.info("Find duplicate -----  " + this.getName() + "-" + word);
            } else if (isDebugEnabled)
                logger.info(this.getName() + "   unique word   " + word);
        }
    }

    /**
     * закрытие потока
     *
     * @throws IOException
     */
    public void close() throws IOException {
        if (nextText != null) {
            nextText.close();
        }
    }

    /**
     * абстрактный(предполагается наследник реализует
     * создания стрима либо откуда) и запустит super.start()
     *
     * @param sours
     */
    abstract void startRun(String sours);

}