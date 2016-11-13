package ru.innopolis.lab1;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import static ru.innopolis.lab1.Debug.isDebugEnabled;

/**
 * Клас с методами для работы с текстом
 * проверяет есть ли латинские символы
 * чистит строку от лишних символов
 */
public class WorkerWithText implements TextWorkable {
    private final static Logger logger = LoggerFactory.getLogger(MyThread.class); //логер

    /**
     * Метод проверяющий отсуцтвие латинских символов в строке
     * и если есть выставляет false в предикат, и все потоки закроются
     *
     * @param str строка для обработки
     * @return если есть латиница false иначе true
     */
    @Override
    public boolean itsRus(String str) {
        if (str.matches("[a-zA-Z]+")) {
            MyThread.predicat = false;//predicat false, clouse program
            if (isDebugEnabled)
                logger.info("Find eanglesh  ---- " + str);
            return false;
        }
        else
        return true;
    }

    /**
     * Метод принимает строку чистит ее от ненужных символов
     * и возвращает обратно
     *
     * @param str строка для обработки
     * @return строка без лишних символов
     */
    @Override
    public String stringClner(String str) {
        return str.replaceAll("[^а-яА-Я\\s]", "");
    }


}
