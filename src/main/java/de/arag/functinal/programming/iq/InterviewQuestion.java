package de.arag.functinal.programming.iq;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import de.arag.functinal.programming.model.dto.EmployeeRequest;
import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.*;

@Slf4j
public final class InterviewQuestion {

    private InterviewQuestion() {
    }

    private static InterviewQuestion interviewQuestion;

    public static InterviewQuestion getInterviewQuestion() {
        return interviewQuestion == null ? new InterviewQuestion() : interviewQuestion;
    }

    public Map<String, Long> countElements(List<String> list) {
        var map = getElementOccurrence(list);
        log.info(map.toString());
        return map;
    }

    public List<String> distinctList(List<String> list) {
        return list.stream().distinct().toList();
    }

    public Map<String, Long> duplicateElements(List<String> list) {
        var map = getElementOccurrence(list);
        var result = new HashMap<String, Long>();
        map.keySet().stream().toList().forEach(key -> {
            if (map.get(key) > 1) {
                result.put(key, map.get(key));
            }
        });
        log.info(result.toString());
        return result;
    }

    public List<String> flatMapList(List<List<String>> list) {
        List<String> stringList = list.stream().flatMap(List::stream).toList();
        stringList.forEach(log::info);
        return stringList;
    }

    public Map.Entry<Integer, List<String>> findNthHighestSalary(Map<String, Integer> map, int nthHighestSalary) {
        Map<Integer, List<String>> salaryGroupedByLoan = map.entrySet().stream().collect(groupingBy(Map.Entry::getValue,
                Collectors.mapping(Map.Entry::getKey, Collectors.toList())));
        var result = salaryGroupedByLoan.entrySet().stream().sorted(Collections.reverseOrder(Map.Entry.comparingByKey())).toList();
        log.info(result.toString());
        return result.get(nthHighestSalary - 1);
    }

    public String firstOccurrenceElement(List<String> list) {
        var map = uniqueElements(list);
        String key = map.entrySet().stream().filter(x -> x.getValue() == 1).findFirst().orElseThrow(() -> new RuntimeException("No value present")).getKey();
        log.info(key);
        return key;
    }

    public String highestElement(List<String> input) {
        return input.stream().reduce((x, y) -> x.length() > y.length() ? x : y).orElseThrow();
    }

    /**
     * public Map<Object, List<EmployeeRequest>> groupingByReference(List<EmployeeRequest> list, Function<EmployeeRequest, Object> functionReference) {
        return list.stream().collect(groupingBy(functionReference));
    }*/

    public Map<?, List<EmployeeRequest>> groupingByField(List<EmployeeRequest> list, Function<EmployeeRequest, ?> functionReference) {
        return list.stream().collect(groupingBy(functionReference));
    }

    public IntSummaryStatistics intSummaryStatistics(List<Integer> list) {
        IntSummaryStatistics intSummaryStatistics = list.stream().collect(summarizingInt(Integer::intValue));
        log.info(intSummaryStatistics.toString());
        return intSummaryStatistics;
    }

    public String joining(List<String> list) {
        return list.stream().collect(Collectors.joining(", ", "[", "]"));
    }

    public List<String> mapList(List<String> list) {
        List<String> stringList = list.stream()
                .filter(Objects::nonNull)
                .map(String::toUpperCase).toList();
        stringList.forEach(log::info);
        return stringList;
    }

    public Integer multipleElements(List<Integer> input) {
        return input.stream().reduce((x, y) -> x * y).orElse(0);
    }

    public Map<Boolean, List<Integer>> partitioningByEvenAndOdd(List<Integer> list) {
        Map<Boolean, List<Integer>> collect = list.stream().collect(partitioningBy(n -> n % 2 == 0));
        log.info(collect.toString());
        return collect;
    }

