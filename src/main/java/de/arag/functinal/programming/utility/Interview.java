package de.arag.functinal.programming.utility;

import de.arag.functinal.programming.model.dto.EmployeeResponse;
import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.util.*;

@Slf4j
public class Interview {

    public static int fibonacci(int n) {
        if( n == 1 || n == 2) {
            return 1;
        }
        return fibonacci(n-1) + fibonacci(n-2);
    }

    public static boolean isIsogramm(String input) {
        StringBuilder sb = new StringBuilder(input.toLowerCase());
        String [] array = sb.toString().split("");
        Map<String, Integer> map = new HashMap<>();
        boolean result = false;
        for (String key : array) {
            if (map.get(key) == null) {
                map.put(key, 1);
                result = true;
            } else {
                map.put(key, map.get(key) + 1);
                result = false;
                break;
            }
        }

        return result;
    }

    public static List<String> generatePerm(String input)
    {
        List<String> set = new ArrayList<>();
        if (input == "") {
            return set;
        }

        Character a = input.charAt(0);

        if (input.length() > 1)
        {
            input = input.substring(1);

            List<String> permSet = generatePerm(input);

            for (String x : permSet)
            {
                for (int i = 0; i <= x.length(); i++)
                {
                    set.add(x.substring(0, i) + a + x.substring(i));
                }
            }
        }
        else
        {
            set.add(a + "");
        }
        return set;
    }

    public EmployeeResponse deserializeFileToEmployeeResponse(String filename) throws IOException {
        EmployeeResponse response = null;
        FileInputStream fileInputStream = null;
        ObjectInputStream inputStream = null;
        try {
            fileInputStream = new FileInputStream(filename);
            inputStream = new ObjectInputStream(fileInputStream);
            response = (EmployeeResponse) inputStream.readObject();

        } catch (FileNotFoundException | ClassNotFoundException e) {
            log.error("An error occurs: {}", e.getMessage());
        }
        finally {
            if(inputStream != null) {
                inputStream.close();
                fileInputStream.close();
            }
        }

        return response;
    }

    public void serializeEmployeeResponseToFile(EmployeeResponse response, String filename) throws IOException {
        log.info("Start of writeEmployeeResponseToFile(EmployeeResponse response)");
        ObjectOutputStream oos = null;
        FileOutputStream fileOutputStream;
        try{
            fileOutputStream = new FileOutputStream(filename, true);
            oos = new ObjectOutputStream(fileOutputStream);
            oos.writeObject(response);
            log.info("Serialization done as expected");
        } catch (Exception ex) {
            log.error("An error occurs: {}", ex.getMessage());
        } finally {
            if(oos != null){
                oos.close();
            }
            log.info("End of writeEmployeeResponseToFile(EmployeeResponse response)");
        }
    }

    public static void main(String[] args) throws IOException {

        /*System.out.println("Fn = " +fibonacci(0));
        System.out.println("Fn = " +fibonacci(1));
        System.out.println("Fn = " +fibonacci(2));
        System.out.println("Fn = " +fibonacci(3));
        System.out.println("Fn = " +fibonacci(4));
        System.out.println("Fn = " +fibonacci(7));
        System.out.println(isIsogram("Pen"));
        System.out.println("Fn = " +fibonacci(9));
        System.out.println("Fn = " +generatePerm("abb"));
        List<String> list1 = new ArrayList();
        list1.addAll(List.of("Hallo", "John", "Doe"));
        List<String> list2 = list1;
        list1.remove("Doe");
        System.out.println(list1);
        System.out.println(list2);
        List<String> list3 = List.of("Hallo", "John", "Doe");
        list3.remove(1);
        String filename = "C:\\test\\serialization\\employee.ser"
        Interview interview = new Interview();
        EmployeeResponse response = EmployeeResponse
                .builder()
                .email("saliou-conde@gmx.de")
                .firstName("Saliou")
                .id(UUID.randomUUID().toString())
                .jobTitle("Fullstack developer")
                .lastName("Condé")
                .build();
        try {
            interview.serializeEmployeeResponseToFile(response, filename);
        } catch (IOException e) {
            log.error("An error occurs: {}", e.getMessage());
        }
        EmployeeResponse response = interview.deserializeFileToEmployeeResponse(filename);
        log.info("EmployeeResponse: {}", response);

        Stack<String> strings = new Stack<>();
        strings.push("Hallo");
        strings.push("John");
        strings.push("Doe");
        System.out.println(strings);
        strings.pop();
        System.out.println(strings);
        strings.pop();
        System.out.println(strings);

         */
        System.out.println("BREAK");
        for(int i = 1; i < 11; i++) {
            if(i == 5) {
                break;
            }
            System.out.println(i);
        }

        System.out.println("CONTINUE");
        for(int i = 1; i < 11; i++) {
            if(i == 5) {
                continue;
            }
            System.out.println(i);
        }
    }
}
