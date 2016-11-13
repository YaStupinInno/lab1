package ru.innopolis.lab1;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.HashSet;
import java.util.Set;
import static ru.innopolis.lab1.Debug.isDebugEnabled;

/**
 * Необходимо разработать программу, которая получает на вход список ресурсов,
 * содержащих текст, и проверяет уникальность каждого слова. Каждый ресурс
 * должен быть обработан в отдельном потоке, текст не должен содержать
 * инностранных символов, только кириллица, знаки препинания и цифры. В случае
 * нахождения дубликата, программа должна прекращать выполнение с соответсвующим
 * сообщением. Все ошибки должны быть корректно обработаны, все API покрыто
 * модульными тестами
 *
 */
public class Main {
    private final static Logger logger = LoggerFactory.getLogger(Main.class);
    public static void main(String[] args) {

        Set<String> globalSet = new HashSet<>(); // Общий для всех потоков по которому анализируем уникальность слов
        KeeperList ivan = new KeeperList(); // обьект у которого всегда есть список,
        String[] myList = ivan.getList();// запрашиваем список ресурсов
        ivan.itsEmpti(myList);// проверяем на валидность список
        MakerThread petya = new MakerThread(myList);// обьект который умеет анализировать список и нужные строки раздавать "подчиненным"

        petya.make(globalSet);

        if (petya.issuccessful())// запрашивает отчет
            logger.info("successful completion ---------------");
        else
            logger.info("we haw some problems---------------");

    }


}