    public Integer secondHighestElement(List<Integer> input) {
        Set<Integer> collected = input.stream().sorted(Comparator.reverseOrder()).collect(toCollection(LinkedHashSet::new));
        log.info(collected.toString());
        return collected.stream().skip(1).findFirst().orElseThrow(()-> new RuntimeException("List contains only one element"));
    }

    //DOSILAB
    public String secondHighestWord(List<String> input) {
        List<String> collected = input.stream()
                .map(s -> new StringBuilder(s).reverse().toString())
                .distinct()
                .sorted(
                        Comparator.comparingInt(String::length).reversed()
                                .thenComparing(Comparator.reverseOrder())
                )
                .toList();
        if (collected.size() < 2) {
            throw new RuntimeException("List contains only one element");
        }

        log.info(collected.toString());
        String second = collected.get(1);
        log.info("Second highest word: {}", second);
        return second;
    }

    public List<String> sortList(List<String> list) {
        List<String> stringList = list.stream().sorted(Comparator.naturalOrder()).toList();
        log.info(stringList.toString());
        return stringList;
    }

    public Map<String, Long> uniqueElements(List<String> list) {
        var result = list.stream()
                .collect(Collectors.groupingBy(Function.identity(), TreeMap::new, Collectors.counting()))
                .entrySet().stream()
                .filter(e -> e.getValue() == 1)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

        log.info(result.toString());
        return result;
    }

    public String findNumber(List<Integer> arr, int k) {
        var result = arr.stream().filter(el -> el.equals(k)).findFirst();
        if(result.isPresent()) {
            return "YES";
        }
        return "NO";
    }

    public List<Integer> oddNumbers(int l, int r) {
        if (l % 2 == 0) l++;
        return java.util.stream.IntStream.iterate(l, n -> n <= r, n -> n + 2)
                .boxed()
                .toList();
    }

    /*
    HackerRank
     */
    public int diagonalDifference(List<List<Integer>> arr) {
        int n = arr.size();
        int k = arr.getFirst().size();
        int primarySum = 0;
        int secondarySum = 0;

        if(n != k) {
            throw new RuntimeException("Matrix is not quadratic");
        }

        for (int i = 0; i < n; i++) {
            primarySum += arr.get(i).get(i);           // primary diagonal
            secondarySum += arr.get(i).get(n - 1 - i); // secondary diagonal
        }
        return Math.abs(primarySum - secondarySum);
    }

    public List<Integer> compareTriplets(List<Integer> a, List<Integer> b) {
        if (a.size() != b.size()) throw new IllegalArgumentException("Size mismatch");
        int scoreA = 0, scoreB = 0;
        for (int i = 0; i < a.size(); i++) {
            scoreA += a.get(i) > b.get(i) ? 1 : 0;
            scoreB += a.get(i) < b.get(i) ? 1 : 0;
        }
        return List.of(scoreA, scoreB);
    }

    /*
     * Complete the 'staircase' function below.
     *
     * The function accepts INTEGER n as parameter.
     */
    public List<String>  staircase(int n) {
        // Write your code here
        List<String> result = new ArrayList<>();
        for(int i = 1; i <= n; i++) {
            String line = " ".repeat(n-i)+"#".repeat(i);
            log.info("{}", line);
            result.add(line);
        }
        return result;
    }

    public List<Long> miniMaxSum(List<Integer> arr) {
        long total = 0;
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;

        for (int num : arr) {
            total += num;             // sum all numbers
            if (num < min) min = num; // track minimum
            if (num > max) max = num; // track maximum
        }

        long minSum = total - max; // exclude the largest number
        long maxSum = total - min; // exclude the smallest number

        log.info("minSum:, maxSum: {} {}", minSum, maxSum);
        return List.of(minSum, maxSum);
    }

