package exp1;

import java.util.Scanner;

public class NextDay {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // 提示用户输入年份
        System.out.print("请输入年份（1900年~3000年）：");
        int year = scanner.nextInt();

        // 提示用户输入月份
        System.out.print("请输入月份（1月~12月）：");
        int month = scanner.nextInt();

        // 提示用户输入日期
        System.out.print("请输入日期（1日~31日）：");
        int day = scanner.nextInt();

        // 检查年份、月份和日期的范围
        if (year < 1900 || year > 3000 || month < 1 || month > 12 || day < 1 || day > 31) {
            System.out.println("日期格式不正确，请重新输入！");
        } else {
            // 调用getNextDay方法获取下一天的日期
            String nextDay = getNextDay(year, month, day);

            // 输出下一天的日期
            System.out.println("下一天的日期是：" + nextDay);
        }
    }

    // 获取下一天的日期的方法
    public static String getNextDay(int year, int month, int day) {
        // 判断是否是闰年
        boolean isLeapYear = (year % 4 == 0 && year % 100 != 0) || year % 400 == 0;

        // 定义每个月的天数
        int[] daysInMonth = {31, isLeapYear ? 29 : 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

        // 判断是否需要进位到下个月
        if (day < daysInMonth[month - 1]) {
            day++;
        } else {
            day = 1;
            if (month < 12) {
                month++;
            } else {
                month = 1;
                year++;
            }
        }

        // 返回下一天的日期
        return String.format("%d年%d月%d日", year, month, day);
    }
}