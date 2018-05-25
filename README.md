# XML-comparator

Приложение сравнивает между собой 2 xml-файла из zip-архива.
Каталог для размещения zip-архивов прописан в классе `DirectoryLookup.class`
в переменной `DIRECTORY_PATH`. По умолчанию: `D://zip-files`.

Количество zip-архивов в этом каталоге может быть любым.

При запуске программа просматривает все zip-архивы в каталоге и из каждого
 считывает 2 xml-файла. Считанные xml-файлы сраниваются между собой посредством
 библиотеки `XMLUnit 2.2.1`. По окончании сравнения, все важные различия xml-файлов
 записываются в базу данных таблицу вида:

    CREATE TABLE difference(
    id serial PRIMARY KEY,
    control_xpath character varying(200),
    control_value character varying(40),
    control_parent_xpath character varying(200) NOT NULL,
    test_xpath character varying(200),
    test_value character varying(40),
    test_parent_xpath character varying(200) NOT NULL,
    file_name character varying(70) NOT NULL,
    difference_type character varying(40) NOT NULL,
    message character varying(400) NOT NULL);`
    
Поиск xml-файлов в zip-архивах и их дальнейшее сравнение выполняется в параллельных
потоках. Число потоков для выполнения задается в классе `Application.class`
в переменной `MAX_XML_COMPARE_THREADS`. По умолчанию число потоков равно 2.