    /*
     * Complete the 'birthdayCakeCandles' function below.
     *
     * The function is expected to return an INTEGER.
     * The function accepts INTEGER_ARRAY candles as parameter.
     */
    public int birthdayCakeCandles(List<Integer> candles) {
        // Write your code here
        int max = 0;
        int countMax = 0;

        for (int candle : candles) {
            if (candle > max) {
                max = candle;      // new maximum found
                countMax = 1;      // reset count
            } else if (candle == max) {
                countMax++;        // increment count for current max
            }
        }
        return countMax;
    }

    public String timeConversion(String s) {
        // Extract hour, minute, second, and AM/PM
        int hour = Integer.parseInt(s.substring(0, 2));
        String minutesSeconds = s.substring(2, 8); // ":MM:SS"
        String amPm = s.substring(8); // "AM" or "PM"

        if (amPm.equals("AM")) {
            if (hour == 12) hour = 0; // 12 AM -> 00
        } else { // PM
            if (hour != 12) hour += 12; // 1-11 PM -> 13-23
        }
        // Format hour as two digits and return full time
        return String.format("%02d%s", hour, minutesSeconds);
    }

    public List<Integer> gradingStudents(List<Integer> grades) {
        // Write your code here
        log.info("Result before rounding: {}", grades);
        List<Integer> result = new ArrayList<>(grades.size());
        grades.forEach(grade -> {
                    if (grade < 38) {
                        result.add(grade);
                    } else {
                        int nextMultipleOf5 = grade + (5 - grade % 5);
                        result.add(nextMultipleOf5 - grade < 3 ? nextMultipleOf5 : grade);
                    }
                }
        );
        log.info("Result after rounding: {}", result);
        return result;
    }

    public List<Integer> findRemovableIndices(String str1, String str2) {
        List<Integer> result = new ArrayList<>();
        int n = str1.length();
        int m = str2.length();

        // If lengths don't match the problem constraint, return [-1]
        if (n != m + 1) return Collections.singletonList(-1);

        // Try removing each character at index i
        for (int i = 0; i < n; i++) {
            int j = 0; // pointer for str2
            boolean match = true;

            for (int k = 0; k < n; k++) {
                if (k == i) continue; // skip the character at index i
                if (str1.charAt(k) != str2.charAt(j)) {
                    match = false;
                    break;
                }
                j++;
            }

            if (match) result.add(i);
        }

        if (result.isEmpty()) return Collections.singletonList(-1);
        return result;
    }

    public List<Integer> findRemovableIndices1(String str1, String str2) {
        if(removableIndices(str1, str2)) {
            int findIndex = 0;
            int n = str1.length();
            StringBuilder sb = new StringBuilder(str2);
            sb.append("*");
            for(int i = 0; i < n; i++){
                if(str1.charAt(i) != sb.charAt(i)) {
                    findIndex = i;
                    break;
                }
            }
            char removedChar = str1.charAt(findIndex);
            List<Integer> result = new ArrayList<>();
            result.add(findIndex);
            for(int i = n-2; i > 0; i--) {
                if(str1.charAt(i) != removedChar) {
                    break;
                }
                result.add(i);
            }
            return result.stream().sorted().toList();
        }
        return Collections.singletonList(-1);
    }

    public boolean removableIndices(String str1, String str2) {
        int n = str1.length();
        int m = str2.length();
        if(n!= m+1) {
            return false;
        }
        return str1.contains(str2);
    }

    public List<Integer> countApplesAndOranges(int s, int t, int a, int b, List<Integer> apples, List<Integer> oranges) {
        // Write your code here
        int countApples = (int) apples.stream().filter(d -> a + d >= s && a + d <= t).count();
        int countOranges = (int) oranges.stream().filter(d -> b + d >= s && b + d <= t).count();
        return List.of(countApples, countOranges);
    }

