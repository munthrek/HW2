import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        System.out.print("""
                Введите арифмитическое выражение,
                которое состоит из:
                - двух целых цифр от 1 до 10 (римские или арабские);
                - и оператора +, -, *, / между ними.
                Ввод:\s""");
        String inputAr = in.nextLine();



        try {
            String outputResult = calc(inputAr);
            System.out.println("Результат: "+outputResult);
        }
        catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        
        in.close();
    }
    public static String calc(String input) throws Exception{
        if(input.length()>9) throw new Exception("Строка явно длинее задуманного");

        byte op = 1;
        if(input.indexOf('+') <0) {
            op = 2;
            if (input.indexOf('-') <0) {
                op = 3;
                if (input.indexOf('*') <0){
                    op=4;
                    if (input.indexOf('/') <0) throw new Exception("Не введен ни один оператор");
                }
            }
        }

        String[] ab = new String[0];

        switch (op) {
            case 1 -> {
                System.out.println("Это сложение");
                ab = input.split("\\+");
            }
            case 2 -> {
                System.out.println("Это вычитание");
                ab = input.split("-");
            }
            case 3 -> {
                System.out.println("Это умножение");
                ab = input.split("\\*");
            }
            case 4 -> {
                System.out.println("Это деление");
                ab = input.split("/");
            }
        }

        if(ab.length > 2) throw new Exception("Слагаемых не может быть больше 2");

        boolean rome = false;

        int left = 0;
        int right = 0;

        char letter;

        ab[0] = ab[0].toUpperCase();
        ab[1] = ab[1].toUpperCase();

        if (ab[0].charAt(0) == 'I' ||
                ab[0].charAt(0) == 'V' ||
                ab[0].charAt(0) == 'X'){
            if (ab[1].charAt(0) == 'I' ||
                    ab[1].charAt(0) == 'V' ||
                    ab[1].charAt(0) == 'X'){

                int i = 0;

                while (i < ab[0].length()) {
                    letter = ab[0].charAt(i);
                    switch (letter) {
                        case ('I') -> left = left + 1;
                        case ('V') -> {
                            left = left + 5;
                            if (i > 0) if(ab[0].charAt(i - 1) == 'I') left = left - 2;
                        }
                        case ('X') -> {
                            left = left + 10;
                            if (i > 0) if(ab[0].charAt(i - 1) == 'I') left = left - 2;
                        }
                        default -> throw new Exception("Неверный формат цифр");
                    }
                    i++;
                }
                i = 0;
                while (i < ab[1].length()){
                    letter = ab[1].charAt(i);
                    switch (letter) {
                        case ('I') -> right = right + 1;
                        case ('V') -> {
                            right = right + 5;
                            if (i > 0) if(ab[1].charAt(i - 1) == 'I') right = right - 2;
                        }
                        case ('X') -> {
                            right = right + 10;
                            if (i > 0) if(ab[1].charAt(i - 1) == 'I') right = right - 2;
                        }
                        default -> throw new Exception("Неверный формат цифр");
                    }
                    i++;
                }
                rome = true;
            }
            else throw new Exception("Используются разные системы цифр");

            }
        else {
            try{
                left = Integer.parseInt(ab[0]);
            }
            catch (NumberFormatException nfe)
            {
                System.out.println("Неверный формат цифр");
                System.exit(-1);
            }
            try{
                right = Integer.parseInt(ab[1]);
            }
            catch (NumberFormatException nfe)
            {
                System.out.println("Неверный формат цифр");
                System.exit(-1);
            }
        }

        if(left > 10 || right > 10) throw new Exception("Цифры больше 10");
        if(left < 1 || right < 1) throw new Exception("Цифры меньше 1");

        int res = 0;

        String output;

        switch (op) {
            case 1 -> res = left+right;
            case 2 -> res = left-right;
            case 3 -> res = left*right;
            case 4 -> res = left/right;
        }

        output = Integer.toString(res);

        if(rome){
            if(res < 1) throw new Exception("Римские цифры не могут быть < 1");
            if(output.length() == 3) output = "C";
            if(output.length() == 2) {
                switch (res/10) {
                    case 1 -> output = "X";
                    case 2 -> output = "XX";
                    case 3 -> output = "XXX";
                    case 4 -> output = "XL";
                    case 5 -> output = "L";
                    case 6 -> output = "LX";
                    case 7 -> output = "LXX";
                    case 8 -> output = "LXXX";
                    case 9 -> output = "XC";
                }
                switch (res%10) {
                    case 1 -> output = output + "I";
                    case 2 -> output = output +  "II";
                    case 3 -> output = output +  "III";
                    case 4 -> output = output +  "IV";
                    case 5 -> output = output +  "V";
                    case 6 -> output = output +  "VI";
                    case 7 -> output = output +  "VII";
                    case 8 -> output = output +  "VIII";
                    case 9 -> output = output +  "IX";
                }
            }
            if(output.length() == 1) {
                switch (res) {
                    case 1 -> output = "I";
                    case 2 -> output = "II";
                    case 3 -> output = "III";
                    case 4 -> output = "IV";
                    case 5 -> output = "V";
                    case 6 -> output = "VI";
                    case 7 -> output = "VII";
                    case 8 -> output = "VIII";
                    case 9 -> output = "IX";
                }
            }
        }

        return output;
    }

}