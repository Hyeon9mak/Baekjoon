package baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class b2108_통계학 {

    private static final String NEW_LINE = "\n";

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        List<Integer> numbers = new ArrayList<>();
        int numberCount = Integer.parseInt(br.readLine());

        for (int i = 0; i < numberCount; i++) {
            numbers.add(Integer.parseInt(br.readLine()));
        }

        StringBuilder sb = new StringBuilder();
        sb.append(getArithmeticMean(numbers))
            .append(NEW_LINE)
            .append(getMedian(numbers))
            .append(NEW_LINE)
            .append(getMode(numbers))
            .append(NEW_LINE)
            .append(getRange(numbers))
            .append(NEW_LINE);
        System.out.print(sb);
    }

    private static long getArithmeticMean(final List<Integer> numbers) {
        return Math.round(numbers.stream()
            .mapToDouble(Integer::doubleValue)
            .sum() / numbers.size());
    }

    private static int getMedian(final List<Integer> numbers) {
        numbers.sort(Integer::compareTo);
        return numbers.get(numbers.size() / 2);
    }

    private static int getMode(List<Integer> numbers) {
        Map<Integer, Long> numberMap = numbers.stream()
            .collect(Collectors.groupingBy(number -> number, TreeMap::new, Collectors.counting()));
        Long maxCount = numberMap.values().stream().max(Long::compareTo).get();
        List<Integer> maxCountNumbers = new ArrayList<>();
        for (Integer key : numberMap.keySet()) {
            if(maxCount.equals(numberMap.get(key))){
                maxCountNumbers.add(key);
            }
        }
        maxCountNumbers.sort(Integer::compareTo);
        if(maxCountNumbers.size() == 1){
            return maxCountNumbers.get(0);
        }
        return maxCountNumbers.get(1);
    }

    private static int getRange(final List<Integer> numbers) {
        int max = numbers.stream().max(Integer::compareTo).get();
        int min = numbers.stream().min(Integer::compareTo).get();
        return max - min;
    }
}

/*
문제
수를 처리하는 것은 통계학에서 상당히 중요한 일이다. 통계학에서 N개의 수를 대표하는 기본 통계값에는 다음과 같은 것들이 있다. 단, N은 홀수라고 가정하자.

산술평균 : N개의 수들의 합을 N으로 나눈 값
중앙값 : N개의 수들을 증가하는 순서로 나열했을 경우 그 중앙에 위치하는 값
최빈값 : N개의 수들 중 가장 많이 나타나는 값
범위 : N개의 수들 중 최댓값과 최솟값의 차이
N개의 수가 주어졌을 때, 네 가지 기본 통계값을 구하는 프로그램을 작성하시오.

입력
첫째 줄에 수의 개수 N(1 ≤ N ≤ 500,000)이 주어진다. 그 다음 N개의 줄에는 정수들이 주어진다. 입력되는 정수의 절댓값은 4,000을 넘지 않는다.

출력
첫째 줄에는 산술평균을 출력한다. 소수점 이하 첫째 자리에서 반올림한 값을 출력한다.

둘째 줄에는 중앙값을 출력한다.

셋째 줄에는 최빈값을 출력한다. 여러 개 있을 때에는 최빈값 중 두 번째로 작은 값을 출력한다.

넷째 줄에는 범위를 출력한다.

예제 입력 1
5
1
3
8
-2
2
예제 출력 1
2
2
1
10

예제 입력 2
1
4000
예제 출력 2
4000
4000
4000
0

예제 입력 3
5
-1
-2
-3
-1
-2
예제 출력 3
-2
-2
-1
2
 */