    /*
     * Complete the 'kangaroo' function below.
     *
     * The function is expected to return a STRING.
     * The function accepts following parameters:
     *  1. INTEGER x1
     *  2. INTEGER v1
     *  3. INTEGER x2
     *  4. INTEGER v2
     */
    public String kangaroo(int x1, int v1, int x2, int v2) {
        // Write your code here
        if (v1 == v2) {
            return x1 == x2 ? "YES" : "NO";
        }

        // The formula: (x1 + n*v1) = (x2 + n*v2)
        // => n = (x2 - x1) / (v1 - v2), where n must be non-negative integer
        int numerator = x2 - x1;
        int denominator = v1 - v2;

        // Check if n is a non-negative integer
        int modulo = numerator % denominator, division = numerator / denominator;
        log.info("Modulo: {}", modulo);
        log.info("Division: {}", division);
        if (modulo == 0 && division >= 0) {
            return "YES";
        }
        return "NO";
    }

    /*
     * Complete the 'birthday' function below.
     *
     * The function is expected to return an INTEGER.
     * The function accepts following parameters:
     *  1. INTEGER_ARRAY s
     *  2. INTEGER d
     *  3. INTEGER m
     */
    public int birthday(List<Integer> s, int d, int m) {
        // Write your code here
        int count = 0;
        int currentSum = 0;

        // Initial window
        for (int i = 0; i < m; i++) {
            currentSum += s.get(i);
        }

        if (currentSum == d) count++;

        // Slide the window
        for (int i = m; i < s.size(); i++) {
            currentSum += s.get(i) - s.get(i - m);
            if (currentSum == d) count++;
        }

        return count;
    }

    /*
     * Complete the 'divisibleSumPairs' function below.
     *
     * The function is expected to return an INTEGER.
     * The function accepts following parameters:
     *  1. INTEGER n
     *  2. INTEGER k
     *  3. INTEGER_ARRAY ar
     */
    public int divisibleSumPairs(int n, int k, int[] ar) {
        int[] freq = new int[k];  // frequency of remainders
        int count = 0;

        for (int value : ar) {
            int mod = value % k;
            int complement = (k - mod) % k;
            count += freq[complement];  // add how many pairs it forms
            freq[mod]++;               // update remainder count
        }
        return count;
    }

    public int pageCount(int n, int p) {
        int fromFront = p / 2;

        int fromBack = (n / 2) - fromFront;

        // Return Minimum
        return Math.min(fromFront, fromBack);
    }


    /*
     * Complete the 'migratoryBirds' function below.
     *
     * The function is expected to return an INTEGER.
     * The function accepts INTEGER_ARRAY arr as parameter.
     */

    public int migratoryBirds(List<Integer> arr) {
        // Write your code here
        Map<Integer, Long> result = arr
                .stream()
                .collect(Collectors.groupingBy(Function.identity(), TreeMap::new, Collectors.counting()));
        Long max = result.values().stream().max(Comparator.comparing(Long::longValue)).orElseThrow();
        List<Integer> maxKeys = new ArrayList<>();
        for(Integer key : result.keySet()) {
            var value = result.get(key);
            if(value.equals(max)) {
                maxKeys.add(key);
            }
        }
        log.info("maxKeys: {}", maxKeys);
        return maxKeys.stream().min(comparing(Integer::intValue)).orElseThrow();
    }

    /*
     * Complete the 'bonAppetit' function below.
     *
     * The function accepts following parameters:
     *  1. INTEGER_ARRAY bill
     *  2. INTEGER k
     *  3. INTEGER b
     */
    public String bonAppetit(List<Integer> bill, int k, int b) {
        // Write your code here
        // Total bill using stream
        int total = bill.stream().mapToInt(Integer::intValue).sum();

        // Anna's share (exclude item at index k, then split in half)
        int annaShare = (total - bill.get(k)) / 2;

        return (annaShare == b) ? "Bon Appetit" : String.valueOf(b - annaShare);
    }

