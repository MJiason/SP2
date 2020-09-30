import java.io.*;
import java.util.Scanner;
import Parser.Compiler;

public class TestMain {
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Input path for input file: ");
        String path = scanner.nextLine();
        File input = null;
        StringBuilder code = new StringBuilder();
        try {
            input = new File(path);
        } catch (Exception exception){
            System.out.println("Invalid path");
        }
        Scanner reader = new Scanner(input);
        while (reader.hasNextLine()) {
            code.append(reader.nextLine());

        }

        System.out.println(code);
        Compiler compiler = new Compiler(code.toString());
        compiler.compile();
        scanner.nextLine();

//
    }
}
