package ru.innopolis.lab1;

/**
 * Класс хранитель списка, пока у него есть свой список
 */
public class KeeperList {
    private String[] list = {"./hlam/1.txt",
            "./hlam/2.txt",
            "./hlam/3.txt",
                /*"http://devcolibri.com/864"*/};

    /**
     * Отдает список с которым нужно работать
     *
     * @return
     */
    public String[] getList() {
        return list;
    }

    /**
     * Устанавливает список
     *
     * @param list
     */
    public void setList(String[] list) {
        this.list = list;
    }

    /**
     * Метод проверяет массив на пустоту и null
     * @param a
     * @return
     */
    public boolean itsEmpti(String[] a){
        if(a!=null && a.length >0 )
            return true;
        throw new EmptyListException();
    }
}
