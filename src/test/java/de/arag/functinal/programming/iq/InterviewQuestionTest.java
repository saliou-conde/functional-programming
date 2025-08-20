package de.arag.functinal.programming.iq;

import de.arag.functinal.programming.model.dto.EmployeeRequest;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class InterviewQuestionTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(InterviewQuestionTest.class);
    private final InterviewQuestion interviewQuestion = InterviewQuestion.getInterviewQuestion();

    @Test
    void countElements() {
        //Given
        List<String> list = List.of("Zebra", "Tiger", "Mango", "Mango", "Ananas", "Ananas", "Apple", "Apple", "Banana", "Banana", "Citron", "Banana", "Banana");

        //When
        var result = interviewQuestion.countElements(list);

        //Then
        assertThat(result).isNotNull();
    }

    @Test
    void distinctList() {
        //Given
        List<String> list = List.of("Mango", "Mango", "Ananas", "Ananas", "Apple", "Apple", "Banana", "Banana", "Microservices", "Macro services");

        //When
        var stringList = interviewQuestion.distinctList(list);
        var result = interviewQuestion.sortList(stringList);

        //Then
        assertTrue(result.contains("Mango"));
        assertThat(result.getFirst()).isEqualTo("Ananas");
        assertThat(result.getLast()).isEqualTo("Microservices");
    }

    @Test
    void highestElement() {
        //Given
        List<String> list = List.of("Mango", "Mango", "Ananas", "Ananas", "Apple", "Apple", "Banana", "Banana", "Microservices", "Macro services");
        var expected = "Macro services";

        //When
        var actual = interviewQuestion.highestElement(list);
        assertThat(actual).isEqualTo(expected);

    }

    @Test
    void duplicateElements() {
        //Given
        List<String> list = List.of("Mango", "Mango", "Ananas", "Ananas", "Apple", "Apple", "Banana", "Banana", "Citron", "Banana", "Banana");

        //When
        var result = interviewQuestion.duplicateElements(list);

        //Then
        assertThat(result).isNotNull();
    }

    @Test
    void flatMapList() {
        //Given
        List<String> list1 = List.of("Ananas", "Banana", "Citron", "Destroy");
        List<String> list2 = List.of("Employee", "Finder", "Guys", "Hello");
        List<List<String>> lists = List.of(list1, list2);

        //When
        var result = interviewQuestion.flatMapList(lists);

        //Then
        boolean contains = result.contains("Hello");
        assertThat(contains).isTrue();
    }

    @Test
    void findNthHighestSalary() {
        //Given
        Map<String, Integer> map = Map.of("Saliou", 1500, "Aliou", 1600, "Tom", 1600, "Ankit", 1200, "Daniel", 1700, "James", 1700);
        Integer expected = 1600;

        //When
        var result = interviewQuestion.findNthHighestSalary(map, 2);

        //Then
        assertThat(result).isNotNull();
        assertThat(result.getKey()).isEqualTo(expected);
    }

    @Test
    void firstOccurrenceElement() {
        //Given
        List<String> list = List.of("Zebra", "Zebra", "Tiger", "Mango", "Mango", "Ananas", "Ananas", "Apple", "Apple", "Banana", "Banana", "Citron", "Banana", "Banana");

        //When
        var result = interviewQuestion.firstOccurrenceElement(list);

        //Then
        assertThat(result).isNotNull();
    }

    @Test
    void joining() {
        //Given
        var expected = "[Mango, Ananas, Apple, Banana]";
        List<String> list = List.of("Mango", "Ananas", "Apple", "Banana");

        //When
        var result = interviewQuestion.joining(list);

        //Then
        assertThat(result).isEqualTo(expected);
    }

    @Test
    void groupingByField() {
        //Given
        var expectedSize = 3;

        EmployeeRequest employeeRequest1 = EmployeeRequest.builder()
                .email("saliou-conde@gmx.de")
                .lastName("Conde")
                .firstName("Saliou").build();

        EmployeeRequest employeeRequest2 = EmployeeRequest.builder()
                .email("aliou-conde@gmx.de")
                .lastName("Conde")
                .firstName("Aliou").build();

        EmployeeRequest employeeRequest3 = EmployeeRequest.builder()
                .email("khadija-conde@gmx.de")
                .lastName("Conde")
                .firstName("Khadija").build();

        EmployeeRequest employeeRequest4 = EmployeeRequest.builder()
                .email("ibrahim-diallo@gmx.de")
                .lastName("Diallo")
                .firstName("Ibrahim").build();

        EmployeeRequest employeeRequest5 = EmployeeRequest.builder()
                .email("aliou-balde@gmx.de")
                .lastName("Balde")
                .firstName("Aliou").build();

        List<EmployeeRequest> employeeRequests = List.of(employeeRequest1, employeeRequest2, employeeRequest3, employeeRequest4, employeeRequest5);

        //When
        var result = interviewQuestion.groupingByField(employeeRequests, EmployeeRequest::getLastName);

        //Then
        assertThat(result).isNotNull();
        List<EmployeeRequest> listMap = result.get("Conde");
        assertThat(listMap).isNotNull();
        var actualSize = listMap.size();
        assertThat(actualSize).isEqualTo(expectedSize);
        LOGGER.info(listMap.toString());
    }

    @Test
    void intSummaryStatistics() {
        //Given
        List<Integer> list = List.of(1, 2, 3, 4, 5, 6);
        var average = 3.5;

        //When
        var intSummaryStatistics = interviewQuestion.intSummaryStatistics(list);

        //Then
        assertThat(intSummaryStatistics.getCount()).isEqualTo(6);
        assertThat(intSummaryStatistics.getMax()).isEqualTo(6);
        assertThat(intSummaryStatistics.getMin()).isEqualTo(1);
        assertThat(intSummaryStatistics.getAverage()).isEqualTo(average);
    }

    @Test
    void mapList() {
        //Given
        List<String> list = List.of("Mango", "Ananas", "Apple", "Banana");

        //When
        var result = interviewQuestion.mapList(list);

        //Then
        boolean contains = result.contains("MANGO");
        assertThat(contains).isTrue();
    }

    @Test
    void partitioningByEvenAndOdd() {
        //Given
        List<Integer> list = List.of(1, 2, 3, 4, 5, 6);

        //When
        var result = interviewQuestion.partitioningByEvenAndOdd(list);

        //Then
        assertThat(result).isNotNull();
    }

    @Test
    void reduce() {
        //Given
        List<Integer> list = List.of(1, 2, 3, 4, 5);

        //When
        var result = interviewQuestion.multipleElements(list);

        //Then
        assertThat(result).isEqualTo(120);
    }

    @Test
    void secondHighestElement() throws Exception {
        //Given
        List<Integer> list = List.of(120, 120, 1, 2, 3, 2, 3, 4, 5, 1, 7, 70, 6, 120, 70);
        int expected = 70;

        //When
        var result = interviewQuestion.secondHighestElement(list);

        //Then
        assertThat(result).isNotNull().isEqualTo(expected);
    }

    @Test
    void summarizeSameElement() {
        //Given
        List<Integer> list = List.of(1, 0, 1, 1, 0, 0, 0, 1, 1, 0, 0, 1, 0);
        List<Integer> expected = List.of(0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1);

        //When
        var result = list.stream().sorted().toList();
        LOGGER.info(result.toString());

        //Then
        assertThat(result).isNotNull().isEqualTo(expected);
    }

    @Test
    void uniqueElements() {
        //Given
        List<String> list = List.of("Zebra", "Tiger", "Mango", "Mango", "Ananas", "Ananas", "Apple", "Apple", "Banana", "Banana", "Citron", "Banana", "Banana");

        //When
        var result = interviewQuestion.uniqueElements(list);

        //Then
        assertThat(result).isNotNull()
                .hasSize(3)
                .containsEntry("Zebra", 1L)
                .containsEntry("Tiger", 1L)
                .containsEntry("Citron", 1L);
    }

    @Test
    void countLetters() {
        //Given
        String txt = "Hello World!";

        //When
        var list = interviewQuestion.countElements(Arrays.stream(txt.split("")).toList());

        //Then
        assertThat(list).isNotNull();
    }

    @Test
    void secondHighestWord() {
        //Given
        var elements = List.of("arbeZ", "enürG","effA", "eneiB", "allihcniC","eraaH", "allihcniC");

        //When
        var result = interviewQuestion.secondHighestWord(elements);

        //Then
        assertThat(result).isEqualTo("Zebra");
    }

    @Test
    void secondHighestWordWithOneElement() {
        //Given
        var elements = List.of("enürG");

        //Then
        var exception = assertThrows(RuntimeException.class, () -> interviewQuestion.secondHighestWord(elements));
        assertThat(exception.getMessage()).isNotNull().isEqualTo("List contains only one element");
    }

    @Test
    void oddNumbers() {
        var result = interviewQuestion.oddNumbers(2, 5);

        assertThat(result).hasSize(2);
    }

    @Test
    void removableIndicesAssertTrue() {
        //Given
        var str1 = "aabbb";
        var str2 = "aabb";

        //When
        var result = interviewQuestion.removableIndices(str1, str2);

        //Then
        assertTrue(result);
    }

    @Test
    void removableIndicesAssertFalseSubStringNotEquals() {
        //Given
        var str1 = "mmgghh";
        var str2 = "mfggh";

        //When
        var result = interviewQuestion.removableIndices(str1, str2);

        //Then
        assertFalse(result);
    }

    @Test
    void removableIndicesAssertFalseSubLengthNotEquals() {
        //Given
        var str1 = "aabbbh";
        var str2 = "aabb";

        //When
        var result = interviewQuestion.removableIndices(str1, str2);

        //Then
        assertFalse(result);
    }

    @Test
    void findRemovableIndicesAssertTrue() {
        //Given
        var str1 = "aabbb";
        var str2 = "aabb";

        //When
        var result = interviewQuestion.findRemovableIndices1(str1, str2);

        //Then
        assertThat(result).hasSize(3);
    }

    @Test
    void findRemovableIndicesAssertFalse() {
        //Given
        var str1 = "mmgghh";
        var str2 = "mfggh";

        //When
        var result = interviewQuestion.findRemovableIndices1(str1, str2);

        //Then
        assertThat(result).hasSize(1).containsAnyOf(-1);
    }

    @Test
    void gradingStudents() {
        //Given
        List<Integer> grades = List.of(73, 67, 38, 33);

        //When
        var result = interviewQuestion.gradingStudents(grades);

        //Then
        assertThat(result).hasSize(4).containsExactly(75, 67, 40, 33);
    }

    @Test
    void countApplesAndOranges1() {
        //Given
        int s = 7, t = 11, a = 5, b =15;
        List<Integer> apples = List.of(-2, 2, 1);
        List<Integer> oranges = List.of(5, -6);

        //When
        var result = interviewQuestion.countApplesAndOranges(s, t, a, b, apples, oranges);

        //Then
        assertThat(result).hasSize(2).containsExactly(1,1);
    }

    @Test
    void countApplesAndOranges2() {
        //Given
        int s = 2, t = 3, a = 1, b =5;
        List<Integer> apples = List.of(2);
        List<Integer> oranges = List.of(-2);

        //When
        var result = interviewQuestion.countApplesAndOranges(s, t, a, b, apples, oranges);

        //Then
        assertThat(result).hasSize(2).containsExactly(1,1);
    }

    @Test
    void countApplesAndOranges3() {
        //Given
        int s = 2, t = 3, a = 1, b =5;
        List<Integer> apples = List.of(-2);
        List<Integer> oranges = List.of(-1);

        //When
        var result = interviewQuestion.countApplesAndOranges(s, t, a, b, apples, oranges);

        //Then
        assertThat(result).containsExactly(0,0);
    }

    @Test
    void kangarooMeetYes() {
        //Given
        int x1 = 0, v1 = 3, x2 = 4, v2 = 2;

        //When
        var result = interviewQuestion.kangaroo(x1, v1, x2, v2);

        //Then
        assertThat(result).isNotNull().isEqualTo("YES");
    }

    @Test
    void kangarooMeetNo() {
        //Given
        int x1 = 0, v1 = 2, x2 = 5, v2 = 3;

        //When
        var result = interviewQuestion.kangaroo(x1, v1, x2, v2);

        //Then
        assertThat(result).isNotNull().isEqualTo("NO");
    }

    @Test
    void getTotalX() {
        //Given
        List<Integer> a = List.of(2,6);
        List<Integer> b = List.of(24,36);

        //When
        var result = interviewQuestion.getTotalX(a,b);

        //Then
        assertThat(result).isEqualTo(2);
    }

    @Test
    void birthday() {
        //Given
        List<Integer> s = List.of(2,2,1,3,2);
        int d = 4, m = 2;

        //When
        var result = interviewQuestion.birthday(s, d, m);

        //Then
        assertThat(result).isEqualTo(2);
    }

    @Test
    void divisibleSumPairs() {
        //Given
        int[] arr = {1,2,3,4,5,6};
        int n = arr.length, k = 5;

        //When
        var result = interviewQuestion.divisibleSumPairs(n, k, arr);

        //Then
        assertThat(result).isEqualTo(3);
    }

    @Test
    void migratoryBirds() {
        //Given
        //List<Integer> birds = List.of(1, 1, 2, 2, 3);
//        List<Integer> birds = List.of(1, 4, 4, 4, 5, 3);
        List<Integer> birds = List.of(1, 2, 3, 4, 5, 4, 3, 2, 1, 3, 4);

        //When
        var result = interviewQuestion.migratoryBirds(birds);

        //Then
        assertThat(result).isEqualTo(3);
    }

    @Test
    void dayOfProgrammer() {
        //Given
        int year = 2016;

        //When
        var result = interviewQuestion.dayOfProgrammer(year);

        //Then
        assertThat(result).isEqualTo("12.09.2016");
    }

    @Test
    void bonAppetit() {
        //Given
        List<Integer> input = List.of(3, 10, 2, 9);
        int b = 12, k = 1;

        //When
        var result = interviewQuestion.bonAppetit(input, k, b);

        //Then
        assertThat(result).isEqualTo("5");
    }

    @Test
    void sockMerchant() {
        //Given
        List<Integer> arr = List.of(10, 20, 20, 10, 10, 30, 50, 10, 20);

        //When
        var result = interviewQuestion.sockMerchant(9, arr);

        //Then
        assertThat(result).isEqualTo(3);
    }

    @Test
    void findNumberShouldReturnYES() {
        //Given
        List<Integer> arr = List.of(10, 20, 20, 10, 10, 30, 50, 10, 20);

        //When
        var result = interviewQuestion.findNumber(arr, 20);

        //Then
        assertThat(result).isEqualTo("YES");
    }

    @Test
    void findNumberShouldReturnNO() {
        //Given
        List<Integer> arr = List.of(10, 20, 20, 10, 10, 30, 50, 10, 20);

        //When
        var result = interviewQuestion.findNumber(arr, Integer.MAX_VALUE);

        //Then
        assertThat(result).isEqualTo("NO");
    }

    @Test
    void diagonalDifference() {
        //Given
        List<List<Integer>> arr = List.of(List.of(1,2,3),List.of(4,5,6),List.of(7,8,9));

        //When
        var result = interviewQuestion.diagonalDifference(arr);

        //Then
        assertThat(result).isEqualTo(0);
    }

    @Test
    void diagonalDifferenceWithNonQuadraticMatrix() {
        //Given
        List<List<Integer>> arr = List.of(List.of(1,2,3),List.of(4,5,6));
        var expectedResult = "Matrix is not quadratic";

        //When
        var result = assertThrows(RuntimeException.class, () -> interviewQuestion.diagonalDifference(arr));

        //Then
        assertThat(result.getMessage()).isEqualTo(expectedResult);
    }

    @Test
    void compareTriplets() {
        //Given
        List<Integer> score1 = List.of(10,20,30);
        List<Integer> score2 = List.of(20,15,35);

        //When
        var result = interviewQuestion.compareTriplets(score1, score2);

        //Then
        assertThat(result).isEqualTo(List.of(1,2));
    }

    @Test
    void compareTripletsWithSameScore() {
        //Given
        List<Integer> score1 = List.of(10,20,30);
        List<Integer> score2 = List.of(10,20,30);

        //When
        var result = interviewQuestion.compareTriplets(score1, score2);

        //Then
        assertThat(result).isEqualTo(List.of(0,0));
    }

    @Test
    void staircase() {
        List<String> result = interviewQuestion.staircase(4);

        assertThat(result).containsExactly(
                "   #",
                "  ##",
                " ###",
                "####"
        );
    }

    @Test
    void miniMaxSum() {
        //Given
        List<Integer> arr = List.of(10, 20, 20, 10, 10, 30, 50, 10, 20);

        //When
        var result = interviewQuestion.miniMaxSum(arr);

        //Then
        assertThat(result).hasSize(2).containsExactly(130L, 170L);
    }

    @Test
    void birthdayCakeCandles() {
        //Given
        List<Integer> arr = List.of(10, 20, 20, 10, 10, 30, 50, 10, 20);

        //When
        var result = interviewQuestion.birthdayCakeCandles(arr);

        //Then
        assertThat(result).isEqualTo(1);
    }
    @Test
    void testMidnight() {
        assertEquals("00:00:00", interviewQuestion.timeConversion("12:00:00AM"));
    }

    @Test
    void testNoon() {
        assertEquals("12:00:00", interviewQuestion.timeConversion("12:00:00PM"));
    }

    @Test
    void testMorning() {
        assertEquals("09:15:45", interviewQuestion.timeConversion("09:15:45AM"));
    }

    @Test
    void testAfternoon() {
        assertEquals("17:30:59", interviewQuestion.timeConversion("05:30:59PM"));
    }

    @Test
    void testSingleDigitHourPM() {
        assertEquals("23:59:59", interviewQuestion.timeConversion("11:59:59PM"));
    }

    @Test
    void testSingleDigitHourAM() {
        assertEquals("01:01:01", interviewQuestion.timeConversion("01:01:01AM"));
    }

    @Test
    void powerOfTwo() {

        //When
        var result = interviewQuestion.checkPowersOfTwo(List.of(1, 3, 8, 12, 16,128,256));

        //Then
        assertThat(result).hasSize(7).containsExactly(1,0,1,0,1,1,1);
    }

    @Test
    void getMovieTitles() throws Exception {
        //Given
        String substring = "Waterworld";

        //When
        var result = interviewQuestion.getMovieTitles(substring);

        //Then
        assertThat(result).isNotNull().hasSize(20);
    }

    @Test
    void getMovieTitlesWithNoSubstring() throws Exception {
        //Given
        String substring = "";

        //When
        var result = interviewQuestion.getMovieTitles(substring);

        //Then
        assertThat(result).isNotNull().hasSize(2770);
    }

    @Test
    void isPowerOfTwoNaive() {
        //When
        var result = InterviewQuestion.isPowerOfTwoNaive(18);

        //Then
        assertThat(result).isEqualTo(false);
    }
}