    /*
     * Complete the 'dayOfProgrammer' function below.
     *
     * The function is expected to return a STRING.
     * The function accepts INTEGER year as parameter.
     */
    public String dayOfProgrammer(int year) {
        // Write your code here
        int day;

        if (year == 1918) {
            // Transition year from Julian to Gregorian (lost 13 days in February)
            day = 26;
        } else if ((year < 1918 && year % 4 == 0) || // Julian leap year
                (year > 1918 && (year % 400 == 0 || (year % 4 == 0 && year % 100 != 0)))) {
            // Gregorian leap year
            day = 12;
        } else {
            // Normal year
            day = 13;
        }

        // Always September (09), format with leading zeros
        return String.format("%02d.09.%d", day, year);
    }

    /*
     * Complete the 'sockMerchant' function below.
     *
     * The function is expected to return an INTEGER.
     * The function accepts following parameters:
     *  1. INTEGER n
     *  2. INTEGER_ARRAY ar
     */
    public int sockMerchant(int n, List<Integer> ar) {
        log.info("Array length is: {}", n);
        // Write your code here
        return ar.stream()
                .collect(Collectors.groupingBy(c -> c, Collectors.counting()))
                .values().stream()
                .mapToInt(count -> (int)(count / 2))
                .sum();
    }

    /**
     *INACTA Assessment
     */
    public String[] getMovieTitles(String substring) throws Exception {
        long start = System.nanoTime();

        List<String> titles = new ArrayList<>();
        Gson gson = new Gson();

        int page = 1;
        int totalPages = 1;

        String urlString = "https://jsonmock.hackerrank.com/api/movies/search/?Title="
                + substring + "&page=" + page;

        URL url = new URI(urlString).toURL();

        // Loop through all pages
        while (page <= totalPages) {
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            StringBuilder sb = new StringBuilder();
            String line;

            while ((line = br.readLine()) != null) {
                sb.append(line);
            }
            br.close();

            // Parse JSON
            JsonObject json = gson.fromJson(sb.toString(), JsonObject.class);

            totalPages = json.get("total_pages").getAsInt();
            JsonArray data = json.getAsJsonArray("data");

            data.forEach(el -> {
                JsonObject movie = el.getAsJsonObject();
                titles.add(movie.get("Title").getAsString());
            });
            page++;
        }

        Collections.sort(titles);
        log.info("Total time is: {} ms", (System.nanoTime() - start) / 1_000_000);
        return titles.toArray(new String[0]);
    }

    public List<Integer> checkPowersOfTwo(List<Integer> arr) {
        List<Integer> result = new ArrayList<>();
        arr.forEach(number -> result.add(isPowerOfTwo(number)? 1:0));
        return result;
    }

    public static boolean isPowerOfTwoNaive(int number) {
        if (number <= 0) return false;
        while (number > 1) {
            if (number % 2 != 0) return false; // not divisible by 2
            number /= 2;
        }
        return true;
    }

    private boolean isPowerOfTwo(int number) {
        return number > 0 && (number & (number - 1)) == 0;
    }

    public int getTotalX(List<Integer> a, List<Integer> b) {
        // Step 1: Find LCM of all numbers in a
        int lcm = a.getFirst();
        for (int i = 1; i < a.size(); i++) {
            lcm = lcm(lcm, a.get(i));
        }

        // Step 2: Find GCD of all numbers in b
        int gcd = b.getFirst();
        for (int i = 1; i < b.size(); i++) {
            gcd = gcd(gcd, b.get(i));
        }

        // Step 3: Count multiples of lcm that divide gcd
        int count = 0;
        for (int multiple = lcm; multiple <= gcd; multiple += lcm) {
            if (gcd % multiple == 0) {
                count++;
            }
        }
        return count;
    }

    // Helper: LCM using GCD
    private int lcm(int a, int b) {
        return a * b / gcd(a, b);
    }

    // Helper: GCD (Euclidean algorithm)
    private int gcd(int a, int b) {
        while (b != 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }

    private Map<String, Long> getElementOccurrence(List<String> list) {
        return list.stream().collect(groupingBy(Function.identity(), TreeMap::new, Collectors.counting()));
    }
}
