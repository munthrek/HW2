import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        System.out.print("Введите арифмитическое выражение," + "\n"+
                "которое состоит из:" +"\n"+
                "- двух целых цифр от 1 до 10 (римские или арабские);" +"\n"+
                "- и оператора +, -, *, / между ними."+"\n"+
                "Ввод: ");
        String inputAr = in.nextLine();



        try {
            String outputResult = calc(inputAr);
            System.out.println("Вы ввели: "+outputResult);
        }
        catch(Exception ex){
            System.out.println(ex.getMessage());
        }


        in.close();
    }
    public static String calc(String input) throws Exception{
        if(input.length()>9) throw new Exception("Строка явно длинее задуманного");

        byte op = 1;
        int operand;
        if((operand = input.indexOf('+'))<0) {
            op = 2;
            if ((operand = input.indexOf('-'))<0) {
                op = 3;
                if ((operand = input.indexOf('*'))<0){
                    op=4;
                    if ((operand = input.indexOf('/'))<0) throw new Exception("Не введен ни один оператор");
                }
            }
        }

        System.out.println(Byte.toString(op));
        String[] ab;

        switch(op){
            case 1:
                System.out.println("Это сложение");
                ab = input.split("\\+");
                break;
            case 2:
                System.out.println("Это вычитание");
                ab = input.split("-");
                break;
            case 3:
                System.out.println("Это умножение");
                ab = input.split("\\*");
                break;
            case 4:
                System.out.println("Это деление");
                ab = input.split("/");
                break;
        }

        System.out.println(Integer.toString(operand));

        System.out.println(Integer.toString(input.length()));
        String output = input;

        return output;
    }
}