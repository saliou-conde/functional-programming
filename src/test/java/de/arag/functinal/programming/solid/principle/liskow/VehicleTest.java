package de.arag.functinal.programming.solid.principle.liskow;

import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

class VehicleTest {

    @Test
    void numberOfWheels() {
        //Given
        List<Vehicle> vehicles = List.of(new Car(), new MotorCycle(), new MotorCycle());

        //When
        vehicles.forEach(Vehicle::numberOfWheels);

        //Then
        assertEquals(3, vehicles.size());
    }

    @Test
    void hasEngine() {
        //Given
        List<Vehicle> vehicles = List.of(new Car(), new MotorCycle(), new Bicycle());

        //When
        vehicles.stream().map(el -> el.numberOfWheels().toString()).toList().forEach(System.out::println);

        //Then
        assertEquals(3, vehicles.size());
    }

    @Test
    void calculateTime() throws InterruptedException {
        //Given
//        List<String> strings = new ArrayList<>();
//        strings.add("Hello");
//        strings.add("World");
//        strings.add("Hello");
//
//        var iterator = strings.iterator();
//
//        while (iterator.hasNext()) {
//            System.out.println(iterator.next());
//            strings.add("Universe");
//        }


//        Vector<String> copyOnWriteArrayList = new Vector<>();
//        copyOnWriteArrayList.add("Hello");
//        copyOnWriteArrayList.add("Hello");
//        copyOnWriteArrayList.add("Hello");
//
//        for (String value : copyOnWriteArrayList) {
//            System.out.println(value);
//            copyOnWriteArrayList.add("Universe");
//        }

//        CopyOnWriteArrayList<String> copyOnWriteArrayList = new CopyOnWriteArrayList<>();
//        copyOnWriteArrayList.add("Hello");
//        copyOnWriteArrayList.add("World");
//        copyOnWriteArrayList.add("Hello");
//
//        for (String value : copyOnWriteArrayList) {
//            System.out.println(value);
//            copyOnWriteArrayList.add("Universe");
//        }
//
//        SortedSet<Integer> sortedSet = new TreeSet<>();
//        sortedSet.add(30);
//        sortedSet.add(10);
//        sortedSet.add(20);
//        System.out.println("SortedSet: "+sortedSet);
//
//        NavigableSet<Integer> navigableSet = new TreeSet<>();
//        navigableSet.add(30);
//        navigableSet.add(10);
//        navigableSet.add(20);
//        System.out.println("navigableSet: "+navigableSet);
//        System.out.println("Lower: "+navigableSet.lower(20));

//        Queue<String> queue = new PriorityQueue<>(2);
//        queue.add("A");
//        queue.add("B");
//        queue.add("C");
//        System.out.println(queue);
//        System.out.println(queue.peek());
//        System.out.println(queue.poll());
//        System.out.println(queue.peek());
//        System.out.println(queue.poll());
//        System.out.println(queue.peek());

//        Deque<String> deque = new ArrayDeque<>(3);
//        deque.add("B");
//        deque.add("C");
//        deque.add("A");
//        deque.add("D");
//        System.out.println(deque.stream().toList());

//        BlockingQueue<String> blockingQueue = new ArrayBlockingQueue<>(2);
//        blockingQueue.put("A");
//        blockingQueue.put("B");
//        System.out.println(blockingQueue);
//        blockingQueue.put("C");
//        System.out.println(blockingQueue);

        SortedMap<String, Integer> map = new TreeMap<>();
        map.put("John", 20);
        map.put("Alice", 25);
        map.put("Emil", 30);
        map.put("Bob", 30);
        System.out.println(map.entrySet());

    }
}