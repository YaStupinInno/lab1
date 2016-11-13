package ru.innopolis.lab1;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static ru.innopolis.lab1.Debug.isDebugEnabled;

/**
 * Created by ADMIN on 04.11.2016.
 * Класс который умеет работать со списком
 * если строка URL  создает обьект МyThreadForURL  является наследником абстрактного класса MyThread
 * если строка Path создает обьект МyThreadForPath является наследником абстрактного класса MyThread
 * при завершении всех потоков сообщает результат
 */

public class MakerThread {
    private final static Logger logger = LoggerFactory.getLogger(MyThread.class); //логер
    private String[] list;
    private List<MyThread> listThreads = new ArrayList<>();

    /**
     * конструктор
     *
     * @param list
     */
    public MakerThread(String[] list) {
        this.list = list;
    }

    /**
     * Метод определяет какой поток создать для обработки строки
     * URL или path, после создания потоков дожидается окончания их работы
     * закрывает открытые стримы
     *
     * @param globalSet
     */

    public void make(Set<String> globalSet) {
        MyThread temp;
        for (String a : list) // перебираем все источники
        {
            if (a.startsWith("http://") || a.startsWith("ftp//")) {
                temp = new MyThreadFofURL(globalSet);
            } else {
                temp = new MyThreadForPath(globalSet);
            }
            temp.startRun(a);
            listThreads.add(temp);
        }
        // ждем все потоки
        for (MyThread a : listThreads) {
            try {
                a.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        // закрываем все стримы
        for (MyThread a : listThreads) {
            try {
                a.nextText.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    /**
     * Проверяет успешно ли завершена программа
     */
    public boolean issuccessful() {
        return MyThread.predicat;
    }
}