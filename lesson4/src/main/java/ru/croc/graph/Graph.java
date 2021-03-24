package ru.croc.graph;

import java.util.*;

/**
 * Неориентированный граф.
 * @param <T> тип данных узла
 */
public class Graph <T> {
    /**
     * Список всех узлов
     */
    private List<Node<T>> vertexList;
    /**
     * Отображение названия узла к его индексу
     */
    private Map<String, Integer> nameToIndex;



    public Graph() {
        this.vertexList = new ArrayList<>();
        this.nameToIndex = new HashMap<>();

    }

    /**
     * Алгоритм поиска в глубину dfs
     */
    public void dfs(String nameVertex, List<List<Node<T>>> comps, Map<String, Boolean> used) {
        int index = nameToIndex.get(nameVertex);
        used.put(nameVertex, true);
        comps.get(comps.size() - 1).add(vertexList.get(index));
        Node<T> node = vertexList.get(index);
        for (Node<T> u : node.getChildList()) {
            if(used.get(u.name) == null) {
                dfs(u.name, comps, used);
            }
        }
    }

    /**
     * Компаратор для сравнения корней подграфов
     */
    public Comparator <List<Node<T>>> comparator = (o1, o2) -> o1.get(0).name.compareTo(o2.get(0).name);
    /**
     * Подготовка comps - массива для подграфов, сортировка подграфов по вершине-корню.
     */
    public List<List<Node<T>>> prepareAndSortComps() {
        List<List<Node<T>>> comps = new ArrayList<>();
        Map<String, Boolean> used= new HashMap<>();
        for(int i = 0; i < vertexList.size();i++) {
            Node<T> node = vertexList.get(i);
            if(used.get(node.name) == null) {
                comps.add(new ArrayList<Node<T>>());
                dfs(node.name, comps, used);
            }
        }

        Collections.sort(comps, comparator);
        return comps;
    }
    /**
     * Вычислить количество компонент связности.
     */
    public int execSizeComps(List<List<Node<T>>> comps) {
        return comps.size();
    }
    /**
     * true <=> вершина с именем name есть в графе
     * @param name имя вершины
     * @return есть или нет вершина в графе
     */
    public boolean containsVertex(String name) {
        return nameToIndex.get(name) != null;
    }
    /**
     * Добавить изолированную вершину
     *
     * @param data
     */
    public void addVertex(T data, String name) {
        Node<T> newNode = new Node<T>(data, false, name);
        vertexList.add(newNode);
        nameToIndex.put(name, vertexList.size() - 1);
    }

    /**
     * Удалить вершину с именем name
     *
     * @param name имя вершины
     */
    public void deleteVertex(String name) {
        Node delNode = vertexList.get(nameToIndex.get(name));
        // Удаление из списков дочерних элементов у узлов-родителей
        for (Node<T> node : vertexList) {
            if (node.getChildList().contains(delNode)) {
                node.removeChild(delNode);
            }
        }
        // Сделать узлы изолированными, если это требуется
        for (Node<T> node : vertexList) {
            boolean flagToDelete = true;
            if (delNode.getChildList().contains(node)) {

                for (Node<T> parentNode : vertexList) {
                    if (parentNode == delNode) continue;
                    if (parentNode.getChildList().contains(node)) flagToDelete = false;
                }
            }
            if (flagToDelete) node.setHasParent(false);
        }
        vertexList.remove(nameToIndex.get(name));
        nameToIndex.remove(name);
    }

    /**
     * Добавить ребро, то есть добавить привязанный узел к существующему
     * @return message с выполнением задачи
     */
    public String addEdge(String nameVertex1, String nameVertex2) {
        if (nameToIndex.get(nameVertex1) == null || nameToIndex.get(nameVertex2) == null) {
            return "Создайте узлы";
        } else {
             int indexVertex1 = nameToIndex.get(nameVertex1);
             int indexVertex2 = nameToIndex.get(nameVertex2);
             Node<T> node1 = vertexList.get(indexVertex1);
             Node<T> node2 = vertexList.get(indexVertex2);
             node1.addChildren(node2);
             node2.addChildren(node1);
        }
        return "Успешно";
    }

    /**
     * Проверка на изолированность
     * @param node узел
     * @return true <=> узел стал изолированным
     */
    private boolean checkOnIsolation(Node<T> node) {
        // Сделать узлы изолированными, если это требуется
        boolean flagToIsolate = true;

        for (Node<T> parentNode : vertexList) {
            if (parentNode.getChildList().contains(node)) flagToIsolate = false;
        }
        return flagToIsolate;
    }
    /**
     * Удалить ребро
     */
    public void deleteEdge(String nameVertex1, String nameVertex2) {
        if(nameToIndex.get(nameVertex1)== (Integer) null || nameToIndex.get(nameVertex2) == (Integer) null) {
            return;
        }
        int indexVertex1 = nameToIndex.get(nameVertex1);
        int indexVertex2 = nameToIndex.get(nameVertex2);

        Node<T> node1 = vertexList.get(indexVertex1);
        Node<T> node2 = vertexList.get(indexVertex2);

        node1.removeChild(node2);
        node2.removeChild(node1);
        // могут вершины стать изолированными
        // Сделать узлы изолированными, если это требуется
        if (checkOnIsolation(node1)) node1.setHasParent(false);
        if (checkOnIsolation(node2)) node2.setHasParent(false);
    }
    /**
     * Проверка на наличие ребра в графе между двумя вершинами
     * @param nameStart имя вершины - начало ребра
     * @param nameEnd имя вершины - конец ребра
     */
    public boolean containsEdge(String nameStart, String nameEnd) {
        if (nameToIndex.get(nameStart) == (Integer) null || nameToIndex.get(nameEnd) == (Integer) null) {
            return false;
        }
        int indexStart = nameToIndex.get(nameStart);
        int indexEnd = nameToIndex.get(nameEnd);

        Node<T> nodeStart = vertexList.get(indexStart);
        Node<T> nodeEnd = vertexList.get(indexEnd);

        return nodeStart.containsEdge(nodeEnd) && nodeEnd.containsEdge(nodeStart);
    }

}
