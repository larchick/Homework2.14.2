package com.example.homework.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class StringListImplTest {

    StringList stringList = new StringListImpl();

    public static Stream<Arguments> itemsForTestAdd() {
        return Stream.of(
                Arguments.of("Привет", "Привет"),
                Arguments.of("Bonjour", "Bonjour"),
                Arguments.of("Hello", "Hello")
        );
    }

    public static Stream<Arguments> itemsForTestAddByIndex() {
        return Stream.of(
                Arguments.of(0, "Привет", "Привет"),
                Arguments.of(1, "Bonjour", "Bonjour"),
                Arguments.of(2, "Hello", "Hello")
        );
    }

    @ParameterizedTest
    @MethodSource("itemsForTestAdd")
    public void shouldReturnAddedString(String putIn, String expected) {
        //given

        //when
        String actual = stringList.add(putIn);

        //then
        assertEquals(expected, actual);

    }

    @ParameterizedTest
    @MethodSource("itemsForTestAddByIndex")
    public void shouldAddItemInListOnIndexPosition(int index, String item, String expected) {

        //given вот здесь я не совсем понял как сделать так, чтобы у меня мой лист не был пустым, через сеттер?, но это тоже метод.
        stringList.add("");
        stringList.add("");
        stringList.add("");

        String actual = stringList.add(index, item);
        assertEquals(expected, actual);

    }

    @Test
    public void shouldThrowExceptionWhenAddByIndexMoreThanSize() {
        Assertions.assertThrows(ArrayIndexOutOfBoundsException.class, () -> {
            stringList.add(1, "exception");
        });
    }

    @ParameterizedTest
    @MethodSource("itemsForTestAddByIndex")
    public void shouldSetElementByIndex(int index, String item, String expected) {
        //given
        stringList.add("");
        stringList.add("");
        stringList.add("");

        //when
        String actual = stringList.add(index, item);

        //then

        assertEquals(expected, actual);

    }

    @Test
    public void shouldThrowExceptionWhenSetByIndexMoreThanSize() {
        //given
        stringList.add("");
        stringList.add("");
        stringList.add("");

        //when
        //then

        Assertions.assertThrows(ArrayIndexOutOfBoundsException.class, () -> {
            stringList.set(3, "exception");
        });
    }

    @Test
    public void shouldRemoveByItem() {
        //given
        stringList.add("123");
        stringList.add("321");
        stringList.add("231");
        //when
        String actual = stringList.remove("321");
        String expected = "321";
        //then
        assertEquals(expected, actual);
    }

    @Test
    public void shouldThrowExceptionRemoveIfElementNotFound() {
        //given
        stringList.add("123");
        stringList.add("321");
        stringList.add("231");
        //when
        //then
        Assertions.assertThrows(RuntimeException.class, () -> {
            stringList.remove("555");
        });
    }

    @Test
    public void shouldRemoveElementByIndex() {
        //given
        stringList.add("Первый");
        stringList.add("Второй");
        stringList.add("Третий");

        //when
        String actual = stringList.remove(2);
        String expected = "Третий";
        //then

        assertEquals(expected, actual);

    }

    @Test
    public void shouldThrowExceptionIfIndexMoreThanSize() {
        //given
        stringList.add("Первый");
        stringList.add("Второй");
        stringList.add("Третий");

        //when
        //then
        Assertions.assertThrows(ArrayIndexOutOfBoundsException.class, () -> {
            stringList.remove(256);
        });
    }

    @Test
    public void shouldCheckContainTrue() {
        //given
        stringList.add("Первый");
        stringList.add("Второй");
        stringList.add("Третий");

        //when
        boolean actual = stringList.contains("Второй");
        boolean expected = true;

        //then
        assertEquals(expected, actual);
    }

    @Test
    public void shouldCheckContainFalse() {
        //given
        stringList.add("Первый");
        stringList.add("Второй");
        stringList.add("Третий");

        //when
        boolean actual = stringList.contains("Пятый");
        boolean expected = false;

        //then
        assertEquals(expected, actual);
    }

    @Test
    public void shouldReturnIndexOfItemIfContains() {
        //given
        stringList.add("Первый");
        stringList.add("Третий");
        stringList.add("Второй");
        stringList.add("Третий");

        //when
        int actual = stringList.indexOf("Третий");
        int expected = 1;

        //then
        assertEquals(expected, actual);
    }

    @Test
    public void shouldReturnIndexOfItemIfDoesNotContain() {
        //given
        stringList.add("Первый");
        stringList.add("Второй");
        stringList.add("Третий");

        //when
        int actual = stringList.indexOf("тринадцатый");
        int expected = -1;

        //then
        assertEquals(expected, actual);
    }

    @Test
    public void shouldReturnLastIndexOfItemIfContains() {
        //given
        stringList.add("Первый");
        stringList.add("Третий");
        stringList.add("Второй");
        stringList.add("Третий");

        //when
        int actual = stringList.lastIndexOf("Третий");
        int expected = 3;

        //then
        assertEquals(expected, actual);
    }

    @Test
    public void shouldReturnLastIndexOfItemIfDoesNotContain() {
        //given
        stringList.add("Первый");
        stringList.add("Второй");
        stringList.add("Третий");

        //when
        int actual = stringList.lastIndexOf("Тринадцатый");
        int expected = -1;

        //then
        assertEquals(expected, actual);
    }

    @Test
    public void shouldReturnElementByIndex() {
        //given
        stringList.add("Первый");
        stringList.add("Второй");
        stringList.add("Третий");

        //when
        String actual = stringList.get(1);
        String expected = "Второй";

        //then
        assertEquals(expected, actual);
    }

    @Test
    public void shouldThrowExceptionIfIndexForGetterMoreThanSize() {
        //given
        stringList.add("Первый");
        stringList.add("Второй");
        stringList.add("Третий");

        //when
        //then
        Assertions.assertThrows(ArrayIndexOutOfBoundsException.class, () -> {
            stringList.get(128);
        });
    }

    @Test
    public void shouldEqualsIfTrue() {
        //given
        stringList.add("Первый");
        StringList stringList1 = new StringListImpl();
        stringList1.add("Первый");

        //when
        boolean actual = stringList.equals(stringList1);
        boolean expected = true;

        //then
        assertEquals(expected, actual);

    }

    @Test
    public void shouldEqualsIfFalse() {
        //given
        stringList.add("Second");
        StringList stringList1 = new StringListImpl();
        stringList1.add("Второй");

        //when
        Boolean actual = stringList.equals(stringList1);
        Boolean expected = false;

        //then
        assertEquals(expected, actual);

    }

    @Test
    public void shouldEqualsIfNull() {
        //given
        stringList.add("Second");
        StringList stringList1 = null;

        //when
        //then
        Assertions.assertThrows(RuntimeException.class, () -> {
            stringList.equals(stringList1);
        });

    }

    @Test
    public void shouldReturnSize() {
        //given
        stringList.add("Первый");
        stringList.add("Второй");
        stringList.add("Третий");

        //when
        int actual = stringList.size();
        int expected = 3;

        //then
        assertEquals(expected, actual);

    }

    @Test
    public void shouldReturnTrueIsEmpty() {
        //given
        //stringList.add("");

        //when
        boolean actual = stringList.isEmpty();
        boolean expected = true;

        //then
        assertEquals(expected, actual);

    }

    @Test
    public void shouldReturnFalseIsEmpty() {
        //given
        stringList.add("");

        //when
        boolean actual = stringList.isEmpty();
        boolean expected = false;

        //then
        assertEquals(expected, actual);

    }
}