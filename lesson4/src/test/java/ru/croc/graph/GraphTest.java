package ru.croc.graph;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

/**
 * Тест для графов
 */
public class GraphTest {

   @Test
   /**
    * Тестирование функции {@link Graph#addVertex} на пустом графе
    */
   public void testEmptyGraphOnAddVertex() {
      Graph<Integer> emptyGraph = new Graph<Integer>();
      emptyGraph.addVertex(1, "v1");
      emptyGraph.addVertex(2, "v2");
      Assertions.assertTrue(emptyGraph.containsVertex("v1"));
      Assertions.assertTrue(emptyGraph.containsVertex("v2"));
   }
   @Test
   /**
    * Тестирование функции {@link Graph#deleteVertex(String)}
    */
   public void testEmptyGraphOnDeleteVertex() {
      Graph<Integer> emptyGraph = new Graph<Integer>();
      emptyGraph.addVertex(1, "v1");
      emptyGraph.addVertex(2, "v2");
      emptyGraph.deleteVertex("v1");
      // проверка на удаление вершины
      Assertions.assertFalse(emptyGraph.containsVertex("v1"));
      // проверка на то, что функция не "сломала" другие вершины
      Assertions.assertTrue(emptyGraph.containsVertex("v2"));
   }
   /**
    * Тестирование функции {@link Graph#addEdge(String, String)}
    */
   @Test
   public void testEmptyGraphOnAddEdge() {
      Graph<Integer> emptyGraph = new Graph<Integer>();
      emptyGraph.addVertex(1, "v1");
      emptyGraph.addVertex(2, "v2");
      // Вершины есть в графе
      String message = emptyGraph.addEdge("v1", "v2");
      Assertions.assertEquals("Успешно", message);
      Assertions.assertTrue(emptyGraph.containsEdge("v1", "v2"));
      // Вершин нет в графе
      message = emptyGraph.addEdge("v3", "v2");
      Assertions.assertEquals("Создайте узлы", message);
      Assertions.assertFalse(emptyGraph.containsEdge("v3", "v2"));

   }
   /**
    * Тестирование функции {@link Graph#deleteEdge(String, String)}
    */
   @Test
   public void testEmptyGraphOnDeleteEdge() {
      Graph<Integer> emptyGraph = new Graph<Integer>();
      emptyGraph.addVertex(1, "v1");
      emptyGraph.addVertex(2, "v2");
      emptyGraph.addEdge("v1", "v2");
      emptyGraph.deleteEdge("v1", "v2");
      Assertions.assertFalse(emptyGraph.containsEdge("v1", "v2"));
      Assertions.assertFalse(emptyGraph.containsEdge("v2", "v1"));
   }
   /**
    * Тестирование компонент связности
    */
   @Test
   public void testCOMPS() {
      Graph<Integer> emptyGraph = new Graph<Integer>();
      emptyGraph.addVertex(1, "v1");
      emptyGraph.addVertex(2, "v2");
      List<List<Node<Integer>>> comps = emptyGraph.prepareAndSortComps();
      Assertions.assertEquals(2, emptyGraph.execSizeComps(comps));

   }
}
