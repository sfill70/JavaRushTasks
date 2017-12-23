package com.javarush.task.task32.task3209;

import javax.swing.*;
import javax.swing.text.html.HTMLDocument;
import javax.swing.text.html.HTMLEditorKit;
import java.io.*;

/**
 * Created by Sfill on 12.07.2017.
 */
public class Controller {

    private View view;
    private HTMLDocument document;
    private File currentFile;

    public Controller(View view) {
        this.view = view;
    }

    public HTMLDocument getDocument() {
        return document;
    }

    public void setDocument(HTMLDocument document) {
        this.document = document;
    }

    public void resetDocument() {
        if (document != null) {
            //Удалять у текущего документа document слушателя правок которые можно отменить/вернуть
            document.removeUndoableEditListener(view.getUndoListener());
            //Создавать новый документ по умолчанию и присваивать его полю document
            document = (HTMLDocument) new HTMLEditorKit().createDefaultDocument();
            //document = new HTMLDocument();
            //Добавлять новому документу слушателя правок
            document.addUndoableEditListener(view.getUndoListener());
            //Вызывать у представления метод update()
            view.update();
        }
    }

    //Он будет записывать переданный текст с html тегами в документ document
    public void setPlainText(String text) {
        //Сбрось документ
        resetDocument();
        //Создай новый реадер StringReader на базе переданного текста
        StringReader stringReader = new StringReader(text);

        try {
            //Вызови метод read() из класса HTMLEditorKit, который вычитает данные из реадера в документ document
            new HTMLEditorKit().read(stringReader, document, 0);

        } catch (Exception e) {
            //Проследи, чтобы метод не кидал исключения. Их необходимо просто логировать
            ExceptionHandler.log(e);
        }
    }

    //он должен получать текст из документа со всеми html тегами
    public String getPlainText() {
        //Создай объект StringWriter
        StringWriter stringWriter = new StringWriter();
        try {
            //Перепиши все содержимое из документа document в созданный объект с помощью метода write класса HTMLEditorKit
            new HTMLEditorKit().write(stringWriter, document, 0, document.getLength());

        } catch (Exception e) {
            //Как обычно, метод не должен кидать исключений
            ExceptionHandler.log(e);
        }

        return stringWriter.toString();
    }

    public void init() {
        createNewDocument();
    }

    public void createNewDocument() {

        view.selectHtmlTab(); //Выбирать html вкладку у представления
        resetDocument();//Сбрасывать текущий документ
        view.setTitle("HTML редактор"); //Устанавливать новый заголовок окна
        view.resetUndo();//Сбрасывать правки в Undo менеджере
        currentFile = null;//Обнулить переменную currentFile
    }

    public void openDocument() {
        view.selectHtmlTab(); //Выбирать html вкладку у представления
        JFileChooser jFileChooser=new JFileChooser(); //Создавать новый объект для выбора файла JFileChooser
        jFileChooser.setFileFilter(new HTMLFileFilter()); //Устанавливать ему в качестве фильтра объект HTMLFileFilter
        int n =jFileChooser.showOpenDialog(view); //Показывать диалоговое окно "Open File" для выбора файла
        //Если пользователь подтвердит выбор файла:
        //Когда файл выбран, необходимо
        if (n == JFileChooser.APPROVE_OPTION) {
            //Установить новое значение currentFile
            currentFile = jFileChooser.getSelectedFile();
            //Сбросить документ
            resetDocument();
            //Установить имя файла в заголовок у представления
            view.setTitle(currentFile.getName());

            //Создать FileReader, используя currentFile
            try (FileReader fileReader = new FileReader(currentFile)) {
                //Вычитать данные из FileReader-а в документ document с помощью объекта класса
                new HTMLEditorKit().read(fileReader, document, 0);
                //Сбросить правки
                view.resetUndo();
            }
            catch (Exception e) {
                ExceptionHandler.log(e);
            }
        }
    }

    public void saveDocument() {
        // Метод должен работать также, как saveDocumentAs(), но не запрашивать файл у пользователя,
        // а использовать currentFile. Если currentFile равен null, то вызывать метод saveDocumentAs().

        if (currentFile == null) {
            saveDocumentAs();
        }
        else {
             view.selectHtmlTab();//Переключать представление на html вкладку
            //Создавать FileWriter на базе currentFile

            try (FileWriter fileWriter = new FileWriter(currentFile)) {
                //Переписывать данные из документа document в объекта FileWriter-а аналогично тому, как мы это делали в методе getPlainText()
                new HTMLEditorKit().write(fileWriter, document, 0, document.getLength());
            }
            catch (Exception e) {
                ExceptionHandler.log(e);
            }
        }
    }

    public void saveDocumentAs() {
        view.selectHtmlTab(); //Выбирать html вкладку у представления
        JFileChooser jFileChooser=new JFileChooser(); //Создавать новый объект для выбора файла JFileChooser
        jFileChooser.setFileFilter(new HTMLFileFilter()); //Устанавливать ему в качестве фильтра объект HTMLFileFilter
        int n =jFileChooser.showSaveDialog(view); //Показывать диалоговое окно "Save File" для выбора файла
        //Если пользователь подтвердит выбор файла:
        if (n == JFileChooser.APPROVE_OPTION) {
            currentFile = jFileChooser.getSelectedFile();//Сохранять выбранный файл в поле currentFile
            view.setTitle(currentFile.getName());//Устанавливать имя файла в качестве заголовка окна представления
            //Создавать FileWriter на базе currentFile
            try (FileWriter fileWriter = new FileWriter(currentFile)) {
                //Переписывать данные из документа document в объекта FileWriter-а аналогично тому, как мы это делали в методе getPlainText()
                new HTMLEditorKit().write(fileWriter, document, 0, document.getLength());
            }
            catch (Exception e) {
                ExceptionHandler.log(e);
            }
        }
    }


    public void exit() {
        System.exit(0);
    }


    public static void main(String[] args) {

        View view = new View();
        Controller controller = new Controller(view);
        view.init();
        controller.init();
        view.setController(controller);

    }
}
