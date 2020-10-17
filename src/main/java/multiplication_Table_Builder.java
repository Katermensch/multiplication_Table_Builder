package java;

import java.util.Scanner;

public class multiplication_Table_Builder {
    public static void main (String[] arg){
        int maxMultiplicationTable = 32;
        int inputSize = 0;
        int minimumSize = 0;
        System.out.println("Введите целое число от 1 до " + maxMultiplicationTable);
        try {
            Scanner input = new Scanner(System.in);
            inputSize = input.nextInt();
        }catch (Exception e){
            System.out.println("Введите целое число число");
        }
        if ((minimumSize >= inputSize)||(inputSize > maxMultiplicationTable)){
            System.out.println("Введите коректные входные данные");
        }
        else{
            String [] outPut = (buildTable(inputSize));
            for (String s : outPut) {
                System.out.println(s);
            }
        }
    }

    private static String[] buildTable(int inputSize){
        int line;
        int column;
        int lineCounter = 0;
        int stringBuilderSize = getSizeOfNumber(inputSize)+(getSizeOfNumber(inputSize*inputSize)+1)*inputSize;
        String[] completedString = new String[(inputSize+1)*2];
        StringBuilder builder = new StringBuilder(stringBuilderSize);
        for (line = 0; line <= inputSize; line++){
            for (column = 0; column <=inputSize; column++){
                builder.append(buildNumberLine(line, column, inputSize, builder));
            }
            completedString[lineCounter] = builder.toString();
            lineCounter++;
            builder.setLength(0);
            for (column = 0; column <= inputSize; column++){
                builder.append(buildDelimiterLine(column, inputSize, builder));
            }
            completedString[lineCounter] = builder.toString();
            lineCounter++;
            builder.setLength(0);
        }
        return completedString;
    }

    private static String buildNumberLine(int line, int column, int inputSize, StringBuilder builder){
        int maxSizeOfCell = getSizeOfNumber(inputSize);
        int maxSize = getSizeOfNumber(inputSize*inputSize);
        int maxSizeOfColumn = getSizeOfNumber(column);
        int maxSizeOfLine = getSizeOfNumber(line);
        String completedString;
        int symbolCounter;
        if (lineZeroColumnZero(line, column)){
            for (symbolCounter = 0; symbolCounter < maxSizeOfCell; symbolCounter++) {
                builder.append(" ");
            }
        }
        if (lineZeroColumnNotZero(line, column)){
            builder.append("|");
            for (symbolCounter = 0; symbolCounter < (maxSize - maxSizeOfColumn); symbolCounter++){
                builder.append(" ");
            }
            builder.append(column);
        }
        if (lineNotZeroColumnZero(line, column)){
            for (symbolCounter = 0; symbolCounter < (maxSizeOfCell - maxSizeOfLine);symbolCounter++){
                builder.append(" ");
            }
            builder.append(line);
        }
        if (lineNotZeroColumnNotZero(line, column)){
            builder.append("|");
            for (symbolCounter = 0; symbolCounter < (maxSize - getSizeOfNumber(getNumber(line, column))); symbolCounter++){
                builder.append(" ");
            }
            builder.append(getNumber(line, column));
        }
        completedString = builder.toString();
        builder.setLength(0);
        return (completedString);
    }

    private static String buildDelimiterLine(int column, int inputSize, StringBuilder builder){
        int maxSizeOfCell = getSizeOfNumber(inputSize);
        int maxSize = getSizeOfNumber(inputSize*inputSize);
        String completedString;
        int symbolCounter;
        if (column == 0){
            for (symbolCounter = 0; symbolCounter < maxSizeOfCell; symbolCounter++){
                builder.append("-");
            }
        }else {
            builder.append("+");
            for (symbolCounter = 0; symbolCounter < maxSize; symbolCounter++){
                builder.append("-");
            }
        }
        completedString = builder.toString();
        builder.setLength(0);
        return (completedString);
    }

    private static int getNumber(int line, int column){
        return (line * column);
    }

    private static int getSizeOfNumber(int number){
        return (Integer.toString(number).length());
    }
    private static boolean lineZeroColumnZero(int line, int column){
        return ((line == 0) && (column == 0));
    }
    private static boolean lineZeroColumnNotZero(int line, int column){
        return ((line == 0) && (column != 0));
    }
    private static boolean lineNotZeroColumnZero(int line, int column){
        return ((line != 0) && (column == 0));
    }
    private static boolean lineNotZeroColumnNotZero(int line, int column){
        return ((line != 0) && (column != 0));
    }
}