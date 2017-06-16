# ShirteeTests

<b>Что нужно для запуска:</b>

1) jdk 
2) maven
3) libopencv и tesseract для sikulli
(http://olyv-qa.blogspot.ru/2016/11/using-sikulix-to-handle-file-upload.html)

<b>Запуск тестов:</b>

1) Запуск всех тестов для дев:  mvn clean -Pdev test
Запускающиеся тесты описаны в testNG_dev.xml
2) Запуск всех тестов для лайв: mvn clean -Pprod test
Запускающиеся тесты описаны в testNG_prod.xml
3) Запуск 1 теста: mvn clean test -Dtest=*ClassName*#*MethodName*
4) Запуск всего тест-сьюта: mvn clean test -Dtest=*ClassName*

<b>Структура проекта:</b>
1. base - внутри базовый класс, от которого наследуют все тесты
2. pageobjects - пэйджопджекты для страниц сайта/админки. Каждый класс, представляющий страницу (или часть страницы) наследует от класса PageObject
3. tests - пакет тестов организованы по тест-сьютам. 1 класс - 1 тест-сьют.
4. Util - Классы для работы с БД, парсер данных и т.д.
5. Resources - chromedriver + изображения/скриншоты для тестов дизайнера
