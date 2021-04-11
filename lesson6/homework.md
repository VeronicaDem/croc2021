# Домашняя задача 6

На вход поступает xml, в виде строки, содержащая список фильмов. По каждому фильму известно уникальное 
название, описание, список сценаристов и список режиссеров. Требуется реализовать класс, который преобразует
xml такого формата в xml сожержающий информацию по людям, в каких фильмах они принимали участие и 
на каких позициях. Имена людей считаем уникальными. Преобразования делаем с помощью JAXB.

Пример входных данных:
````xml
<films>
    <film>
        <title>Фильм 1</title>
        <description>Описание 1</description>
        <screenwriters>
            <screenwriter name="Человек 1"/>
            <screenwriter name="Человек 2"/>
        </screenwriters>
        <directors>
            <director name="Человек 1"/>
            <director name="Человек 3"/>
        </directors>
    </film>
    <film>
        <title>Фильм 2</title>
        <description>Описание 2</description>
        <screenwriters>
            <screenwriter name="Человек 3"/>
            <screenwriter name="Человек 2"/>
        </screenwriters>
        <directors>
            <director name="Человек 2"/>
            <director name="Человек 4"/>
            <director name="Человек 3"/>
        </directors>
    </film>
</films>
````

Пример выходных данных:
````xml
<people>
   <person>
       <name>Человек 1</name>
       <films>
           <film title="Фильм 1">
               <functions>
                   <function name="Сценарист"/>
                   <function name="Режиссер"/>
               </functions>
           </film>
       </films>
   </person>
   <person>
       <name>Человек 2</name>
       <films>
           <film title="Фильм 1">
               <functions>
                   <function name="Сценарист"/>
               </functions>
           </film>
           <film title="Фильм 2">
               <functions>
                   <function name="Сценарист"/>
                   <function name="Режиссер"/>
               </functions>
           </film>
       </films>
   </person>
   <person>
       <name>Человек 3</name>
       <films>
           <film title="Фильм 1">
               <functions>
                   <function name="Режиссер"/>
               </functions>
           </film>
           <film title="Фильм 2">
               <functions>
                   <function name="Сценарист"/>
                   <function name="Режиссер"/>
               </functions>
           </film>
       </films>
   </person>
   <person>
       <name>Человек 4</name>
       <films>
           <film title="Фильм 2">
               <functions>
                   <function name="Режиссер"/>
               </functions>
           </film>
       </films>
   </person>
</people>
````

---

Не забываем правила и замечания предыдущих домашних заданий. Код должен быть
понятным и иметь javadoc. Все методы класса и общий сценарий должены
быть покрыты тестами.